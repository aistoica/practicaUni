package com.endava.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@Builder
public class Subject {

	private String name;
	private String creditPoints;
	private String coursePercent;
	private String seminaryPercent;
	private boolean optional;
}
