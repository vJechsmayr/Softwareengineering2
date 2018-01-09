package streamdemo;

import inout.Out;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamDemo04 {
	public static void main(String[] args) {
		List<String> words = new ArrayList<String>();
		words.add("A");
		words.add("B");
		words.add("C");
		words.add("D");
		words.add("E");
		words.add("B");
		words.add("C");

		Stream<String> wordStream = words.stream();
		// ---- distinct, sort and find -------------------------------------

		// findFirst and findAny will just return an Optional of
		// arbitrary/first element when available
		wordStream = words.stream();
		Optional<String> anyWord = wordStream.findAny();
		wordStream = words.stream();
		Optional<String> firstWord = wordStream.findAny();

		if (anyWord.isPresent()) {
			Out.println("Word = " + anyWord.get());
		} else {
			Out.println("No word");
		}

		Out.println(firstWord.orElse("No word"));

		// distinct: compare all elements and remove duplicates using equal
		wordStream = words.stream();
		Stream<String> noDuplicates = wordStream.distinct();

		// sorting: using natural order (= Comparable)
		Stream<String> wordsSorted = noDuplicates.sorted();

		// building string
		String wordsStr = wordsSorted.reduce("", (a, b) -> a + " " + b);
		Out.println("Words: " + wordsStr);

		// building chains of functions: VERY IMPORTANT!
		wordStream = words.stream();
		String ws = wordStream.distinct().sorted()
				.reduce("", (a, b) -> a + " " + b);
		Out.println("Words: " + ws);
	}

}
