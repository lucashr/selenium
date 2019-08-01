import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TesteFramesEJanelas {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before // Antes de cada metodo, execute o conteudo deste metodo
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// System.getProperty("user.dir") -> Pega o caminho de execucao do java ( diretorio raiz )
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void DeveInteragirComFrames() {
		dsl.entrarframe("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoAceita();
		Assert.assertEquals("Frame OK!", msg);
		
		dsl.sairframe();
		dsl.escrever("elementosForm:nome" ,msg);
	}
	
	@Test
	public void deveInteragirComJanelas() {
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		driver.close();
		dsl.trocarJanela("");
		dsl.escrever(By.tagName("textarea"), "e agora?");
		
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		
	}
}
