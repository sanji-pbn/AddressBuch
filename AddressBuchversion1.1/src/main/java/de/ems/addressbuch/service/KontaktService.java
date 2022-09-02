package de.ems.addressbuch.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import de.ems.addressbuch.entities.KontaktEntity;
import de.ems.addressbuch.entities.PersonEntity;
import de.ems.addressbuch.model.Kontakt;

public interface KontaktService {
	
	public KontaktEntity createKontakt();
	
	public List<KontaktEntity> getAlleKontakte();
	
	public KontaktEntity addKontakt(Kontakt kontakt);
	
	public KontaktEntity getKontaktByPk(String pk);
	
	public ResponseEntity<KontaktEntity> updateKontakt(String pk, KontaktEntity kontaktDetails);
	
	public ResponseEntity<Map<String,Boolean>> deleteKontakt(String pk);
	
	public KontaktEntity addPersonPkToKontakt(String kontaktPk, String personFk);
	
	public KontaktEntity deletePersonPkFromKontakt(String kontaktPk);

	
}
