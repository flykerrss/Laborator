import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;

public class DomXMLModify {
    public static void main(String[] argv) {
        String genreToCopy = "02";

        try {
            File inputFile = new File("books.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            DocumentBuilderFactory dbrFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder drBuilder = dbrFactory.newDocumentBuilder();
            Document rdoc = drBuilder.newDocument();

            NodeList genreList = doc.getElementsByTagName("genre");

            for (int temp = 0; temp < genreList.getLength(); temp++) {
                Node genre = genreList.item(temp);
                if (genre.getNodeType() == Node.ELEMENT_NODE) {
                    Element genreAtt = (Element) genre;
                    if (genreAtt.getAttribute("genreId").equals(genreToCopy)) {
                        Element booksElement = rdoc.createElement("books");
                        rdoc.appendChild(booksElement);

                        Element genreElement = rdoc.createElement("genre");
                        booksElement.appendChild(genreElement);

                        Attr genreId = rdoc.createAttribute("genreId");
                        genreId.setValue(genreAtt.getAttribute("genreId"));
                        genreElement.setAttributeNode(genreId);

                        Attr genreName = rdoc.createAttribute("genreName");
                        genreName.setValue(genreAtt.getAttribute("genreName"));
                        genreElement.setAttributeNode(genreName);

                        NodeList bookNameList = genreAtt.getElementsByTagName("book");

                        for (int count = 0; count < bookNameList.getLength(); count++) {
                            Node books = bookNameList.item(count);
                            if (books.getNodeType() == books.ELEMENT_NODE) {
                                Element bookInfo = (Element) books;

                                Element book = rdoc.createElement("book");
                                genreElement.appendChild(book);

                                Attr rBookId = rdoc.createAttribute("bookId");
                                rBookId.setValue(bookInfo.getAttribute("bookId"));
                                book.setAttributeNode(rBookId);

                                Element bookAuthor = rdoc.createElement("author");
                                bookAuthor.appendChild(rdoc.createTextNode(bookInfo.getElementsByTagName("author").item(0).getTextContent()));
                                book.appendChild(bookAuthor);

                                Element bookName = rdoc.createElement("name");
                                bookName.appendChild(rdoc.createTextNode(bookInfo.getElementsByTagName("name").item(0).getTextContent()));
                                book.appendChild(bookName);

                                Element publicationDate = rdoc.createElement("publicationDate");
                                publicationDate.appendChild(rdoc.createTextNode(bookInfo.getElementsByTagName("publicationDate").item(0).getTextContent()));
                                book.appendChild(publicationDate);

                                Element availability = rdoc.createElement("availability");
                                availability.appendChild(rdoc.createTextNode(bookInfo.getElementsByTagName("availability").item(0).getTextContent()));
                                book.appendChild(availability);

                                Element characters = rdoc.createElement("characters");
                                book.appendChild(characters);

                                NodeList charactersList = doc.getElementsByTagName("characters");
                                for (int i = 0; i < charactersList.getLength(); i++){
                                    Element character = rdoc.createElement("character");
                                    character.appendChild(rdoc.createTextNode(bookInfo.getElementsByTagName("character").item(i).getTextContent()));
                                    characters.appendChild(character);
                                }

                                Element publisher = rdoc.createElement("publisher");
                                publisher.appendChild(rdoc.createTextNode(bookInfo.getElementsByTagName("publisher").item(0).getTextContent()));
                                book.appendChild(publisher);
                            }
                        }
                    }
                }
            }
            // write the content into xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            StreamResult result = new StreamResult(new File("books_new.xml"));
            DOMSource source = new DOMSource(rdoc);
            transformer.transform(source, result);

            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
