import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
	
	@Test
	public void deveInteragirComAlertSimples() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("alert")).click();
		driver.switchTo().alert();
	}

}
