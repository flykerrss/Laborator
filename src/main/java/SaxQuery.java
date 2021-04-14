import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SaxQuery {
    public static void main(String[] args) {

        try {
            File inputFile = new File("books.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler2 userHandler = new UserHandler2();
            saxParser.parse(inputFile, userHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class UserHandler2 extends DefaultHandler {

    boolean author = false;
    boolean name = false;
    boolean publicationDate = false;
    boolean availability = false;
    boolean characters = false;
    boolean character = false;
    boolean publisher = false;
    String bookId = null;
    String bookToFind = "002";

    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("book")) {
            bookId = attributes.getValue("bookId");
        }
        if (bookToFind.equals(bookId)) {
            if (qName.equalsIgnoreCase("author")) {
                author = true;
            } else if (qName.equalsIgnoreCase("name")) {
                name = true;
            } else if (qName.equalsIgnoreCase("publicationDate")) {
                publicationDate = true;
            } else if (qName.equalsIgnoreCase("availability")) {
                availability = true;
            } else if (qName.equalsIgnoreCase("character")) {
                character = true;
            } else if (qName.equalsIgnoreCase("characters")) {
                characters = true;
            } else if (qName.equalsIgnoreCase("publisher")) {
                publisher = true;
            }
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (bookToFind.equals(bookId)) {
            if (author) {
                System.out.println("author: " + new String(ch, start, length));
                author = false;
            } else if (name) {
                System.out.println("name: " + new String(ch, start, length));
                name = false;
            } else if (publicationDate) {
                System.out.println("publicationDate: " + new String(ch, start, length));
                publicationDate = false;
            } else if (availability) {
                System.out.println("availability: " + new String(ch, start, length));
                availability = false;
            } else if (characters) {
                System.out.print("characters: ");
                characters = false;
            } else if (character) {
                System.out.print(new String(ch, start, length) + ", ");
                character = false;
            } else if (publisher) {
                System.out.println("\b\b");
                System.out.println("publisher: " + new String(ch, start, length));
                publisher = false;
            }
        }
    }
}