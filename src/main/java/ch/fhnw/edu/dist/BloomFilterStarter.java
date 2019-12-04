package ch.fhnw.edu.dist;

import ch.fhnw.edu.dist.reader.Reader;
import com.google.common.base.Strings;
import org.apache.commons.cli.*;

import java.util.List;

public class BloomFilterStarter {
    
    static final int LENGTHOF = 55;
    static final char WCHAR = ' ';

    public static void main(String[] args) {


        Options options = new Options();
        options.addOption("p", "probability", true, "Sets the probability"); // double
        options.addOption("w", "wordlist", true, "Path to a wordlist as .txt"); //String
        options.addOption("t", "testwordlist", true, "a wordlist to test the filter"); // String

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            return;
        }


        // Parsing Arguments
        if (cmd.hasOption("p") && cmd.hasOption("w") && cmd.hasOption("t")) {
            double probability = Double.parseDouble(cmd.getOptionValue("p"));
            String wordlistpath = cmd.getOptionValue("w");
            String testwordlist = cmd.getOptionValue("t");

            Reader wReader = new Reader(wordlistpath);
            wReader.readWords();
            List<String> wlist = wReader.getWordList();

            Reader tReader = new Reader(testwordlist);
            tReader.readWords();


            BloomFilter bloomFilter = new BloomFilter(wlist, probability);

            int right = 0;
            int wrong = 0;

            int inListDetected = 0;

            for (String s : tReader.getWordList()) {
                boolean contains = bloomFilter.checkString(s);
                if (contains) {
                    inListDetected++;
                    if (wlist.contains(s) == bloomFilter.checkString(s)) {
                        right++;
                    } else {
                        wrong++;
                    }
                }
            }

            double percentageRight = (right / (double) inListDetected);
            double percentageWrong = (wrong / (double) inListDetected);


            String rightPositive = String.format("Right positive: %s ", right);
            String falsePositive = String.format("False positive: %s ", wrong);
            String inList = String.format("In list detected: %s ", inListDetected);
            String sizeOfTestWords = String.format("Size of TestwordsList: %s ", tReader.getSize());
            String prob = String.format("Probability: %s ", probability);
            String percRight = String.format("Percent OK (right positive): %s %% ", percentageRight);
            String percWrong = String.format("Percent NOK (false positive): %s %% ", percentageWrong);


            System.out.println("+---------------------------------------------------------------+");
            System.out.println("|                    Testresults BloomFilter                    |");
            System.out.println("|                        implemented by                         |");
            System.out.println("|        Stefan Gruber, Roger Kreienbühl, Florian Thiévent      |");
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("|        " + Strings.padEnd(rightPositive, LENGTHOF, WCHAR) + "|");
            System.out.println("|        " + Strings.padEnd(falsePositive, LENGTHOF, WCHAR) + "|");
            System.out.println("|        " + Strings.padEnd(inList, LENGTHOF, WCHAR) + "|");
            System.out.println("|        " + Strings.padEnd(sizeOfTestWords, LENGTHOF, WCHAR) + "|");
            System.out.println("|        " + Strings.padEnd(prob, LENGTHOF, WCHAR) + "|");
            System.out.println("|        " + Strings.padEnd(percRight, LENGTHOF, WCHAR) + "|");
            System.out.println("|        " + Strings.padEnd(percWrong, LENGTHOF, WCHAR) + "|");
            System.out.println("+---------------------------------------------------------------+");


        } else {
            System.exit(0);
            // print help
        }
    }
}
