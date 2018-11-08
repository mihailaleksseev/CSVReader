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

    public void Process(String inputFile, String numOutputFile) throws Exception {
        this.Process(inputFile, numOutputFile, null);
    }

    public void Process(String inputFile, String numOutputFile, String removingColumn) throws Exception {
        String readingFilePath = "./../../files/" + inputFile;
        File readingFile = new File(readingFilePath);

        if (readingFile.exists())
        {
            List<String> lines = Files.readAllLines(Paths.get(readingFilePath), Charset.forName("windows-1251"));
            StringBuilder newLines = new StringBuilder();

            for(String str : lines){
                if (!flagRemoveHeader) {
                    flagRemoveHeader = true;
                    continue;
                } else {

                    System.out.println("removing column by num: " + removingColumn);
                    String newStr = this.removeColumns(str, removingColumn);

//                    String newStr = "^" + str + "^";

                    newLines.append(newStr).append(System.lineSeparator());

//                    String secondLine = newStr.substring(0,27)+ "2" +newStr.substring(28);
                    newLines.append(newStr).append(System.lineSeparator());
                }
            }

            String text = newLines.toString();
            text = text.replaceAll(";", "^");

            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date = new Date();

            String outputFile = "VO_" + dateFormat.format(date) + "_" + numOutputFile + ".exb";

            PrintWriter out = new PrintWriter("./../../files/" + outputFile);
            out.println(text);
            out.close();
        } else {
            System.out.println("File " + inputFile + " now found");
        }
    }


    public String removeColumns(String startString, String removingColumn) {

        ArrayList<String> lineWithoutSomeColumns = new ArrayList(Arrays.asList(startString.split(";")));

        ArrayList<String> removingColumnsArray = new ArrayList(Arrays.asList(removingColumn.split(",")));
        ArrayList<Integer> columns = getIntegerArray(removingColumnsArray);

        lineWithoutSomeColumns.set(6, "2");

        lineWithoutSomeColumns.remove(2);
        lineWithoutSomeColumns.remove(1);
        lineWithoutSomeColumns.remove(0);

        for (int column : columns){
            System.out.println("int column " + column);
            lineWithoutSomeColumns.remove(column);
        }

        String result = lineWithoutSomeColumns.toString();
        System.out.println("result " + result);

        return result;
    }

    private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> removingColumnIndexes = new ArrayList<Integer>();
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