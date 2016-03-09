package com.cs441.autorep.model;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class TCStockReportGeneration {
	StockItem siobj = new StockItem();
	
	@Test
	public void TestCaseSI()
	{
		
		siobj.setPID("Product1");
		String result = siobj.getPID();
		assertEquals("Product1", result);
				
	}
}
