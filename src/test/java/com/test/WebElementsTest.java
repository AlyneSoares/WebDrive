package com.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WebElementsTest {
	public WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://treinoautomacao.hol.es/elementsweb.html");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testValidaTextField() throws InterruptedException {
		WebElement txtNome = driver.findElement(By.name("txtbox1"));
		assertTrue(txtNome.isEnabled());
		txtNome.sendKeys("Alyne");
		Thread.sleep(5000);
		assertEquals("Alyne", 
				txtNome.getAttribute("value"));
	}

	@Test
	public void ValidaTestFieldDesabilitado()  {
		WebElement disabledField = driver.findElement(By.name("txtbox2"));
		assertFalse(disabledField.isEnabled());
	}

	@Test
	public void radioButton() {
		List<WebElement> elementsRadio = driver.findElements(By.name("radioGroup1"));
		for(WebElement e : elementsRadio) {
			System.out.println(e.getAttribute("value").toString());
			assertFalse(e.isSelected());

			if(e.getAttribute("value").equals("Radio Button 3 selecionado")) {
				e.click();
				System.out.println("Está selecionado");
				assertTrue("Deveria estar selecionado", e.isSelected());
				//break;
			}
		}
	}

	@Test
	public void chkBox() {
		List<WebElement> elementsChkbox = driver.findElements(By.name("chkbox"));
		WebElement check3 = elementsChkbox.get(2);
		check3.click();
		assertTrue("Está selecionado", check3.isSelected());
		WebElement check4 = elementsChkbox.get(3);
		check4.click();
		assertTrue("Está selecionado", check4.isSelected());
			if(check4.isSelected() && check3.isSelected()) {
				System.out.println("deu certo");
			}
		
//		for (WebElement c: elementsChkbox) {
//			
//		}
//		
		
		}
	
	@Test
	public void dropDownListSingle() {
		WebElement dropdownlist = driver.findElement(By.name("dropdownlist"));
		Select listBoxElements = new Select(dropdownlist);
		
		listBoxElements.selectByIndex(6);
		
		assertEquals("Item 7",listBoxElements.getFirstSelectedOption().getText()); 
	}
	
	@Test
	public void dropdownMultiSelect() {
		WebElement dropMultiple = driver.findElement(By.name("multiselectdropdown"));
		Select listboxMultiples = new Select(dropMultiple);
		
		listboxMultiples.selectByIndex(4);
		listboxMultiples.selectByIndex(6);
		listboxMultiples.selectByIndex(7);
		
		List<WebElement> allSelectedOptions = listboxMultiples.getAllSelectedOptions();
		
		for (WebElement e : allSelectedOptions) {
			System.out.println(e.getText());
		}
	}
	
	@Test
	public void iFrame() {
		driver.switchTo().frame("iframe_b");
		WebElement searchField = driver.findElement(By.id("s"));
		searchField.sendKeys("Alyne");
		assertEquals("Alyne", searchField.getAttribute("value"));
		driver.switchTo().defaultContent();
		driver.switchTo().frame("iframe_d");
		WebElement searchFieldSelenium = driver.findElement(By.id("q"));
		searchFieldSelenium.sendKeys("Alyne");
		assertEquals("Alyne", searchFieldSelenium.getAttribute("value"));
	}
	
	@Test
	public void testAlert() {
		//Alert
		WebElement btnAlert = driver.findElement(By.name("alertbtn"));
		btnAlert.click();
		//cria o alerta
		Alert myAlert = driver.switchTo().alert();
		System.out.println(myAlert.getText());
		assertEquals("Eu sou um alerta!", myAlert.getText());
		myAlert.accept();
	}

	
}
