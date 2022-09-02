package de.ems.addressbuch.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import de.ems.addressbuch.entities.AnschriftEntity;
import de.ems.addressbuch.enumerations.Enum_Anschriftentyp;
import de.ems.addressbuch.model.Anschrift;
import de.ems.addressbuch.repository.AnschriftRepository;
import de.ems.tools.AbstractPK;

@Service
public class AnschriftServiceImpl implements AnschriftService {
	
	@Autowired
	private AnschriftRepository anschriftRepository;

	private static Logger log = Logger.getLogger(AnschriftServiceImpl.class.getName());
	
	@Override
	public AnschriftEntity createAnschrift() {
		AnschriftEntity a = new AnschriftEntity();
		a.setAnschriftPK(AbstractPK.create());
		return a;
	}

	@Override
	public List<AnschriftEntity> getAlleAnschrifte() {
		return anschriftRepository.findAll();
	}

	@Override
	public AnschriftEntity addAnschrift(Anschrift anschrift) {
		if(anschrift == null) {
			log.error("Anschrift is not available");
		}else
			System.out.println(anschrift.toString());
		
		AnschriftEntity newAnschrift = createAnschrift();
		
		newAnschrift.setPerson_fk(anschrift.getPerson_fk());
		newAnschrift.setPlz(anschrift.getPlz());
		newAnschrift.setOrt(anschrift.getOrt());
		newAnschrift.setLand(anschrift.getLand());
		newAnschrift.setStrasse(anschrift.getStrasse());
		newAnschrift.setAnschriftentyp_Enum(Enum_Anschriftentyp.valueOf(anschrift.getAnschriftentyp()));
		newAnschrift.setAktiv(true);
		
		return anschriftRepository.save(newAnschrift);
	}

	@Override
	public AnschriftEntity getAnschriftByPk(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AnschriftEntity> updateAnschrift(String pk, AnschriftEntity anschriftDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> deleteAnschrift(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

}
