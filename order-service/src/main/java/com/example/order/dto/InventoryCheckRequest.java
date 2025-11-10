package com.example.order.dto;
import java.util.List;
public class InventoryCheckRequest {
  public static class Item{private Long productId; private int quantity;
    public Item(){} public Item(Long p,int q){this.productId=p;this.quantity=q;}
    public Long getProductId(){return productId;} public void setProductId(Long p){this.productId=p;}
    public int getQuantity(){return quantity;} public void setQuantity(int q){this.quantity=q;}
  }
  private List<Item> items;
  public InventoryCheckRequest(){} public InventoryCheckRequest(List<Item> items){this.items=items;}
  public List<Item> getItems(){return items;} public void setItems(List<Item> items){this.items=items;}
}
