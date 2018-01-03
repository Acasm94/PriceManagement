package com.sep.pricemanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OsiguravajucaKuca implements Serializable {

	private static final long serialVersionUID = -4134784298073252398L;

	private Long id;
	private String pib;
	private String naziv;
	private String adresa;
	private String mesto;
	private String telefon;
	private List<TipOsiguranja> tipoviOsiguranja;
	private List<Cenovnik> listaCenovnika;

	public OsiguravajucaKuca() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<TipOsiguranja> getTipoviOsiguranja() {
		if (tipoviOsiguranja == null) {
			return new ArrayList<TipOsiguranja>();
		}
		return tipoviOsiguranja;
	}

	public void setTipoviOsiguranja(List<TipOsiguranja> tipoviOsiguranja) {
		this.tipoviOsiguranja = tipoviOsiguranja;
	}

	public List<Cenovnik> getListaCenovnika() {
		return listaCenovnika;
	}

	public void setListaCenovnika(List<Cenovnik> listaCenovnika) {
		this.listaCenovnika = listaCenovnika;
	}

}
