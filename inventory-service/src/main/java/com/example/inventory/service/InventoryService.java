package com.example.inventory.service;
import com.example.inventory.dto.InventoryBatchDto;
import com.example.inventory.dto.UpdateInventoryRequest;
import java.util.List;
public interface InventoryService {
  List<InventoryBatchDto> getBatchesByProduct(Long productId);
  boolean updateInventory(UpdateInventoryRequest req);
}
