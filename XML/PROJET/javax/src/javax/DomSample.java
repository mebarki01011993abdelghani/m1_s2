/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javax;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author scra
 */
public class DomSample {

    static boolean findMois = false;
    static boolean findNom = false;

    public static void main(String args[]) throws Exception {
        DomSample parser = new DomSample();
        // API SAX
        System.out.println("");
        System.out.printf("<%s>", "mois");
        parser.parseXmlFile("src/javax/siecle.xml");
        System.out.printf("</%s>", "mois");

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
            System.out.print(n.getNodeValue());
        } else if (n instanceof Comment) {
            System.out.printf("<!-- %s -->", n.getNodeValue());
        } else if (n instanceof Element) {
            if (n.getNodeName().equals("mois")) {
                findMois = true;
                //System.out.println("");
                //System.out.printf("<%s>", n.getNodeName());
                printTrees(n.getChildNodes());
                //System.out.printf("</%s>", n.getNodeName());
            } else if (n.getNodeName().equals("nom") && findMois == true) {
                findNom = true;
                System.out.println("");
                System.out.printf("<%s>", n.getNodeName());
                printTrees(n.getChildNodes());
                System.out.printf("</%s>", n.getNodeName());
                System.out.println("");
            } else if (findMois == false) {
                printTrees(n.getChildNodes());
            }

        } else if (n instanceof Document) {

            printTrees(n.getChildNodes());

        }
    }

}
