import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
	@Test
	public void testeTextField() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		// System.getProperty("user.dir") -> Pega o caminho de execucao do java ( diretorio raiz )
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");		
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComTextArea() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste\n\naksdjlasj\nultima linha");
		//Assert.assertEquals("teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioCheckBox() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComCombo() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
//		combo.selectByIndex(3);
		combo.selectByVisibleText("2o grau completo");
		
		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
		
		driver.quit();
		
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for (WebElement option : options) {
			if (option.getText().contentEquals("Mestrado")) {
				encontrou = true;
				break;
			}
			
		}
		Assert.assertTrue(encontrou);
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
		
		driver.quit();
		
	}
	
	@Test
	public void deveInteragirComBotoes() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
		driver.quit();
		
	}
	
	@Test
	//@Ignore
	public void deveInteragirComLinks() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.linkText("Voltar")).click();
		//Assert.fail();
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
		
		driver.quit();
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
		
		driver.quit();
	}
}
