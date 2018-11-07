package ru.csvreader;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class PreprocessingCSV {

    public void Process() {
//Удалять подстроку из строки
        String csvFile = "/mnt/dev/Java/CSVReader/files/2.csv";
        CSVReader reader = null;
        try {
            System.out.println("4");
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            System.out.println("5");
            while ((line = reader.readNext()) != null) {
                System.out.println("IS_CHANGED=" + line[0] + ", Poriad_nomer_oper_vvoda=" + line[5] + " , Name_ORG=" + line[7] + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}