package es.weso.web

import cucumber.runtime.{ ScalaDsl, EN }
import org.scalatest.selenium.WebBrowser
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

object RankScoreWebBuilder extends ScalaDsl with EN with WebBrowser{
	
	implicit val webDriver: WebDriver = new HtmlUnitDriver
  
	def checkRankScoreISO3(iso3:String, year: String): (String, Double) = {
	  val uri = "http://thewebindex.org/data/all/country/"+iso3
	  go to (uri)
	  (uri,webDriver.findElement(By.xpath("//*[@id=\"webindex\"]/tbody/tr[1]/td[2]")).getText().toDouble)
	}
}