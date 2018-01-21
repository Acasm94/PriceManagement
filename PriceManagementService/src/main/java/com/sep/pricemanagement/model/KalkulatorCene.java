package com.sep.pricemanagement.model;

import java.util.List;

public class KalkulatorCene {
	
	public List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja;
	public double cena;	
	
	public KalkulatorCene(List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja, double cena) {
		this.vrednostiAtributaOsiguranja = vrednostiAtributaOsiguranja;
		this.cena = cena;
	}
	
	public List<VrednostAtributaOsiguranja> getVrednostiAtributaOsiguranja() {
		return vrednostiAtributaOsiguranja;
	}
	public void setVrednostiAtributaOsiguranja(List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja) {
		this.vrednostiAtributaOsiguranja = vrednostiAtributaOsiguranja;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public void setPopust(double popust) 
	{
		cena = cena - cena * popust / 100;
	}

}
