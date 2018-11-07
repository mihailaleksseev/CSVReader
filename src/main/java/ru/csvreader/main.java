package ru.csvreader;

public class main {
    public static void main(String[] args) throws Exception {
//        new Preprocessing().Process(args[0], args[1], args[2])
//        java -jar  CSVReader-1.0.0.jar tem.csv 1 2
        new PreprocessingCSV().Process(args[0], args[1], args[2]);
        System.out.println("start main");
    }
}
