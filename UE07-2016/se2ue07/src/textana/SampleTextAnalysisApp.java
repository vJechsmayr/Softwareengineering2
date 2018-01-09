package textana;

import static textana.TextAnalysisFns.findLine;
import static textana.TextAnalysisFns.findLines;
import static textana.TextAnalysisFns.setOfWords;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import inout.Out;

public class SampleTextAnalysisApp {

	public static void main(String[] args) throws IOException {

		String fileName = "faust_1.txt";

		// a) find first line which contains a particular text

		Optional<String> line = findLine(fileName, "Freundschaft");
		Out.println("\nLine with Freundschaft: \n" + line.orElse("NOT FOUND"));

		line = findLine(fileName, "mich");
		Out.println("\nLine with mich: \n" + line.orElse("NOT FOUND"));

		// a) find all lines which contain a text and return them in a list

		List<String> lines = findLines(fileName, "Freundschaft");
		Out.println("\nLines with Freundschaft: ");
		for (String l : lines) {
			Out.println(l);
		}

		lines = TextAnalysisFns.findLines(fileName, "mich");
		Out.println("\nLines with mich: ");
		for (String l : lines) {
			Out.println(l);
		}

		// b) find first line number which contains a particular text

		Optional<Integer> lineNum = TextAnalysisFns.findLineNum(fileName, "Freundschaft");
		Out.println("\nLine number with Freundschaft: \n" + lineNum.orElse(-1));

		lineNum = TextAnalysisFns.findLineNum(fileName, "mich");
		Out.println("\nLine number with mich: \n" + lineNum.orElse(-1));

		// b) number lines and search

		List<Integer> lineNums = TextAnalysisFns.findLineNums(fileName, "Freundschaft");
		Out.println("\nLines numbers with Freundschaft: ");
		for (int l : lineNums) {
			Out.println(l);
		}

		lineNums = TextAnalysisFns.findLineNums(fileName, "mich");
		Out.println("\nLines numbers with mich: ");
		for (int l : lineNums) {
			Out.println(l);
		}

		// c) all words

		List<String> allWords = TextAnalysisFns.words(fileName);
		Out.println("\nWords in text: ");
		for (String w : allWords) {
			Out.println(w);
		}

		// d) word occur
		Map<String, Integer> wordOcurrs = TextAnalysisFns.wordOccurrences(fileName);
		Out.println("\nWords occurrences in text: ");
		for (String w : wordOcurrs.keySet()) {
			Out.println(w + ": " + wordOcurrs.get(w));
		}

		// e) set of words

		Set<String> words = setOfWords(fileName);
		Out.println("\nSet of words: ");
		for (String w : words) {
			Out.println(w);
		}

	}

}
