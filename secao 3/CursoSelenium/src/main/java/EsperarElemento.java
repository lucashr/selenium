import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EsperarElemento {

	private static WebDriver driver;
	private static String caso;
	private static int nErro;
	private static String cCaso;
	private static Object cPath;

	static void TestarElemento(By by) {
		Pausar(1000);

		boolean xLoop = true;
		boolean xEncontrou = false;
		int xInt = 0;

		while (xLoop) {
			xEncontrou = driver.findElement(by).isDisplayed();

			if  (!xEncontrou) {
				Pausar(2000);

				xInt++;

				EscreveLog(by.toString() + " : " + xInt);

				driver.switchTo().frame("ifrm");
				//driver.switchTo().window("ifrm");
				//driver.switchTo().frame(null);
				//driver.switchTo().window(null);

				if (xInt > 15) {

					caso = "Testar Elemento";

					nErro++;

					GravaTelaEspecial(cPath,"Elemento não encontrado: " + by.toString());

					EscreveLog("Elemento não encontrado: " + by.toString());

					xLoop = false;

					EscreveLog("Fechar Janela por erro de execução");

					driver.quit();

					FechaPlano();

					cCaso = "XXXXXX";

					System.exit(1);
				}
			}
			else {
				xLoop = false;
			}
		}
	}

	static void EsperarElemento(By by, String xTexto) {
		boolean xExiste = false; 

		boolean xLoop = true;
		int xInt = 0;

		caso = "Esperar Elemento " + by.toString() + "  (" + xTexto + ")";
		EscreveLog(caso);

		while (xLoop) {

			Pausar(1000);

			xExiste = driver.findElement(by).isDisplayed();

			if (!xExiste) {
				xInt ++;

				EscreveLog(by.toString() + " : " + xInt);

				driver.switchTo().frame("ifrm");
				//driver.switchTo().window("ifrm");
				//driver.switchTo().frame(null);
				//driver.switchTo().window(null);

				if (xInt > 10) {
					nErro++;

					GravaTelaEspecial(cPath,"Elemento não encontrado: " + by.toString()  + "  ( " + xTexto + " )");

					EscreveLog("Elemento não encontrado: " + by.toString()  + "  ( " + xTexto + " )");

					xLoop = false;

					EscreveLog("Fechar Janela por erro de execução");

					driver.quit();

					FechaPlano();

					cCaso = "XXXXXX";

					System.exit(1);
				}
			}
			else {
				if (driver.findElement(by).isEnabled() && driver.findElement(by).getText().trim().equalsIgnoreCase(xTexto)) {
					xLoop = false;
				}
				else {
					xInt ++;

					if (xInt > 10) {
						nErro++;

						GravaTelaEspecial(cPath,"Elemento não encontrado: " + by.toString()  + "  ( " + xTexto + " )  Texto encontrado  ( " + driver.findElement(by).getText() + " )");

						EscreveLog("Elemento não encontrado: " + by.toString()  + "  ( " + xTexto + " )  Texto encontrado  ( " + driver.findElement(by).getText() + " )");

						xLoop = false;

						EscreveLog("Fechar Janela por erro de execução");

						driver.quit();

						FechaPlano();

						cCaso = "XXXXXX";

						System.exit(1);
					}
				}
			}
		}
	}

	static void EsperarElementoHabilidado(By by) {
		boolean xExiste = false; 

		boolean xLoop = true;
		int xInt = 0;

		caso = "Esperar Elemento habilidado " + by.toString();
		EscreveLog(caso);

		while (xLoop) {

			Pausar(1000);

			xExiste = driver.findElement(by).isEnabled();

			if (!xExiste) {
				xInt ++;

				EscreveLog(by.toString() + " : " + xInt);

				driver.switchTo().frame("ifrm");

				if (xInt > 10) {
					nErro++;

					GravaTelaEspecial(cPath,"Elemento não encontrado: " + by.toString());

					EscreveLog("Elemento não encontrado: " + by.toString());

					xLoop = false;

					EscreveLog("Fechar Janela por erro de execução");

					driver.quit();

					FechaPlano();

					cCaso = "XXXXXX";

					System.exit(1);
				}
			}
			else {
				xLoop = false;
			}
		}
	}

	/*
	 * Aguarda o elemento ser exibido (wait dinâmico). Quando o mesmo estiver
	 * visível, o teste irá prosseguir. O tempo limite é de 30 segundos.
	 */
	public void aguardarPorElemento(By by) throws Exception {
		long tempoEspera = 30;
		try {
			WebDriverWait wait = new WebDriverWait(driver, tempoEspera);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			logErro("Timeout por aguardar pela presença do elemento " + by + " por " + tempoEspera + " segundos", e);
		}
	}

	// Altera a janela corrente do teste pelo título da página passado por
	// parâmetro.
	public void alterarJanelaPorTituloPagina(String titulo) throws Exception {
		try {
			for (String winHandle : driver.getWindowHandles()) {
				if (driver.switchTo().window(winHandle).getTitle().equals(titulo)) {
					break;
				}
			}

			pausar(2000);
			logSucesso("Altera o foco para janela com o título " + titulo);
		} catch (Exception e) {
			logErro("Não foi possível alterar o foco para a janela com o título " + titulo, e);
		}
	}

	// Altera a janela corrente do teste pela URL da página passada por
	// parâmetro.
	public void alterarJanelaPorURL(String url) throws Exception {
		try {
			for (String winHandle : driver.getWindowHandles()) {
				if (driver.switchTo().window(winHandle).getCurrentUrl().equals(url)) {
					break;
				}
			}

			pausar(2000);
			logSucesso("Altera o foco para janela com a URL " + url);
		} catch (Exception e) {
			logErro("Não foi possível alterar o foco para janela com a URL " + url, e);
		}
	}

	// Altera o foco para o frame 'ifrm'
	public void alterarFocoFrame() throws Exception {
		String frame = "ifrm";
		int frameZero = 0;

		try {
			driver.switchTo().frame(frame);
			logSucesso("Altera o foco para o frame " + frame);
		} catch (NoSuchElementException e) {
			driver.switchTo().frame(frameZero);
			logErro("Não foi possível alterar o foco para o frame " + frame + ",o foco foi alterado para o frame "
					+ frameZero, e);
		}
	}

	// Store the current window handle
	String winHandleBefores = driver.getWindowHandle();

	String JanelaBase = winHandleBefores;

	// Perform the click operation that opens new window

	// Switch to new window opened
	//	for(String winHandle : driver.getWindowHandles()){
	//	driver.switchTo().window(winHandle);
	//	}

	// Perform the actions on new window

	// Seleciona opção em combobox pelo texto passado por parâmetro.
	public void selecionarElementoPorTexto(By by, String texto, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(by);
			new Select(driver.findElement(by)).selectByVisibleText(texto);

			logSucesso("Seleciona o texto " + texto + " no elemento " + nomeElemento);
		} catch (Exception e) {
			logErro("Não foi possível selecionar o texto " + texto + " no elemento " + by, e);
		}
	}

	// Seleciona opção em combobox pela propriedade value passada por parâmetro.
	public void selecionarElementoPorValue(By by, String valor, String nomeElemento) throws Exception {
		try {
			aguardarPorElemento(by);
			new Select(driver.findElement(by)).selectByValue(valor);

			logSucesso("Seleciona o value " + valor + " no elemento " + nomeElemento);
		} catch (Exception e) {
			logErro("Não foi possível selecionar o value " + valor + " no elemento " + by, e);
		}
	}

	private void logErro(String string, Exception e) {
		// TODO Auto-generated method stub
	}

	private void logSucesso(String string) {
		// TODO Auto-generated method stub

	}

	private void pausar(int i) {
		// TODO Auto-generated method stub

	}

	private static void GravaTelaEspecial(Object cPath2, String string) {
		// TODO Auto-generated method stub

	}

	private static void FechaPlano() {
		// TODO Auto-generated method stub

	}

	private static void EscreveLog(String string) {
		// TODO Auto-generated method stub

	}

	private static void Pausar(int i) {
		// TODO Auto-generated method stub

	}

}
