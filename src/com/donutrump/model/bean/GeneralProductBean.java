//***********************************Bean di prodottogenerico
package com.donutrump.model.bean;

//import java.awt.image.BufferedImage;
import java.io.Serializable;

public class GeneralProductBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public GeneralProductBean() {
		this.id = 0; 
		this.nome = ""; 
		this.quantitaDisponibile = 0; 
		this.prezzo= 0; 
		this.iva = 22; 
		this.disponibilita= false; 
		this.descrizione= ""; 
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantitaDisponibile() {
		return quantitaDisponibile;
	}
	public void setQuantitaDisponibile(int quantitaDisponibile) {
		this.quantitaDisponibile = quantitaDisponibile;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public boolean isDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	//ritorna il bean in forma di Stringa
	public String toString() {
		String disp; 
		if (this.disponibilita) disp = "disponibile";
		else disp = "non disponibile"; 
		return nome + " (" + id + "), " + prezzo + " " + quantitaDisponibile + ". " + descrizione + " IVA: "+ this.iva + "% "+ disp;
	}

	//VARIABILI D'ISTANZA: 
	private int id; 
	private String nome; 
	private int quantitaDisponibile; 
	private double prezzo; 
	private double iva; 
	private boolean disponibilita; 
	private String descrizione; 
	
  // private BufferedImage immagine; 
	private int categoria; 

}
