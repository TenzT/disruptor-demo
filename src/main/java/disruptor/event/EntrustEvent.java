package disruptor.event;

import constant.EntrustBuySideEnum;
import constant.EntrustStatusEnum;

public class EntrustEvent {

    private EntrustStatusEnum status;

    private EntrustBuySideEnum buySide;

    private double price;

    private long quantity;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    private long orderId;

    public EntrustEvent() {
        this(EntrustStatusEnum.PENDING, EntrustBuySideEnum.BUY, 0.0, 0, -1);
    }

    public EntrustEvent(EntrustStatusEnum status, EntrustBuySideEnum buySide,
                        double price, long quantity, long orderId) {
        this.status = status;
        this.buySide = buySide;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "EntrustEvent{" +
                "status=" + status +
                ", buySide=" + buySide +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public EntrustStatusEnum getStatus() {
        return status;
    }

    public void setStatus(EntrustStatusEnum status) {
        this.status = status;
    }

    public EntrustBuySideEnum getBuySide() {
        return buySide;
    }

    public void setBuySide(EntrustBuySideEnum buySide) {
        this.buySide = buySide;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
