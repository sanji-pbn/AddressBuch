package de.ems.addressbuch.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.ems.addressbuch.enumerations.Enum_Anschriftentyp;

@Entity
@Table (name = "AB_Anschrift")
public class AnschriftEntity {

	private String anschriftPK;
	
	private String person_fk;
	
	private String plz;
	
	private String ort;
	
	private String land;
	
	private String strasse;
	
	private boolean aktiv;
	
	private Enum_Anschriftentyp anschriftentyp_Enum;
	
	public AnschriftEntity() {
	}

	@Id
	@Column(name = "Pk")
	public String getAnschriftPK() {
		return anschriftPK;
	}

	public void setAnschriftPK(String anschriftPK) {
		this.anschriftPK = anschriftPK;
	}

	@Column(name = "Person_fk")
	public String getPerson_fk() {
		return person_fk;
	}

	public void setPerson_fk(String person_fk) {
		this.person_fk = person_fk;
	}

	@Column(name = "Plz")
	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	@Column(name = "Ort")
	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	@Column(name = "Land")
	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	@Column(name = "Strasse")
	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	@Column(name = "Aktiv")
	public boolean isAktiv() {
		return aktiv;
	}

	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}

	@Column(name = "Anschriftentyp_Enum")
	public Enum_Anschriftentyp getAnschriftentyp_Enum() {
		return anschriftentyp_Enum;
	}

	public void setAnschriftentyp_Enum(Enum_Anschriftentyp anschriftentyp_Enum) {
		this.anschriftentyp_Enum = anschriftentyp_Enum;
	}

	@Override
	public String toString() {
		return "AnschriftEntity [anschriftPK=" + anschriftPK + ", person_fk=" + person_fk + ", plz=" + plz + ", ort="
				+ ort + ", land=" + land + ", strasse=" + strasse + ", aktiv=" + aktiv + ", anschriftentyp_Enum="
				+ anschriftentyp_Enum + "]";
	}

	
	
}
