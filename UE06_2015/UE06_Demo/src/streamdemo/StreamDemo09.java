package streamdemo;

import inout.Out;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo09 {
	public static void main(String[] args) {
		Stream<String> names;
		// reduce with combiner and accumulator TBD

		// --- collect --------------------------------------------------------
		// collect is a very similar to reduce as it builds a result from a
		// stream
		// but result is collection
		// collect materializes stream by computing collection as specified by
		// parameters
		// use collect for final result only!

		// <R> R collect(Supplier<R> supplier, BiConsumer<R,? super T>
		// accumulator, BiConsumer<R,R> combiner)
		// supplier: initial collection
		// accumulator: adding elements
		// combiner: combining partially computed collections
		// ==> amenable to parallel execution
		names = Stream.of("Ann", "Pat", "Mary", "Joe");
		List<Integer> listOfLengths = names.collect(
				() -> new ArrayList<Integer>(), (l, e) -> l.add(e.length()), (
						l1, l2) -> l1.addAll(l2));
		names = Stream.of("Ann", "Pat", "Mary", "Joe");
		listOfLengths = names.mapToInt(e -> e.length()).collect(
				ArrayList<Integer>::new, ArrayList<Integer>::add,
				ArrayList<Integer>::addAll);
		Out.println();
		Out.println("List of length 1: ");
		for (int l : listOfLengths) {
			Out.print(l + " ");
		}

		// Collector are objects which do the collection as shown above
		// consist of supplier accumulator, combiner and optionally a finisher
		// static <T,R> Collector<T,R,R> of(Supplier<R> supplier,
		// BiConsumer<R,T> accumulator, BinaryOperator<R> combiner,
		names = Stream.of("Ann", "Pat", "Mary", "Joe");
		listOfLengths = names.collect(Collector.of(
				() -> new ArrayList<Integer>(), (l, e) -> l.add(e.length()), (
						l1, l2) -> {
					l1.addAll(l2);
					return l1;
				}));
		Out.println();
		Out.println("List of length 2: ");
		for (int l : listOfLengths) {
			Out.print(l + " ");
		}

		// Collectors provides convenience functions to create collectors
		names = Stream.of("Ann", "Pat", "Mary", "Joe", "John");
		// Collectors.toList()
		List<String> initialsList = names.map(na -> initial(na)).collect(
				Collectors.toList());
		Out.println();
		Out.println("List of initials: ");
		for (String i : initialsList) {
			Out.print(i + " ");
		}

		// Collectors.toList()
		names = Stream.of("Ann", "Pat", "Mary", "Joe", "John");
		Set<String> initialsSet = names.map(na -> initial(na)).collect(
				Collectors.toCollection(TreeSet::new));
		Out.println();
		Out.println("Set of initials: ");
		for (String i : initialsSet) {
			Out.print(i + " ");
		}
	}

	private static String initial(String n) {
		return n.substring(0, 1) + ".";
	}
}
