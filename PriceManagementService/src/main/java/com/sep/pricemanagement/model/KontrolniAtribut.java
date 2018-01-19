package com.sep.pricemanagement.model;

public class KontrolniAtribut {
	
	private static final long serialVersionUID = -4758907317695159170L;

	private Long id;
	private KontekstAtributa kontekstAtributa;
	private TipAtributa tipAtributa;
	
	public KontrolniAtribut() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public KontekstAtributa getKontekstAtributa() {
		return kontekstAtributa;
	}

	public void setKontekstAtributa(KontekstAtributa kontekstAtributa) {
		this.kontekstAtributa = kontekstAtributa;
	}

	public TipAtributa getTipAtributa() {
		return tipAtributa;
	}

	public void setTipAtributa(TipAtributa tipAtributa) {
		this.tipAtributa = tipAtributa;
	}
}
