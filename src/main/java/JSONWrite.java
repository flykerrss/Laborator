import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.LinkedHashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class JSONWrite {
    public static void main(String[] args) {
        Map m = new LinkedHashMap();
        JSONObject obj = new JSONObject();
        m.put("author", "Joanne Rowling");
        m.put("name", "Harry Potter and the Philosopher's Stone");
        m.put("publicationDate", "1995");
        m.put("availability", "true");
        JSONArray list = new JSONArray();
        list.add("Harry Potter");
        list.add("Ronald Weasley");
        list.add("Hermione Granger");
        m.put("characters", list);
        m.put("publisher", "Bloomsbury");
        obj.put("book1", m);


        Map m1 = new LinkedHashMap();
        m1.put("author", "J. R. R. Tolkien");
        m1.put("name", "The Lord of the Rings");
        m1.put("publicationDate", "1954");
        m1.put("availability", "false");
        JSONArray list1 = new JSONArray();
        list1.add("Brego");
        list1.add("Éothain and Freda");
        list1.add("Figwit");
        m1.put("characters", list1);
        m1.put("publisher", "Allen and Unwin");
        obj.put("book2", m1);


        Map m2 = new LinkedHashMap();
        m2.put("author", "William Shakespeare");
        m2.put("name", "Romeo and Juliet");
        m2.put("publicationDate", "1597");
        m2.put("availability", "true");
        JSONArray list2 = new JSONArray();
        list2.add("Romeo Montague");
        list2.add("Juliet Capulet");
        list2.add("Tybalt");
        m2.put("characters", list2);
        m2.put("publisher", "First Quarto");
        obj.put("book3", m2);

        try (FileWriter file = new FileWriter("books.json")) {
            file.write(obj.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);

    }
}