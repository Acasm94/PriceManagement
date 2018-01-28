package com.sep.pricemanagement.model.notification;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Klijent {
	

	private Long id;
	private String ime;
	private String prezime;
	private String jmbg;
	private String brojPasosa;
	private String adresa;
	private String brojTelefona;
	private String email;
	private List<Osiguranje> osiguranja = new ArrayList<>();
	
	public Klijent() {
	}
	
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
	
	@JsonIgnore
	public List<Osiguranje> getOsiguranja() {
		if(osiguranja == null) {
			return new ArrayList<Osiguranje>();
		}
		return osiguranja;
	}
	
	@JsonProperty
	public void setOsiguranja(List<Osiguranje> osiguranja) {
		this.osiguranja = osiguranja;
	}
	
}
