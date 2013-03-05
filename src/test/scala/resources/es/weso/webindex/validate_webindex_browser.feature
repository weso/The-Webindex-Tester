Feature: Cucumber
  In order to validate "The Webindex" data
  As a user
  I want to be able to validate the results

  Scenario: Validate WebIndex Data
  
  	Given I want to check the ranking for "ARG" in "2011"
  	When I go to the page
    Then the value should be "38"
    
    Given I want to check the rank score for "ARG" in "2011"
  	When I go to the page
    Then the value should be "42.14"