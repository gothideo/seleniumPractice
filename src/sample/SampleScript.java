package sample;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;

public class SampleScript {

	public static void main(String[] args) {

		System.setProperty("webdriver.ie.driver", "C:\\Program Files\\Selenium\\IEDriverServer.exe");

		//デスクトップ操作用ドライバインスタンスを生成
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("requireWindowFocus", true);

		DesiredCapabilities winAppCapability = new DesiredCapabilities();
		winAppCapability.setCapability("app", "Root");

		try {
			WindowsDriver winAppDriver  = new WindowsDriver(new URL("http://127.0.0.1:4723"), winAppCapability);
			WebElement calcWindow = winAppDriver.findElementByName("電卓");
			String calcTopLevelWindow = calcWindow.getAttribute("NativeWindowHandle");
			calcTopLevelWindow = Integer.toHexString(Integer.valueOf(calcTopLevelWindow));

			//アプリ操作用ドライバインスタンスを生成
			DesiredCapabilities appCapabilities = new DesiredCapabilities();
			appCapabilities.setCapability("appTopLevelWindow", calcTopLevelWindow);
			WindowsDriver calcSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), appCapabilities);


			File scrFile = ((TakesScreenshot) calcSession).getScreenshotAs(OutputType.FILE);
			System.out.println(scrFile.getAbsolutePath());


		} catch (MalformedURLException MUE){
			System.out.println(MUE);
		} catch (IOException IOE) {
			System.out.println(IOE);
		}
//		System.setProperty("webdriver.ie.driver", "C:\\Program Files\\Selenium\\IEDriverServer.exe");
//		WebDriver driver = new InternetExplorerDriver();
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.)


//		driver.get("http://example.selenium.jp/reserveApp");
//		try {
//			Thread.sleep(10000);
//		}catch(InterruptedException e){
//			e.printStackTrace();
//		}

//		driver.findElement(By.id("guestname")).sendKeys("サンプルユーザ");
//		driver.findElement(By.id("goto_next")).click();
//		driver.quit();

	}

}
