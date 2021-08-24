package com.endava.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.endava.models.Teacher;

import java.util.List;

public class TeachersPage extends BasePage {

	private By addTeacherButton = By.cssSelector("div.add-teacher-container button");
	private By teacherList = By.cssSelector( "app-teacher .teacher-details" );
	private By editButton = By.xpath( "./parent::div//mat-icon[@mattooltip='Edit teacher']" );
	private By deleteButton = By.xpath( "./parent::div//mat-icon[@mattooltip='Delete teacher']" );
	private By subjectsTab = By.cssSelector( "#mat-tab-label-0-1" );

	public TeachersPage( WebDriver driver ) {
		super(driver);
	}

	public SubjectsPage goToSubjectsPage() {
		findElement( subjectsTab ).click();
		try {
			Thread.sleep( 300 );
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
		return new SubjectsPage( driver );
	}

	public AddTeacherPopUp goToAddTeacherScreen() {
		findElement( addTeacherButton ).click();
		return new AddTeacherPopUp( driver );
	}

	public boolean hasTeacherInList( Teacher teacher ) {
		List<WebElement> teachers = findElements( teacherList );
		return teachers.stream().peek( t -> System.out.println(t.getText()) ).anyMatch( t -> t.getText().trim().equals( teacher.getFirstName() + " " + teacher.getLastName() ) );
	}

	public AddTeacherPopUp editTeacher(Teacher teacher) {
		List<WebElement> teachers = findElements( teacherList );
		teachers.stream().peek( t -> System.out.println(t.getText()) )
				.filter( t -> t.getText().trim().equals( teacher.getFirstName() + " " + teacher.getLastName() ))
				.findFirst().get().findElement( editButton ).click();
		return new AddTeacherPopUp( driver );
	}

	public void deleteTeacher(Teacher teacher) {
		List<WebElement> teachers = findElements( teacherList );
		teachers.stream().peek( t -> System.out.println(t.getText()) )
				.filter( t -> t.getText().trim().equals( teacher.getFirstName() + " " + teacher.getLastName() ))
				.findFirst().get().findElement( deleteButton ).click();
	}
}
