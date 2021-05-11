package com.donutrump.model.bean;

import java.io.Serializable;

public class InstanceProductBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//istanzia un' "istanzaProdotto" che non ha nessun collegamento con alcun prodotto generico e non si trova in nessun ordine
		public InstanceProductBean() {
			this.id = 0; 
			this.ivaAcquisto = 22;
			this.prodottoGenerico = null;
			this.prezzoAcquisto = 0;
			this.ordine=null; 
		}
	
	//istanzia un' "istanzaProdotto" che non si trova in nessun ordine
	public InstanceProductBean(GeneralProductBean prodottoGenerico) {
		this.id = 0; 
		this.prodottoGenerico = prodottoGenerico;
		this.ivaAcquisto = this.prodottoGenerico.getIva();
		this.prezzoAcquisto = this.prodottoGenerico.getPrezzo();
		this.ordine=null; 
	}
	
	//istanzia un' "istanzaProdotto" che ha il collegamento con sia con il prodotto generico che con un'ordine 
	public InstanceProductBean(GeneralProductBean prodottoGenerico, OrderBean ordine) {
		this.id = 0; 
		this.prodottoGenerico = prodottoGenerico;
		this.ivaAcquisto = this.prodottoGenerico.getIva();
		this.prezzoAcquisto = this.prodottoGenerico.getPrezzo();
		this.ordine=ordine; 
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getIvaAcquisto() {
		return ivaAcquisto;
	}
	public void setIvaAcquisto(double iva) {
		this.ivaAcquisto = iva;
	}
	public double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public void setPrezzoAcquisto(double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
	
	public GeneralProductBean getProdottoGenerico() {
		return prodottoGenerico;
	}
	public void setProdottoGenerico(GeneralProductBean prodottoGenerico) {
		this.prodottoGenerico = prodottoGenerico;
	}
	public OrderBean getOrdine() {
		return ordine;
	}
	public void setOrdine(OrderBean ordine) {
		this.ordine = ordine;
	} 
	
	@Override
	public String toString() {
		return "InstanceProductBean [iva=" + ivaAcquisto + ", prezzoAcquisto=" + prezzoAcquisto + ", generalProduct="
				+ prodottoGenerico + "]";
	}
	
	private int id; 
	private double ivaAcquisto;
	private double prezzoAcquisto;
	private GeneralProductBean prodottoGenerico;
	private OrderBean ordine;

}
