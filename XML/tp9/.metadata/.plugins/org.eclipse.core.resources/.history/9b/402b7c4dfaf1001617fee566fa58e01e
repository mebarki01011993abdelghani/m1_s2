package javaEtXML;


import javax.xml.stream.events.StartDocument;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
public class SaxSample extends  DefaultHandler{
	
	int nbElements = 0;
    int nbCharacters = 0;
    
	public static void main(String args[]) throws Exception {
		SaxSample parser = new SaxSample();
	    parser.parseXmlFile("src/siecle.xml");
	}

	public void parseXmlFile(String fileName) throws Exception {

	    // créer un analyseur SAX par défaut
	    XMLReader xr = XMLReaderFactory.createXMLReader();

	    // fixer le(s) gestionnaire(s) d'évènements
	    xr.setContentHandler(this);
	    xr.setErrorHandler(this);
	    // xr.setDTDHandler(this);

	    // fixer les comportements
	    xr.setFeature("http://xml.org/sax/features/validation", true);

	    // analyser la source XML
	    xr.parse(new InputSource(fileName));
	    System.out.println("Fichier parser");
	  
	}
	
	// Travail à faire en début d'analyse
	@Override
	public void startDocument() {
	    nbElements = 0;
	    nbCharacters = 0;
	}
	// Travail à faire en fin d'analyse
	@Override
	public void endDocument() {
	    System.out.println();
	    System.out.println("nbElements = " + nbElements);
	    System.out.println("nbCharacters = " + nbCharacters);
	}
	//Le gestionnaire de contenu

	@Override
	public void startElement(String uri, String name, String qName, Attributes atts) {
		if(qName.equals("nom")){
			System.out.println("<" + qName + ">");
		    nbElements++;
	    }
	
	    
	}
	//Travail à faire : préparer le stockage ou le traitement des données.

	@Override
	public void endElement(String uri, String name, String qName) {
			if(qName.equals("nom")){
				System.out.println("<" + qName + ">");
		    }
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
	    nbCharacters += (length);
	    for (int i = start; i < start + length; i++) {
	        switch (ch[i]) {
	        case '&':
	            System.out.print("&amp;");
	            break;
	        case '<':
	            System.out.print("&lt;");
	            break;
	        case '>':
	            System.out.print("&gt;");
	            break;
	        default :
	            System.out.print(ch[i]);
	            break;
	        }
	    }
	}
	
	@Override
	public void error(SAXParseException e) {
	    System.err.printf("Erreur non fatale (ligne %d, col %d) : %s\n",
	            e.getLineNumber(), e.getColumnNumber(), e.getMessage());
	}
	@Override
	public void fatalError(SAXParseException e) {
	    System.err.printf("Erreur fatale : %s\n", e.getMessage());
	}
	@Override
	public void warning(SAXParseException e) {
	    System.err.printf("warning : %s\n", e.getMessage());
	}
}