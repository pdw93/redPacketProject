package service;

/**
 * @ClassName UserRedPacketService
 * @Description 用户抢红包服务层
 * @Author shnstt
 * @Date 2019/4/20 15:00
 * @Version 1.0
 **/
public interface UserRedPacketService {
    /**
     * 插入抢红包书
     *
     * @param redPacketId 红包id
     * @param userId 用户id
     * @return 影响记录数
     */
    int grabRedPacket(Long redPacketId, Long userId);

    /**
     * 插入抢红包书
     *
     * @param redPacketId 红包id
     * @param userId 用户id
     * @return 影响记录数
     */
    int grabRedPacketForVersion(Long redPacketId, Long userId);

    /**
     * 通过抢红包书
     *
     * @param redPacketId 红包id
     * @param userId 用户id
     * @return 0=失败；1=成功不是最后一个；2= 成功时最后一个
     */
    int grabRedPacketByRedis(Long redPacketId, Long userId);
}
