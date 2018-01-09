package textana;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextAnalysisFns {

	// (a) find lines which contains a particular word
	
	public static Optional<String> findLine(String fileName, String word) throws IOException {
		// TODO
		return null;
	}
	
	public static List<String> findLines(String fileName, String word) throws IOException {
		// TODO
		return null;
	}
	
	// (b) find all line numbers which contain a text and return them in a list 
	
	public static Optional<Integer> findLineNum(String fileName, String word) throws IOException {
		// TODO
		return null;
	}
	
	public static List<Integer> findLineNums(String fileName, String word) throws IOException {
		// TODO
		return null;
	}
	
	// (c) all words
	
	public static List<String> words(String fileName) throws IOException {
		// TODO
		return null;
	}
	
	// (d) word occurrences 

	public static Map<String, Integer> wordOccurrences(String fileName) throws IOException {
		// TODO
		return null;
	}

	// (e) set of words 
	
	public static Set<String> setOfWords(String fileName) throws IOException {
		// TODO
		return null;
	}

	// (f) grouping alphabetically and by length
	
	public static Map<Character, List<String>> groupByFirstChar(String fileName) throws IOException {
		// TODO
		return null;
	}

	public static Map<Integer, List<String>> groupByLength(String fileName) throws IOException {
		// TODO
		return null;
	}
	
}
