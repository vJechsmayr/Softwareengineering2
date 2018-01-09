package streamdemo;

import inout.Out;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamDemo08 {
	public static void main(String[] args) {
		Stream<String> names;
		// ==== collect and reduce ============================================

		// --- reduce ---------------------------------------------------------

		// reduce taking first parameter as start
		names = Stream.of("Ann", "Pat", "Mary", "Joe");
		String namesConcat = names.reduce("", (a, b) -> a + " " + b);
		Out.println();
		Out.println("Reduce 1: " + namesConcat);

		// reduce taking first element as start
		// returns an Optional with empty for empty stream
		names = Stream.of("Ann", "Pat", "Mary", "Joe");
		Optional<String> namesOpt = names.reduce((a, b) -> a + ", " + b);
		Out.println("Reduce 2: " + namesOpt.orElse("EMPTY"));

		// using reduce to sum length of names
		names = Stream.of("Ann", "Pat", "Mary", "Joe");
		int totalLength = names.mapToInt(na -> na.length()).reduce(0,
				(a, b) -> a + b);
		Out.println("TotalLength 1: " + totalLength);

		// sum
		names = Stream.of("Ann", "Pat", "Mary", "Joe");
		totalLength = names.mapToInt(na -> na.length()).sum();
		Out.println("TotalLength 2: " + totalLength);
	}
}
