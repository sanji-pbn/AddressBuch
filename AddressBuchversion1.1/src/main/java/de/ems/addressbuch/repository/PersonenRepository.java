package de.ems.addressbuch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ems.addressbuch.entities.PersonEntity;

public interface PersonenRepository extends JpaRepository<PersonEntity, String> {


}
