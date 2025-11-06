package testCase.pages;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void openHomePage() {

		driver.findElement(By.cssSelector("p.fc-button-label")).click(); // Consent
	}
	public void verifyTheHomePageIsVisibleSuccessfully() {
		driver.getTitle();
		System.out.println(driver.getTitle());
		if(Objects.equals(driver.getTitle(), "Automation Exercise")) {
			System.out.println("Home page is visible successfully");
		}
		else {
			System.out.println("Home page is not visible successfully");
		}
	}

	public void clickOnSignupButton() {
		driver.findElement(By.linkText("Signup / Login")).click();
	}
	public void takeScreenshot(String fileName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("./screenshots/" + fileName + ".png"));
	}
}
