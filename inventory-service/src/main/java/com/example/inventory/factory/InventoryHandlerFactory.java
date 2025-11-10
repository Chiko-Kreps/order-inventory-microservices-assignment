package com.example.inventory.factory;
import org.springframework.stereotype.Component;
import java.util.Map;
@Component
public class InventoryHandlerFactory {
  private final Map<String,InventoryHandler>handlers;
  public InventoryHandlerFactory(Map<String,InventoryHandler>h){this.handlers=h;}
  public InventoryHandler getHandler(String key){return handlers.getOrDefault(key,handlers.get("defaultInventoryHandler"));}
}
