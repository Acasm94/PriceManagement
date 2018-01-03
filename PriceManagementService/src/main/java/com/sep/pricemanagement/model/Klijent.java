package com.sep.pricemanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Klijent implements Serializable{
	
	private static final long serialVersionUID = 23507162692089508L;
	
	public Klijent() {}
	
	private Long id;
	private String ime;
	private String prezime;
	private String jmbg;
	private String brojPasosa;
	private String adresa;
	private String brojTelefona;
	private String email;
	private List<Polisa> polise;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getBrojPasosa() {
		return brojPasosa;
	}
	public void setBrojPasosa(String brojPasosa) {
		this.brojPasosa = brojPasosa;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Polisa> getPolise() {
		if(polise == null) {
			return new ArrayList<Polisa>();
		}
		return polise;
	}
	public void setPolise(List<Polisa> polise) {
		this.polise = polise;
	}
}
