package sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class ExEventListener extends AbstractWebDriverEventListener {

	@Override
	public void afterClickOn(WebElement element,WebDriver driver) {
		System.out.println("click");
		System.out.println(driver.getCurrentUrl());
	}

}
