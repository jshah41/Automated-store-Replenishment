package com.cs441.autorep.interfaces;

import java.util.ArrayList;

import com.cs441.autorep.model.Inventory;

public interface InventoryManager {

	public ArrayList<Inventory> getInventory(String storeId) throws Exception;
	
}
