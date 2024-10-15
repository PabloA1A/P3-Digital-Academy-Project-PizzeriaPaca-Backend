package org.factoriaf5.pizzeriapaca.orders;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void testOrderId() {
        order.setOrderId(1L);
        assertEquals(1L, order.getOrderId());
    }

    @Test
    void testOrderNumber() {
        order.setOrderNumber("ORD123");
        assertEquals("ORD123", order.getOrderNumber());
    }

    @Test
    void testOrderTypeCode() {
        order.setOrderTypeCode("DINE_IN");
        assertEquals("DINE_IN", order.getOrderTypeCode());
    }

    @Test
    void testUserId() {
        order.setUserId(2L);
        assertEquals(2L, order.getUserId());
    }

    @Test
    void testPaymentId() {
        order.setPaymentId("PAY123");
        assertEquals("PAY123", order.getPaymentId());
    }

    @Test
    void testOrderStatus() {
        order.setOrderStatus("PENDING");
        assertEquals("PENDING", order.getOrderStatus());
    }

    @Test
    void testDateOrder() {
        Date date = Date.valueOf("2024-10-14");
        order.setDateOrder(date);
        assertEquals(date, order.getDateOrder());
    }

    @Test
    void testTotalPaid() {
        order.settotalPaid(25.50f);
        assertEquals(25.50f, order.gettotalPaid());
    }

    @Test
    void testOrderDetails() {
        List<OrderDetail> details = new ArrayList<>();
        order.setOrderDetails(details);
        assertEquals(details, order.getOrderDetails());
    }

}
