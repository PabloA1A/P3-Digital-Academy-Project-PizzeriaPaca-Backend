package org.factoriaf5.pizzeriapaca.orders;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderDetailTest {

    private OrderDetail orderDetail;

    @BeforeEach
    void setUp() {
        orderDetail = new OrderDetail();
    }

    @Test
    void testId() {
        orderDetail.setId(1L);
        assertEquals(1L, orderDetail.getId());
    }

    @Test
    void testOrderId() {
        orderDetail.setOrderId(2L);
        assertEquals(2L, orderDetail.getOrderId());
    }

    @Test
    void testProductId() {
        orderDetail.setProductId(3L);
        assertEquals(3L, orderDetail.getProductId());
    }

    @Test
    void testProductQuantity() {
        orderDetail.setProductQuantity(5);
        assertEquals(5, orderDetail.getProductQuantity());
    }

    @Test
    void testProductPrice() {
        orderDetail.setProductPrice(15.99);
        assertEquals(15.99, orderDetail.getProductPrice());
    }

    @Test
    void testOrder() {
        Order order = new Order(); 
        orderDetail.setOrder(order);
        assertEquals(order, orderDetail.getOrder());
    }
}
