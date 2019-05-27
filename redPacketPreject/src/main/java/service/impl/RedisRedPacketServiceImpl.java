package service.impl;

import com.sun.javafx.UnmodifiableArrayList;
import entity.UserRedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import service.RedisRedPacketService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RedisRedPacketServiceImpl
 * @Description TODO
 * @Author shnstt
 * @Date 2019/4/21 16:41
 * @Version 1.0
 **/
public class RedisRedPacketServiceImpl implements RedisRedPacketService {
    private static final String PREFIX = "red_packet_list_";
    private static final int TIME_SIZE = 1000;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private DataSource dataSource;

    @Override
    @Async
    public void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount) {
        long start = System.currentTimeMillis();
        System.out.println("开始保存数据：" + start);
        // 获取要保存的列表信息
        BoundListOperations ops = redisTemplate.boundListOps(PREFIX + redPacketId);
        Long size = ops.size();
        long times = size % TIME_SIZE == 0 ? size / TIME_SIZE : size /TIME_SIZE + 1;
        // 保存的条数
        int count = 0;
        List<UserRedPacket> userRedPacketList = new ArrayList<>(TIME_SIZE);
        for (int i=0;i<times;i++){
            List userIdList = null;
            if (i == 0){
                userIdList = ops.range(0, TIME_SIZE);
            } else {
                userIdList = ops.range(i * TIME_SIZE + 1, (i + 1) * TIME_SIZE);
            }
            userRedPacketList.clear();
            for (Object o : userIdList) {
                String args = o.toString();
                String[] split = args.split("-");
                String userIdStr = split[0];
                String timeStr = split[1];
                long userId = Long.parseLong(userIdStr);
                long time = Long.parseLong(timeStr);
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setAmount(unitAmount);
                userRedPacket.setUserId(userId);
                userRedPacket.setNote("抢红包：" + redPacketId);
                userRedPacket.setGrabTime(new Timestamp(time));
                userRedPacketList.add(userRedPacket);
            }
            count += executeBatch(userRedPacketList);

        }
        redisTemplate.delete(PREFIX + redPacketId);
        long end = System.currentTimeMillis();
        System.out.println("保存数据耗时：" + (end - start) + "毫秒，共" + count + "条记录保存");
    }

    private int executeBatch(List<UserRedPacket> userRedPacketList){
        Connection connection = null;
        Statement statement;
        int[] count = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            for (UserRedPacket userRedPacket : userRedPacketList) {
                StringBuffer sql1 = new StringBuffer();
                sql1.append("update t_red_packet set stock = stock - 1 where id = ").append(userRedPacket.getRedPacketId());
                StringBuffer sql2 = new StringBuffer();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sql2.append("insert into t_user_red_packet (red_packet_id,user_id,amount,grab_time,note) values (")
                        .append(userRedPacket.getRedPacketId())
                        .append(userRedPacket.getUserId()).append(userRedPacket.getAmount()).append(df.format(userRedPacket.getGrabTime()))
                        .append(userRedPacket.getNote())
                        .append(")");
                statement.addBatch(sql1.toString());
                statement.addBatch(sql2.toString());
            }
            count = statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count.length/2;
    }
}
