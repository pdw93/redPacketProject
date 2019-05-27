package service.impl;


import dao.RedPacketDao;
import dao.UserRedPacketDao;
import entity.RedPacket;
import entity.UserRedPacket;
import org.apache.cxf.resource.ClasspathResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import service.RedisRedPacketService;
import service.UserRedPacketService;

import java.io.*;

/**
 * @ClassName UserRedPacketServiceImpl
 * @Description TODO
 * @Author shnstt
 * @Date 2019/4/20 15:04
 * @Version 1.0
 **/
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserRedPacketServiceImpl implements UserRedPacketService {
    /**
     * 失败
     */
    private static final int FAILED = 0;
    /**
     * 成功，不是最后一个
     */
    private static final int SUCCESS_CONTINUE = 1;
    /**
     * 成功，最后一个
     */
    private static final int SUCCESS_END = 2;
    /**
     * 缓存Lua脚本
     */
    private static String shal = null;
    /**
     * 抢红包Lua脚本
     */
    private static String script;
    private static String scriptPath = "redis/grabRedPacket.lua";
    @Autowired
    private UserRedPacketDao userRedPacketDao;
    @Autowired
    private RedPacketDao redPacketDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisRedPacketService redisRedPacketService;

    @Override
    public int grabRedPacket(Long redPacketId, Long userId) {
        RedPacket redPacket = redPacketDao.getRedPacketForUpdate(redPacketId);
        if (redPacket.getStock() > 0) {
            redPacketDao.decreaseRedPacket(redPacketId);
            // 生成抢红包信息
            return grabRedPacket(redPacketId, userId, redPacket);
        }
        return FAILED;
    }

    private int grabRedPacket(Long redPacketId, Long userId, RedPacket redPacket) {
        UserRedPacket userRedPacket = new UserRedPacket();
        userRedPacket.setRedPacketId(redPacketId);
        userRedPacket.setUserId(userId);
        userRedPacket.setAmount(redPacket.getUnitAmount());
        userRedPacket.setNote("抢红包：" + redPacketId);
        int result = userRedPacketDao.grabRedPacket(userRedPacket);
        return result;
    }

    @Override
    public int grabRedPacketForVersion(Long redPacketId, Long userId) {

        // 使用乐观锁重入解决大量更新失败的问题
        // 一定时间内重试
//            long start = System.currentTimeMillis();
//            while (true) {
//                long end = System.currentTimeMillis();
//                if (end - start > 100){
//                    return FAILED;
//                }
//                RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
//                if (redPacket.getStock() > 0) {
//                int update = redPacketDao.decreaseRedPacketForVersion(redPacketId,redPacket.getVersion());
//                if (update == 0){
//                    continue;
//                }
//                // 生成抢红包信息
//                return grabRedPacket(redPacketId, userId, redPacket);
//                } else {
//                    return FAILED;
//                }
//            }
        // 确定重试次数
        for (int i = 0; i < 3; i++) {
            RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
            if (redPacket.getStock() > 0) {
                int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
                if (update == 0) {
                    continue;
                }
                // 生成抢红包信息
                return grabRedPacket(redPacketId, userId, redPacket);
            } else {
                return FAILED;
            }
        }
        return FAILED;
    }

    @Override
    public int grabRedPacketByRedis(Long redPacketId, Long userId) {
        // 抢红包用户信息
        String args = userId + "-" + System.currentTimeMillis();
        int result = 0;
        try (Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection()) {
            // 脚本没加载过
            if (shal == null) {
                getScript();
                shal = jedis.scriptLoad(script);
            }
            Object res = jedis.evalsha(shal, 1, redPacketId + "" + args);
            Integer redisRes = (Integer) res;
            if (SUCCESS_END == redisRes) {
                String unit_amount = jedis.hget("red_packet_" + redPacketId, "unit_amount");
                double unitAmount = Double.parseDouble(unit_amount);
                redisRedPacketService.saveUserRedPacketByRedis(redPacketId, unitAmount);
            }
        }
        return result;
    }

    private void getScript(){
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            InputStream file = new ClassPathResource("redis/grabRedPacket.lua").getInputStream();
            byte[] bb = new byte[1024];
            int read = file.read(bb);
            while (read != -1){
                outputStream.write(bb,0,read);
                read = file.read(bb);
            }
            outputStream.toString(script);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
