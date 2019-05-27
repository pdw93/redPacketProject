package service.impl;

import dao.RedPacketDao;
import entity.RedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.RedPacketService;

/**
 * @ClassName RedPacketServiceImpl
 * @Description TODO
 * @Author shnstt
 * @Date 2019/4/20 15:02
 * @Version 1.0
 **/
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RedPacketServiceImpl implements RedPacketService {
    @Autowired
    private RedPacketDao redPacketDao;

    @Override
    public RedPacket getRedPacket(Long id) {
        return redPacketDao.getRedPacket(id);
    }

    @Override
    public int decreaseRedPacket(Long id) {
        return redPacketDao.decreaseRedPacket(id);
    }
}
