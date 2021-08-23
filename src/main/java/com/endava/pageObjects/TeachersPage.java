package com.endava.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.endava.models.Teacher;

import java.util.List;

public class TeachersPage extends BasePage {

	private By addTeacherButton = By.cssSelector("div.add-teacher-container button");
	private By teacherList = By.cssSelector( "app-teacher .teacher-details" );

	public TeachersPage( WebDriver driver ) {
		super(driver);
	}

	public AddTeacherPopUp goToAddTeacherScreen() {
		findElement( addTeacherButton ).click();
		return new AddTeacherPopUp( driver );
	}

	public boolean hasTeacherInList( Teacher teacher ) {
		List<WebElement> teachers = findElements( teacherList );
		return teachers.stream().peek( t -> System.out.println(t.getText()) ).anyMatch( t -> t.getText().trim().equals( teacher.getFirstName() + " " + teacher.getLastName() ) );
	}
}
