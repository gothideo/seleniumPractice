package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SampleScript {

	public static void main(String[] args) {
		System.setProperty("webdriver.ie.driver", "C:\\Program Files\\Selenium\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.)


		driver.get("http://example.selenium.jp/reserveApp");
//		try {
//			Thread.sleep(10000);
//		}catch(InterruptedException e){
//			e.printStackTrace();
//		}

		driver.findElement(By.id("guestname")).sendKeys("サンプルユーザ");
		driver.findElement(By.id("goto_next")).click();
		driver.quit();

	}

}
