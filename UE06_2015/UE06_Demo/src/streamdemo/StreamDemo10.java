package streamdemo;

import inout.Out;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo10 {
	public static void main(String[] args) {
		Stream<String> names;
		// grouping builds maps (VERY USEFUL!!)

		names = Stream.of("Ann", "Pat", "Mary", "Joe", "John");
		Map<String, List<String>> byInitials = names.collect(Collectors
				.groupingBy(e -> initial(e)));
		printGroupings(byInitials);

		names = Stream.of("Ann", "Pat", "Mary", "Joe", "John");
		Map<Integer, List<String>> byLength = names.collect(Collectors
				.groupingBy(e -> e.length()));
		printGroupings(byLength);

		// show other very useful methods of Collectors in API documentation:
		// e.g., averagingDouble(ToDoubleFunction<? super T> mapper)
		names = Stream.of("Ann", "Pat", "Mary", "Joe", "John");
		double avrgLength = names.collect(Collectors.averagingDouble(na -> na
				.length()));
		Out.println("Avrg length: " + avrgLength);

		// e.g., counting(),
		// joining(), joining(delimator), joining(CharSequence delimiter,
		// CharSequence prefix, CharSequence suffix)
		// maxBy(Comparator<? super T> comparator)
		// partitioningBy(Predicate<? super T> predicate)
		// reducing(BinaryOperator<T> op)
		// summarizingDouble(ToDoubleFunction<? super T> mapper)
		// toMap, toSet, toConcurrentMap, ...

		// ==> most tasks can be performed by those Collectors !!!

		// ==== quantifiers ===================================================

		// --- allMatch, anyMatch, noneMatch ----------------------------------

		names = Stream.of("Ann", "Pat", "Mary", "Joe", "John");
		boolean noneEmpty = names.noneMatch(na -> na.length() == 0);
		Out.println("None empty: " + noneEmpty);

		names = Stream.of("Ann", "Pat", "Mary", "joe", "John");
		boolean allUpperCase = names.allMatch(na -> na.length() > 0
				&& Character.isUpperCase(na.charAt(0)));
		Out.println("All upper case: " + allUpperCase);
	}

	private static String initial(String n) {
		return n.substring(0, 1) + ".";
	}

	private static <K, V> void printGroupings(Map<K, List<V>> groupings) {
		StringBuilder b;
		Out.println();
		for (K k : groupings.keySet()) {
			b = new StringBuilder();
			b.append(k);
			b.append(": ");
			for (V v : groupings.get(k)) {
				b.append(v);
				b.append(" ");
			}
			Out.println(b.toString());
		}

	}
}
