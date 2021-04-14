import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XPATH {
    public static void main(String[] args) {
        try {
            File inputFile = new File("books.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath =  XPathFactory.newInstance().newXPath();

            String expression = "/books/genre/book[@bookId = '002']";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("bookId: "
                            + eElement.getAttribute("bookId"));
                    System.out.println("author: "
                            + eElement
                            .getElementsByTagName("author")
                            .item(0)
                            .getTextContent());
                    System.out.println("name: "
                            + eElement
                            .getElementsByTagName("name")
                            .item(0)
                            .getTextContent());
                    System.out.println("publicationDate: "
                            + eElement
                            .getElementsByTagName("publicationDate")
                            .item(0)
                            .getTextContent());
                    System.out.println("availability: "
                            + eElement
                            .getElementsByTagName("availability")
                            .item(0)
                            .getTextContent());
                    System.out.println("characters: "
                            + eElement
                            .getElementsByTagName("characters")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
