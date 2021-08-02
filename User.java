package com.lara.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@ToString
@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="first_name")
	private String firstName;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="sur_name")
	private String surName;

	@NotNull(message="is required")
	@Column(name="pin_code")
	private String pincode;

	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(name="data_of_birth")
	private String dob;

	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(name="joining_date")
	private String joiningDate;




}
