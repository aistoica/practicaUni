package com.endava.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.endava.env.EnvReader;
import com.endava.models.Teacher;
import com.endava.pageObjects.AddTeacherPopUp;
import com.endava.pageObjects.TeachersPage;
import com.endava.testData.TestDataGenerator;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {

	private WebDriver webDriver;
	private TestDataGenerator testDataGenerator = new TestDataGenerator();

	@BeforeEach
	public void setUpWebDriver() {
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		webDriver.get( EnvReader.getUrl() );
		webDriver.manage().window().fullscreen();
	}

	@AfterEach
	public void closeWebDriver() throws InterruptedException {
		Thread.sleep( 5000 );
		webDriver.close();
	}

	@Test
	public void shouldAddTeacher() {
		//GIVEN
		TeachersPage teachersPage = new TeachersPage( webDriver );
		AddTeacherPopUp addTeacherPopUp = teachersPage.goToAddTeacherScreen();

		Teacher teacher = testDataGenerator.getTeacher();

		//WHEN
		addTeacherPopUp.addTeacher( teacher );

		//THEN
		assertThat("Added teacher is not in UI list", teachersPage.hasTeacherInList( teacher ), is(true) );
	}

	@Test
	public void shouldFailToAddTeacherGivenCNPLowerThan13() {
		//GIVEN
		TeachersPage teachersPage = new TeachersPage( webDriver );
		AddTeacherPopUp addTeacherPopUp = teachersPage.goToAddTeacherScreen();

		Teacher teacher = testDataGenerator.getTeacher();
		teacher.setCnp( testDataGenerator.getNumber( 1, 12 ) );

		//WHEN
		addTeacherPopUp.addTeacher( teacher );

		//THEN
		assertThat( addTeacherPopUp.isPopUpPresent(), is( true ) );
		assertThat( addTeacherPopUp.getCnpError(), is("Cnp must be numeric and of size 13.") );
	}

	@Test
	public void shouldFailToAddTeacherGivenCNPHigherThan13() {
		//GIVEN
		TeachersPage teachersPage = new TeachersPage( webDriver );
		AddTeacherPopUp addTeacherPopUp = teachersPage.goToAddTeacherScreen();

		Teacher teacher = testDataGenerator.getTeacher();
		teacher.setCnp( testDataGenerator.getNumber( 14, 100 ) );

		//WHEN
		addTeacherPopUp.addTeacher( teacher );

		//THEN
		assertThat( addTeacherPopUp.isPopUpPresent(), is( true ) );
		assertThat( addTeacherPopUp.getCnpError(), is("Cnp must be numeric and of size 13.") );
	}

	@Test
	public void shouldEditTeacher() {
		//GIVEN
		TeachersPage teachersPage = new TeachersPage( webDriver );
		AddTeacherPopUp addTeacherPopUp = teachersPage.goToAddTeacherScreen();

		Teacher teacher = testDataGenerator.getTeacher();
		addTeacherPopUp.addTeacher( teacher );

		//WHEN
		addTeacherPopUp = teachersPage.editTeacher( teacher );
		teacher = testDataGenerator.getTeacher();
		addTeacherPopUp.addTeacher( teacher );

		//THEN
		assertThat("Added teacher is not in UI list", teachersPage.hasTeacherInList( teacher ), is(true) );
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
