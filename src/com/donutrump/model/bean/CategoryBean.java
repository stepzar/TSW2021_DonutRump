package com.donutrump.model.bean;

import java.io.Serializable;

public class CategoryBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public CategoryBean() {
		this.nome = "";
		this.id = 0;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int newId) {
		this.id = newId;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String newName)
	{
		this.nome = newName;
	}
	
	public boolean equals (CategoryBean category2) {
		if (this.id==category2.getId() && this.nome.equals(category2.getNome())) return true; 
		else return false; 		
	}
	
	
	private int id;
	private String nome;
}
