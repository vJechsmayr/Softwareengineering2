package at.jku.ssw.swe.list;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface List<T> extends Iterable<T> {
	void add(int index, T value);

	void add(T value);

	T get(int index);

	T remove(int index);

	T removeLast();

	int indexOf(T value);

	int size();

	<R> List<R> map(Function<? super T, ? extends R> mapper);

	List<T> filter(Predicate<? super T> predicate);

	T reduce(T identity, BinaryOperator<T> accumulator);

	void forEach(Consumer<? super T> action);
}
