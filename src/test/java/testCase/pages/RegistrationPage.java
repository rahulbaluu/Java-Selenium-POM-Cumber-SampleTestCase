package testCase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends BasePage {
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	public void verifyTheTextPresent(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		String expectedText = "ENTER ACCOUNT INFORMATION";
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='title text-center']")));
		if(element.isDisplayed() && element.getText().equals(expectedText)){
			System.out.println("Text Present");
		}
		else{
			System.out.println("Text Not Present");
		}
	}

	public void fillRegistrationForm(String password, String firstName, String lastName, String company,
									 String address1, String address2, String country, String state,
									 String city, String zipcode, String mobileNumber,
									 String date, String month, String year) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		//Scroll to and click gender radio button
		WebElement genderRadio = driver.findElement(By.id("id_gender1"));
		js.executeScript("arguments[0].scrollIntoView(true);", genderRadio);
		genderRadio.click();

		//Password
		driver.findElement(By.id("password")).sendKeys(password);

		//Selecting the dropdown
		WebElement DayDropdown = driver.findElement(By.id("days"));
		js.executeScript("arguments[0].scrollIntoView(true);", DayDropdown);
		Select Daydropdown = new Select(DayDropdown);
		Daydropdown.selectByValue(date);

		WebElement MonthDropdown = driver.findElement(By.id("months"));
		Select Monthdropdown = new Select(MonthDropdown);
		Monthdropdown.selectByValue(month);

		WebElement YearDropdown = driver.findElement(By.id("years"));
		Select Yeardropdown = new Select(YearDropdown);
		Yeardropdown.selectByValue(year);

		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		driver.findElement(By.id("first_name")).sendKeys(firstName);
		driver.findElement(By.id("last_name")).sendKeys(lastName);
		driver.findElement(By.id("company")).sendKeys(company);
		driver.findElement(By.id("address1")).sendKeys(address1);
		driver.findElement(By.id("address2")).sendKeys(address2);
		driver.findElement(By.id("country")).sendKeys(country);
		driver.findElement(By.id("state")).sendKeys(state);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.id("zipcode")).sendKeys(zipcode);
		driver.findElement(By.id("mobile_number")).sendKeys(mobileNumber);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
	}

	public void verifyTheTextAccountCreated() {
		String ExpectedText = "ACCOUNT CREATED!";
		WebElement element = driver.findElement(By.cssSelector("h2[data-qa='account-created']"));
		if(element.isDisplayed() && element.getText().equals(ExpectedText)){
			System.out.println("Account Created Visible");
			driver.findElement(By.linkText("Continue")).click();
		}
		else{
			System.out.println("Account Created Visible");
		}
	}
}
