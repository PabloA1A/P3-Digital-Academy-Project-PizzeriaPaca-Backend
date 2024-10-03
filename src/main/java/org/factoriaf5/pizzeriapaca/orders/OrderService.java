package org.factoriaf5.pizzeriapaca.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        if (!orderRepository.existsById(order.getOrderId())) {
            throw new RuntimeException("Order not found");
        }
        return orderRepository.save(order);
    }

    public void deleteOrderById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }

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
