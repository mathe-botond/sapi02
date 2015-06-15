Feature: Viewing acceptance criteria
	As Sapientia scrum tool user I want to be able to view acceptance criterias

	Scenario: View acceptance criteria
		Given I have a story added 
			And I go to the story View page
		When I add an acceptance criteria with "title1" and with 
			"""
				Feature: test feature
				Scenario: test scenario
				Given test stuff
				And other test stuff
				When I do some testing
				Then it should result in some test result
			"""
		Then it should highlight the keywords
				| Featur	|
			    	| Scenario  	|
			    	| Given 	| 
			    	| When 		| 
			    	| Then 		| 
			    	| And 		|
		And indent rows in the criteria    
