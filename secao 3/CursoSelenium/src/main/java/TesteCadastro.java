import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	
	@Test
	public void deveBuscarTextosNaPagina() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Lucas");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Silva");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("2o grau completo");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Futebol");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertEquals("Cadastrado!", driver.findElement(By.id("resultado")).getText());
		Assert.assertEquals("Lucas", driver.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Silva", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Pizza", driver.findElement(By.id("descComida")).getText());
		
		
		
	}
}
