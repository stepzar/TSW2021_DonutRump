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
		this.disponibilita = false; 
		this.descrizione = ""; 
		this.categoria = null; 
		this.immagine = "";
	}
	
	public GeneralProductBean(CategoryBean categoria) {
		this.id = 0; 
		this.nome = ""; 
		this.quantitaDisponibile = 0; 
		this.prezzo= 0; 
		this.iva = 22; 
		this.disponibilita = false; 
		this.descrizione = ""; 
		this.categoria = categoria; 
		this.immagine = "";
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
	
	public boolean setQuantitaDisponibile(int quantitaDisponibile) {
		if (quantitaDisponibile < 0) return false; 
		else if (quantitaDisponibile == 0) {
			this.quantitaDisponibile = quantitaDisponibile;
			this.disponibilita=false; 
			return true;
			}
		else {
			this.quantitaDisponibile = quantitaDisponibile;
			this.disponibilita = true;
			return true;
		}
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	public boolean setPrezzo(double prezzo) {
		if (prezzo < 0) return false; 
		else {
			this.prezzo = prezzo;
			return true;
		}
	}
	public double getIva() {
		return iva;
	}
	
	public boolean setIva(double iva) {
		if (iva < 0) return false; 
		else {
			this.iva = iva;
			return true;
		}
	}
	public boolean isDisponibilita() {
		return disponibilita;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public CategoryBean getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoryBean categoria) {
		this.categoria = categoria;
	} 

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
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
	private CategoryBean categoria;   //La cattegoria momentaneamente non la trattiamo, sar� impostata ad 1, il nostro sito per pra ha una sola categoria = 1.
	private String immagine; 	//path immagine
	
}
