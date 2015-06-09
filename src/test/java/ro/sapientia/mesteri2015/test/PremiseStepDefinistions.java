package ro.sapientia.mesteri2015.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ro.sapientia.mesteri2015.Criteria;
import ro.sapientia.mesteri2015.DriverProvider;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class PremiseStepDefinistions {
	protected WebDriver driver;

	public static final String STORY_TITLE = "test-story";
	public static final String CRITERIA = "Feature: criteria";
	
	@Before(order=20000) //be the last before that is ran
	public void setup() {
		driver = DriverProvider.getDriver();
	}
	
	protected WebElement getStoryLink() {
		return driver.findElement(By.linkText(STORY_TITLE));
	}
	
	protected boolean isThereStoryAdded() {
		try {
			getStoryLink();
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
	
	@Given("^I have a story added$")
	public void I_have_a_story_added() throws Throwable {
		driver.get("http://localhost:8080/");
		
		if (isThereStoryAdded()) {
			return;
		}
		
		WebElement addButton = driver.findElement(By.id("add-button"));
		addButton.click();

		// Write term in google textbox
		WebElement titleTextBox = driver.findElement(By.id("story-title"));
		titleTextBox.clear();
		titleTextBox.sendKeys(STORY_TITLE);

		// Click on searchButton
		WebElement submitButton = driver.findElement(By.id("add-story-button"));
		submitButton.click();
	}

	@Given("^I go to the story View page$")
	public void I_go_to_the_story_View_page() throws Throwable {
		driver.get("http://localhost:8080/");
		
		WebElement storyLink = getStoryLink();
		storyLink.click();
	}

	private boolean criteriaExists() {
		try {
			Criteria.count = driver.findElements(By.className("acceptance-criteria-entry")).size();
		} catch (NoSuchElementException e) {
			Criteria.count = 0;
			return true;
		}
		return false;
	}
	
	@Given("^the story has a criteria and I view it$")
	public void the_story_has_a_criteria() throws Throwable {
		if (criteriaExists()) {
			driver.findElement(By.className("acceptance-criteria-entry")).click();;
		} else {
		    AcceptanceCriteriaStepDefinitions steps = new AcceptanceCriteriaStepDefinitions();
		    steps.setup();
		    steps.I_add_an_acceptance_criteria("title", CRITERIA);
		}
	}
}
