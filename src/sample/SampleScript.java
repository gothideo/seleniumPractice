package sample;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;

public class SampleScript {

	public static void main(String[] args) {

//		try {
//			Runtime runtime = Runtime.getRuntime();
//			runtime.exec("C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe");
//		}catch (IOException e) {
//			e.printStackTrace();
//		}


		System.setProperty("webdriver.ie.driver", "C:\\Program Files\\Selenium\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		driver.get("http://example.selenium.jp/reserveApp/");

		driver.findElement(By.id("reserve_year")).sendKeys("2021");

		//デスクトップ操作用ドライバインスタンスを生成
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("requireWindowFocus", true);

		DesiredCapabilities winAppCapability = new DesiredCapabilities();
		winAppCapability.setCapability("app", "Root");

		WindowsDriver desktopDriver = null;
		WindowsDriver appDriver = null;

		String winAppDriverName = "WinAppDriver.exe";

		ProcessCheck proCheck = new ProcessCheck();
		if (proCheck.isRun(winAppDriverName)) {
			try {
				desktopDriver  = new WindowsDriver(new URL("http://127.0.0.1:4723"), winAppCapability);
				//ファイルアップロードダイアログの場合
				//todo IEのブラウザが複数立ち上がっている場合の対応を要検討
				//WebElement calcWindow = desktopDriver.findElementByClassName("IEFrame");

				WebElement calcWindow = desktopDriver.findElementByName("電卓");
				String calcTopLevelWindow = calcWindow.getAttribute("NativeWindowHandle");
				calcTopLevelWindow = Integer.toHexString(Integer.valueOf(calcTopLevelWindow));

				//アプリ操作用ドライバインスタンスを生成
				DesiredCapabilities appCapabilities = new DesiredCapabilities();
				appCapabilities.setCapability("appTopLevelWindow", calcTopLevelWindow);
				appDriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), appCapabilities);

				//ファイルアップロードのダイアログ操作
				//appDriver.findElementByName("参照...").click();

				//todo \が文字化けするのでその対応が必要
				//appDriver.findElementByName("アップロードするファイルの選択").findElement(By.className("Edit")).sendKeys("");

				File scrFile = ((TakesScreenshot) appDriver).getScreenshotAs(OutputType.FILE);
				System.out.println(scrFile.getAbsolutePath());


			} catch (MalformedURLException MUE){
				System.out.println(MUE);
			} catch (IOException IOE) {
				System.out.println(IOE);
			}
		}else {
			System.out.println(winAppDriverName + "が起動していないため、処理を終了します。");
			driver.quit();
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
