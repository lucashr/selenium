import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before // Antes de cada metodo, execute o conteudo deste metodo
	public void inicializa() {
		//System.setProperty("webdriver.gecko.driver","C:/Users/lucas.rafael/Downloads/drivers/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver","C:/Users/lucas/Downloads/drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		// System.getProperty("user.dir") -> Pega o caminho de execucao do java ( diretorio raiz )
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		//driver.quit();
	}
	
	@Test
	public void testeTextField() {		
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
		
	}
	
	@Test
	public void testTextFieldDulplo() {
		dsl.escrever("elementosForm:nome", "Lucas");
		Assert.assertEquals("Lucas", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escrever("elementosForm:nome", "Silva");
		Assert.assertEquals("Silva", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea() {		
		dsl.escrever("elementosForm:sugestoes", "teste\n\naksdjlasj\nultima linha");
		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));
		
	}
	
	@Test
	public void deveInteragirComRadioButton() {		
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
		
	}
	
	@Test
	public void deveInteragirComRadioCheckBox() {
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
		
	}
	
	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
		
	}
	
	@Test
	public void deveVerificarValoresCombo() {	
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificaOpcaoCombo("elementosForm:escolaridade", "Mestrado"));	
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		
		dsl.deselecionarCombo("elementosForm:esportes", "corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));		
	}
	
	@Test
	public void deveInteragirComBotoes() {	
		dsl.clicarBotao("buttonSimple");		
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
		
	}
	
	@Test
	//@Ignore
	public void deveInteragirComLinks() {		
		dsl.clicarLink("resultado");		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
		
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {		
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
		
	}
	
}
