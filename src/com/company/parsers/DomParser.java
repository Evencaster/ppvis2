package com.company.parsers;

import com.company.model.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class DomParser {
    String pathTo;
    private static List<Model> Clients;
    public void writeStudents(List<Model> Clients, String path){
        DomParser.Clients = Clients;
        pathTo = path;
    }
    private Node createBookElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    private Node createBook(Document document, String fullName, String accountNumber, String registrationAddress, String mobileNumber, String landLineNumber) {
        Element customer = document.createElement("customer");
        customer.appendChild(createBookElement(document, "fullName", fullName));
        customer.appendChild(createBookElement(document, "accountNumber", accountNumber));
        customer.appendChild(createBookElement(document, "registrationAddress", registrationAddress));
        customer.appendChild(createBookElement(document, "mobileNumber", String.valueOf(mobileNumber)));
        customer.appendChild(createBookElement(document, "landLineNumber", landLineNumber));
        return customer;
    }
    public void writeBooks() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElementNS("", "Clients");
            document.appendChild(root);
            for (Model customer : Clients) {
                root.appendChild(createBook(document, customer.getFullName(), customer.getAccountNumber(), customer.getRegistrationAddress(), customer.getMobileNumber(), customer.getLandLineNumber()));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try {
                transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                DOMSource source = new DOMSource(document);
                File myFile = new File(pathTo);
                StreamResult file = new StreamResult(myFile);
                transformer.transform(source, file);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}