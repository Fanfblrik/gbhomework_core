package Lesson5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        genCsvF("1.csv");
        readCSVF();
    }

    private static void genCsvF(String sFileName)
    {
        try
        {
            FileWriter writer = new FileWriter(sFileName);

            writer.append("Hello");
            writer.append(';');
            writer.append("world!");
            writer.append('\n');

            writer.append("321");
            writer.append(';');
            writer.append("123!");
            writer.append('\n');

            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void readCSVF(){
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("1.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(records.toString());
    }
    public static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
