package ro.sapientia.mesteri2015.test;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ro.sapientia.mesteri2015.Criteria;
import ro.sapientia.mesteri2015.DriverProvider;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AcceptanceCriteriaStepDefinitions {
public static final String STORY_TITLE = "test-story";


	protected WebDriver driver;
	
	@Before
	public void setup() {
		driver = DriverProvider.getDriver();
	}
	
	@When("^I add an acceptance criteria with \"([^\"]*)\" and with \"([^\"]*)\"$")
	public void I_add_an_acceptance_criteria(String title, String criteria) throws Throwable {
		driver.findElement(By.linkText("Add new acceptance criteria")).click();
		
		driver.findElement(By.name("title")).sendKeys(title);
		driver.findElement(By.name("criteria")).sendKeys(criteria);
		driver.findElement(By.id("add-acceptance-criteria-button")).click();
		
		Criteria.count ++;
	}
	
	@When("^I update the acceptance criteria with \"([^\"]*)\" and with \"([^\"]*)\"$")
	public void I_update_the_acceptance_criteria_with_and_with(String title, String criteria) throws Throwable {
		driver.findElement(By.id("update-link")).click();
		
		WebElement titleInput = driver.findElement(By.name("title"));
		titleInput.clear();
		titleInput.sendKeys(title);
		
		WebElement criteriaInput = driver.findElement(By.name("criteria"));
		criteriaInput.clear();
		criteriaInput.sendKeys(criteria);
		
		driver.findElement(By.id("update-acceptance-criteria-button")).click();
	}

	@Then("^it should fail with a validation error$")
	public void it_should_fail_with_a_validation_error() throws Throwable {
	    String url = driver.getCurrentUrl();
	    Assert.assertThat(url, CoreMatchers.containsString("/acceptance-criteria/add"));
	    
	    WebElement error = driver.findElement(By.id("error-criteria"));
	    Assert.assertThat(error.getText(), CoreMatchers.startsWith("Lexing error"));
	}
	
	@Then("^it should show the criteria titled \"([^\"]*)\" successfully$")
	public void it_should_show_the_criteria_successfully(String title) throws Throwable {
		String result = driver.findElement(By.id("acceptance-criteria-title")).getText();
		Assert.assertEquals(title, result);
	}

	@Then("^it should highlight the keywords$")
	public void it_should_highlight_the_keywords(List<String> expectedKeywords) throws Throwable {
		String source = driver.getPageSource();
		for (String keyword : expectedKeywords) {
			Assert.assertThat(source, CoreMatchers.containsString("<span class=\"keyword\">" + keyword));
		}
	}
	
	@Then("^indent rows in the criteria$")
	public void indent_rows_in_the_criteria() throws Throwable {
		int count = driver.findElements(By.className("acceptance-criteria-block")).size();
		Assert.assertTrue(count > 3);
	}
	
	@When("^I delete the acceptance criteria$")
	public void I_delete_the_acceptance_criteria() throws Throwable {
		driver.findElement(By.id("delete-link")).click();
		driver.findElement(By.id("delete-button")).click();
	}
	
	@Then("^it should return to the story$")
	public void it_should_return_to_the_story() throws Throwable {
		String url = driver.getCurrentUrl();
	    Assert.assertThat(url, CoreMatchers.containsString("/story/"));
	}
	
	@Then("^it should be one criteria missing$")
	public void it_should_be_oen_criteria_missing() throws Throwable {
		int newCount = driver.findElements(By.className("acceptance-criteria-entry")).size();
		Assert.assertEquals(newCount, Criteria.count - 1);
	}
	
	@When("^I add an acceptance criteria with \"([^\"]*)\" and with$")
	public void I_add_an_acceptance_criteria_with_multiline_content(String title, String criteria) throws Throwable {
	    String modifiedCriteria = criteria.replaceAll("\t", "");
		I_add_an_acceptance_criteria(title, modifiedCriteria);
	}
	
	@After
	public void closeBrowser() {
		DriverProvider.close();
	}
}
