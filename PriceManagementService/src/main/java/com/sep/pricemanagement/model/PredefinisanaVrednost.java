package com.sep.pricemanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PredefinisanaVrednost implements Serializable{

	private static final long serialVersionUID = -2837150851847837894L;
	private Long id;
	private String konkretnaVrednost;
	private TipAtributa tipAtributa;
	private List<StavkaCenovnika> stavkeCenovnika;
	
	public PredefinisanaVrednost() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKonkretnaVrednost() {
		return konkretnaVrednost;
	}

	public void setKonkretnaVrednost(String konkretnaVrednost) {
		this.konkretnaVrednost = konkretnaVrednost;
	}

	public TipAtributa getTipAtributa() {
		return tipAtributa;
	}

	public void setTipAtributa(TipAtributa tipAtributa) {
		this.tipAtributa = tipAtributa;
	}

	public List<StavkaCenovnika> getStavkeCenovnika() {
		if(stavkeCenovnika == null) {
			stavkeCenovnika = new ArrayList<>();
		}
		return stavkeCenovnika;
	}

	public void setStavkeCenovnika(List<StavkaCenovnika> stavkeCenovnika) {
		this.stavkeCenovnika = stavkeCenovnika;
	}
	
}
