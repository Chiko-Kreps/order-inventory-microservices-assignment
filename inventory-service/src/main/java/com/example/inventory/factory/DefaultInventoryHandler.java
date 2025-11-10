package com.example.inventory.factory;
import com.example.inventory.dto.UpdateInventoryRequest;
import com.example.inventory.model.InventoryBatch;
import com.example.inventory.repository.InventoryBatchRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Component("defaultInventoryHandler")
public class DefaultInventoryHandler implements InventoryHandler{
  private final InventoryBatchRepository repo;
  public DefaultInventoryHandler(InventoryBatchRepository r){this.repo=r;}
  @Override @Transactional
  public boolean updateInventory(UpdateInventoryRequest req){
    for(UpdateInventoryRequest.Item it:req.getItems()){
      List<InventoryBatch>batches=repo.findByProductIdOrderByExpiryDateAsc(it.getProductId());
      int need=it.getQuantity();
      int total=batches.stream().mapToInt(InventoryBatch::getQuantity).sum();
      if(total<need)return false;
      for(InventoryBatch b:batches){if(need<=0)break;int t=Math.min(b.getQuantity(),need);b.setQuantity(b.getQuantity()-t);repo.save(b);need-=t;}
    }return true;
  }
}
