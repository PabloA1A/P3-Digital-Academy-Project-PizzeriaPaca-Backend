package org.factoriaf5.pizzeriapaca.orders;

import org.factoriaf5.pizzeriapaca.orders.OrderDto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderDtoService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Order save(OrderDto orderDto) {
        System.out.println("Datos del orderDtoSERVICE : " + orderDto);

        Order order = new Order();
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setOrderTypeCode(orderDto.getOrderTypeCode());
        order.setUserId(orderDto.getUserId());
        order.setPaymentId(orderDto.getPaymentId());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.settotalPaid(orderDto.getTotalPaid());
        order.setDateOrder(orderDto.getDateOrder()); 

        orderRepository.save(order);

        List<ProductDTO> productDtos = orderDto.getProducts();
        if (productDtos != null && !productDtos.isEmpty()) {

     
        for (ProductDTO productDto : productDtos) {
          
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setOrder(order);
            orderDetail.setProductId(productDto.getProductId());
            orderDetail.setProductQuantity(productDto.getProductQuantity());
            orderDetail.setProductPrice(productDto.getProductPrice());

            orderDetailRepository.save(orderDetail);
        }
       }
        return order;
    }
}