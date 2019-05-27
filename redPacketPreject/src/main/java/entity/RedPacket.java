package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName RedPacket
 * @Description 红包类
 * @Author shnstt
 * @Date 2019/4/20 14:14
 * @Version 1.0
 **/
public class RedPacket implements Serializable {
    private static final long serialVersionUID = -2488009276455525275L;
    private Long id;
    private Long userId;
    private Double amount;
    private Timestamp sendDate;
    private Integer total;
    private Double unitAmount;
    private Integer stock;
    private Integer version;
    private String note;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RedPacket{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", amount=").append(amount);
        sb.append(", sendDate=").append(sendDate);
        sb.append(", total=").append(total);
        sb.append(", unitAmount=").append(unitAmount);
        sb.append(", stock=").append(stock);
        sb.append(", version=").append(version);
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

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(Double unitAmount) {
        this.unitAmount = unitAmount;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
