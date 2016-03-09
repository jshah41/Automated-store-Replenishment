package com.cs441.autorep.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TCViewTranLogs {

	SkuWLogs obj = new SkuWLogs();
	
	@Test
	public void testskuId(){
		
		obj.setSkuId(1009);
		int result = obj.getSkuId();
		assertEquals(1009, result);
	}
	
	@Test
	public void teststoreId()
	{
		
		obj.setStoreId(9001);
		int result = obj.getStoreId();
		assertEquals(9001, result);
	}
	
	
	@Test
	public void testGetWarehouseId() {
		obj.setWarehouseId(9001);
		int result = obj.getWarehouseId();
		assertEquals(9001, result);
	}

}
