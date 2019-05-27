package dao;

import entity.RedPacket;
import org.springframework.stereotype.Repository;

/**
 * @ClassName RedPacketDao
 * @Description 操作红包dao层
 * @Author shnstt
 * @Date 2019/4/20 14:24
 * @Version 1.0
 **/
@Repository
public interface RedPacketDao {

    /**
     *  获取红包
     * @param id 红包id
     * @return com.ssnail.entiey.RedPacket
     **/
    RedPacket getRedPacket(Long id);

    /**
     * 扣减红包
     * @param id 操作成功数
     * @return int
     */
    int decreaseRedPacket(Long id);

    /**
     * 扣减红包
     * @param id 操作成功数
     * @return int
     */
    int decreaseRedPacketForVersion(Long id,Integer version);

    /**
     *  使用 for update 获取红包
     * @param id 红包id
     * @return com.ssnail.entiey.RedPacket
     **/
    RedPacket getRedPacketForUpdate(Long id);
}
