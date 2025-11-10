package com.example.order.service;
import com.example.order.dto.InventoryCheckRequest;
import com.example.order.dto.PlaceOrderRequest;
import com.example.order.model.OrderEntity;
import com.example.order.repository.OrderRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
@Service
public class OrderServiceImpl implements OrderService{
  private final OrderRepository repo; private final RestTemplate rest;
  private final String inventoryBase="http://localhost:8081/inventory";
  public OrderServiceImpl(OrderRepository r,RestTemplate rest){this.repo=r;this.rest=rest;}
  @Override
  public OrderEntity placeOrder(PlaceOrderRequest req){
    OrderEntity o=new OrderEntity(req.getProductId(),req.getQuantity(),"PENDING");
    try{
      InventoryCheckRequest.Item item=new InventoryCheckRequest.Item(req.getProductId(),req.getQuantity());
      InventoryCheckRequest body=new InventoryCheckRequest(Collections.singletonList(item));
      ResponseEntity<Void>resp=rest.postForEntity(inventoryBase+"/update",body,Void.class);
      if(resp.getStatusCode()==HttpStatus.OK)o.setStatus("PLACED");else o.setStatus("FAILED");
    }catch(Exception e){o.setStatus("FAILED");}
    return repo.save(o);
  }
}
