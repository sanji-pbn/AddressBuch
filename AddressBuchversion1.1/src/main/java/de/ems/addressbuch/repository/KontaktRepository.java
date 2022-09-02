package de.ems.addressbuch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ems.addressbuch.entities.KontaktEntity;

public interface KontaktRepository extends JpaRepository<KontaktEntity, String>{
	

}
