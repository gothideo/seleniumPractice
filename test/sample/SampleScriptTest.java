package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SampleScriptTest {

	WebDriver driver;
	EventFiringWebDriver eventDriver;
	String currentUrl;
	Set<String> originalHandles;
	String originalHandle;
//	WebDriverWait wait;
	@Before
	public void setUp() {
		System.setProperty("webdriver.ie.driver", "C:\\Program Files\\Selenium\\IEDriverServer.exe");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("requireWindowFocus", true);


		driver = new InternetExplorerDriver(capability);

		//イベントリスナー
//		eventDriver = new EventFiringWebDriver(driver);
//		ExEventListener eventListener = new ExEventListener();
//
//		eventDriver.register(eventListener);
//
//		eventDriver.get("http://example.selenium.jp/reserveApp");

		driver.get("https://web-designer.cman.jp/javascript_ref/window/open/");

	}

//	@Test
//	public void displayName() {
//		assertThat(driver.getTitle(), is("予約情報入力"));
//	}
//
//	@Test
//	public void checkBoxTest() {
//		assertThat(driver.getTitle(), is("予約情報入力"));
//	}

//	@Test
//	public void keyDown_buttonClick() {
//		WebElement year = eventDriver.findElement(By.name("reserve_y"));
//		year.sendKeys(Keys.chord(Keys.CONTROL,"c"));
//		eventDriver.findElement(By.id("goto_next")).click();
//		eventDriver.findElement(By.id("guestname")).sendKeys("サンプルユーザ");
//		eventDriver.findElement(By.name("gname")).clear();
//		eventDriver.findElement(By.name("gname")).sendKeys("テストユーザ",Keys.BACK_SPACE);
//		eventDriver.findElement(By.name("gname")).sendKeys(Keys.chord(Keys.CONTROL,"v"));
//		eventDriver.findElement(By.id("goto_next")).click();
//	}

	@Test
	public void screenTransition() {

		currentUrl = driver.getCurrentUrl();
		originalHandle = driver.getWindowHandle();
		originalHandles = driver.getWindowHandles();

		//waitの実験
		Wait<WebDriver> wait = new WebDriverWait(driver,600);
		ExpectedCondition<Boolean> urlCheck = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				String tmpUrl = driver.getCurrentUrl();
				return  !currentUrl.equals(tmpUrl);
			}
		};


		((JavascriptExecutor) driver).executeScript("var element = document.createElement('script');document.body.appendChild(element);");
		while(true) {
		    Set<String> tmpHandles = driver.getWindowHandles();
			if(originalHandles.size() != tmpHandles.size()) {
				List<String> handles = new ArrayList<String>(tmpHandles);
				handles.removeAll(originalHandles);

				if (handles.size() == 1) {
					driver.switchTo().window(handles.get(0));
				}


//				Iterator<String> itr = tmpHandles.iterator();
//				while(itr.hasNext()) {
//					if (!itr.next().equals(originalHandle)) {
//						String tmpHandle = itr.next();
//						driver.switchTo().window(tmpHandle);
//					}
//				}

			}

			if(wait.until(urlCheck)) {
				((JavascriptExecutor) driver).executeScript("var element = document.createElement('script');document.body.appendChild(element);");
				currentUrl = driver.getCurrentUrl();
				continue;
			}

		}
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
