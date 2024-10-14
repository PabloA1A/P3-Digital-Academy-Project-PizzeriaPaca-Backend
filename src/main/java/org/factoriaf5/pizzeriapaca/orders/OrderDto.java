package org.factoriaf5.pizzeriapaca.orders;

import java.sql.Date;
import java.util.List;

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
    private String paymentId;

    @NotBlank
    private String orderStatus;
   
    @NotBlank
    private Float totalPaid;

    @NotBlank
    private Date dateOrder;
    
    // Agregar lista de productos
    private List<ProductDTO> products;

    public OrderDto(Long orderId, String orderNumber, String orderTypeCode, Long userId, String paymentId, String orderStatus, Float totalPaid, Date dateOrder) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderTypeCode = orderTypeCode;
        this.userId = userId;
        this.paymentId = paymentId;
        this.orderStatus = orderStatus;
        this.totalPaid = totalPaid;
        this.dateOrder = dateOrder;
    }

    // Constructor por defecto
    public OrderDto(){
    }
    public static class ProductDTO {
        private Long productId;
        private Integer productQuantity;
        private Double productPrice;   

        // Constructor por defecto
        public ProductDTO() {}

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
        public Double getProductPrice() {
            return productPrice;
        }
        public void setProductPrice(Double productPrice) {
            this.productPrice = productPrice;
        } 
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

    public float getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Float totalPaid) {
        this.totalPaid = totalPaid;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder productDetails = new StringBuilder();
        if (products != null && !products.isEmpty()) {
            productDetails.append("[");
            for (ProductDTO product : products) {
                productDetails.append("{")
                            .append("productId='").append(product.getProductId()).append("', ")
                            .append("productQuantity='").append(product.getProductQuantity()).append("', ")
                            .append("productPrice='").append(product.getProductPrice()).append("'}");
                productDetails.append(", ");
            }
            // Eliminar la última coma y espacio
            if (productDetails.length() > 1) {
                productDetails.setLength(productDetails.length() - 2);
            }
            productDetails.append("]");
        } else {
            productDetails.append("No products");
        }

        return "OrderDto{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderTypeCode='" + orderTypeCode + '\'' +
                ", userId='" + userId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", totalPaid='" + totalPaid + '\'' +
                ", dateOrder='" + dateOrder + '\'' +
                ", products=" + productDetails.toString() +
                '}';
    }
}
