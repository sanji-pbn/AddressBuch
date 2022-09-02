package de.ems.addressbuch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import de.ems.addressbuch.entities.KontaktEntity;
import de.ems.addressbuch.entities.PersonEntity;
import de.ems.addressbuch.enumerations.Enum_Messagetyp;
import de.ems.addressbuch.exception.ResourceNotFoundException;
import de.ems.addressbuch.model.Kontakt;
import de.ems.addressbuch.repository.KontaktRepository;
import de.ems.tools.AbstractPK;


@Service
public class KontaktServiceImpl implements KontaktService {

	@Autowired
	private KontaktRepository kontaktRepository;
		
	private static Logger log = Logger.getLogger(KontaktServiceImpl.class.getName());

	@Override
	public KontaktEntity createKontakt() {
		KontaktEntity k = new KontaktEntity();
		k.setKontaktPk(AbstractPK.create());
		return k;
	}
	
	@Override
	public KontaktEntity addKontakt(Kontakt kontakt) {
		KontaktEntity newKontakt = createKontakt();
		
		
		if(kontakt.getPerson_fk()== null) {
			log.error("Peerson_fk missing.");
			return null;
		}
		//PersonEntity personFk = personenService.getPersonByPk(kontakt.getPerson_fk());
		newKontakt.setPerson_fk(kontakt.getPerson_fk());
		newKontakt.setEmailOrPhone(kontakt.getEmailOrPhone());
		newKontakt.setMessagetyp_Enum(Enum_Messagetyp.valueOf(kontakt.getMessageTyp()));  //Enum_Messagetyp.valueOf("EMAIL"));
		newKontakt.setAktiv(true);
		
		return kontaktRepository.save(newKontakt);
	}

	@Override
	public List<KontaktEntity> getAlleKontakte() {
		return kontaktRepository.findAll();
	}


	@Override
	public KontaktEntity getKontaktByPk(String pk) {
		return kontaktRepository.findById(pk).orElseThrow(() -> new ResourceNotFoundException("Kontakt not exist by PK: " + pk));
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> deleteKontakt(String pk) {
		
		KontaktEntity kontaktDelete = kontaktRepository.findById(pk).orElseThrow(() -> new ResourceNotFoundException("Kontakt not exist by Pk: " + pk));
		
		kontaktRepository.delete(kontaktDelete);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted Kontakt", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}

	@Override
	public KontaktEntity addPersonPkToKontakt(String kontaktPk, String personFk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KontaktEntity deletePersonPkFromKontakt(String kontaktPk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<KontaktEntity> updateKontakt(String pk, KontaktEntity kontaktDetails) {
		KontaktEntity editKontakt = kontaktRepository.findById(pk).orElseThrow(() -> new ResourceNotFoundException("Kontakt not exist with PK: + " + pk));
		
		editKontakt.setMessagetyp_Enum(kontaktDetails.getMessagetyp_Enum());
		editKontakt.setEmailOrPhone(kontaktDetails.getEmailOrPhone());
		editKontakt.setAktiv(kontaktDetails.isAktiv());
		
		KontaktEntity editedKontakt = kontaktRepository.save(editKontakt);
		
		return ResponseEntity.ok(editedKontakt);
	}

}
