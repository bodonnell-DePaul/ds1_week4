package csc402.week4;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String csvFile = "/home/bodonnell/csc402/lectures/week4/demo/202303300_batting.csv";
        List<PositionPlayer> persons = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> records = reader.readAll();
            String[] headers = records.get(0); // First row is the header
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                PositionPlayer person = new PositionPlayer();
                for (int j = 0; j < headers.length; j++) {
                    person.setAttribute(headers[j], record[j]);
                }
                persons.add(person);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        System.out.println("Hello World!");
    }
}
