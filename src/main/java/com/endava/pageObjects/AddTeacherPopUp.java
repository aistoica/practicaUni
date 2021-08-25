package com.endava.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.endava.models.Teacher;

public class AddTeacherPopUp extends BasePage {

	private By firstNameField = By.cssSelector( "app-add-edit-teacher input[name='firstName']" );
	private By lastNameField = By.cssSelector( "app-add-edit-teacher input[name='lastName']" );
	private By birthDateField = By.cssSelector( "app-add-edit-teacher input[name='birthDate']" );
	private By cnpField = By.cssSelector( "app-add-edit-teacher input[name='cnp']" );
	private By salaryField = By.cssSelector( "app-add-edit-teacher input[name='salary']" );
	private By employmentDateField = By.cssSelector( "app-add-edit-teacher input[name='employmentDate']" );
	private By submitButton = By.cssSelector( "app-add-edit-teacher mat-card-footer button[type='submit']" );
	private By closeButton = By.cssSelector( "app-add-edit-teacher mat-card-footer button[type='button']" );

	private By popUp = By.cssSelector( "app-add-edit-teacher" );
	private By errorLabel = By.xpath( "./ancestor::mat-form-field//mat-error" );
	private By errors = By.cssSelector( "input.ng-invalid" );

	public AddTeacherPopUp( WebDriver driver ) {
		super( driver );
	}

	public void addTeacher( Teacher teacher ) {
		addTeacher( teacher, true );
	}

	public void addTeacher( Teacher teacher, boolean shouldSave ) {
		findElement( firstNameField ).clear();
		findElement( firstNameField ).sendKeys( teacher.getFirstName() );
		findElement( lastNameField ).clear();
		findElement( lastNameField ).sendKeys( teacher.getLastName() );
		findElement( birthDateField ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		findElement( birthDateField ).sendKeys( teacher.getBirthDate() );
		findElement( cnpField ).clear();
		findElement( cnpField ).sendKeys( teacher.getCnp() );
		findElement( salaryField ).clear();
		findElement( salaryField ).sendKeys( teacher.getSalary().toString() );
		findElement( employmentDateField ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		findElement( employmentDateField ).sendKeys( teacher.getEmploymentDate() );

		if ( shouldSave ) {
			findElement( submitButton ).submit();
		} else {
			findElement( closeButton ).click();
		}
		if(findElements( errors ).size() == 0) {
			WebDriverWait wait = new WebDriverWait( driver, 2 );
			wait.until( ExpectedConditions.invisibilityOfElementLocated( popUp ) );
		}

	}

	public boolean isPopUpPresent() {
		try {
			return findElement( popUp ).isDisplayed();
		} catch ( TimeoutException e ) {
			return false;
		}

	}

	public String getCnpError() {
		return findElement( cnpField ).findElement( errorLabel ).getText();
	}

	public String getFirstNameColor() {
		return findElement( firstNameField ).getCssValue( "caret-color" );
	}
}
