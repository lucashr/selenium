import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
	@Test
	public void teste() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//driver.manage().window().setSize(new Dimension(300, 300)); // Redimensiona o tamanho do browser
		driver.manage().window().maximize(); // Executa em tela cheia
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();
	}
	
}
