package de.ems.addressbuch.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import de.ems.addressbuch.entities.PersonEntity;
import de.ems.addressbuch.entities.Testv1Entity;
import de.ems.addressbuch.exception.ResourceNotFoundException;
import de.ems.addressbuch.repository.PersonenRepository;
import de.ems.addressbuch.repository.Testv1Repository;
import de.ems.tools.AbstractPK;

@Service
public class PersonenServiceImpl implements PersonenService {

	@Autowired
	private PersonenRepository personenRepository;

	@Autowired
	private Testv1Repository testv1Repository;

	private static Logger log = Logger.getLogger(PersonenServiceImpl.class.getName());

	@Override
	public PersonEntity createPerson() {
		PersonEntity p = new PersonEntity();
		p.setPk(AbstractPK.create());
		return p;
	}

	/*
	 * @Override public PersonEntity modelPerson(PersonEntity person) { PersonEntity
	 * newPerson = createPerson();
	 * 
	 * newPerson.setVorname(person.getVorname());
	 * newPerson.setNachName(person.getNachName());
	 * 
	 * return newPerson; }
	 */

	@Override
	public PersonEntity addPerson(PersonEntity person) {

		if (person == null) {
			log.error("Person ist null");
		} else
			System.out.println(person.toString());

		PersonEntity newPerson = createPerson();

		newPerson.setVorname(person.getVorname());
		newPerson.setNachName(person.getNachName());
		
		return personenRepository.save(newPerson);
	}
	

	@Override
	public List<PersonEntity> getAllPersonen() {
		return personenRepository.findAll();
	}

	@Override
	public PersonEntity getPersonByPk(String pk) {
		return personenRepository.findById(pk).orElseThrow(() -> new ResourceNotFoundException("Person not exist with pk: " + pk));
	}

	@Override
	public ResponseEntity<PersonEntity> updatePerson(String pk, PersonEntity personDetails) {
		PersonEntity suchtPerson = personenRepository.findById(pk).orElseThrow(() -> new ResourceNotFoundException("Person not exist with pk: " + pk));
		
		suchtPerson.setVorname(personDetails.getVorname());
		suchtPerson.setNachName(personDetails.getNachName());
		
		PersonEntity updatedPerson = personenRepository.save(suchtPerson);
		
		return ResponseEntity.ok(updatedPerson);
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> deletePerson(String pk) {
		PersonEntity personDelete = personenRepository.findById(pk).orElseThrow(() -> new ResourceNotFoundException("Person not exist with pk: " + pk));
		
		personenRepository.delete(personDelete);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	
	// test section
	@Override
	public Testv1Entity saveTest(Testv1Entity testPerson) {
		return testv1Repository.save(testPerson);
	}

	@Override
	public List<Testv1Entity> getAllPersonenFromTestv1() {
		return testv1Repository.findAll();
	}

	@Override
	public ResponseEntity<Testv1Entity> getPersonByPkTest(Long pk) {
		Testv1Entity personTest = testv1Repository.findById(pk)
				.orElseThrow(() -> new ResourceNotFoundException("Person not exist with pk: " + pk));

		return ResponseEntity.ok(personTest);
	}

	@Override
	public ResponseEntity<Testv1Entity> updatePersonTest(Long pk, Testv1Entity personDetailsTest) {
		Testv1Entity personTest = testv1Repository.findById(pk)
				.orElseThrow(() -> new ResourceNotFoundException("Person not exist with pk: " + pk));

		personTest.setVorname(personDetailsTest.getVorname());
		personTest.setNachName(personDetailsTest.getNachName());
		personTest.setEmailId(personDetailsTest.getEmailId());

		Testv1Entity updatedPersonTest = testv1Repository.save(personTest);
		return ResponseEntity.ok(updatedPersonTest);
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> deletePersonTest(Long pk) {
		Testv1Entity personTest = testv1Repository.findById(pk)
				.orElseThrow(() -> new ResourceNotFoundException("Person not exist with pk: " + pk));

		testv1Repository.delete(personTest);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}



}
