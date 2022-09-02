package de.ems.addressbuch.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import de.ems.addressbuch.entities.AnschriftEntity;
import de.ems.addressbuch.model.Anschrift;

@Service
public interface AnschriftService {

	public AnschriftEntity createAnschrift();
	
	public List<AnschriftEntity> getAlleAnschrifte();
	
	public AnschriftEntity addAnschrift(Anschrift anschrift);
	
	public AnschriftEntity getAnschriftByPk(String pk);
	
	public ResponseEntity<AnschriftEntity> updateAnschrift(String pk, AnschriftEntity anschriftDetails);
	
	public ResponseEntity<Map<String, Boolean>> deleteAnschrift(String pk);
}
