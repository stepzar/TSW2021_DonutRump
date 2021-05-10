package com.donutrump.model.bean;

import java.io.Serializable;

public class InstanceProductBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public InstanceProductBean(GeneralProductBean generalProduct) {
		this.iva = 22;
		this.prezzoAcquisto = this.generalProduct.getPrezzo();
		this.generalProduct = generalProduct;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public void setPrezzoAcquisto(double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
	public GeneralProductBean getGeneralProduct() {
		return generalProduct;
	}
	public void setGeneralProduct(GeneralProductBean generalProduct) {
		this.generalProduct = generalProduct;
	}
	
	@Override
	public String toString() {
		return "InstanceProductBean [iva=" + iva + ", prezzoAcquisto=" + prezzoAcquisto + ", generalProduct="
				+ generalProduct + "]";
	}

	private double iva;
	private double prezzoAcquisto;
	private GeneralProductBean generalProduct;
}
