import org.json.simple.parser.*;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

public class JSONRead {
    public static void main(String[] args) throws Exception {

        Object obj = new JSONParser().parse(new FileReader("books.json"));

        JSONObject jObject = (JSONObject) obj;

        Map book = ((Map) jObject.get("book3"));

        Iterator itr1 = book.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = (Map.Entry) itr1.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
    }
}