Feature: Adding acceptance criteria
	As Sapientia scrum tool user I want to be able to add acceptance criterias to stories
	
	Scenario: Add invalid acceptance criteria
		Given I have a story added 
			And I go to the story View page
		When I add an acceptance criteria with "title1" and with "an invalid criteria"
		Then it should fail with a validation error
		
	Scenario: Add acceptance criteria
		Given I have a story added 
			And I go to the story View page
		When I add an acceptance criteria with "title1" and with "Feature: test"
		Then it should show the criteria titled "title1" successfully