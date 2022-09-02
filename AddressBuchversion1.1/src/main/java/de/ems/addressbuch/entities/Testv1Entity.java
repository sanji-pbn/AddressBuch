package de.ems.addressbuch.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_Person")
public class Testv1Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Pk")
	private String pk;
	@Column(name = "Vorname")
	private String vorname;
	@Column(name = "Nachname")
	private String nachName;
	@Column(name = "Email_Id")
	private String emailId;
	
	public Testv1Entity() {
		
	}	

	public Testv1Entity(String vorname, String nachName, String emailId) {
		super();
		this.vorname = vorname;
		this.nachName = nachName;
		this.emailId = emailId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Testv1Entity [pk=" + pk + ", vorname=" + vorname + ", nachName=" + nachName + ", emailId=" + emailId
				+ "]";
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachName() {
		return nachName;
	}

	public void setNachName(String nachName) {
		this.nachName = nachName;
	}
	
	
}
