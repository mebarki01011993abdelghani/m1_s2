package javax;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Comment;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SaxSample extends DefaultHandler {

    int nbElements = 0;
    int nbCharacters = 0;
    static boolean findJour = false;
    static boolean findNom = false;

    public static void main(String args[]) throws Exception {
        SaxSample parser = new SaxSample();
        // API SAX
        //parser.parseXmlFile("src/javax/siecle.xml");
        //API DOM
        
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
    }



    /**
     * ****************FIN DOM*************************************
     */
    /**
     * ***** API SAX ***************
     */
    //Le gestionnaire de contenu
    @Override
    public void startElement(String uri, String name, String qName, Attributes atts) {
        // Je suis dans un nom
        if (qName.equals("nom") && findJour == true) {
            System.out.println("<" + qName + ">");
            findNom = true;
            nbElements++;
        } else //Je suis dans un jour
        if (qName.equals("jour")) {
            System.out.println("<" + qName + ">");
            findJour = true;
        } else {
            findJour = false;
            findNom = false;
        }

    }
    //Travail à faire : préparer le stockage ou le traitement des données.

    @Override
    public void endElement(String uri, String name, String qName) {
        if (qName.equals("nom") && findNom == true && findJour == true) {
            System.out.println("<" + qName + ">");
        }
        if (qName.equals("jour") && findNom == true && findJour == true) {
            System.out.println("<" + qName + ">");
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        nbCharacters += (length);
        if (findNom == true && findJour == true) {
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
                    default:
                        System.out.print(ch[i]);
                        break;
                }
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

    private void printTree(Document doc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void printTrees(NodeList childNodes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
