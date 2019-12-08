package ch.fhnw.edu.dist;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.Collection;

/**
 * Naive BloomFilter Class
 */
public class BloomFilter {

    private boolean[] bits;
    private HashFunction[] hf;
    private int nOfItems;
    private int nOfBits;
    private int nOfHash;
    private double probability;

    /**
     * Construtor of class BloomFilter
     *
     * @param set         Collection with all Strings to add at first
     * @param probability Probability of false positive
     */
    public BloomFilter(Collection<String> set, double probability) {
        this.probability = probability;
        this.nOfItems = set.size();
        nOfBits = calculateNumbersOfBits();
        bits = new boolean[nOfBits];

        nOfHash = calculateNumberOfHashFunctions();
        hf = new HashFunction[nOfHash];
        for (int i = 0; i < nOfHash; i++) {
            hf[i] = Hashing.murmur3_128((int) Math.random());
        }

        for (String s : set) {
            addString(s);
        }
    }

    /**
     * Method to calculate the number of bits
     *
     * @return Number of bits as int
     */
    private int calculateNumbersOfBits() {
        return (int) ((nOfItems * Math.log(probability)) / Math.log(1 / Math.pow(2, Math.log(2))));
    }

    /**
     * Method to calculate the number of HashFunctions
     *
     * @return Number of HashFunctions as int
     */
    private int calculateNumberOfHashFunctions() {
        return (int) Math.round((nOfBits / nOfItems) * Math.log(2));
    }

    /**
     * Method to add a new String to the BloomFilter
     *
     * @param s String to add to the BloomFilter
     */
    public void addString(String s) {
        for (HashFunction hashFunction : hf) {
            HashCode hc = hashFunction.newHasher().putString(s, Charsets.UTF_8).hash();
            int bit = Math.abs( hc.asInt() % nOfBits);
            this.bits[bit] = true;
        }
    }

    /**
     * Method to check if a string in the data structure. false positive is possible
     *
     * @param s String to check for
     * @return boolean value if string is inside the data structure
     */
    public boolean checkString(String s) {
        boolean check = true;

        for (HashFunction hashFunction : hf) {
            HashCode hc = hashFunction.newHasher().putString(s, Charsets.UTF_8).hash();
            int bit = Math.abs(hc.asInt() % nOfBits);
            check = check && this.bits[bit];

            if (!check) {
                break;
            }
        }

        return check;
    }

    /**
     * Getter method for nOfBits
     * @return Number of bits as integer
     */
    public int getnOfBits() {
        return nOfBits;
    }

    /**
     * Getter method for nOfHash
     * @return Number of hash functions as integer
     */
    public int getnOfHash() {
        return nOfHash;
    }
}
