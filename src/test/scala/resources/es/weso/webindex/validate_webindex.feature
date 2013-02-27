Feature: Cucumber
  In order to validate "The Webindex" data
  As a developer
  I want to be able to validate the results

  Scenario: Validate WebIndex Data
  	Given I want to check "Civil liberties" at "Kazakhstan" in "2011"
  	When I make the SPARQL query
    Then the value should be "-1.264743268725671"
  	
  	
  	Given I want to check "Civil liberties" at "Ghana" in "2010"
  	When I make the SPARQL query
    Then the value should be "0.560233698605618"
    
  Scenario: Validate Concrete Observations
    Given I want to validate indicator "FHB" of type "Raw" at "Burkina Faso" in "2011"
    When I make the SPARQL query
    Then the value should be "3"
    
    
    Given I want to validate indicator "FHB" of type "Normalised" at "Burkina Faso" in "2011"
    When I make the SPARQL query
    Then the value should be "-0.04979304207581375"