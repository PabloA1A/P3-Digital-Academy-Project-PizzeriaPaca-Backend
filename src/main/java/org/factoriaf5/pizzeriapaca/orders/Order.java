package org.factoriaf5.pizzeriapaca.orders;

import java.sql.Date;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "order_type_code")
    private String orderTypeCode;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_date")
    private Date orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(Long orderId, String orderNumber, String orderTypeCode, Long userId, Long paymentId, String orderStatus, Date orderDate) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderTypeCode = orderTypeCode;
        this.userId = userId;
        this.paymentId = paymentId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    public void setOrderTypeCode(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
