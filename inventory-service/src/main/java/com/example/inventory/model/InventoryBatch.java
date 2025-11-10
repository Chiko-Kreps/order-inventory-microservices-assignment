package com.example.inventory.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
@Entity
@Table(name="inventory_batches")
public class InventoryBatch {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private Long productId;
  private int quantity;
  private LocalDate expiryDate;
  public InventoryBatch(){}
  public InventoryBatch(Long p,int q,LocalDate e){this.productId=p;this.quantity=q;this.expiryDate=e;}
  public Long getId(){return id;}
  public Long getProductId(){return productId;}
  public void setProductId(Long p){this.productId=p;}
  public int getQuantity(){return quantity;}
  public void setQuantity(int q){this.quantity=q;}
  public LocalDate getExpiryDate(){return expiryDate;}
  public void setExpiryDate(LocalDate e){this.expiryDate=e;}
  @Override public boolean equals(Object o){return o instanceof InventoryBatch && Objects.equals(id,((InventoryBatch)o).id);}
  @Override public int hashCode(){return Objects.hash(id);}
}
