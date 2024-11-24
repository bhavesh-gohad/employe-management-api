package com.employee.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee 
{
	@Id
	@Column(nullable = false)
	Long emp_id;
	
	@Column(nullable = false)
	String fname;
	
	@Column(nullable = false)
	String lname;
	
	@Column(nullable = false)
	private String mob_no;
}
