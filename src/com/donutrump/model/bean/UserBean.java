package com.donutrump.model.bean;

public class UserBean {
	
	public UserBean() {
		super();
		this.id = 0;
		this.nome = "";
		this.cognome = "";
		this.email = "";
		this.isAdmin = false;
		this.telefono = "";
		this.pswd = "";
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
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", isAdmin="
				+ isAdmin + ", telefono=" + telefono + ", pswd=" + pswd + "]";
	}

	private int id;
	private String nome;
	private String cognome;
	private String email;
	private boolean isAdmin;
	private String telefono;
	private String pswd;
}
