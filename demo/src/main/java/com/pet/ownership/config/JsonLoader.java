package com.pet.ownership.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.ownership.entity.Customers;
import com.pet.ownership.entity.Pets;
import com.pet.ownership.repository.CustomerRepository;
import com.pet.ownership.repository.PetRepository;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class JsonLoader {
	
	private final CustomerRepository customerRepo;
	
	private final PetRepository petRepo; 
	
	@Value(value = "${json.customerurl}")
	private String customerJsonUrl;
	
	@Value(value = "${json.peturl}")
	private String petJsonUrl;
	
	@Bean
	public void saveCustomerJsonToDB() { // When application starts, Customer Json File has been read and save to db.
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<List<Customers>> typeReference = new TypeReference<List<Customers>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream(customerJsonUrl);
		try {
			List<Customers> customerList = objectMapper.readValue(inputStream,typeReference);
			for(Customers c : customerList) {
				Customers customerEntity = new Customers();
				customerEntity.setId(c.getId());
				customerEntity.setFirstName(c.getFirstName());
				customerEntity.setLastName(c.getLastName());
				customerEntity.setGender(c.getGender());
				customerEntity.setEmail(c.getEmail());
				customerRepo.save(customerEntity);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Bean
	public void savePetJsonToDb() { // When application starts, Pet Json file has been read and save to db.
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<List<Pets>> typeReference = new TypeReference<List<Pets>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream(petJsonUrl);
		try {
			List<Pets> petList = objectMapper.readValue(inputStream,typeReference);
			for (Pets p : petList) {
				Pets petsEntity = new Pets();
				petsEntity.setId(p.getId());
				petsEntity.setAnimalName(p.getAnimalName());
				petRepo.save(petsEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
