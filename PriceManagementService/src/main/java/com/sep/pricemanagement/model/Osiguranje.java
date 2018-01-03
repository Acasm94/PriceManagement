package com.sep.pricemanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Osiguranje implements Serializable{
	
	private static final long serialVersionUID = -7663607432545399871L;
	
	private Long id;
	private Polisa polisa;
	private TipOsiguranja tipOsiguranja;
	private List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja;
	
	public Osiguranje() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Polisa getPolisa() {
		return polisa;
	}
	
	public void setPolisa(Polisa polisa) {
		this.polisa = polisa;
	}
	
	public TipOsiguranja getTipoviOsiguranja() {
		return tipOsiguranja;
	}
	
	public void setTipoviOsiguranja(TipOsiguranja tipOsiguranja) {
		this.tipOsiguranja = tipOsiguranja;
	}
	
	public List<VrednostAtributaOsiguranja> getVrednostiAtributaOsiguranja() {
		if(vrednostiAtributaOsiguranja == null) {
			return new ArrayList<VrednostAtributaOsiguranja>();
		}
		return vrednostiAtributaOsiguranja;
	}
	
	public void setVrednostiAtributaOsiguranja(List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja) {
		this.vrednostiAtributaOsiguranja = vrednostiAtributaOsiguranja;
	}
	
}
