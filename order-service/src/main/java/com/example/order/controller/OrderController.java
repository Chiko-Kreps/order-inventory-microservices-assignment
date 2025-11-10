package com.example.order.controller;
import com.example.order.dto.PlaceOrderRequest;
import com.example.order.model.OrderEntity;
import com.example.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/order")
public class OrderController {
  private final OrderService s;
  public OrderController(OrderService s){this.s=s;}
  @PostMapping public ResponseEntity<OrderEntity> placeOrder(@RequestBody PlaceOrderRequest req){
    OrderEntity o=s.placeOrder(req);
    if("PLACED".equals(o.getStatus())) return ResponseEntity.ok(o);
    return ResponseEntity.badRequest().body(o);
  }
}
