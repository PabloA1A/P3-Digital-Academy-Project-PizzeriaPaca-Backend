package org.factoriaf5.pizzeriapaca.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDtoService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Order save(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setOrderTypeCode(orderDto.getOrderTypeCode());
        order.setUserId(orderDto.getUserId());
        order.setPaymentId(orderDto.getPaymentId());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setDateOrder(orderDto.getDateOrder());

        orderRepository.save(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProductId(orderDto.getProductId());
        orderDetail.setProductQuantity(orderDto.getProductQuantity());

        orderDetailRepository.save(orderDetail);

        return order;
    }
}
