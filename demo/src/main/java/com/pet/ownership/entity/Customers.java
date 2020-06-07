package com.pet.ownership.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customers")
@Table(name = "customers")
public class Customers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5076233606410952576L;
	@Id
	private Long id;
	@JsonProperty(value = "first_name")
	private String firstName;
	@JsonProperty(value = "last_name")
	private String lastName;
	@JsonProperty(value = "email")
	private String email;
	@JsonProperty(value = "gender")
	private String gender;

}
