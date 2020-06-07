package com.pet.ownership.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -903141587074895102L;
	private Long customerId;
	private String customerName;
	private String animalNames;
	private String gender;

}
