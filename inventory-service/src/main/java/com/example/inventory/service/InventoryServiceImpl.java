package com.example.inventory.service;
import com.example.inventory.dto.InventoryBatchDto;
import com.example.inventory.dto.UpdateInventoryRequest;
import com.example.inventory.factory.InventoryHandlerFactory;
import com.example.inventory.model.InventoryBatch;
import com.example.inventory.repository.InventoryBatchRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class InventoryServiceImpl implements InventoryService{
  private final InventoryBatchRepository repo;private final InventoryHandlerFactory factory;
  public InventoryServiceImpl(InventoryBatchRepository r,InventoryHandlerFactory f){this.repo=r;this.factory=f;}
  public List<InventoryBatchDto> getBatchesByProduct(Long p){return repo.findByProductIdOrderByExpiryDateAsc(p).stream()
    .map(b->new InventoryBatchDto(b.getId(),b.getProductId(),b.getQuantity(),b.getExpiryDate())).collect(Collectors.toList());}
  public boolean updateInventory(UpdateInventoryRequest req){return factory.getHandler("defaultInventoryHandler").updateInventory(req);}
}
