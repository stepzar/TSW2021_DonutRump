//NOTA: La classe è stata creata solo a scopo provvisorio

package com.donutrump.model.bean;

import java.io.Serializable;

public class OrderBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	public OrderBean () {
		this.id=1; 
	}
		
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	
	
	
	
	
	



	//VARIABILI D'ISTANZA:
	private int id; 

}
