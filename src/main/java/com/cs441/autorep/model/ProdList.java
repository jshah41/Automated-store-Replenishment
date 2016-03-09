package com.cs441.autorep.model;

import java.io.Serializable;


public class ProdList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String count;
	
	private String product;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
}
