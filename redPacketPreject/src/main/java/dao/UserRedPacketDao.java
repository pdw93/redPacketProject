package dao;

import entity.UserRedPacket;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserRedPacketDao
 * @Description TODO
 * @Author shnstt
 * @Date 2019/4/20 14:48
 * @Version 1.0
 **/
@Repository
public interface UserRedPacketDao {

    /**
     * 插入抢红包书
     *
     * @param userRedPacket 抢红包信息
     * @return 影响记录数
     */
    int grabRedPacket(UserRedPacket userRedPacket);
}
