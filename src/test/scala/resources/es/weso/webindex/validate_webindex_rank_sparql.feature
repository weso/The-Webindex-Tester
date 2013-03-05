Feature: Cucumber
  In order to validate "The Webindex" data
  As a developer or Linked Data Expert
  I want to be able to validate The Webindex Rank

  Scenario: Validate WebIndex Ranking
    Given I want to check the ranking for "Argentina" in "2011"
  	When I make the SPARQL query
    Then the value should be "38"
    
  	Given I want to check the ranking for "ARG" in "2011"
  	When I make the SPARQL query
    Then the value should be "38"
    
    Given I want to check the ranking for "AR" in "2011"
  	When I make the SPARQL query
    Then the value should be "38"