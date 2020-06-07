package com.pet.ownership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pet.ownership.entity.Pets;

@Repository
public interface PetRepository extends JpaRepository<Pets, Long> {

}
