package com.pet.ownership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pet.ownership.entity.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

}
