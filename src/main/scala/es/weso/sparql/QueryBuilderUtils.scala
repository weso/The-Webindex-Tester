package es.weso.sparql

object QueryBuilderUtils {

  def prefixes() : String = {

    {new StringBuilder().append("PREFIX owl2xml: <http://www.w3.org/2006/12/owl2-xml#>\n")
      .append("PREFIX nvcl: <http://www.auscope.org/ontology/vocabs/nvcl/0.3#>\n")
      .append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n")
      .append("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n")
      .append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n")
      .append("PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n")
      .append("PREFIX skosxl: <http://www.w3.org/2008/05/skos-xl#>\n")
      .append("PREFIX owl: <http://www.w3.org/2002/07/owl#>\n")
      .append("PREFIX void: <http://rdfs.org/ns/void#>\n")
      .append("PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n")
      .append("PREFIX qb: <http://purl.org/linked-data/cube#>\n")
      .append("PREFIX dcterms: <http://purl.org/dc/terms/>\n")
      .append("PREFIX interval: <http://www.w3.org/2006/time#>\n")
      .append("PREFIX lg: <http://linkedgeodata.org/ontology/>\n")
      .append("PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n")
      .append("PREFIX wi-onto: <http://data.webfoundation.org/webindex/ontology/>\n")
      .append("PREFIX wi-index: <http://data.webfoundation.org/webindex/index/>\n")
      .append("PREFIX wi-component: <http://data.webfoundation.org/webindex/component/>\n")
      .append("PREFIX wi-indicator: <http://data.webfoundation.org/webindex/indicator/>\n")
      .append("PREFIX wi-indicator-ext: <http://data.webfoundation.org/webindex/indicator/external/>\n")
      .append("PREFIX wi-slice: <http://data.webfoundation.org/webindex/slice/>\n")
      .append("PREFIX wi-dsd: <http://data.webfoundation.org/webindex/dsd/>\n")
      .append("PREFIX wi-dataset: <http://data.webfoundation.org/webindex/dataset/>\n")
      .append("PREFIX wi-observation: <http://data.webfoundation.org/webindex/observation/>\n")
      .append("PREFIX wi-area: <http://data.webfoundation.org/webindex/area/area/>\n")
      .append("PREFIX wi-region: <http://data.webfoundation.org/webindex/area/region/>\n")
      .append("PREFIX wi-country: <http://data.webfoundation.org/webindex/area/country/>\n")
      .append("PREFIX wi-org: <http://data.webfoundation.org/webindex/organization/>\n")
      .append("PREFIX wi-people: <http://data.webfoundation.org/webindex/people/>\n")
      .append("PREFIX wi-score: <http://data.webfoundation.org/webindex/score/>\n")
      .append("PREFIX sdmx-concept: <http://purl.org/linked-data/sdmx/2009/concept#>\n")
      .append("PREFIX sdmx-dimension: <http://purl.org/linked-data/sdmx/2009/dimension#>\n")
      .append("PREFIX sdmx-attribute: <http://purl.org/linked-data/sdmx/2009/attribute#>\n")
      .append("PREFIX sdmx-measure: <http://purl.org/linked-data/sdmx/2009/measure#>\n")
      .append("PREFIX sdmx-metadata: <http://purl.org/linked-data/sdmx/2009/metadata#>\n")
      .append("PREFIX sdmx-code: <http://purl.org/linked-data/sdmx/2009/code#>\n")
      .append("PREFIX sdmx-subject: <http://purl.org/linked-data/sdmx/2009/subject#>\n")
      .append("PREFIX dc: <http://purl.org/dc/elements/1.1/>\n")}.toString
  }

}