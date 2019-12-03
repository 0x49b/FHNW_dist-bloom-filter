package ch.fhnw.edu.dist;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BloomFilter {

    private Set<String> data;

    public BloomFilter(Collection<String> set) {
        data = new HashSet<>();
        for (String s : set) {
            data.add(s);
        }
    }
}
