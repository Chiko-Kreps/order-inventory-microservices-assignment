package com.example.order.dto;
public class PlaceOrderRequest {
  private Long productId; private int quantity;
  public PlaceOrderRequest(){}
  public PlaceOrderRequest(Long p,int q){this.productId=p;this.quantity=q;}
  public Long getProductId(){return productId;} public void setProductId(Long p){this.productId=p;}
  public int getQuantity(){return quantity;} public void setQuantity(int q){this.quantity=q;}
}
