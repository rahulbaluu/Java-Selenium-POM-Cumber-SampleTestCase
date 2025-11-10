package testCase.pages;

import constant.RegistrationPageConstant;
import constant.WaitTimeConstant;
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTimeConstant.waitTime));
		String expectedText = "ENTER ACCOUNT INFORMATION";
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationPageConstant.expectedText)));
		if(element.isDisplayed() && element.getText().equals(expectedText)){
			System.out.println("Text Present");
		}
		else{
			System.out.println("Text Not Present");
		}
	}

	public void fillRegistrationForm(String password, String first, String last, String company,
									 String address1, String address2, String country, String state,
									 String city, String zip, String mobile,
									 String date, String month, String year) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Scroll to and click gender radio button
		WebElement genderRadio = driver.findElement(By.id(RegistrationPageConstant.genderrRadioButton));
		js.executeScript("arguments[0].scrollIntoView(true);", genderRadio);
		genderRadio.click();

		//Password
		driver.findElement(By.id(RegistrationPageConstant.password)).sendKeys(password);

		//Selecting the dropdown
		WebElement DayDropdown = driver.findElement(By.id(RegistrationPageConstant.dayDropDown));
		js.executeScript("arguments[0].scrollIntoView(true);", DayDropdown);
		Select Daydropdown = new Select(DayDropdown);
		Daydropdown.selectByValue(date);

		WebElement MonthDropdown = driver.findElement(By.id(RegistrationPageConstant.monthDropDown));
		Select Monthdropdown = new Select(MonthDropdown);
		Monthdropdown.selectByValue(month);

		WebElement YearDropdown = driver.findElement(By.id(RegistrationPageConstant.yearDropDown));
		Select Yeardropdown = new Select(YearDropdown);
		Yeardropdown.selectByValue(year);

		driver.findElement(By.id(RegistrationPageConstant.newsLetter)).click();
		driver.findElement(By.id(RegistrationPageConstant.optionalCheckBox)).click();
		driver.findElement(By.id(RegistrationPageConstant.firstName)).sendKeys(first);
		driver.findElement(By.id(RegistrationPageConstant.lastName)).sendKeys(last);
		driver.findElement(By.id(RegistrationPageConstant.companyName)).sendKeys(company);
		driver.findElement(By.id(RegistrationPageConstant.address1)).sendKeys(address1);
		driver.findElement(By.id(RegistrationPageConstant.address2)).sendKeys(address2);
		driver.findElement(By.id(RegistrationPageConstant.country)).sendKeys(country);
		driver.findElement(By.id(RegistrationPageConstant.state)).sendKeys(state);
		driver.findElement(By.id(RegistrationPageConstant.city)).sendKeys(city);
		driver.findElement(By.id(RegistrationPageConstant.zipcode)).sendKeys(zip);
		driver.findElement(By.id(RegistrationPageConstant.mobileNumber)).sendKeys(mobile);

		WebElement elementDriver = driver.findElement(By.cssSelector(RegistrationPageConstant.registrationSubmitButton));
		elementDriver.click();
	}

	public void verifyTheTextAccountCreated() {
		String ExpectedText = "ACCOUNT CREATED!";
		WebElement element = driver.findElement(By.cssSelector(RegistrationPageConstant.accountCreatedMessage));
		if(element.isDisplayed() && element.getText().equals(ExpectedText)){
			System.out.println("Account Created Visible");
			driver.findElement(By.linkText(RegistrationPageConstant.accountCreatedContinueButton)).click();
		}
		else{
			System.out.println("Account Created Visible");
		}
	}
}
