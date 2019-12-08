# Bloom Filter for dist @ FHNW

## Collaborators
Roger Kreienbühl (@rkreienbuehl)  
Florian Thiévent (@lichtwellenreiter)  
Stefan Gruber (@zone39) 

## How to use
Start the application with three arguments:

```-p``` Double value for probability of false positive

```-w``` file with words which are added to the BloomFilter

```-t``` file with words to test the Bloomfilter

The files have to be in .txt format. Execution command:

```java -jar fhnw-dist-bloom-filter-1.0-SNAPSHOT.jar -p <probability as double> -w <path/to/wordfile> -t <path/to/testwordfile>```

All three arguments ```-p```, ```-w``` and ```-t``` are needed.
