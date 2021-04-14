import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class DomQuery {
    public static void main(String argv[]) {

        try {
            File inputFile = new File("books.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("genre");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    NodeList bookNameList = eElement.getElementsByTagName("book");

                    for (int count = 0; count < bookNameList.getLength(); count++) {
                        Node node1 = bookNameList.item(count);

                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element book = (Element) node1;
                            String m = book.getAttribute("bookId");
                            if (m.equals("001")) {
                                System.out.println("\nCurrent Element: ");
                                System.out.print("\tBook id: ");
                                System.out.println(book.getAttribute("bookId"));
                                System.out.print("\tGenre: ");
                                System.out.println(eElement.getAttribute("genreName"));
                                System.out.println("\tName: "
                                        + book.getElementsByTagName("name")
                                        .item(0)
                                        .getTextContent());
                                System.out.println("\tAuthor: "
                                        + book.getElementsByTagName("author")
                                        .item(0)
                                        .getTextContent());
                                System.out.println("\tPublication Date: "
                                        + book.getElementsByTagName("publicationDate")
                                        .item(0)
                                        .getTextContent());
                                System.out.println("\tAvailability: "
                                        + eElement
                                        .getElementsByTagName("availability")
                                        .item(0)
                                        .getTextContent());
                                System.out.println("\tPublisher: "
                                        + eElement
                                        .getElementsByTagName("publisher")
                                        .item(0)
                                        .getTextContent());
                                System.out.print("\tCharacters: ");
                                NodeList cList = doc.getElementsByTagName("characters");
                                for (int i = 0; i < cList.getLength(); i++) {
                                    System.out.print(eElement
                                            .getElementsByTagName("character")
                                            .item(i)
                                            .getTextContent() + ", ");
                                    if (i == cList.getLength() - 1) {
                                        System.out.print("\b\b.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
