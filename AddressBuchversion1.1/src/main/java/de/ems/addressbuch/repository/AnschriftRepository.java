package de.ems.addressbuch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ems.addressbuch.entities.AnschriftEntity;

public interface AnschriftRepository extends JpaRepository<AnschriftEntity, String> {

}
