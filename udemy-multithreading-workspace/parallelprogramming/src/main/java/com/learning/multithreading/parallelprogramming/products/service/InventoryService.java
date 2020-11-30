package com.learning.multithreading.parallelprogramming.products.service;

import com.learning.multithreading.parallelprogramming.products.beans.Inventory;

public interface InventoryService {
//option id is parameter of product option
	Inventory retrieveInventory(int optionId);
		
}
