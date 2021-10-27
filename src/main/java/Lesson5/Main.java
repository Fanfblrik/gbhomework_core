package Lesson5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        genCsvF("test.csv");
    }

    private static void genCsvF(String sFileName)
    {
        try
        {
            FileWriter writer = new FileWriter(sFileName);

            writer.append("Name");
            writer.append(';');
            writer.append("email");
            writer.append('\n');

            writer.append("sample");
            writer.append(',');
            writer.append("sample@sample.com");
            writer.append('\n');

            writer.append("demo");
            writer.append(',');
            writer.append("demo@demo.com");
            writer.append('\n');

            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
