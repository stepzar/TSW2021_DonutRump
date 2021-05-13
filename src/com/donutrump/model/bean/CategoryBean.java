package com.donutrump.model.bean;

import java.io.Serializable;

public class CategoryBean implements Serializable{

	private static final long serialVersionUID = -4980442176904447273L;

	public CategoryBean()
	{
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
	
	
	private int id;
	private String nome;
}
