package service;

import entity.RedPacket;

/**
 * @ClassName RedPacketService
 * @Description TODO
 * @Author shnstt
 * @Date 2019/4/20 14:57
 * @Version 1.0
 **/
public interface RedPacketService {
    /**
     * 获取红包
     * @param id 红包id
     * @return 红包信息
     */
    RedPacket getRedPacket(Long id);

    /**
     * 扣减红包
     * @param id 操作成功数
     * @return int
     */
    int decreaseRedPacket(Long id);
}
