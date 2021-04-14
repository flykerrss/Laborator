import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.*;

public class APOI {

    public static void main(String[] argv) {

        ArrayList<String> authors = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> characters = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> availability = new ArrayList<>();
        ArrayList<String> publisher = new ArrayList<>();
        ArrayList<String> booksId = new ArrayList<>();

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("books.xml"));
            doc.getDocumentElement().normalize();

            NodeList listOfBooks = doc.getElementsByTagName("book");

            for (int s = 0; s < listOfBooks.getLength(); s++) {
                Node nNode = listOfBooks.item(s);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element idElement = (Element) nNode;
                    booksId.add(idElement.getAttribute("bookId"));

                    Element eElement = (Element) nNode;
                    NodeList AuthorsList = eElement.getElementsByTagName("author");
                    Element firstNameElement = (Element) AuthorsList.item(0);
                    NodeList textFNList = firstNameElement.getChildNodes();
                    authors.add(textFNList.item(0).getNodeValue());

                    NodeList lastNameList = eElement.getElementsByTagName("name");
                    Element lastNameElement = (Element) lastNameList.item(0);
                    NodeList textLNList = lastNameElement.getChildNodes();
                    names.add(textLNList.item(0).getNodeValue());

                    NodeList ageList = eElement.getElementsByTagName("publicationDate");
                    Element ageElement = (Element) ageList.item(0);
                    NodeList textAgeList = ageElement.getChildNodes();
                    dates.add(textAgeList.item(0).getNodeValue());

                    NodeList nAvailability = eElement.getElementsByTagName("availability");
                    Element eAvailability = (Element) nAvailability.item(0);
                    NodeList nlAvailability = eAvailability.getChildNodes();
                    availability.add(nlAvailability.item(0).getNodeValue());

                    NodeList listOfCharacters = doc.getElementsByTagName("characters");

                    for (int tmp = 0; tmp < listOfCharacters.getLength(); tmp++) {
                        Node firstPersonNode = listOfCharacters.item(tmp);
                        if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element firstPersonElement = (Element) firstPersonNode;
                            NodeList cast1 = firstPersonElement.getElementsByTagName("character");
                            for (int i = 0; i < listOfCharacters.getLength(); i++) {
                                Element casts2 = (Element) cast1.item(i);
                                NodeList textcastList1 = casts2.getChildNodes();
                                characters.add(textcastList1.item(0).getNodeValue());
                            }
                        }
                    }

                    NodeList nPublisher = eElement.getElementsByTagName("publisher");
                    Element ePublisher = (Element) nPublisher.item(0);
                    NodeList nlPublisher = ePublisher.getChildNodes();
                    publisher.add(nlPublisher.item(0).getNodeValue());
                }
            }
        } catch (SAXParseException err) {
            System.out.println("Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        HSSFWorkbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("01 fantasy");
        Sheet sheet1 = wb.createSheet("02 tragedy");
        Map<String, Object[]> data = new HashMap<>();

        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("bookId");
        row0.createCell(1).setCellValue("author");
        row0.createCell(2).setCellValue("name");
        row0.createCell(3).setCellValue("publicationDate");
        row0.createCell(4).setCellValue("availability");
        row0.createCell(5).setCellValue("publisher");
        row0.createCell(6).setCellValue("characters");
        for (int temp = 0; temp < row0.getLastCellNum(); temp++) {
            row0.getCell(temp).setCellStyle(style);
        }

        Row row01 = sheet1.createRow(0);
        row01.createCell(0).setCellValue("bookId");
        row01.createCell(1).setCellValue("author");
        row01.createCell(2).setCellValue("name");
        row01.createCell(3).setCellValue("publicationDate");
        row01.createCell(4).setCellValue("availability");
        row01.createCell(5).setCellValue("publisher");
        row01.createCell(6).setCellValue("characters");
        for (int temp = 0; temp < row01.getLastCellNum(); temp++) {
            row01.getCell(temp).setCellStyle(style);
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 8));
        sheet1.addMergedRegion(new CellRangeAddress(0, 0, 6, 8));

        for (int i = 0; i < authors.size(); i++) {
            data.put(i + "", new Object[]{booksId.get(0), authors.get(0), names.get(0), dates.get(0), availability.get(0),
                    publisher.get(0), characters.get(0), characters.get(1), characters.get(2)});
        }

        Set<String> keyset = data.keySet();
        int rownum = 1;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    sheet.autoSizeColumn(i);
                }
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }
        Map<String, Object[]> data1 = new HashMap<>();
        for (int i = 0; i < authors.size(); i++) {
            data1.put(i + "", new Object[]{booksId.get(1), authors.get(1), names.get(1), dates.get(1),
                    availability.get(1), publisher.get(1), characters.get(3), characters.get(4), characters.get(5)});
        }

        Set<String> keyset1 = data1.keySet();
        int rownum1 = 2;
        for (String key : keyset1) {
            Row row1 = sheet.createRow(rownum1);
            Object[] objArr1 = data1.get(key);
            int cellnum = 0;
            for (Object obj : objArr1) {
                Cell cell = row1.createCell(cellnum++);
                for (int i = 0; i < row1.getLastCellNum(); i++) {
                    sheet.autoSizeColumn(i);
                }
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        Map<String, Object[]> data2 = new HashMap<>();
        for (int i = 0; i < authors.size(); i++) {
            data2.put(i + "", new Object[]{booksId.get(2), authors.get(2), names.get(2), dates.get(2),
                    availability.get(2), publisher.get(2), characters.get(6), characters.get(7), characters.get(8)});
        }

        Set<String> keyset2 = data2.keySet();
        int rownum2 = 1;
        for (String key : keyset2) {
            Row row2 = sheet1.createRow(rownum2);
            Object[] objArr = data2.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row2.createCell(cellnum++);
                for (int i = 0; i < row2.getLastCellNum(); i++) {
                    sheet1.autoSizeColumn(i);
                }
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        try {
            FileOutputStream out = new FileOutputStream("books.xls");
            wb.write(out);
            out.close();
            System.out.println("Excel written successfully...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}