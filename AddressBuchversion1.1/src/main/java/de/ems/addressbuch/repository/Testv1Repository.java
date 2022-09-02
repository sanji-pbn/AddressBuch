package de.ems.addressbuch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ems.addressbuch.entities.PersonEntity;
import de.ems.addressbuch.entities.Testv1Entity;

public interface Testv1Repository extends JpaRepository<Testv1Entity, Long> {

}
