package streamdemo;

import inout.Out;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo07 {
	public static void main(String[] args) {
		Stream<String> names = Stream.of("Ann", "Pat", "Mary", "Joe");
		Stream<Integer> posStream;
		// --- flatMap
		// -----------------------------------------------------------
		// used to do mapping and building from stream of streams flat stream

		posStream = Stream.iterate(0, x -> x + 1);
		Out.println("Subsequences 1 to 4:");
		IntStream subSeq = posStream.limit(5).flatMapToInt(
				i -> IntStream.iterate(1, j -> j + 1).limit(i));
		subSeq.forEach(i -> Out.print(i + " "));

		names = Stream.of("Ann", "Pat", "Mary", "Joe");
		IntStream letters = names.flatMapToInt(w -> w.chars());
		Stream<Character> chars = letters
				.mapToObj(i -> new Character((char) i));
		System.out.println();
		chars.forEach(c -> System.out.println(c));
	}
}
