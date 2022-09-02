package de.ems.addressbuch.model;

public class Anschrift {
	
	private String pk;
	
	private String person_fk;
	
	private String plz;
	
	private String ort;
	
	private String land;
	
	private String strasse;
	
	private String anschriftentyp;
	
	private Boolean aktiv;
	
	public Anschrift() {
		
	}

	public Anschrift(String person_fk, String plz, String ort, String land, String strasse) {
		super();
		this.person_fk = person_fk;
		this.plz = plz;
		this.ort = ort;
		this.land = land;
		this.strasse = strasse;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getPerson_fk() {
		return person_fk;
	}

	public void setPerson_fk(String person_fk) {
		this.person_fk = person_fk;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getAnschriftentyp() {
		return anschriftentyp;
	}

	public void setAnschriftentyp(String anschriftentyp) {
		this.anschriftentyp = anschriftentyp;
	}

	public Boolean getAktiv() {
		return aktiv;
	}

	public void setAktiv(Boolean aktiv) {
		this.aktiv = aktiv;
	}

	@Override
	public String toString() {
		return "Anschrift [pk=" + pk + ", person_fk=" + person_fk + ", plz=" + plz + ", ort=" + ort + ", land=" + land
				+ ", strasse=" + strasse + ", anschriftentyp=" + anschriftentyp + ", aktiv=" + aktiv + "]";
	}
	
	
}
