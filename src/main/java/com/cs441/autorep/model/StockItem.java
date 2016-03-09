package com.cs441.autorep.model;

public class StockItem {
	
	private String product_id;
	private int quantity;
	private String store_id;
	
	public String getPID() {
		return this.product_id;
	}
	
	public int getQuant() {
		return this.quantity;
	}
	
	public String getSID() {
		return this.store_id;
	}
	
	public void setPID(String prod) {
		this.product_id = prod;
	}
	
	public void setQuant(int q) {
		this.quantity = q;
	}
	
	public void setSID(String store) {
		this.store_id = store;
	}
	
}
