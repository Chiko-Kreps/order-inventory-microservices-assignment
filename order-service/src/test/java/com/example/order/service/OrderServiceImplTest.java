package com.example.order.service;
import com.example.order.dto.PlaceOrderRequest;
import com.example.order.model.OrderEntity;
import com.example.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class OrderServiceImplTest {
  private OrderRepository repo; private RestTemplate rest; private OrderServiceImpl service;
  @BeforeEach void setUp(){repo=mock(OrderRepository.class);rest=mock(RestTemplate.class);
    service=new OrderServiceImpl(repo,rest);
    when(repo.save(any(OrderEntity.class))).thenAnswer(i->i.getArgument(0));}
  @Test void placeOrder_success(){
    PlaceOrderRequest r=new PlaceOrderRequest(1L,2);
    when(rest.postForEntity(anyString(),any(),eq(Void.class)))
      .thenReturn(new ResponseEntity<>(HttpStatus.OK));
    var o=service.placeOrder(r);
    assertEquals("PLACED",o.getStatus());
  }
  @Test void placeOrder_failure(){
    PlaceOrderRequest r=new PlaceOrderRequest(1L,99);
    when(rest.postForEntity(anyString(),any(),eq(Void.class)))
      .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    var o=service.placeOrder(r);
    assertEquals("FAILED",o.getStatus());
  }
}
