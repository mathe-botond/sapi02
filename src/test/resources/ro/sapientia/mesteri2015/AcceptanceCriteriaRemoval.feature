Feature: Removing acceptance criteria
	As Sapientia scrum tool user I want to be able to remove acceptance criterias

	Scenario: Update criteria
		Given I have a story added 
			And I go to the story View page
			And the story has a criteria and I view it
		When I delete the acceptance criteria
		Then it should return to the story
			And it should be one criteria missing