Feature: Cucumber
  In order to validate "The Webindex" data
  As a developer or Linked Data Expert
  I want to be able to validate the Webindex Rank Score

  Scenario: Validate WebIndex Rank Score
    Given I want to check the rank score for "Argentina" in "2011"
  	When I make the SPARQL query
    Then the value should be "42.15"
  
  	Given I want to check the rank score for "ARG" in "2011"
  	When I make the SPARQL query
    Then the value should be "42.15"
    
    Given I want to check the rank score for "AR" in "2011"
  	When I make the SPARQL query
    Then the value should be "42.15"