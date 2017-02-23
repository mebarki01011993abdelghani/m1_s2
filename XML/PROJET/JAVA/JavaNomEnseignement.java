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


    static boolean findEnseignement = false;
    static boolean findNom = false;
    static Element racine;
    static Document doc;

    public static void main(String args[]) throws Exception {
        JavaNomEnseignement parser = new JavaNomEnseignement();
        //On build le l'arbre xml avec les noms des enseignements
        buildTreeNom();
        // API SAX
        parser.parseXmlFile("../XML/master.xml");
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
    	//On est sur le noeud nom de l'enseignement et on souhaite afficher son contenu
        if (n.getNodeType() == org.w3c.dom.Node.TEXT_NODE && findEnseignement == true && findNom == true) {
            findEnseignement = false;
            findNom = false;
            Element nom = doc.createElement("nom");
            nom.appendChild(doc.createTextNode(n.getNodeValue()));
            racine.appendChild(nom);
        } else if (n instanceof Comment) {
            System.out.printf("<!-- %s -->", n.getNodeValue());
        } else if (n instanceof Element) {
        	//On est dans le noeud enseignement
            if (n.getNodeName().equals("enseignement")) {
            	findEnseignement = true;
                printTrees(n.getChildNodes());
                //On est sur le noeud nom de l'enseignement
            } else if (n.getNodeName().equals("nom") && findEnseignement == true) {
                findNom = true;
                printTrees(n.getChildNodes());
            } else if (findEnseignement == false) {
                printTrees(n.getChildNodes());
            }
        } else if (n instanceof Document) {
            printTrees(n.getChildNodes());
        }
    }

    public static void buildTreeNom() throws TransformerException, ParserConfigurationException {
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

