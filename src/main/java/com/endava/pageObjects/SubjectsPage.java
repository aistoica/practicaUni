package com.endava.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.endava.models.Subject;
import com.endava.models.Teacher;

import java.util.List;

public class SubjectsPage extends BasePage {

	private By addSubjectButton = By.cssSelector( "div.add-subject-container button" );
	private By tableRows = By.cssSelector( "table tbody tr" );

	public SubjectsPage( WebDriver driver ) {
		super( driver );
	}

	public AddSubjectPopUp goToAddSubjectScreen() {
		findElement( addSubjectButton ).click();
		return new AddSubjectPopUp(driver);
	}

	public boolean hasSubjectInList( Subject subject ) {
		List<WebElement> subjects = findElements( tableRows );
		return subjects.stream().peek( t -> System.out.println(t.getText()) )
				.anyMatch( row -> row.findElement( By.cssSelector( "td.mat-column-name" ) ).getText().trim().equals( subject.getName() ) &&
						row.findElement( By.cssSelector( "td.mat-column-creditPoints" ) ).getText().trim().equals( subject.getCreditPoints() ) &&
						row.findElement( By.cssSelector( "td.mat-column-isOptional" ) ).getText().trim().equals( String.valueOf( subject.isOptional() ) ) &&
						row.findElement( By.cssSelector( "td.mat-column-coursePercent" ) ).getText().trim().equals( String.valueOf( subject.getCoursePercent() ) ) &&
						row.findElement( By.cssSelector( "td.mat-column-seminaryPercent " ) ).getText().trim().equals( String.valueOf( subject.getSeminaryPercent() ) ));
	}
}
