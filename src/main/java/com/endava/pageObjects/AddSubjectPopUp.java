package com.endava.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.endava.models.Subject;

public class AddSubjectPopUp extends BasePage {

	private By nameField = By.cssSelector( "app-add-edit-subject input[name='name']" );
	private By creditPointsField = By.cssSelector( "app-add-edit-subject input[name='creditPoints']" );
	private By coursePercentField = By.cssSelector( "app-add-edit-subject input[name='coursePercent']" );
	private By seminaryPercentField = By.cssSelector( "app-add-edit-subject input[name='seminaryPercent']" );
	private By optionalField = By.cssSelector( "app-add-edit-subject input[name='optional']" );
	private By optionToggle = By.cssSelector( "div .mat-slide-toggle-thumb" );
	private By submitButton = By.cssSelector( "app-add-edit-subject mat-card-footer button[type='submit']" );
	private By closeButton = By.cssSelector( "app-add-edit-subject mat-card-footer button[type='button']" );

	private By popUp = By.cssSelector( "app-add-edit-subject" );
	private By errorLabel = By.xpath( "./ancestor::mat-form-field//mat-error" );
	private By errors = By.cssSelector( "input.ng-invalid" );

	public AddSubjectPopUp( WebDriver driver ) {
		super( driver );
	}

	public void addSubject( Subject subject, boolean shouldSave ) {

		if ( subject.isOptional() != findElement( optionalField ).isSelected() ) {
			findElement( optionToggle ).click();
		}
		findElement( nameField ).clear();
		findElement( nameField ).sendKeys( subject.getName() );
		findElement( creditPointsField ).clear();
		findElement( creditPointsField ).sendKeys( subject.getCreditPoints() );
		findElement( coursePercentField ).clear();
		findElement( coursePercentField ).sendKeys( subject.getCoursePercent() );
		findElement( seminaryPercentField ).clear();
		findElement( seminaryPercentField ).sendKeys( subject.getSeminaryPercent() );

		if ( shouldSave ) {
			findElement( submitButton ).click();
		} else {
			findElement( closeButton ).click();
		}

		if ( findElements( errors ).size() == 0 ) {
			WebDriverWait wait = new WebDriverWait( driver, 2 );
			wait.until( ExpectedConditions.invisibilityOfElementLocated( popUp ) );
		}
	}
}
