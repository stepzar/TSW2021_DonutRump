package com.donutrump.model.bean;

import java.util.ArrayList;

public class Cart {

	private ArrayList<InstanceProductBean> products;
	
	public Cart() {
		products = new ArrayList<InstanceProductBean>();
	}
	
	public void addProduct(InstanceProductBean product) {
		products.add(product);
	}
	
	public void deleteProduct(InstanceProductBean product) {
		for(InstanceProductBean item : products) {
			if(item.getGeneralProduct().getId() == product.getGeneralProduct().getId()) {
				products.remove(item);
				break;
			}
		}
 	}
	
	public ArrayList<InstanceProductBean> getProducts() {
		return  products;
	}
}
