import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author scra and Ptikyou
 */
public class JavaNomEnseignement {


    static boolean findMois = false;
    static boolean findNom = false;
    static Element racine;
    static Document doc;

    public static void main(String args[]) throws Exception {
        JavaNomEnseignement parser = new JavaNomEnseignement();
        buildTreeMois();
        // API SAX
        parser.parseXmlFile("master.xml");
        encoding();

    }

    public void parseXmlFile(String fileName) throws Exception {

        // préparer une usine à fabriquer les analyseurs DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        // construire une analyseur DOM
        DocumentBuilder parser = factory.newDocumentBuilder();

        // analyser et afficher le document XML
        Document doc = parser.parse(new File(fileName));
        printTree(doc);
    }

    public void printTrees(NodeList nodes) {
        for (int i = 0; (i < nodes.getLength()); i++) {
            printTree(nodes.item(i));
        }
    }

    public void printTree(Node n) {
        if (n.getNodeType() == org.w3c.dom.Node.TEXT_NODE && findMois == true && findNom == true) {
            findMois = false;
            findNom = false;
            Element nom = doc.createElement("nom");
            nom.appendChild(doc.createTextNode(n.getNodeValue()));
            racine.appendChild(nom);
        } else if (n instanceof Comment) {
            System.out.printf("<!-- %s -->", n.getNodeValue());
        } else if (n instanceof Element) {
            if (n.getNodeName().equals("enseignement")) {
                findMois = true;
                printTrees(n.getChildNodes());
            } else if (n.getNodeName().equals("nom") && findMois == true) {
                findNom = true;
                printTrees(n.getChildNodes());
            } else if (findMois == false) {
                printTrees(n.getChildNodes());
            }
        } else if (n instanceof Document) {
            printTrees(n.getChildNodes());
        }
    }

    public static void buildTreeMois() throws TransformerException, ParserConfigurationException {
        // ajout de noeuds
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        racine = doc.createElement("enseignements");
        doc.appendChild(racine);
    }

    public static void encoding() throws TransformerConfigurationException, TransformerException {
        // sérialisation
        TransformerFactory myFactory = TransformerFactory.newInstance();
        Transformer transformer = myFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(new DOMSource(doc), new StreamResult(System.out));
    }

}

