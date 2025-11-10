package com.example.order.model;
import jakarta.persistence.*;
@Entity
@Table(name="orders")
public class OrderEntity {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private Long productId;
  private int quantity;
  private String status;
  public OrderEntity(){}
  public OrderEntity(Long p,int q,String s){this.productId=p;this.quantity=q;this.status=s;}
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public Long getProductId(){return productId;} public void setProductId(Long p){this.productId=p;}
  public int getQuantity(){return quantity;} public void setQuantity(int q){this.quantity=q;}
  public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
}
