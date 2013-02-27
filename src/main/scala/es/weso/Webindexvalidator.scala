package es.weso

import org.scardf.QueryEngine
import net.croz.scardf.Sparql
import net.croz.scardf.RDF
import net.croz.scardf.asc
import com.hp.hpl.jena.query.QueryFactory
import com.hp.hpl.jena.query.Query
import com.hp.hpl.jena.query.QueryExecution
import com.hp.hpl.jena.query.QueryExecutionFactory
import com.hp.hpl.jena.query.ResultSet
import com.hp.hpl.jena.query.QuerySolution

object Webindexvalidator extends App {
  
  def checkObservation(name:String, country:String, year:String) : Double =  {
	val queryString : String = new StringBuilder()
	  .append("PREFIX wi-onto: <http://data.webfoundation.org/webindex/ontology/>\n")
	  .append("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n")
	  .append("PREFIX qb: <http://purl.org/linked-data/cube#>\n")
	  .append("SELECT ?obs ?countryName ?value ?comment WHERE {\n")
	  .append("?obs wi-onto:sheet-type wi-onto:Normalised.\n")
	  .append("?obs a qb:Observation.\n")
	  .append("?obs wi-onto:ref-year ?year.\n")
	  .append("?obs wi-onto:value ?value.\n")
	  .append("Filter(?year = ").append(year).append(").\n")
	  .append("?obs wi-onto:ref-area ?country.\n")
	  .append("?country rdfs:label ?countryName.\n")
	  .append("Filter(str(?countryName) = str(\"").append(country).append("\")).\n")
	  .append("?obs rdfs:comment ?comment.\n")
	  .append("Filter(str(?comment) = str(\"").append(name).append("\")).\n")
	  .append("} ORDER BY desc(?value)").toString;
	val query : Query = QueryFactory.create(queryString)
	
	val qexec : QueryExecution = QueryExecutionFactory.sparqlService("http://data.webfoundation.org/sparql", query)
	
	val results : ResultSet = qexec.execSelect();
   
    if(results.hasNext()){
      val soln : QuerySolution = results.nextSolution() 
      soln.get("value").asLiteral().getDouble();
    } else throw new Exception("")
  }
  
  def checkIndicator(indicator:String, iType:String, country:String, year:String) : Double =  {
	val queryString : String = new StringBuilder()
	  .append("PREFIX wi-onto: <http://data.webfoundation.org/webindex/ontology/>\n")
	  .append("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n")
	  .append("PREFIX qb: <http://purl.org/linked-data/cube#>\n")
	  .append("SELECT ?obs ?countryName ?value WHERE {\n")
	  .append("?obs wi-onto:sheet-type wi-onto:").append(iType).append(".\n")
	  .append("?obs a qb:Observation.\n")
	  .append("?obs wi-onto:ref-year ?year.\n")
	  .append("?obs wi-onto:value ?value.\n")
	  .append("Filter(?year = ").append(year).append(").\n")
	  .append("?obs wi-onto:ref-area ?country.\n")
	  .append("?country rdfs:label ?countryName.\n")
	  .append("Filter(str(?countryName) = str(\"").append(country).append("\")).\n")
	  .append("?obs wi-onto:ref-indicator ?indicator.\n")
	  .append("?indicator rdfs:label ?indicatorName.\n")
	  .append("Filter(str(?indicatorName) = str(\"").append(indicator).append("\")).\n")
	  .append("} ORDER BY desc(?value)").toString;
	val query : Query = QueryFactory.create(queryString)
	
	val qexec : QueryExecution = QueryExecutionFactory.sparqlService("http://data.webfoundation.org/sparql", query)
	
	val results : ResultSet = qexec.execSelect();
   
    if(results.hasNext()){
      val soln : QuerySolution = results.nextSolution() 
      soln.get("value").asLiteral().getDouble();
    } else throw new Exception("")
  }
  
}