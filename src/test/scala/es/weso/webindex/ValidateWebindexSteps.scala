package es.weso.webindex

import cucumber.runtime.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.{ Map => MutableMap }
import cucumber.runtime.PendingException
import org.scalatest.selenium.WebBrowser
import org.scalatest.selenium.HtmlUnit
import org.scalatest.selenium._
import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.By
import es.weso._
import es.weso.ObservationQueryBuilder._
import es.weso.ObservationQueryBuilder._

class ValidateWebindexSteps extends ScalaDsl with EN with ShouldMatchers with WebBrowser{

  implicit val webDriver: WebDriver = new HtmlUnitDriver
  
  val host = "http://data.webfoundation.org/"
  
  val PROPERTY_QUERY = "property_query"
  val INDICATOR_QUERY = "indicator_query"
  val RANKING_QUERY = "ranking_query"
  val RANKING_QUERY_ISO2 = "ranking_query_ISO2"
  val RANKING_QUERY_ISO3 = "ranking_query_ISO3"
  val RANK_SCORE_QUERY = "rank_score_query"
  val RANK_SCORE_QUERY_ISO2 = "rank_score_query_ISO2"
  val RANK_SCORE_QUERY_ISO3 = "rank_score_query_ISO3"
  var result : (String, Double) = null
  
  val vars = MutableMap[String, String]()
  
  Given("""^I want to check "([^"]*)" in "([^"]*)" in "([^"]*)"$"""){ (property:String, country:String, year:String) =>
    vars.clear
    vars += "query" -> PROPERTY_QUERY
    vars += "property" -> property
    vars += "country" -> country
    vars += "year" -> year
  }
  
  Given("""^I want to validate indicator "([^"]*)" of type "([^"]*)" in "([^"]*)" in "([^"]*)"$"""){ (indicator:String, iType:String, country:String, year:String) =>
  	vars.clear
  	vars += "query" -> INDICATOR_QUERY
  	vars += "indicator" -> indicator
  	vars += "type" -> iType
    vars += "country" -> country
    vars += "year" -> year
  }
  
  Given("""^I want to check the ranking for "([^"]{2})" in "([^"]*)"$"""){(isoCode2:String, year:String)=>
  	vars.clear
  	vars += "query" -> RANKING_QUERY_ISO2
  	vars += "iso_code_2" -> isoCode2
  	vars += "year" -> year
  }
  
  Given("""^I want to check the ranking for "([^"]{3})" in "([^"]*)"$"""){(isoCode3:String, year:String)=>
  	vars.clear
  	vars += "query" -> RANKING_QUERY_ISO3
  	vars += "iso_code_3" -> isoCode3
  	vars += "year" -> year
  }
            
  Given("""^I want to check the ranking for "([^"]{4,})" in "([^"]*)"$"""){(country:String, year:String)=>
  	vars.clear
  	vars += "query" -> RANKING_QUERY
  	vars += "country" -> country
  	vars += "year" -> year
  }
  
  Given("""^I want to check the rank score for "([^"]{2})" in "([^"]*)"$"""){(isoCode2:String, year:String)=>
  	vars.clear
  	vars += "query" -> RANK_SCORE_QUERY_ISO2
  	vars += "iso_code_2" -> isoCode2
  	vars += "year" -> year
  }
  
  Given("""^I want to check the rank score for "([^"]{3})" in "([^"]*)"$"""){(isoCode3:String, year:String)=>
  	vars.clear
  	vars += "query" -> RANK_SCORE_QUERY_ISO3
  	vars += "iso_code_3" -> isoCode3
  	vars += "year" -> year
  }
  
  Given("""^I want to check the rank score for "([^"]{4,})" in "([^"]*)"$"""){(country:String, year:String)=>
  	vars.clear
  	vars += "query" -> RANK_SCORE_QUERY
  	vars += "country" -> country
  	vars += "year" -> year
  }
  
  When("""^I make the SPARQL query$"""){ () =>
    result = vars("query") match {
      case PROPERTY_QUERY => 
        ObservationQueryBuilder.checkObservation(vars("property"), vars("country"), vars("year"))
      case INDICATOR_QUERY =>
        ObservationQueryBuilder.checkIndicator(vars("indicator"), vars("type"), vars("country"), vars("year"))
      case RANKING_QUERY =>
        RankQueryBuilder.checkRankCountry(vars("country"), vars("year"))
      case RANKING_QUERY_ISO2=>
        RankQueryBuilder.checkRankISO2(vars("iso_code_2"), vars("year"))
      case RANKING_QUERY_ISO3 =>
        RankQueryBuilder.checkRankISO3(vars("iso_code_3"), vars("year"))
       case RANK_SCORE_QUERY =>
        RankScoreQueryBuilder.checkRankScoreCountry(vars("country"), vars("year"))
      case RANK_SCORE_QUERY_ISO2=>
        RankScoreQueryBuilder.checkRankScoreISO2(vars("iso_code_2"), vars("year"))
      case RANK_SCORE_QUERY_ISO3 =>
        RankScoreQueryBuilder.checkRankScoreISO3(vars("iso_code_3"), vars("year"))
    }
  }
  
  When("""^I go to the page$"""){ () =>
    go to ("http://thewebindex.org/data/all/country/ARG")
    result = ("",webDriver.findElement(By.xpath("//*[@id=\"webindex\"]/tbody/tr[1]/td[3]")).getText().toDouble)
  }
  
  Then("""^the value should be "([^"]*)"$"""){ (arg0:String) =>
    arg0.toDouble should be (result._2 plusOrMinus 0.0000001f)
  }

}