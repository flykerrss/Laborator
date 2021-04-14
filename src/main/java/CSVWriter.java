import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class CSVWriter {
    private static final String SAMPLE_CSV_FILE = "students.csv";

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "Numele", "Universitatea", "Profesia","Hobby"));
        ) {
            csvPrinter.printRecord("001", "Spînu Dan", "USM", "student","muzică");
            csvPrinter.printRecord("002", "Andronic Roman", "USM", "student","jocuri");
            csvPrinter.printRecord("003", "Barbos Oleg", "USM", "student","grafică");
            csvPrinter.printRecord("004", "Dimineț Ion", "USM", "student","jocuri");
            csvPrinter.printRecord(Arrays.asList("005", "Temciuc Nicolae", "USM", "student", "basketball"));

            csvPrinter.flush();
        }
    }
}