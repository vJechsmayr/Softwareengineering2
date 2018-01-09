package streamdemo;

import inout.Out;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo05 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<String> words = new ArrayList<String>();
		words.add("A");
		words.add("B");
		words.add("C");
		words.add("D");
		words.add("E");
		words.add("B");
		words.add("C");

		Stream<String> wordStream;
		final Random r = new Random();
		IntStream randStream;
		IntStream rands20;
		// --- Functions with comparators -------------------------------------

		wordStream = words.stream();
		Stream<String> reverseOrder = wordStream.distinct().sorted(
				(a, b) -> -(a.compareTo(b)));
		Out.println("Words: " + reverseOrder.reduce("", (a, b) -> a + " " + b));

		rands20 = IntStream.generate(() -> r.nextInt(100)).limit(20);
		OptionalInt min = rands20.min();
		min.ifPresent(i -> System.out.println(i));
		rands20 = IntStream.generate(() -> r.nextInt(100)).limit(20);
		OptionalInt max = rands20.max();
		max.ifPresent(i -> System.out.println(i));
		// THIS FAILS:
		// randStream = IntStream.generate(() -> r.nextInt(100));
		// min = randStream.min();
	}
}
