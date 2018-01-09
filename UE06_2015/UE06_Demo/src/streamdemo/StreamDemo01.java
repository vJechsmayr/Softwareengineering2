package streamdemo;

import inout.Out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo01 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// ==== generating streams ============================================

		// from collections

		List<String> words = new ArrayList<String>();
		words.add("A");
		words.add("B");
		words.add("C");
		words.add("D");
		words.add("E");
		words.add("B");
		words.add("C");

		Stream<String> wordStream = words.stream();

		// from arrays

		int[] numbers = new int[] { 1, 2, 3 };
		IntStream numberStream = Arrays.stream(numbers);

		// with static methods

		Stream<String> names = Stream.of("Ann", "Pat", "Mary", "Joe");

		Stream<String> single = Stream.of("Me");

		Stream<String> none = Stream.empty();

		Stream<String> all = Stream.concat(single, names);

		IntStream range0_99 = IntStream.range(0, 100);
		IntStream range0_100 = IntStream.rangeClosed(0, 100);

		// by generator functions

		Stream<Integer> posStream = Stream.iterate(0, x -> x + 1);

		final Random r = new Random();
		IntStream randStream = IntStream.generate(() -> r.nextInt(100));
		randStream.limit(100).forEach(x -> Out.println(x));
	}
}
