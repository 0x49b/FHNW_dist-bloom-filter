package ch.fhnw.edu.dist;

import ch.fhnw.edu.dist.reader.Reader;

public class BloomFilterStarter {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Please specify the argument for the Path to Wordlist");
            System.out.println("How to call: ");
            System.out.println("   java -jar -f <path/to/wordlist>");
            System.exit(0);
        } else if (args.length > 2) {
            System.out.println("Please specify the correct amount of arguments");
            System.out.println("How to call: ");
            System.out.println("   java -jar -f <path/to/wordlist>");
            System.exit(0);
        } else if (args[0].equals("-f")) {
            System.out.println("Path is present " + args[1]);

            Reader reader = new Reader(args[1]);
            reader.readWords();
            reader.printWordList();

        } else {
            System.out.println("An error occured. please try again");
        }

    }
}
