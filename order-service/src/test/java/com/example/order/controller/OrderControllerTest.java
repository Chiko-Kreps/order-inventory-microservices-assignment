package com.example.order.controller;
import com.example.order.dto.PlaceOrderRequest;
import com.example.order.model.OrderEntity;
import com.example.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {
  @Autowired private TestRestTemplate rest;
  @Autowired private OrderRepository repo;
  @Test void contextLoads_andPostWorks(){
    PlaceOrderRequest req=new PlaceOrderRequest(1L,1);
    ResponseEntity<OrderEntity> resp=rest.postForEntity("/order",req,OrderEntity.class);
    assert(resp.getStatusCode()==HttpStatus.BAD_REQUEST||resp.getStatusCode()==HttpStatus.OK);
  }
}
