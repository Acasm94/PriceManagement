package com.sep.pricemanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TipOsiguranja implements Serializable{

	private static final long serialVersionUID = -2182384755331442068L;

	private Long id;
	private String naziv;
	private short brojFormi;
	private OsiguravajucaKuca osiguravajucaKuca;
	private List<Osiguranje> osiguranja;
	private List<TipAtributa> tipoviAtributa;
	
	public TipOsiguranja() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public short getBrojFormi() {
		return brojFormi;
	}

	public void setBrojFormi(short brojFormi) {
		this.brojFormi = brojFormi;
	}

	public OsiguravajucaKuca getOsiguravajucaKuca() {
		return osiguravajucaKuca;
	}
	
	public void setOsiguravajucaKuca(OsiguravajucaKuca osiguravajucaKuca) {
		this.osiguravajucaKuca = osiguravajucaKuca;
	}
	
	public List<Osiguranje> getOsiguranja() {
		if(osiguranja == null) {
			return new ArrayList<Osiguranje>();
		}
		return osiguranja;
	}
	
	public void setOsiguranja(List<Osiguranje> osiguranja) {
		this.osiguranja = osiguranja;
	}
	
	public List<TipAtributa> getTipoviAtributa() {
		if(tipoviAtributa == null) {
			return new ArrayList<TipAtributa>();
		}
		return tipoviAtributa;
	}
	
	public void setTipoviAtributa(List<TipAtributa> tipoviAtributa) {
		this.tipoviAtributa = tipoviAtributa;
	}
	
}
