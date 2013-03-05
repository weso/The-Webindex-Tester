package es.weso

import com.hp.hpl.jena.query.QueryFactory
import com.hp.hpl.jena.query.Query
import com.hp.hpl.jena.query.QueryExecution
import com.hp.hpl.jena.query.QueryExecutionFactory
import com.hp.hpl.jena.query.ResultSet
import com.hp.hpl.jena.query.QuerySolution

object RankScoreQueryBuilder {

  def checkRankScoreCountry(country: String, year: String): (String, Double) = {
    val filter = new StringBuilder("?country rdfs:label ?countryName.")
      .append("Filter(str(?countryName) = str(\"").append(country).append("\")).")
    checkRankScoreUtil(year, filter.toString)
  }

  def checkRankScoreISO2(iso2: String, year: String): (String, Double) = {
    val filter = new StringBuilder("?country wi-onto:has-iso-alpha2-code ?iso2.")
      .append("Filter(str(?iso2) = str(\"").append(iso2).append("\")).")
    checkRankScoreUtil(year, filter.toString)
  }

  def checkRankScoreISO3(iso3: String, year: String): (String, Double) = {
    val filter = new StringBuilder("?country wi-onto:has-iso-alpha3-code ?iso3.")
      .append("Filter(str(?iso3) = str(\"").append(iso3).append("\")).")
    checkRankScoreUtil(year, filter.toString)
  }

  private def checkRankScoreUtil(year: String, countryFilter: String): (String, Double) = {
    val queryString: String = new StringBuilder()
      .append(QueryBuilderUtils.prefixes)
      .append("select ?score ?value where {")
      .append("?score a qb:Observation.")
      .append("?score wi-onto:sheet-type wi-onto:Score.")
      .append("?score wi-onto:ref-type wi-index:WebIndex.")
      .append("?score dcterms:date ?date.")
      .append("Filter(?date = ").append(year).append(").\n")
      .append("?score wi-onto:ref-value ?value.")
      .append("?score wi-onto:ref-area ?country.")
      .append(countryFilter)
      .append("} ORDER BY desc(?value)").toString

    val query: Query = QueryFactory.create(queryString)

    val qexec: QueryExecution = QueryExecutionFactory.sparqlService("http://data.webfoundation.org/sparql", query)

    val results: ResultSet = qexec.execSelect();

    if (results.hasNext()) {
      val soln: QuerySolution = results.nextSolution()
      println(soln.get("score").asResource().getURI())
      (soln.get("score").asResource().getURI(), soln.get("value").asLiteral().getDouble());
    } else throw new Exception("")
  }
}