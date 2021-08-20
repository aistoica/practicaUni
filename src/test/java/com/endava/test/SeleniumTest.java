package com.endava.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {

	@Test
	public void test() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver webDriver = new ChromeDriver();

		webDriver.get( "http://localhost:4200/" );
		webDriver.manage().window().fullscreen();

		WebElement addTeacherButton = webDriver.findElement( By.cssSelector("div.add-teacher-container button") );
		addTeacherButton.click();

		WebElement firstNameField = webDriver.findElement( By.cssSelector( "app-add-edit-teacher input[name='firstName']" ) );
		firstNameField.sendKeys( "Toma" );

		WebElement lastNameField = webDriver.findElement( By.cssSelector( "app-add-edit-teacher input[name='lastName']" ) );
		lastNameField.sendKeys( "Caragiu" );

		WebElement birthDateField = webDriver.findElement( By.cssSelector( "app-add-edit-teacher input[name='birthDate']" ) );
		birthDateField.sendKeys( "7/10/1988" );

		WebElement cnpField = webDriver.findElement( By.cssSelector( "app-add-edit-teacher input[name='cnp']" ) );
		cnpField.sendKeys( "1212315444332" );

		WebElement salaryField = webDriver.findElement( By.cssSelector( "app-add-edit-teacher input[name='salary']" ) );
		salaryField.sendKeys( "130" );

		WebElement employmentDate = webDriver.findElement( By.cssSelector( "app-add-edit-teacher input[name='employmentDate']" ) );
		employmentDate.sendKeys( "08/20/2021" );

		WebElement submitButton = webDriver.findElement( By.cssSelector( "app-add-edit-teacher button[type='submit']" ) );
		submitButton.submit();

		Thread.sleep( 5000 );
		webDriver.close();
	}

	@Test
	public void testAlert() throws InterruptedException {
		System.setProperty( "webdriver.chrome.driver", "C:\\tools\\chromedriver.exe" );
		WebDriver webDriver = new ChromeDriver();

		webDriver.get( "http://localhost:4200/" );
		webDriver.manage().window().fullscreen();
		webDriver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS );

		WebElement subjectsTab = webDriver.findElement( By.cssSelector( "#mat-tab-label-0-1" ) );
		subjectsTab.click();

		WebDriverWait wait = new WebDriverWait( webDriver, 5 );
		wait.until( ExpectedConditions.elementToBeClickable( By.cssSelector( "app-subject-list tbody tr:first-child mat-icon[mattooltip='Delete subject']" ) ) );

		webDriver.findElement( By.cssSelector( "app-subject-list tbody tr:first-child mat-icon[mattooltip='Delete subject']" ) ).click();

		wait.until( ExpectedConditions.alertIsPresent() );

		Alert alert = webDriver.switchTo().alert();
		alert.accept();

		Thread.sleep( 5000 );
		webDriver.close();
	}
}
