package com.example.inventory.model;
import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name="products")
public class Product {
  @Id private Long id;
  private String name;
  public Product(){}
  public Product(Long id,String name){this.id=id;this.name=name;}
  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getName(){return name;}
  public void setName(String name){this.name=name;}
  @Override public boolean equals(Object o){return o instanceof Product && Objects.equals(id,((Product)o).id);}
  @Override public int hashCode(){return Objects.hash(id);}
}
