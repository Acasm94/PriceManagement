package com.sep.pricemanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cenovnik implements Serializable{

	private static final long serialVersionUID = 9131762390974840535L;
	private Long id;
	private Date datumOd;
	private Date datumDo;
	private OsiguravajucaKuca osiguravajucaKuca;
	private List<StavkaCenovnika> stavkeCenovnika;
	
	public Cenovnik() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}

	public Date getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}
	
	public OsiguravajucaKuca getOsiguravajucaKuca() {
		return osiguravajucaKuca;
	}

	public void setOsiguravajucaKuca(OsiguravajucaKuca osiguravajucaKuca) {
		this.osiguravajucaKuca = osiguravajucaKuca;
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
