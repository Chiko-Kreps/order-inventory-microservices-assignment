package com.example.inventory.service;
import com.example.inventory.dto.UpdateInventoryRequest;
import com.example.inventory.factory.InventoryHandlerFactory;
import com.example.inventory.model.InventoryBatch;
import com.example.inventory.repository.InventoryBatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceImplTest {
  private InventoryBatchRepository repo; private InventoryHandlerFactory factory; private InventoryServiceImpl service;
  @BeforeEach void init(){repo=mock(InventoryBatchRepository.class);factory=mock(InventoryHandlerFactory.class);
    service=new InventoryServiceImpl(repo,factory);}
  @Test void getBatchesByProduct_sortsByExpiry(){
    InventoryBatch b1=new InventoryBatch(1L,5, LocalDate.now().plusDays(5));
    when(repo.findByProductIdOrderByExpiryDateAsc(1L)).thenReturn(List.of(b1));
    assertEquals(1, service.getBatchesByProduct(1L).size());
  }
  @Test void updateInventory_callsFactory(){
    UpdateInventoryRequest req=new UpdateInventoryRequest(Collections.emptyList());
    var handler=mock(com.example.inventory.factory.InventoryHandler.class);
    when(factory.getHandler("defaultInventoryHandler")).thenReturn(handler);
    when(handler.updateInventory(req)).thenReturn(true);
    assertTrue(service.updateInventory(req));
  }
}
