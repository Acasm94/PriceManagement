package com.sep.pricemanagement.model;

import java.util.ArrayList;
import java.util.List;

public class KalkulatorCene {
	
	public List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja;
	public List<String> vrednostiAtributaLista;
	public double cena;	
	
	public KalkulatorCene(List<VrednostAtributaOsiguranja> vrednostiAtributaOsiguranja, double cena) {
		this.vrednostiAtributaOsiguranja = vrednostiAtributaOsiguranja;
		this.cena = cena;
		this.vrednostiAtributaLista = new ArrayList<String>();
		
		for(VrednostAtributaOsiguranja vrednostAtrOsig : vrednostiAtributaOsiguranja) 
		{
			this.vrednostiAtributaLista.add(vrednostAtrOsig.getVrednost());
		}
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
	
	public List<String> getVrednostiAtributaLista() {
		return vrednostiAtributaLista;
	}

	public void setVrednostiAtributaLista(List<String> vrednostiAtributaLista) {
		this.vrednostiAtributaLista = vrednostiAtributaLista;
	}

	public void setPopust(double popust) 
	{
		this.cena = this.cena - this.cena * popust / 100;
	}

}
