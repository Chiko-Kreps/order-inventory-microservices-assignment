package com.example.order.service;
import com.example.order.dto.PlaceOrderRequest;
import com.example.order.model.OrderEntity;
public interface OrderService {
  OrderEntity placeOrder(PlaceOrderRequest req);
}
