package org.factoriaf5.pizzeriapaca.orders;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;

public class OrderDto {

    @NotBlank
    private Long orderId;

    @NotBlank
    private Long userId;

    @NotBlank
    private String orderNumber;

    @NotBlank
    private String orderTypeCode;

    @NotBlank
    private Long paymentId;

    @NotBlank
    private String orderStatus;

    @NotBlank
    private Long orderDetailId;

    @NotBlank
    private Long productId;

    @NotBlank
    private Integer productQuantity;
    
    @NotBlank
    private Date dateOrder;

    public OrderDto(Long orderId, String orderNumber, String orderTypeCode, Long userId, Long paymentId, String orderStatus, Long orderDetailId, Long productId, Integer productQuantity, Date dateOrder) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderTypeCode = orderTypeCode;
        this.userId = userId;
        this.paymentId = paymentId;
        this.orderStatus = orderStatus;
        this.orderDetailId = orderDetailId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.dateOrder = dateOrder;
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

    public OrderDto(){

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

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
