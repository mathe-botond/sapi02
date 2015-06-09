Feature: Updating acceptance criteria
	As Sapientia scrum tool user I want to be able to update acceptance criterias

	Scenario: Update criteria
		Given I have a story added 
			And I go to the story View page
			And the story has a criteria and I view it
		When I update the acceptance criteria with "title2" and with "Feature: updated criteria"
		Then it should show the criteria titled "title2" successfully