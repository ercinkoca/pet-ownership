package com.pet.ownership.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pets")
@Table(name = "pets")
public class Pets implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6307114657744425574L;
	
	@Id
	private Long id;
	@JsonProperty(value = "animal_name")
	private String animalName;

}
