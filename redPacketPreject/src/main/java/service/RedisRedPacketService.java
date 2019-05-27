package service;

/**
 * @ClassName RedisRedPacketService
 * @Description redis保存抢红包信息
 * @Author shnstt
 * @Date 2019/4/21 15:16
 * @Version 1.0
 **/
public interface RedisRedPacketService {

    /**
     * 保存抢红包信息
     * @param redPacketId
     * @param unitAmount
     */
    void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount);
}
