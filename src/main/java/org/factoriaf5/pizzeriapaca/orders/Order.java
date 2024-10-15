package org.factoriaf5.pizzeriapaca.orders;

import java.sql.Date;
import jakarta.persistence.*;
import java.util.List;

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
  
    private String paymentId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "date_order")
    private Date dateOrder;

    @Column(name = "order_total_paid")
    private float totalPaid;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(Long orderId, String orderNumber, String orderTypeCode, Long userId, String paymentId, String orderStatus, Date dateOrder, Float totalPaid) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderTypeCode = orderTypeCode;
        this.userId = userId;
        this.paymentId = paymentId;
        this.orderStatus = orderStatus;
        this.dateOrder = dateOrder;
        this.totalPaid= totalPaid;
    }

    public float gettotalPaid() {
        return totalPaid;
    }

    public void settotalPaid(float totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}