package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
//This is a base class provided by cucumber for TestNG and extending this class allows cucumber to run feature files using TestNG
import io.cucumber.testng.CucumberOptions;

//This TestRunner class is the entry point to run cucumber tests using TestNG.
//When executed Cucumber scans the features folder for .feature files
//Matched steps in those feature files to the step definition in glue
//Executes the test scenarios
//Generates reports(Pretty in console + HTML report in target folder)
@CucumberOptions(
		features = "src/test/java/testCase/features", //Path of the folder containing features file
		glue = {"testCase.stepdefinition"},           //Path of the package containing step definition file location
		plugin = {"pretty", "json:target/cucumber.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
		//"pretty" prints a readable format in the console
		//"html:target/cucumber-reports.html" Generates an HTML report in the target folder
		monochrome = true
		//"true" makes console output readable(no extra special characters)
		//"false" keeps colored output (less readable in some consoles)
		tags = "@product"
)
public class TestRunner extends AbstractTestNGCucumberTests {
	//"extends AbstractTestNGCucumberTests" Allows this class to run all scenarios defined in feature files using TestNG
}

//Cucumber plugins are used to generate reports or format output when you run tests
//1. Console Output Plugins - These plugins control what you see in the console when you run tests
//1.1.pretty - Prints readable text of steps and results in the console
//1.2.summary - Shows a concise summary of passed/failed scenarios
//1.3.rerun - Writes failed scenarios into a file rerunning later
//1.4.progress - Shows progress of tests using dots or symbols

//2. File-based Report Plugins - These generate reports in various format
//2.1.html:<path> - Generates an HTML reports
//2.2.json:<path> - Generates a JSON reports, useful for integration with other tools(eg., Jenkins, Allure)
//2.3.junit:<path> - Generates JUnit XML reports, useful for CI/CD pipelines

//3. Custom/3rd party plugins - You can use external libraries for advanced reporting
//3.1.Extent Reports: Pretty dashboards with charts, screenshot and logs
//3.2.Allure Reports: Generates interactive test reports with history and graphs.



