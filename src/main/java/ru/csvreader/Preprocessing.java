package ru.csvreader;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Preprocessing {

    private Boolean flagRemoveHeader = false;
    private Boolean needIdChanged = false;

    public void Process(String inputFile, String numOutputFile) throws Exception {
        this.Process(inputFile, numOutputFile, "");
    }

    public void Process(String inputFile, String numOutputFile, String removingColumn) throws Exception {
        String readingFilePath = inputFile;
        File readingFile = new File(readingFilePath);

        if (readingFile.exists())
        {
            List<String> lines = Files.readAllLines(Paths.get
                    (readingFilePath), Charset.forName("windows-1251"));
            StringBuilder newLines = new StringBuilder();

            for(String str : lines){
                if (!flagRemoveHeader) {
                    flagRemoveHeader = true;
                } else {
                    newLines.append(this.removeColumns(str,removingColumn)).append(System.lineSeparator());
                    newLines.append(this.removeColumns(str,removingColumn)).append(System.lineSeparator());
                }
            }

            String text = newLines.toString();

            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date = new Date();

            String outputFile = "VO_" + dateFormat.format(date) + "_" + numOutputFile + ".exb";

            PrintWriter out = new PrintWriter(outputFile);
            out.println(text);
            out.close();
        } else {
            System.out.println("File " + inputFile + " now found");
        }
    }


    public String removeColumns(String startString, String removingColumn) {

        ArrayList<String> lineWithoutSomeColumns = new ArrayList(Arrays.asList(startString.split(";")));

       if (lineWithoutSomeColumns.size() > 1) {

           if (needIdChanged){
               lineWithoutSomeColumns.set(4, "2");
           }

           needIdChanged = !needIdChanged;

           if (removingColumn != "") {

               ArrayList<String> removingColumnsArray = new ArrayList(Arrays.asList(removingColumn.split(",")));
               ArrayList<Integer> columns = getIntegerArray(removingColumnsArray);

               for (int column : columns){
                   lineWithoutSomeColumns.remove(column);
               }
           }

           lineWithoutSomeColumns.remove(0);

           String result = "^" + String.join("^", lineWithoutSomeColumns) + "^";
           return result;

       } else {
           return "";
       }
    }

    private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> removingColumnIndexes = new ArrayList<>();

        for(String stringValue : stringArray) {
            try {
                removingColumnIndexes.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                System.out.println("Could not remove column with index " + stringValue);
            }
        }
        Collections.sort(removingColumnIndexes, Collections.reverseOrder());
        return removingColumnIndexes;
    }
}