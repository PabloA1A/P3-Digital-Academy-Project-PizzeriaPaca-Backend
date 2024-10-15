package org.factoriaf5.pizzeriapaca.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    private Order order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
       
        order = new Order();
        order.setOrderId(1L);
        order.setOrderNumber("123456");
        order.setOrderTypeCode("TYPE1");
        order.setUserId(1L);
        order.setPaymentId("PAY123");
        order.setOrderStatus("NEW");
        order.settotalPaid(100.0f);
        order.setDateOrder(new java.sql.Date(System.currentTimeMillis())); 
    }

    @Test
    public void testGetAllOrders() {
       
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        
        when(orderRepository.findAll()).thenReturn(orders);

       
        List<Order> result = orderService.getAllOrders();

        
        assertEquals(1, result.size());
        assertEquals(order.getOrderNumber(), result.get(0).getOrderNumber());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderById_NotFound() {
       
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.getOrderById(1L);
        });

        assertEquals("Order not found", exception.getMessage());
    }

    @Test
    public void testCreateOrder() {
        
        when(orderRepository.save(order)).thenReturn(order);

       
        Order result = orderService.createOrder(order);

       
        assertNotNull(result);
        assertEquals(order.getOrderNumber(), result.getOrderNumber());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testUpdateOrder() {
        
        when(orderRepository.existsById(order.getOrderId())).thenReturn(true);
        when(orderRepository.save(order)).thenReturn(order);

    
        Order result = orderService.updateOrder(order);

        
        assertNotNull(result);
        assertEquals(order.getOrderNumber(), result.getOrderNumber());
        verify(orderRepository, times(1)).existsById(order.getOrderId());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testUpdateOrder_NotFound() {
     
        when(orderRepository.existsById(order.getOrderId())).thenReturn(false);

        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.updateOrder(order);
        });

        assertEquals("Order not found", exception.getMessage());
    }

    @Test
    public void testDeleteOrderById() {
        
        when(orderRepository.existsById(1L)).thenReturn(true);

        
        orderService.deleteOrderById(1L);

       
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteOrderById_NotFound() {
      
        when(orderRepository.existsById(1L)).thenReturn(false);

    
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.deleteOrderById(1L);
        });

        assertEquals("Order not found", exception.getMessage());
    }
}
