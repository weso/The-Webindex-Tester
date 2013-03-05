package es.weso.sparql

import com.hp.hpl.jena.query.QueryFactory
import com.hp.hpl.jena.query.Query
import com.hp.hpl.jena.query.QueryExecution
import com.hp.hpl.jena.query.QueryExecutionFactory
import com.hp.hpl.jena.query.ResultSet
import com.hp.hpl.jena.query.QuerySolution

object RankQueryBuilder {

  def checkRankCountry(country: String, year: String): (String, Double) = {
    val filter = new StringBuilder("?country rdfs:label ?countryName.\n")
      .append("Filter(str(?countryName) = str(\"").append(country).append("\")).\n")
    checkRankUtil(year, filter.toString)
  }

  def checkRankISO2(iso2: String, year: String): (String, Double) = {
    val filter = new StringBuilder("?country wi-onto:has-iso-alpha2-code ?iso2.")
      .append("Filter(str(?iso2) = str(\"").append(iso2).append("\")).")
    checkRankUtil(year, filter.toString)
  }

  def checkRankISO3(iso3: String, year: String): (String, Double) = {
    val filter = new StringBuilder("?country wi-onto:has-iso-alpha3-code ?iso3.\n")
      .append("Filter(str(?iso3) = str(\"").append(iso3).append("\")).\n")
    checkRankUtil(year, filter.toString)
  }

  private def checkRankUtil(year: String, countryFilter: String): (String, Double) = {
    val queryString: String = new StringBuilder()
      .append(QueryBuilderUtils.prefixes)
      .append("SELECT ((count(?score)+1) as ?rank) ?score WHERE {\n")
      .append("	{SELECT ?valueFiltered ?score WHERE{\n")
      .append("		?score a qb:Observation.\n")
      .append("		?score wi-onto:sheet-type wi-onto:Score.\n")
      .append("		?score wi-onto:ref-type wi-index:WebIndex.\n")
      .append("		?score dcterms:date ?date.\n")
      .append("		Filter(?date = 2011).\n")
      .append("		?score wi-onto:ref-value ?valueFiltered.\n")
      .append("		?score wi-onto:ref-area ?country.\n")
      .append(countryFilter)
      .append("	}}\n")
      .append("	?obs  rdf:type qb:Observation.\n")
      .append("	?obs  wi-onto:sheet-type wi-onto:Score.\n")
      .append("	?obs  wi-onto:ref-type  wi-index:WebIndex.\n")
      .append("	?obs  wi-onto:ref-year  ?year.\n")
      .append("	FILTER (?year = 2011).\n")
      .append("	?obs wi-onto:ref-value ?value.\n")
      .append("	FILTER (?value > ?valueFiltered).\n")
      .append("	}\n")
      .append("group by ?score\n").toString
    
    val query: Query = QueryFactory.create(queryString)

    val qexec: QueryExecution = QueryExecutionFactory.sparqlService("http://data.webfoundation.org/sparql", query)

    val results: ResultSet = qexec.execSelect();

    if (results.hasNext()) {
      val soln: QuerySolution = results.nextSolution()
      println(soln.get("score").asResource().getURI())
      (soln.get("score").asResource().getURI(), soln.get("rank").asLiteral().getDouble());
    } else throw new Exception("")
  }
}