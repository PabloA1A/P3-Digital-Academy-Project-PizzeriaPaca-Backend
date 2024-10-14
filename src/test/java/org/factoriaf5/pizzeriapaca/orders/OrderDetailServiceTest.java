package org.factoriaf5.pizzeriapaca.orders;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OrderDetailServiceTest {

    @InjectMocks
    private OrderDetailService orderDetailService;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    private OrderDetail orderDetail;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        
        orderDetail = new OrderDetail();
        orderDetail.setId(1L);
        orderDetail.setProductId(100L);
        orderDetail.setProductQuantity(2);
        orderDetail.setProductPrice(50.0);
    }

    @Test
    public void testGetAllOrderDetails() {
        
        List<OrderDetail> orderDetails = Arrays.asList(orderDetail);
        when(orderDetailRepository.findAll()).thenReturn(orderDetails);

        List<OrderDetail> result = orderDetailService.getAllOrderDetails();

        
        assertEquals(1, result.size());
        assertEquals(orderDetail.getId(), result.get(0).getId());
        verify(orderDetailRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderDetailById() {
        
        when(orderDetailRepository.findById(1L)).thenReturn(Optional.of(orderDetail));

       
        OrderDetail result = orderDetailService.getOrderDetailById(1L);

    
        assertEquals(orderDetail.getId(), result.getId());
        verify(orderDetailRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetOrderDetailById_NotFound() {
        
        when(orderDetailRepository.findById(1L)).thenReturn(Optional.empty());

        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderDetailService.getOrderDetailById(1L);
        });

        assertEquals("OrderDetail not found", exception.getMessage());
        verify(orderDetailRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateOrderDetail() {
        
        when(orderDetailRepository.save(orderDetail)).thenReturn(orderDetail);

      
        OrderDetail result = orderDetailService.createOrderDetail(orderDetail);

        
        assertEquals(orderDetail.getId(), result.getId());
        verify(orderDetailRepository, times(1)).save(orderDetail);
    }

    @Test
    public void testUpdateOrderDetail() {
        
        when(orderDetailRepository.existsById(1L)).thenReturn(true);
        when(orderDetailRepository.save(orderDetail)).thenReturn(orderDetail);

        
        OrderDetail result = orderDetailService.updateOrderDetail(orderDetail);

        
        assertEquals(orderDetail.getId(), result.getId());
        verify(orderDetailRepository, times(1)).save(orderDetail);
    }

    @Test
    public void testUpdateOrderDetail_NotFound() {
        
        when(orderDetailRepository.existsById(1L)).thenReturn(false);

        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderDetailService.updateOrderDetail(orderDetail);
        });

        assertEquals("OrderDetail not found", exception.getMessage());
        verify(orderDetailRepository, times(1)).existsById(1L);
    }

    @Test
    public void testDeleteOrderDetail() {
        
        when(orderDetailRepository.existsById(1L)).thenReturn(true);
        doNothing().when(orderDetailRepository).deleteById(1L);

        
        orderDetailService.deleteOrderDetailById(1L);

        
        verify(orderDetailRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteOrderDetail_NotFound() {
        
        when(orderDetailRepository.existsById(1L)).thenReturn(false);

        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderDetailService.deleteOrderDetailById(1L);
        });

        assertEquals("OrderDetail not found", exception.getMessage());
        verify(orderDetailRepository, times(1)).existsById(1L);
    }
}
