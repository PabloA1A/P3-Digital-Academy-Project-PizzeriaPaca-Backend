package org.factoriaf5.pizzeriapaca.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
     @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        order.setOrderId(id);
        Order updatedOrder = orderService.updateOrder(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/details")
    public List<OrderDetail> getAllOrderDetails() {
        return orderService.getAllOrderDetails();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Long id) {
        OrderDetail orderDetail = orderService.getOrderDetailById(id);
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }

    @PostMapping("/details")
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        OrderDetail createdOrderDetail = orderService.createOrderDetail(orderDetail);
        return new ResponseEntity<>(createdOrderDetail, HttpStatus.CREATED);
    }

    @PutMapping("/details/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        orderDetail.setId(id);
        OrderDetail updatedOrderDetail = orderService.updateOrderDetail(orderDetail);
        return new ResponseEntity<>(updatedOrderDetail, HttpStatus.OK);
    }

    @DeleteMapping("/details/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
        orderService.deleteOrderDetailById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
