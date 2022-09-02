package de.ems.addressbuch.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.ems.addressbuch.entities.KontaktEntity;
import de.ems.addressbuch.model.Kontakt;
import de.ems.addressbuch.service.KontaktService;

@RestController
@RequestMapping("/api/")
public class KontaktController {

	@Autowired
	private KontaktService kontaktService;

	@GetMapping("/versiontype")
	public ResponseEntity<String> getVersion() {
		System.out.println("Enter Version kontakt");
		return new ResponseEntity<String>("V0.0.1.Kontakt", HttpStatus.OK);
	}
	
	@GetMapping("/kontakte")
	public List<KontaktEntity> getAlleKontakte(){
		System.out.println("All kontakts are shown");
		return kontaktService.getAlleKontakte();
	}
	
	// create person restAPI

	@PostMapping("/kontakt/create")
	public KontaktEntity createKontakt(@RequestBody Kontakt kontakt) {
		System.out.println("Testing for creating");
		System.out.println(kontakt.toString());
		
		return kontaktService.addKontakt(kontakt);
	}
	
	// get by pk
	@GetMapping("/kontakt/get/{pk}")
	public ResponseEntity<KontaktEntity> getKontaktByPk(@PathVariable String pk){
		KontaktEntity getKontakt = kontaktService.getKontaktByPk(pk);
		return new ResponseEntity<>(getKontakt, HttpStatus.OK);
	}
	
	// to update kontakt
	@PutMapping("/kontakt/update/{pk}")	
	public ResponseEntity<KontaktEntity> updateKontakt(@PathVariable String pk, @RequestBody KontaktEntity details){
		System.out.println("Kontakt edited");
		System.out.println(details.toString());
		return kontaktService.updateKontakt(pk, details);
	}
	
	@DeleteMapping("/kontakt/delete/{pk}")
	public ResponseEntity<Map<String, Boolean>> deleteKontakt(@PathVariable String pk){
		System.out.println("Person with id: " + pk +" is deleted");
		return kontaktService.deleteKontakt(pk);
	}
	
}
