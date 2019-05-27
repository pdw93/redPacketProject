package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName UserRedPacket
 * @Description TODO
 * @Author shnstt
 * @Date 2019/4/20 14:19
 * @Version 1.0
 **/
public class UserRedPacket implements Serializable {
    private static final long serialVersionUID = -3655382528355937931L;
    private Long id;
    private Long redPacketId;
    private Long userId;
    private Double amount;
    private Timestamp grabTime;
    private String note;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserRedPacket{");
        sb.append("id=").append(id);
        sb.append(", redPacketId=").append(redPacketId);
        sb.append(", userId=").append(userId);
        sb.append(", amount=").append(amount);
        sb.append(", grabTime=").append(grabTime);
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(Long redPacketId) {
        this.redPacketId = redPacketId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getGrabTime() {
        return grabTime;
    }

    public void setGrabTime(Timestamp grabTime) {
        this.grabTime = grabTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
