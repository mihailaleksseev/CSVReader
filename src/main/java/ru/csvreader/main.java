package ru.csvreader;

public class main {
    public static void main(String[] args) throws Exception {
        switch (args.length) {
            case 3:
                new Preprocessing().Process(args[0], args[1], args[2]);
                System.out.println("3");
                break;
            case 2:
                new Preprocessing().Process(args[0], args[1]);
                System.out.println("2");
                break;
            case 1:
            case 0:
                System.out.println("Missed input or output file name");
                break;
        }


    }
}
