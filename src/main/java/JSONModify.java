import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class JSONModify {
    public static void main(String[] args) throws IOException, ParseException {
        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader("books.json"));

        ObjectMapper mapper = new ObjectMapper();

        JSONObject jo = (JSONObject) obj;

        Map book = ((Map)jo.get("book2"));

        Iterator<Map.Entry> itr1 = book.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
        }
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
            try (FileWriter file = new FileWriter("books_new.json")) {
                file.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}