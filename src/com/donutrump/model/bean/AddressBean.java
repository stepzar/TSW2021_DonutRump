package com.donutrump.model.bean;

public class AddressBean {
	
	public AddressBean() {
		id = 0;
		utente = null;
		cap = "";
		nCivico = 0;
		via = "";
		provincia = "";
		citta = "";
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
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public int getnCivico() {
		return nCivico;
	}
	public void setnCivico(int nCivico) {
		this.nCivico = nCivico;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String toString() {
		return via + " "+nCivico +", "+ citta+ " ("+provincia+") "+cap;
	}

	private int id;
	private UserBean utente;
	private String cap;
	private int nCivico;
	private String via;
	private String provincia;
	private String citta;
}
