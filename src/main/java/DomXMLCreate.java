import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.StringWriter;

public class DomXMLCreate {
    public static void main(String argv[]) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // books (root) element
            Element booksElement = doc.createElement("books");
            doc.appendChild(booksElement);

            // genre1 element
            Element genre1 = doc.createElement("genre");
            booksElement.appendChild(genre1);

            // setting genreID attribute to element genre1
            Attr genreId = doc.createAttribute("genreId");
            genreId.setValue("01");
            genre1.setAttributeNode(genreId);

            // setting genreName attribute to element genre1
            Attr genreName = doc.createAttribute("genreName");
            genreName.setValue("fantasy");
            genre1.setAttributeNode(genreName);

            // book1 element
            Element book1 = doc.createElement("book");
            genre1.appendChild(book1);

            // setting bookId attribute to element book
            Attr bookId = doc.createAttribute("bookId");
            bookId.setValue("001");
            book1.setAttributeNode(bookId);

            // author element
            Element bookAuthor = doc.createElement("author");
            bookAuthor.appendChild(doc.createTextNode("Joanne Rowling"));
            book1.appendChild(bookAuthor);

            // book name element
            Element bookName = doc.createElement("name");
            bookName.appendChild(doc.createTextNode("Harry Potter and the Philosopher's Stone"));
            book1.appendChild(bookName);

            // publication date element
            Element publicationDate = doc.createElement("publicationDate");
            publicationDate.appendChild(doc.createTextNode("1995"));
            book1.appendChild(publicationDate);

            // availability element
            Element availability = doc.createElement("availability");
            availability.appendChild(doc.createTextNode("true"));
            book1.appendChild(availability);

            // characters list element
            Element characters = doc.createElement("characters");
            book1.appendChild(characters);

            // character elements
            Element character1 = doc.createElement("character");
            character1.appendChild(doc.createTextNode("Harry Potter"));
            characters.appendChild(character1);

            Element character2 = doc.createElement("character");
            character2.appendChild(doc.createTextNode("Ronald Weasley"));
            characters.appendChild(character2);

            Element character3 = doc.createElement("character");
            character3.appendChild(doc.createTextNode("Hermione Granger"));
            characters.appendChild(character3);

            // publisher element
            Element publisher = doc.createElement("publisher");
            publisher.appendChild(doc.createTextNode("Bloomsbury"));
            book1.appendChild(publisher);

            // book2 element
            Element book2 = doc.createElement("book");
            genre1.appendChild(book2);

            // setting bookId attribute to element book
            Attr book2Id = doc.createAttribute("bookId");
            book2Id.setValue("002");
            book2.setAttributeNode(book2Id);

            // author element
            Element book2Author = doc.createElement("author");
            book2Author.appendChild(doc.createTextNode("J. R. R. Tolkien"));
            book2.appendChild(book2Author);

            // book name element
            Element book2Name = doc.createElement("name");
            book2Name.appendChild(doc.createTextNode("The Lord of the Rings"));
            book2.appendChild(book2Name);

            // publication date element
            Element publicationDate2 = doc.createElement("publicationDate");
            publicationDate2.appendChild(doc.createTextNode("1954"));
            book2.appendChild(publicationDate2);

            // availability element
            Element availability2 = doc.createElement("availability");
            availability2.appendChild(doc.createTextNode("false"));
            book2.appendChild(availability2);

            // characters list element
            Element characters2 = doc.createElement("characters");
            book2.appendChild(characters2);

            // character elements
            Element character12 = doc.createElement("character");
            character12.appendChild(doc.createTextNode("Brego"));
            characters2.appendChild(character12);

            Element character22 = doc.createElement("character");
            character22.appendChild(doc.createTextNode("Éothain and Freda"));
            characters2.appendChild(character22);

            Element character32 = doc.createElement("character");
            character32.appendChild(doc.createTextNode("Figwit"));
            characters2.appendChild(character32);

            // publisher element
            Element publisher2 = doc.createElement("publisher");
            publisher2.appendChild(doc.createTextNode("Allen and Unwin"));
            book2.appendChild(publisher2);

            // genre2 element
            Element genre2 = doc.createElement("genre");
            booksElement.appendChild(genre2);

            // setting genreID attribute to element genre1
            Attr genre2Id = doc.createAttribute("genreId");
            genre2Id.setValue("02");
            genre2.setAttributeNode(genre2Id);

            // setting genreName attribute to element genre1
            Attr genre2Name = doc.createAttribute("genreName");
            genre2Name.setValue("tragedy");
            genre2.setAttributeNode(genre2Name);

            // book1 element
            Element book3 = doc.createElement("book");
            genre2.appendChild(book3);

            // setting bookId attribute to element book
            Attr book3Id = doc.createAttribute("bookId");
            book3Id.setValue("003");
            book3.setAttributeNode(book3Id);

            // author element
            Element book3Author = doc.createElement("author");
            book3Author.appendChild(doc.createTextNode("William Shakespeare"));
            book3.appendChild(book3Author);

            // book name element
            Element book3Name = doc.createElement("name");
            book3Name.appendChild(doc.createTextNode("Romeo and Juliet"));
            book3.appendChild(book3Name);

            // publication date element
            Element publicationDate3 = doc.createElement("publicationDate");
            publicationDate3.appendChild(doc.createTextNode("1597"));
            book3.appendChild(publicationDate3);

            // availability element
            Element availability3 = doc.createElement("availability");
            availability3.appendChild(doc.createTextNode("true"));
            book3.appendChild(availability3);

            // characters list element
            Element characters3 = doc.createElement("characters");
            book3.appendChild(characters3);

            // character elements
            Element character13 = doc.createElement("character");
            character13.appendChild(doc.createTextNode("Romeo Montague"));
            characters3.appendChild(character13);

            Element character23 = doc.createElement("character");
            character23.appendChild(doc.createTextNode("Juliet Capulet"));
            characters3.appendChild(character23);

            Element character33 = doc.createElement("character");
            character33.appendChild(doc.createTextNode("Tybalt"));
            characters3.appendChild(character33);

            // publisher element
            Element publisher3 = doc.createElement("publisher");
            publisher3.appendChild(doc.createTextNode("First Quarto"));
            book3.appendChild(publisher3);


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            StreamResult result = new StreamResult(new File("books.xml"));
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}