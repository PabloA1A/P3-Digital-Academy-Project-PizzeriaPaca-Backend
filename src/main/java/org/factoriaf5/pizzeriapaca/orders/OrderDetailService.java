package org.factoriaf5.pizzeriapaca.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    
     @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderDetail not found"));
    }

    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        if (!orderDetailRepository.existsById(orderDetail.getId())) {
            throw new RuntimeException("OrderDetail not found");
        }
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetailById(Long id) {
        if (!orderDetailRepository.existsById(id)) {
            throw new RuntimeException("OrderDetail not found");
        }
        orderDetailRepository.deleteById(id);
    }
}
