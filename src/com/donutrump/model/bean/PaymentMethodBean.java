package com.donutrump.model.bean;

import java.io.Serializable;
import java.sql.Date; 

public class PaymentMethodBean implements Serializable {

	private static final long serialVersionUID = -1096552767583246583L;
	
	public PaymentMethodBean() {
		this.numeroCarta = "";
		this.cvv = "";
		this.scadenza = null;
		this.user = null;
	}
	
	public String getNumeroCarta() {
		return this.numeroCarta;
	}
	
	public void setNumeroCarta(String newCn) {
		this.numeroCarta = newCn;
	}
	
	public String getCvv() {
		return this.cvv;
	}
	
	public void setCvv(String newCvv) {
		this.cvv = newCvv;
	}
	
	public Date getScadenza() {
		return this.scadenza;
	}
	
	public void setScadenza(Date newDate) {
		this.scadenza = newDate;
	}
	
	public UserBean getUtente() {
		return this.user;
	}
	
	public void setUtente(UserBean newUtente) {
		this.user = newUtente;
	}
	
	private String cvv, numeroCarta;
	private Date scadenza;
	private UserBean user;
}