package com.merckgroup.parser;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * XML Parser
 */
public class Parser {

    public static void main(String[] args) throws Exception {

        String fileName = "data.xml"; // Please keep the data.xml file at the same folder where the program file is located

        if (args.length == 1) {
            fileName = args[0];
        }

        File inputFile = new File(fileName);

        Document document = getDocument(inputFile);

        // Extract all the XML elements with tag "x1"
        NodeList x1Elements = document.getElementsByTagName("x1");

        for (int i = 0; i < x1Elements.getLength(); i++) {

            Node nNode = x1Elements.item(i);

            if (nNode.getNodeName() == "x1") {
                NodeList childNodes = nNode.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {

                    if (childNodes.item(j).getNodeName() == "x2") { // If it is not y1 scenario

                        Node item = childNodes.item(j);
                        NamedNodeMap attributes = item.getAttributes();
                        for (int k = 0; k < attributes.getLength(); k++) { // Looping over the attributes to check it has Id and is a1
                            if (attributes.item(k).getNodeName().equals("id")
                                    && attributes.item(k).getNodeValue().equals("a1")) {

                                // printing the values meeting the condition above conditions
                                System.out.println(item.getChildNodes().item(0).getNodeValue());
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * Creates the XML document from the file input
     *
     * @param inputFile
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private static Document getDocument(File inputFile) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(inputFile);
        document.getDocumentElement().normalize();
        return document;
    }

}
