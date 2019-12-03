package ch.fhnw.edu.dist;

import ch.fhnw.edu.dist.reader.Reader;
import org.apache.commons.cli.*;

import javax.management.AttributeList;
import java.util.List;

public class BloomFilterStarter {

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
            Double probability = Double.parseDouble(cmd.getOptionValue("p"));
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

            double percentageRight = (right/ (double) inListDetected);
            double percentageWrong = (wrong/ (double) inListDetected);

            System.out.println("right positive: " + right);
            System.out.println("false positive: " + wrong);
            System.out.println("in list detected: " + inListDetected);
            System.out.println("anz tw: " + tReader.getSize());
            System.out.println("porp: " + probability);
            System.out.println("perc OK (right positive): " + percentageRight);
            System.out.println("perc NOK (false positive): "  + percentageWrong);


        } else {
            System.exit(0);
            // print help
        }
    }
}
