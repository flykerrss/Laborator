import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomParser {
    public static void main(String[] args) {
        try {
            File inputFile = new File("books.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("book");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println();

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Book ID: "
                            + eElement.getAttribute("bookId"));
                    System.out.println("Book name: "
                            + eElement.getElementsByTagName("name")
                            .item(0)
                            .getTextContent());
                    System.out.println("Author : "
                            + eElement
                            .getElementsByTagName("author")
                            .item(0)
                            .getTextContent());
                    System.out.println("Publication date: "
                            + eElement
                            .getElementsByTagName("publicationDate")
                            .item(0)
                            .getTextContent());
                    System.out.println("Availability: "
                            + eElement
                            .getElementsByTagName("availability")
                            .item(0)
                            .getTextContent());
                    System.out.println("Publisher: "
                            + eElement
                            .getElementsByTagName("publisher")
                            .item(0)
                            .getTextContent());
                    System.out.print("Characters: ");
                    NodeList cList = doc.getElementsByTagName("characters");
                    for (int i = 0; i < cList.getLength(); i++) {
                        System.out.print(eElement
                                .getElementsByTagName("character")
                                .item(i)
                                .getTextContent() + " ");
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}