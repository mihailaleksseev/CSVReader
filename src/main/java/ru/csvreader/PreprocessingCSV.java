package ru.csvreader;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class PreprocessingCSV {

    public void Process(String inputFile, String numOutputFile, String
            removingColumnByNum) throws Exception {

        System.out.println("1");
        CSVReader reader2 = new CSVReader(new FileReader("./../../files/" + inputFile));
        System.out.println("2");
        List<String[]> allElements = reader2.readAll();
        System.out.println("3");
        allElements.remove(removingColumnByNum);
        FileWriter sw = new FileWriter("./../../files/" + numOutputFile +
                ".csv");
        System.out.println("4");
        CSVWriter writer = new CSVWriter(sw);
        System.out.println("5");
        writer.writeAll(allElements);
        writer.close();

    }
}