package com.endava.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.endava.models.Teacher;

public class AddTeacherPopUp extends BasePage {

	private By firstNameField = By.cssSelector( "app-add-edit-teacher input[name='firstName']" );
	private By lastNameField = By.cssSelector( "app-add-edit-teacher input[name='lastName']" );
	private By birthDateField = By.cssSelector( "app-add-edit-teacher input[name='birthDate']" );
	private By cnpField = By.cssSelector( "app-add-edit-teacher input[name='cnp']" );
	private By salaryField = By.cssSelector( "app-add-edit-teacher input[name='salary']" );
	private By employmentDateField = By.cssSelector( "app-add-edit-teacher input[name='employmentDate']" );
	private By submitButton = By.cssSelector( "app-add-edit-teacher button[type='submit']" );

	private By popUp = By.cssSelector( "app-add-edit-teacher" );
	private By errorLabel = By.xpath( "./ancestor::mat-form-field//mat-error" );

	public AddTeacherPopUp( WebDriver driver ) {
		super(driver);
	}

	public void addTeacher( Teacher teacher) {
		findElement( firstNameField ).clear();
		findElement( firstNameField ).sendKeys( teacher.getFirstName() );
		findElement( lastNameField ).clear();
		findElement( lastNameField ).sendKeys( teacher.getLastName() );
		findElement( birthDateField ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		findElement( birthDateField ).sendKeys( teacher.getBirthDate() );
		findElement( cnpField ).clear();
		findElement( cnpField ).sendKeys( teacher.getCnp() );
		findElement( salaryField ).clear();
		findElement( salaryField ).sendKeys( teacher.getSalary() );
		findElement( employmentDateField ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		findElement( employmentDateField ).sendKeys( teacher.getEmploymentDate() );

		findElement( submitButton ).submit();
	}

	public boolean isPopUpPresent() {
		return findElement( popUp ).isDisplayed();
	}

	public String getCnpError() {
		return findElement( cnpField ).findElement( errorLabel ).getText();
	}
}
