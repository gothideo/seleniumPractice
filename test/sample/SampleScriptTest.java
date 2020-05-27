package sample;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.appium.java_client.windows.WindowsDriver;

public class SampleScriptTest {

	WebDriver driver;
	WindowsDriver winAppDriver;
	EventFiringWebDriver eventDriver;
	String currentUrl;
	Set<String> originalHandles;
	String originalHandle;
//	WebDriverWait wait;
	@Before
	public void setUp() {
		System.setProperty("webdriver.ie.driver", "C:\\Program Files\\Selenium\\IEDriverServer.exe");

		//デスクトップ操作用ドライバインスタンスを生成
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("requireWindowFocus", true);

		DesiredCapabilities winAppCapability = new DesiredCapabilities();
		winAppCapability.setCapability("app", "Root");

		try {
			winAppDriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), winAppCapability);
			WebElement calcWindow = winAppDriver.findElementByName("電卓");
			String calcTopLevelWindow = calcWindow.getAttribute("NativeWindowHandle");
			calcTopLevelWindow = Integer.toHexString(Integer.valueOf(calcTopLevelWindow));

			//アプリ操作用ドライバインスタンスを生成
			DesiredCapabilities appCapabilities = new DesiredCapabilities();
			appCapabilities.setCapability("appTopLevelWindow", calcTopLevelWindow);
			WindowsDriver calcSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), appCapabilities);


			File scrFile = ((TakesScreenshot) calcSession).getScreenshotAs(OutputType.FILE);


		} catch (MalformedURLException MUE){
			System.out.println(MUE);
		} catch (IOException IOE) {
			System.out.println(IOE);
		}

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

	@Test
	public void takeScreenShot() {
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println(screenshotFile);

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

//	@Test
//	public void screenTransition() {
//
//		currentUrl = driver.getCurrentUrl();
//		originalHandle = driver.getWindowHandle();
//		originalHandles = driver.getWindowHandles();
//
//		//waitの実験
//		Wait<WebDriver> wait = new WebDriverWait(driver,600);
//		ExpectedCondition<Boolean> urlCheck = new ExpectedCondition<Boolean>() {
//			public Boolean apply(WebDriver driver) {
//				String tmpUrl = driver.getCurrentUrl();
//				return  !currentUrl.equals(tmpUrl);
//			}
//		};
//
//
//		((JavascriptExecutor) driver).executeScript("var element = document.createElement('script');document.body.appendChild(element);");
//		while(true) {
//		    Set<String> tmpHandles = driver.getWindowHandles();
//			if(originalHandles.size() != tmpHandles.size()) {
//				List<String> handles = new ArrayList<String>(tmpHandles);
//				handles.removeAll(originalHandles);
//
//				if (handles.size() == 1) {
//					driver.switchTo().window(handles.get(0));
//				}
//
//
////				Iterator<String> itr = tmpHandles.iterator();
////				while(itr.hasNext()) {
////					if (!itr.next().equals(originalHandle)) {
////						String tmpHandle = itr.next();
////						driver.switchTo().window(tmpHandle);
////					}
////				}
//
//			}
//
//			if(wait.until(urlCheck)) {
//				((JavascriptExecutor) driver).executeScript("var element = document.createElement('script');document.body.appendChild(element);");
//				currentUrl = driver.getCurrentUrl();
//				continue;
//			}
//
//		}
//	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
