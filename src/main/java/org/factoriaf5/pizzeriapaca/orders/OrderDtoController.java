package org.factoriaf5.pizzeriapaca.orders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "${api-endpoint}/order")
public class OrderDtoController {
    
     private final OrderDtoService service;

    public OrderDtoController(OrderDtoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody OrderDto orderDto) {
        Order order = service.save(orderDto);

        Map<String, String> json = new HashMap<>();
        json.put("message", "Order created successfully");
        json.put("orderNumber", order.getOrderNumber());
        json.put("orderStatus", order.getOrderStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }
}
