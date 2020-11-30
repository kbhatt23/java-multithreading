package com.learning.multithreading.parallelprogramming.products.service;

import java.util.HashMap;
import java.util.Map;

import com.learning.multithreading.parallelprogramming.products.beans.Inventory;
import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class InventoryServiceImpl implements InventoryService {

	private static Map<Integer, Integer> inventoryMap;
	
	static {
		inventoryMap = new HashMap<Integer, Integer>();
		inventoryMap.put(1, 11);
		inventoryMap.put(2, 22);
		inventoryMap.put(3, 33);
		inventoryMap.put(4, 44);
	}
	@Override
	public Inventory retrieveInventory(int optionId) {
		//simulating web service call
		CommonUtil.sleep(1000);
		return new Inventory(inventoryMap.get(optionId));
	}

}
