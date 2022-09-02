package de.ems.addressbuch.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import de.ems.addressbuch.enumerations.Enum_Messagetyp;

@Entity
@Table (name = "AB_Kontakt")
public class KontaktEntity {
	
	private String kontaktPk;
	private String person_fk;
	private Enum_Messagetyp messagetyp_Enum;
	private String emailOrPhone;
	private boolean aktiv; 
	
	
	public KontaktEntity() {		
	}

	
	@Id
	@Column(name = "Pk")
	public String getKontaktPk() {
		return kontaktPk;
	}
	public void setKontaktPk(String kontaktPk) {
		this.kontaktPk = kontaktPk;
	}



	@Column(name = "Person_fk")
	public String getPerson_fk() {
		return person_fk;
	}
	public void setPerson_fk(String person_fk) {
		this.person_fk = person_fk;
	}



	@Column(name="Messagetyp_Enum")
	@Enumerated(EnumType.STRING)
	public Enum_Messagetyp getMessagetyp_Enum() {
		return messagetyp_Enum;
	}
	public void setMessagetyp_Enum(Enum_Messagetyp messagetyp_Enum) {
		this.messagetyp_Enum = messagetyp_Enum;
	}



	@Column(name = "EmailOrPhone")
	public String getEmailOrPhone() {
		return emailOrPhone;
	}
	public void setEmailOrPhone(String emailOrPhone) {
		this.emailOrPhone = emailOrPhone;
	}



	@Column(name = "Aktiv")	
	public boolean isAktiv() {
		return aktiv;
	}
	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}


	@Override
	public String toString() {
		return "KontaktEntity [kontaktPk=" + kontaktPk + ", person_fk=" + person_fk + ", messagetyp_Enum="
				+ messagetyp_Enum + ", emailOrPhone=" + emailOrPhone + ", aktiv=" + aktiv + "]";
	}


	
}
