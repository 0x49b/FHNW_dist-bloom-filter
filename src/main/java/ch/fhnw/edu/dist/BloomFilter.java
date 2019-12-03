package ch.fhnw.edu.dist;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BloomFilter {

    private final Set<String> data = new HashSet<>();
    private boolean[] bits;

    public BloomFilter(Collection<String> set) {
        for (String s : set) {
            data.add(s);
        }
    }
}
