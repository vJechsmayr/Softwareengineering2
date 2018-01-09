package streamdemo;

import inout.Out;

import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo06 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// ==== HOF ===========================================================

		// --- map ------------------------------------------------------------
		Stream<String> names = Stream.of("Ann", "Pat", "Mary", "Joe");
		Stream<String> initials = names.map(a -> initial(a));
		Out.println("Initials:");
		initials.forEach(a -> Out.println(a));
		names = Stream.of("Ann", "Pat", "Mary", "Joe");
		IntStream nameLengths = names.mapToInt(a -> a.length());
		Out.println("Lengths:");
		nameLengths.forEach(a -> Out.println(a));

		// --- filter ---------------------------------------------------------
		final Random r = new Random();
		Stream<Integer> posStream = Stream.iterate(0, x -> x + 1);
		IntStream evens = posStream.limit(100).filter(x -> x % 2 == 0)
				.mapToInt(x -> x);

		// --- find -----------------------------------------------------------

		// findFirst and findAny will just return an Optional of
		// arbitrary/first element when available
		// ==> CAN BE used together with filter to find elements fulfilling
		// certain predicate

		IntStream randStream = IntStream.generate(() -> r.nextInt(100));
		OptionalInt randGreater97 = randStream.filter(x -> x > 97).findFirst();
		Out.println(randGreater97.orElse(-1));

		randStream = IntStream.generate(() -> r.nextInt(100));
		randGreater97 = randStream.filter(x -> x > 97).findAny();
		Out.println(randGreater97.orElse(-1));

		// ==> difference between findFirst and findAny when parallel streams
		// are used
	}

	private static String initial(String n) {
		return n.substring(0, 1) + ".";
	}
}
