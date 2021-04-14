import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVReader {
    private static final String SAMPLE_CSV_FILE_PATH = "students.csv";

    public static void main(String[] args) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                String ID = csvRecord.get("ID");
                String Name = csvRecord.get("Numele");
                String Universitate = csvRecord.get("Universitatea");
                String Profesie = csvRecord.get("Profesia");
                String Hobby = csvRecord.get("Hobby");

                System.out.println("Studentul Nr." + csvRecord.getRecordNumber());
                System.out.println("Id-ul: " + ID);
                System.out.println("Numele: " + Name);
                System.out.println("Universitatea: " + Universitate);
                System.out.println("Profesia: " + Profesie);
                System.out.println("Hobby-ul: " + Hobby);
                System.out.println("---------------\n");
            }
        }
    }
}