package ch.fhnw.edu.dist.reader;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private List<String> wordList = new ArrayList<>();
    private String wordListPath;

    /**
     * Initialize the Reader with a Path to a wordlist, usually from a Commandline Argument
     *
     * @param wordListPath String
     */
    public Reader(String wordListPath) {
        this.wordListPath = wordListPath;
    }

    /**
     * Read the words to a list
     */
    public void readWords() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(
                    new FileReader(this.wordListPath)
            );

            String line = reader.readLine();
            while (line != null) {
                wordList.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * For Debug Purposes print out all words
     */
    public void printWordList() {
        for (String word : wordList) {
            System.out.println(word);
        }
        System.out.println("-------------------------------------");
        System.out.println("# of Words in List " + wordList.size());
    }

    /**
     * Getter for the wordlist
     * @return List<String>
     */
    @SuppressWarnings("unused")
    public List<String> getWordList() {
        return wordList;
    }
}
