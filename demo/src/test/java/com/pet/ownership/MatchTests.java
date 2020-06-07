package com.pet.ownership;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pet.ownership.entity.Customers;
import com.pet.ownership.entity.Pets;
import com.pet.ownership.repository.CustomerRepository;
import com.pet.ownership.repository.PetRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class MatchTests {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private PetRepository petRepo;
	
	private List<Customers> customerList = new ArrayList<Customers>();
	private List<Pets> petList = new ArrayList<Pets>();
	private HashMap<Long, String> matchMap = new HashMap<>();
	
	@Before
	public void initial() {
		customerList = customerRepo.findAll();
		petList = petRepo.findAll();
	}
	
	@Test
	public void matchFirstAllCustomers(){
		Random rnd = new Random();
		for(Customers c: customerList) {
			if(c.getId() <= petList.size()){
				matchMap.put(c.getId(), petList.get(c.getId().intValue()-1).getAnimalName());
			}else {
				matchMap.put(c.getId(), petList.get(rnd.nextInt(petList.size())).getAnimalName());
			}
		}
		assertTrue(matchMap.get(Long.valueOf(1)).equals("Pie, indian tree"));
	}
	
	@Test
	public void matchSecondOnlyFemale() {
		Set<String> strList = null;
		List<String> strArrList = null;
		Random rnd = new Random();
		for(Customers c: customerList) {
			if(c.getId() <= petList.size()){
				matchMap.put(c.getId(), petList.get(c.getId().intValue()-1).getAnimalName());
			}else {
				matchMap.put(c.getId(), petList.get(rnd.nextInt(petList.size())).getAnimalName());
			}
		}
		
		for(Customers c: customerList) {
			strList = new HashSet<String>();
			strArrList = new ArrayList<String>();
			if(c.getGender().equals("Female")) {
				strList.add(matchMap.get(c.getId()));
				strList.add(petList.get(rnd.nextInt(petList.size())).getAnimalName());
				strList.add(petList.get(rnd.nextInt(petList.size())).getAnimalName());
				strArrList.addAll(strList);
				matchMap.put(c.getId(), addStrings(strArrList));
			}
		}
		System.out.println("İkinci---------");
		System.out.println(matchMap);
		assertTrue(matchMap.get(Long.valueOf(1)).contains("Pie, indian tree"));
	}
	
	private String addStrings(List<String> strList) {
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
