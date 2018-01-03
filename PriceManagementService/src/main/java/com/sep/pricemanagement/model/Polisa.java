package com.sep.pricemanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Polisa implements Serializable{

	private static final long serialVersionUID = -6901258118812785721L;

	private Long id;
	private Date datumSklapanja;
	private Double cena;
	private List<Osiguranje> osiguranja;
	private Klijent klijent;
	
	public Polisa() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDatumSklapanja() {
		return datumSklapanja;
	}

	public void setDatumSklapanja(Date datumSklapanja) {
		this.datumSklapanja = datumSklapanja;
	}

	public Double getCena() {
		return cena;
	}
	
	public void setCena(Double cena) {
		this.cena = cena;
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
	
	public Klijent getKlijent() {
		return klijent;
	}
	
	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

}
