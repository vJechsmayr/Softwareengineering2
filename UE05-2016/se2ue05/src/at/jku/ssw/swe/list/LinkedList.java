package at.jku.ssw.swe.list;

/**
 * 
 * @author V.Jechsmayr 
 * Datum: 16.04.2016
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {

	protected int n = 0;
	protected ListNode<T> head = null;
	protected ListNode<T> tail = null;

	@Override
	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>() {
			ListNode<T> curr = head;

			@Override
			public T next() {
				if (curr != null) {
					ListNode<T> temp = curr;
					curr = curr.getNext();

					return temp.getValue();
				} else {
					throw new NoSuchElementException();
				}

			}

			@Override
			public boolean hasNext() {

				if (curr != null) {
					return true;
				}
				return false;
			}
		};
		return it;
	}

	@Override
	public void add(int index, T value) {

		if (index == 0) {
			add(value);
		} else {
			ListNode<T> temp = new ListNode<T>(value);
			ListNode<T> indexNode = getListNode(index);

			temp.setNext(indexNode);
			temp.setPrev(indexNode.getPrev());
			indexNode.getPrev().setNext(temp);
			indexNode.setPrev(temp);
			n++;
		}
	}

	@Override
	public void add(T value) {
		ListNode<T> temp = new ListNode<T>(value);

		if (size() == 0) {
			head = temp;
			tail = temp;
		} else {
			temp.setNext(head);
			head.setPrev(temp);
			head = temp;
		}
		n++;
	}

	@Override
	public T get(int index) {
		return getListNode(index).getValue();
	}

	@Override
	public T remove(int index) {

		if (size() == 0) {
			throw new IndexOutOfBoundsException("Keine Elemente vorhanden!");
		}

		if (index == size() - 1) {
			return removeLast();
		} else if (index == 0) {
			ListNode<T> temp = head;
			temp.getNext().setPrev(null);
			head = temp.getNext();
			temp.setNext(null);
			n--;
			return temp.getValue();
		} else {
			ListNode<T> indexNode = getListNode(index);

			indexNode.getNext().setPrev(indexNode.getPrev());
			indexNode.getPrev().setNext(indexNode.getNext());
			indexNode.setNext(null);
			indexNode.setPrev(null);
			n--;
			return indexNode.getValue();
		}
	}

	@Override
	public T removeLast() {

		if (size() == 0) {
			throw new IndexOutOfBoundsException("Keine Elemente vorhanden!");
		}

		ListNode<T> temp = tail;

		if (size() == 1) {
			head = null;
			tail = null;
		} else {
			tail.getPrev().setNext(null);
			tail = tail.getPrev();
			temp.setPrev(null);
		}
		n--;

		return temp.getValue();
	}

	@Override
	public int indexOf(T value) {

		ListNode<T> temp = head;

		for (int i = 0; i < size(); i++) {
			if (temp.getValue().equals(value)) {
				return i;
			} else {
				temp = temp.getNext();
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return n;
	}

	@Override
	public <R> List<R> map(Function<? super T, ? extends R> mapper) {
		List<R> result = new LinkedList<R>();

		for (T t : this) {
			result.add(mapper.apply(t));
		}
		return result;
	}

	@Override
	public List<T> filter(Predicate<? super T> predicate) {
		List<T> result = new LinkedList<T>();

		for (T t : this) {
			if (predicate.test(t)) {
				result.add(t);
			}
		}
		return result;
	}

	@Override
	public T reduce(T identity, BinaryOperator<T> accumulator) {
		T result = identity;

		for (T t : this) {
			result = accumulator.apply(result, t);
		}
		return result;
	}

	@Override
	public void forEach(Consumer<? super T> action) {
		for (T t : this) {
			action.accept(t);
		}
	}

	private ListNode<T> getListNode(int index) {
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("Index nicht vorhanden!");
		}

		ListNode<T> curr = head;

		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}

		return curr;
	}

}
