package com.endava.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.NgWebDriver;

import java.util.List;

public abstract class BasePage {

	protected WebDriver driver;

	public BasePage( WebDriver driver ) {
		this.driver = driver;
	}

	public WebElement findElement( By by ) {
		WebDriverWait wait = new WebDriverWait( driver, 10 );
		WebElement element = wait.until( ExpectedConditions.visibilityOfElementLocated( by ) );
		return element;
	}

	public List<WebElement> findElements( By by ) {
		NgWebDriver ngWebDriver = new NgWebDriver( (JavascriptExecutor) driver );
		ngWebDriver.waitForAngularRequestsToFinish();

		WebDriverWait wait = new WebDriverWait( driver, 10 );
		List<WebElement> elements = wait.until( ExpectedConditions.visibilityOfAllElementsLocatedBy( by ) );
		return elements;
	}
}
