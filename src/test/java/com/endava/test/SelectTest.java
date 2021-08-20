package com.endava.test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectTest {

	@Test
	public void selectTest() {
		System.setProperty( "webdriver.chrome.driver", "C:\\tools\\chromedriver.exe" );
		WebDriver webDriver = new ChromeDriver();

		webDriver.get( "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html" );
		webDriver.manage().window().fullscreen();

		WebElement selectWebElement = webDriver.findElement( By.cssSelector( "#select-demo" ) );
		Select selectDropDown = new Select( selectWebElement );
		selectDropDown.selectByValue( "Monday" );

		WebElement textField = webDriver.findElement( By.cssSelector( ".selected-value" ) );
		assertThat(textField.getText(), containsString("Monday"));

		webDriver.close();
	}

	@Test
	public void multiSelectTest() throws InterruptedException {
		System.setProperty( "webdriver.chrome.driver", "C:\\tools\\chromedriver.exe" );
		WebDriver webDriver = new ChromeDriver();

		webDriver.get( "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html" );
		webDriver.manage().window().fullscreen();

		WebElement multiSelectElement = webDriver.findElement( By.cssSelector( "#multi-select" ) );
		Select select = new Select( multiSelectElement );
		select.selectByValue( "New York" );
		select.selectByValue( "Texas" );
		Thread.sleep( 5000 );
		select.deselectAll();
		select.selectByValue( "Ohio" );
		select.selectByValue( "Texas" );

		webDriver.findElement( By.cssSelector( "#printAll" ) ).click();

		WebElement textField = webDriver.findElement( By.cssSelector( ".getall-selected" ) );
		assertThat( textField.getText(), both( containsString( "Ohio" ) ).and( containsString( "Texas" ) ) );

		webDriver.close();
	}
}
