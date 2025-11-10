package com.example.inventory.dto;
import java.time.LocalDate;
public class InventoryBatchDto {
  private Long id;private Long productId;private int quantity;private LocalDate expiryDate;
  public InventoryBatchDto(){}
  public InventoryBatchDto(Long id,Long p,int q,LocalDate e){this.id=id;this.productId=p;this.quantity=q;this.expiryDate=e;}
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public Long getProductId(){return productId;} public void setProductId(Long p){this.productId=p;}
  public int getQuantity(){return quantity;} public void setQuantity(int q){this.quantity=q;}
  public LocalDate getExpiryDate(){return expiryDate;} public void setExpiryDate(LocalDate e){this.expiryDate=e;}
}
