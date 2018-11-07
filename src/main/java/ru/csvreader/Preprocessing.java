package ru.csvreader;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Preprocessing {

    private String outputFile;
    private Boolean flagRemoveHeader = false;

    public void Process(String inputFile, String numOutputFile, String removingColumnByName, String removingColumnByNum) throws Exception {

        List<String> lines = Files.readAllLines(Paths.get("/mnt/dev/Java/CSVReader/files/" + inputFile + ".csv"), Charset.forName("windows-1251"));

        StringBuilder newLines = new StringBuilder();

        System.out.println("removing column by name: " + removingColumnByName);
        System.out.println("removing column by num: " + removingColumnByNum);

        for(String str : lines){
            if (!flagRemoveHeader) {
                flagRemoveHeader = true;
                continue;
            } else {
                String newStr = "^" + str + "^";
                newLines.append(newStr).append(System.lineSeparator());
                String secondLine = newStr.substring(0,26)+ "2" +newStr.substring(27);
                newLines.append(secondLine).append(System.lineSeparator());
            }
        }

        String text = newLines.toString();
        text = text.replaceAll(";", "^");

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();

        outputFile = "VO_" + dateFormat.format(date) + "_" + numOutputFile + ".exb";

        PrintWriter out = new PrintWriter("/mnt/dev/Java/CSVReader/files/" + outputFile);
        out.println(text);
        out.close();

    }
}