package ro.sapientia.mesteri2015;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverProvider {
	protected static WebDriver driver = null;
	
	public static WebDriver getDriver() {
		if (driver == null) {
			driver = new FirefoxDriver();
			// Set implicit wait of 10 seconds and launch google
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		return driver;
	}

	public static void close() {
		driver.quit();
		driver = null; //dereference
	}
}
