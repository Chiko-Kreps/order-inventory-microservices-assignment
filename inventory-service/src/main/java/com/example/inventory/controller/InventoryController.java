package com.example.inventory.controller;
import com.example.inventory.dto.InventoryBatchDto;
import com.example.inventory.dto.UpdateInventoryRequest;
import com.example.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/inventory")
public class InventoryController{
  private final InventoryService s;
  public InventoryController(InventoryService s){this.s=s;}
  @GetMapping("/{pid}") public ResponseEntity<List<InventoryBatchDto>>get(@PathVariable("pid")Long id){return ResponseEntity.ok(s.getBatchesByProduct(id));}
  @PostMapping("/update") public ResponseEntity<?>update(@Valid @RequestBody UpdateInventoryRequest req){
    return s.updateInventory(req)?ResponseEntity.ok().build():ResponseEntity.badRequest().body("Insufficient stock");}
}
