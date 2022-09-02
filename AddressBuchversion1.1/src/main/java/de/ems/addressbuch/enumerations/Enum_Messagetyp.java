package de.ems.addressbuch.enumerations;

public enum Enum_Messagetyp {
	EMAIL(1, "E-Mail"), 
	FESTNETZ(2, "Festnetz"), 
	MOBIL(3, "Mobiltelefon"), 
	FAX(4, "Fax");

	private Integer id;
	private String bezeichnung;

	Enum_Messagetyp(Integer id, String bezeichnung) {
		this.id = id;
		this.bezeichnung = bezeichnung;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	@Override
	public String toString() {
		return this.bezeichnung;
	}

	public static Enum_Messagetyp getEnumById(int id) {
		for (Enum_Messagetyp g : Enum_Messagetyp.values()) {
			if (g.getId().intValue() == id)
				return g;
		}
		return null;
	}
}
