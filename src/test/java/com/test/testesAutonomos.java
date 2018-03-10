package com.test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testesAutonomos {
	public WebDriver driver; 

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://treinoautomacao.hol.es/desafiosoma.html");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		WebElement termoA = driver.findElement(By.id("number1"));
		termoA.sendKeys("212");
		WebElement termoB = driver.findElement(By.id("number2"));
		termoB.sendKeys("121");
		WebElement total = driver.findElement(By.id("total"));
		WebElement sinal = driver.findElement(By.id("somar"));
		sinal.click();
		
		assertEquals("333", total.getText());
	}

}
