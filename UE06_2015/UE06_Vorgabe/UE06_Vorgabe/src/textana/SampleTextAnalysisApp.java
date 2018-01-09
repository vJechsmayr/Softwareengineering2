package textana;

import static textana.TextAnalysisFns.findLine;
import static textana.TextAnalysisFns.findLines;
import static textana.TextAnalysisFns.setOfWords;
import static textana.TextAnalysisFns.groupByFirstChar;
import static textana.TextAnalysisFns.groupByLength;
import inout.Out;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SampleTextAnalysisApp {

	public static void main(String[] args) throws IOException {
		
		String fileName = "sampletext.txt";
		
		// a) find first line which contains a particular text 

		Optional<String>  line = findLine(fileName, "file"); 
		Out.println("\nLine with file: \n" + line.orElse("NOT FOUND"));
		
		line = findLine(fileName, "nix");
		Out.println("\nLine with nix: \n" + line.orElse("NOT FOUND"));
		
		// a) find all lines which contain a text and return them in a list 
		
		List<String> lines = findLines(fileName, "file");
		Out.println("\nLines with file: "); 
		for (String l : lines) {
			Out.println(l); 
		}
		
		lines = TextAnalysisFns.findLines(fileName, "nix");
		Out.println("\nLines with nix: "); 
		for (String l : lines) {
			Out.println(l); 
		}
		
		// b) find first line number which contains a particular text 

		Optional<Integer>  lineNum = TextAnalysisFns.findLineNum(fileName, "file"); 
		Out.println("\nLine number with file: \n" + lineNum.orElse(-1));
		
		lineNum = TextAnalysisFns.findLineNum(fileName, "nix");
		Out.println("\nLine number with nix: \n" + lineNum.orElse(-1));
		
		// b) number lines and search
		
		List<Integer> lineNums = TextAnalysisFns.findLineNums(fileName, "file");
		Out.println("\nLines numbers with file: "); 
		for (int l : lineNums) {
			Out.println(l); 
		}
		
		lineNums = TextAnalysisFns.findLineNums(fileName, "nix");
		Out.println("\nLines numbers with nix: "); 
		for (int l : lineNums) {
			Out.println(l); 
		}
		
		// b) all words 
		
		List<String> allWords = TextAnalysisFns.words(fileName); 
		Out.println("\nWords in text: "); 
		for (String w : allWords) {
			Out.println(w); 
		}
		
		// c) word occur 
		Map<String, Integer> wordOcurrs = TextAnalysisFns.wordOccurrences(fileName); 
		Out.println("\nWords occurrences in text: "); 
		for (String w: wordOcurrs.keySet()) {
			Out.println(w + ": " + wordOcurrs.get(w)); 
		}
		
		
		// d) set of words 

		Set<String> words = setOfWords(fileName);
		Out.println("\nSet of words: "); 
		for (String w : words) {
			Out.println(w); 
		}
		
		// e) grouping alphabetically, upper and lower cases same
		Map<Character, List<String>> byChar = groupByFirstChar(fileName); 
		Out.println("\nGrouped by first character: "); 
		printGroupings(byChar); 
		
		// e) grouping by length
		Map<Integer, List<String>> byLength = groupByLength(fileName); 
		Out.println("\nGrouped by length: "); 
		printGroupings(byLength); 
		
	}
	
	private static void printGroupings(Map groupings) {
		StringBuilder b;  
		for (Object k: groupings.keySet()) {
			b = new StringBuilder();
			b.append(k.toString()); 
			b.append(": "); 
			b.append(((Collection)groupings.get(k)).stream().map(s -> s.toString()).collect(Collectors.joining(", "))); 
			Out.println(b.toString()); 
		}
		
	}

}
