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
    private Integer numOutputFile = 0;
    private Boolean flagRemoveHeader = false;

    public void Process(String inputFile) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("files/" + inputFile), Charset.defaultCharset());

        StringBuilder newLines = new StringBuilder();

        for(String str : lines){
            if (!flagRemoveHeader) {
                flagRemoveHeader = true;
                continue;
            } else {
                newLines.append(str).append(System.lineSeparator());
                newLines.append(str).append(System.lineSeparator());
            }
        }

        String text = newLines.toString();
        text = text.replaceAll(";", "^");

        numOutputFile++;
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();

        outputFile = "VO_" + dateFormat.format(date) + "_" + numOutputFile + ".exb";

        PrintWriter out = new PrintWriter("files/" + outputFile);
        out.println(text);
        out.close();
    }
}