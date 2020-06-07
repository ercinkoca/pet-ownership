package com.pet.ownership.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.ownership.entity.Customers;
import com.pet.ownership.entity.Pets;
import com.pet.ownership.model.ApiResponse;
import com.pet.ownership.repository.CustomerRepository;
import com.pet.ownership.repository.PetRepository;
import com.pet.ownership.service.PetOwnershipService;

@Service
public class PetOwnershipServiceImpl implements PetOwnershipService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private PetRepository petRepo;
	
	private Random rnd = new Random();
	
	@Override
	public List<ApiResponse> match() {
		List<Customers> customerList = customerRepo.findAll();
		List<Pets> petList = petRepo.findAll();
		return makeMatch(customerList, petList);
	}
	
	
	private List<ApiResponse> makeMatch(List<Customers> customerList, List<Pets> petList) {
		List<ApiResponse> responseList = new ArrayList<>();
		HashMap<Long, String> matchMap = matchFirstAllCustomers(customerList, petList);
		for (Customers c: customerList) {
			ApiResponse response = new ApiResponse();
			response.setCustomerId(c.getId());
			response.setCustomerName(c.getFirstName() + " "+ c.getLastName());
			response.setAnimalNames(matchMap.get(c.getId()));
			response.setGender(c.getGender());
			responseList.add(response);
		}
		return responseList;
	}
	
	private HashMap<Long, String> matchFirstAllCustomers(List<Customers> customerList,List<Pets> petList){
		HashMap<Long, String> matchMap = new HashMap<>();
		for(Customers c: customerList) {
			if(c.getId() <= petList.size()){
				matchMap.put(c.getId(), petList.get(c.getId().intValue()-1).getAnimalName()); // all customers matches a pet until pet size.
			}else {
				matchMap.put(c.getId(), petList.get(rnd.nextInt(petList.size())).getAnimalName()); // rest of customers matches a pet with random on pet list.
			}
		}
		return matchSecondOnlyFemale(customerList, petList, matchMap);
	}
	
	
	private HashMap<Long, String> matchSecondOnlyFemale(List<Customers> customerList,List<Pets> petList,HashMap<Long, String> matchMap){
		Set<String> strList = null; // So that the same animal name is not added.
		List<String> strArrList = null;
		for(Customers c: customerList) {
			strList = new HashSet<>();
			strArrList = new ArrayList<>();
			if(c.getGender().equals("Female")) { // Female customers can match 3 pets.
				strList.add(matchMap.get(c.getId()));
				strList.add(petList.get(rnd.nextInt(petList.size())).getAnimalName()); // They can get animals with random index on pet list.
				strList.add(petList.get(rnd.nextInt(petList.size())).getAnimalName());
				strArrList.addAll(strList);
				matchMap.put(c.getId(), addStrings(strArrList));
			}
		}
		return matchMap;
	}
	
	
	private String addStrings(List<String> strList) { // Animal names are adding with '---'
		StringBuilder result = new StringBuilder("");
		for(int i = 0; i<strList.size();i++) {
			result.append(strList.get(i));
			if(i!=strList.size()-1) {
				result.append(" --- ");
			}
		}
		return result.toString();
	}
	
	
	
}
