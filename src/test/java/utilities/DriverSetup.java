package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverSetup {
	private static WebDriver driver;
	public static WebDriver getDriver() {
		if (driver == null) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		return driver;
	}

	public static void quit() {
		if(driver != null){
			driver.quit();
			driver = null;
		}
	}
}
