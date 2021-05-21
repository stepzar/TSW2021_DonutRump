package com.donutrump.model.bean;

import java.io.Serializable;
import java.sql.Date;

public class OrderBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public OrderBean () {
		this.id=0;
		this.dataConsegna=null; 
		this.dataOrdine=null;
		this.importoTotale=0; 
		this.indirizzo=null;
		this.metodoPagamento = null;
		this.quantitaAcquisto=0; 
		this.speseSpedizione=0; 
		this.utente=null; 
		this.stato="attesa"; 
	}
	
	public OrderBean (UserBean utente) {
		this.id=0;
		this.dataConsegna=null; 
		this.dataOrdine=null; 
		this.importoTotale=0; 
		this.indirizzo=null;
		this.metodoPagamento = null;
		this.quantitaAcquisto=0; 
		this.speseSpedizione=0; 
		this.utente=utente; 
		this.stato="attesa"; 
	}
	
	public OrderBean (UserBean utente, AddressBean indirizzo, PaymentMethodBean metodoPagamento) {
		this.id=0;
		this.dataConsegna=null; 
		this.dataOrdine=null; 
		this.importoTotale=0; 
		this.indirizzo=indirizzo;
		this.metodoPagamento = metodoPagamento;
		this.quantitaAcquisto=0; 
		this.speseSpedizione=0; 
		this.utente=utente; 
		this.stato="attesa"; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public UserBean getUtente() {
		return utente;
	}

	public void setUtente(UserBean utente) {
		this.utente = utente;
	}

	public AddressBean getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(AddressBean indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public PaymentMethodBean getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(PaymentMethodBean metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public double getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(double importoTotale) {
		this.importoTotale = importoTotale;
	}

	public double getSpeseSpedizione() {
		return speseSpedizione;
	}

	public void setSpeseSpedizione(double speseSpedizione) {
		this.speseSpedizione = speseSpedizione;
	}

	public int getQuantitaAcquisto() {
		return quantitaAcquisto;
	}

	public void setQuantitaAcquisto(int quantitaAcquisto) {
		this.quantitaAcquisto = quantitaAcquisto;
	}

	public Date getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	//VARIABILI D'ISTANZA:
	private int id; 
	private UserBean utente; 
	private AddressBean indirizzo; 
	private PaymentMethodBean metodoPagamento;
	private String stato;
	private Date dataOrdine; 
	private double importoTotale; 
	private double speseSpedizione; 
	private int quantitaAcquisto; 
	private Date dataConsegna; 
}
