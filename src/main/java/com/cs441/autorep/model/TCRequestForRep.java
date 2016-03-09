package com.cs441.autorep.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TCRequestForRep {

	RepSuggestions obj = new RepSuggestions();
	
	@Test
	public void testGetStoreId() {
		
		obj.setStoreId("100");
		String result = obj.getStoreId();
		assertEquals("100",result);
	}

	

	@Test
	public void testGetRepId() {
		obj.setRepId("101");
		String result = obj.getRepId();
		assertEquals("101",result);
		
	}

	

	@Test
	public void testGetProductId() {
		obj.setProductId("001");
		String result = obj.getProductId();
		assertEquals("001",result);
	}

	
	@Test
	public void testGetPackSize() {
		obj.setPackSize("25");
		String result = obj.getPackSize();
		assertEquals("25",result);
	}

	

	@Test
	public void testGetExpiryDate() {
		obj.setExpiryDate("2014-11-30");
		String result = obj.getExpiryDate();
		assertEquals("2014-11-30", result);
	}

	
	@Test
	public void testGetDiscount() {
		obj.setDiscount("5");
		String result = obj.getDiscount();
		assertEquals("5", result);
	}

	

	@Test
	public void testGetDateOfMf() {
		obj.setDateOfMf("2014-11-30");
		String result = obj.getDateOfMf();
		assertEquals("2014-11-30", result);
	}

	

	@Test
	public void testGetMrp() {
		obj.setMrp("50");
		String result = obj.getMrp();
		assertEquals("50", result);
	}

	
	@Test
	public void testGetUnitPrice() {
		obj.setUnitPrice("10");
		String result = obj.getUnitPrice();
		assertEquals("10", result);
	}

	

	@Test
	public void testGetWeight() {
		obj.setWeight("5");
		String result = obj.getWeight();
		assertEquals("5", result);
	}

	

	@Test
	public void testGetNote() {
		obj.setNote("This is note");
		String result = obj.getNote();
		assertEquals("This is note", result);
	}

	

	@Test
	public void testGetVendorId() {
		obj.setVendorId("123");
		String result = obj.getVendorId();		
		assertEquals("123", result);
	}

	
	@Test
	public void testGetWarehouseId() {
		obj.setWarehouseId("9001");
		String result = obj.getWarehouseId();
		assertEquals("9001", result);
	}

	
}
