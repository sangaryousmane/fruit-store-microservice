package com.example.fruitsorderservice.controller;


import com.example.fruitsorderservice.dto.OrderRequest;
import com.example.fruitsorderservice.model.Order;
import com.example.fruitsorderservice.service.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable String orderId){
        return ResponseEntity.ok()
                .body(orderService.getOrderById(orderId));
    }

    // TODO: Cancel an order
    @DeleteMapping("/cancelOrder/{orderId}")
    public ResponseEntity<Map<String, Boolean>> cancelOrder(
            @PathVariable String orderId){
        boolean cancelledOrder = orderService.cancelOrder(orderId);
        Map<String, Boolean> isOrderCancelled=new HashMap<>();
        isOrderCancelled.put("Cancelled", cancelledOrder);
        return ResponseEntity.ok().body(isOrderCancelled);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/updateOrder/{orderId}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable String orderId,
            @RequestBody Order order){
        return ResponseEntity.ok(orderService.updateOrder(order, orderId));
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(
            @RequestBody OrderRequest orderRequest){
        Order order = orderService.placeOrder(
                orderRequest.getCustomerId(),
                orderRequest.getFruitIds());

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/customerHistory/{customerId}")
    public ResponseEntity<List<Order>> getOrderHistoryForCustomer(
            @PathVariable String customerId){
        List<Order> orders = orderService.getOrderHistoryForCustomer(customerId);
        return ResponseEntity.ok(orders);
    }

}
