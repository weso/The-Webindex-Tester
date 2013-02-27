package es.weso.webindex

import cucumber.runtime.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.{ Map => MutableMap }
import cucumber.runtime.PendingException
import es.weso.Webindexvalidator

class ValidateWebindexSteps extends ScalaDsl with EN with ShouldMatchers {

  val PROPERTY_QUERY = "property_query"
  val INDICATOR_QUERY = "indicator_query"
  var result : Double = 0
  
  val vars = MutableMap[String, String]()

  Given("""^I want to check "([^"]*)" at "([^"]*)" in "([^"]*)"$"""){ (property:String, country:String, year:String) =>
    vars.clear
    vars += "query" -> PROPERTY_QUERY
    vars += "property" -> property
    vars += "country" -> country
    vars += "year" -> year
  }
  
  Given("""^I want to validate indicator "([^"]*)" of type "([^"]*)" at "([^"]*)" in "([^"]*)"$"""){ (indicator:String, iType:String, country:String, year:String) =>
  	vars.clear
  	vars += "query" -> INDICATOR_QUERY
  	vars += "indicator" -> indicator
  	vars += "type" -> iType
    vars += "country" -> country
    vars += "year" -> year
  }
  
  When("""^I make the SPARQL query$"""){ () =>
    vars("query") match {
      case PROPERTY_QUERY => 
        result = Webindexvalidator.checkObservation(vars("property"), vars("country"), vars("year"))
      case INDICATOR_QUERY =>
        result = Webindexvalidator.checkIndicator(vars("indicator"), vars("type"), vars("country"), vars("year"))
    }
  }
  
  Then("""^the value should be "([^"]*)"$"""){ (arg0:String) =>
    arg0.toDouble should be (result plusOrMinus 0.0000001f)
  }

}