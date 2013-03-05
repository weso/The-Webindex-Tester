package es.weso.web

import cucumber.runtime.{ ScalaDsl, EN }
import org.scalatest.selenium.WebBrowser
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By


object RankWebBuilder extends ScalaDsl with EN with WebBrowser{
	
	implicit val webDriver: WebDriver = new HtmlUnitDriver
  
	def checkRankISO3(iso3:String, year: String): (String, Double) = {
	  val uri = "http://thewebindex.org/data/all/country/"+iso3
	  go to (uri)
	  (uri, webDriver.findElement(By.xpath("//*[@id=\"webindex\"]/tbody/tr[1]/td[3]")).getText().toDouble)
	}
	
}