<img src="http://weso.es/img/web_index_github.png">

# The Webindex Tester

## What is The Web Index? ##
The ["Webindex"](http://thewebindex.org/ "Go to the Webindex Page") is a unique annual ranking of countries on the progress and social utility of the Web. Combining over 80 indicators to evaluate access, affordability, institutional and policy environment and social and economic utility, it will provide an evidence-based tool for national and regional advocacy, intervention, and strategy to strengthen the Web. The 2012 Web Index was published on September 5, 2012.

## What can "The Webindex Tester" do?
The Webindex Tester is a tool to validate, in a natural way, 
data included within ["The Webindex's Dataset"](http://data.webfoundation.org/ "Go to the Webindex's Dataset").

## How It Works? ##
Just Fill your .feature file (src/resources/es/weso/webindex/) and execute from command line:
```
sbt cucumber
```

# Show me some examples!
The next examples accepts the full country name (i.e. **Argentina**), the ISO 2-alpha code (i.e. **AR**) or the ISO 3-alpha code (i.e. **ARG**):

 - >      Given I want to check "Civil liberties" in "Kazakhstan" in "2011"
   >      When I make the SPARQL query
   >      Then the value should be "-1.264743268725671"

 - >      Given I want to validate indicator "FHB" of type "Normalised" in "Burkina Faso" in "2011"
   >      When I make the SPARQL query
   >      Then the value should be "-0.04979304207581375"

 - >      Given I want to check the ranking for "ARG" in "2011"
   >      When I make the SPARQL query
   >      Then the value should be "38"