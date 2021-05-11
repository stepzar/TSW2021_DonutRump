/**************************Cart � l'insieme di "General prodotto" che caratterizza il carrello************************/

package com.donutrump.model.bean;

import java.util.ArrayList;

public class Cart {

	public Cart() {
		products = new ArrayList<GeneralProductBean>();
	}
	
	public void addProduct(GeneralProductBean product) {
		products.add(product);
		// controlliamo se già esiste e nel caso aumentiamo la quantita
	}
	
	public void deleteProduct(GeneralProductBean product) {
		for(GeneralProductBean item : products) {
			if(item.getId() == product.getId()) {
				products.remove(item);
				break;
			}
		}
 	}
	
	public ArrayList<GeneralProductBean> getProducts() {
		return  products;
	}
	
		
	private ArrayList<GeneralProductBean> products;
}
