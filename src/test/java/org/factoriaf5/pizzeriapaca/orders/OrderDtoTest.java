package org.factoriaf5.pizzeriapaca.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDtoTest {

    private OrderDto orderDto;

    @BeforeEach
    void setUp() {
        orderDto = new OrderDto();
    }

    @Test
    void testOrderId() {
        orderDto.setOrderId(1L);
        assertEquals(1L, orderDto.getOrderId());
    }

    @Test
    void testUserId() {
        orderDto.setUserId(2L);
        assertEquals(2L, orderDto.getUserId());
    }

    @Test
    void testOrderNumber() {
        orderDto.setOrderNumber("12345");
        assertEquals("12345", orderDto.getOrderNumber());
    }

    @Test
    void testOrderTypeCode() {
        orderDto.setOrderTypeCode("TYPE_A");
        assertEquals("TYPE_A", orderDto.getOrderTypeCode());
    }

    @Test
    void testPaymentId() {
        orderDto.setPaymentId("PAY_001");
        assertEquals("PAY_001", orderDto.getPaymentId());
    }

    @Test
    void testOrderStatus() {
        orderDto.setOrderStatus("PENDING");
        assertEquals("PENDING", orderDto.getOrderStatus());
    }

    @Test
    void testTotalPaid() {
        orderDto.setTotalPaid(150.75f);
        assertEquals(150.75f, orderDto.getTotalPaid());
    }

    @Test
    void testDateOrder() {
        Date date = new Date(System.currentTimeMillis());
        orderDto.setDateOrder(date);
        assertEquals(date, orderDto.getDateOrder());
    }

    @Test
    void testProducts() {
        List<OrderDto.ProductDTO> productList = new ArrayList<>();
        OrderDto.ProductDTO productDTO = new OrderDto.ProductDTO();
        productDTO.setProductId(10L);
        productDTO.setProductQuantity(2);
        productDTO.setProductPrice(25.0);
        productList.add(productDTO);

        orderDto.setProducts(productList);
        assertEquals(productList, orderDto.getProducts());
    }

    @Test
    void testToString() {
        orderDto.setOrderId(1L);
        orderDto.setUserId(2L);
        orderDto.setOrderNumber("12345");
        orderDto.setOrderTypeCode("TYPE_A");
        orderDto.setPaymentId("PAY_001");
        orderDto.setOrderStatus("PENDING");
        orderDto.setTotalPaid(150.75f);
        orderDto.setDateOrder(new Date(System.currentTimeMillis()));

        List<OrderDto.ProductDTO> products = new ArrayList<>();
        OrderDto.ProductDTO product = new OrderDto.ProductDTO();
        product.setProductId(10L);
        product.setProductQuantity(2);
        product.setProductPrice(25.0);
        products.add(product);

        orderDto.setProducts(products);
        
        String expectedString = "OrderDto{" +
                "orderNumber='12345', " +
                "orderTypeCode='TYPE_A', " +
                "userId='2', " +
                "paymentId='PAY_001', " +
                "orderStatus='PENDING', " +
                "totalPaid='150.75', " +
                "dateOrder='" + orderDto.getDateOrder() + '\'' +
                ", products=[{productId='10', productQuantity='2', productPrice='25.0'}]" +
                '}';

        assertEquals(expectedString, orderDto.toString());
    }
}
