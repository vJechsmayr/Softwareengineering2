package streamdemo;

import inout.Out;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo03 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// ==== simple functions ============================================
		List<String> words = new ArrayList<String>();
		words.add("A");
		words.add("B");
		words.add("C");
		words.add("D");
		words.add("E");
		words.add("B");
		words.add("C");

		Stream<String> wordStream = words.stream();

		Stream<String> names = Stream.of("Ann", "Pat", "Mary", "Joe");

		Stream<String> single = Stream.of("Me");

		Stream<String> all = Stream.concat(single, names);

		final Random r = new Random();
		IntStream randStream = IntStream.generate(() -> r.nextInt(100));
		// ---- count -------------------------------------------------------

		long n = wordStream.count();

		// cannot iterate function twice
		// THIS FAILS:
		// n = wordStream.count();
		// count leads to infinite loop on infinite streams

		randStream = IntStream.generate(() -> r.nextInt(100));
		// DON'T DO THAT:
		// long countRands = randStream.count();

		// ---- limit and skip and toArray ------------------------------------

		// limit allows to take a number of elements from the stream

		Out.println();
		Out.println("20 random numbers: ");
		IntStream rands20 = randStream.limit(20);
		for (int x : rands20.toArray()) {
			Out.println(x);
		}

		// skip will skip a number of elements from a stream
		Stream<String> withoutFirst = all.skip(1);
		Out.println();
		Out.println("all without first: ");
		for (String a : withoutFirst.toArray(String[]::new)) {
			Out.println(a);
		}

	}
}
