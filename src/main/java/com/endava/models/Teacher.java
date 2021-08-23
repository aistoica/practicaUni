package com.endava.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Teacher {

	private String firstName;
	private String lastName;
	private String birthDate;
	private String cnp;
	private String salary;
	private String employmentDate;

}
