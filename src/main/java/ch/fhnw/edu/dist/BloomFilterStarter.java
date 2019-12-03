package ch.fhnw.edu.dist;

import ch.fhnw.edu.dist.reader.Reader;
import org.apache.commons.cli.*;

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

            BloomFilter bloomFilter = new BloomFilter( wReader.getWordList(), probability  );



        } else {
            System.exit(0);
            // print help
        }


    }
}
