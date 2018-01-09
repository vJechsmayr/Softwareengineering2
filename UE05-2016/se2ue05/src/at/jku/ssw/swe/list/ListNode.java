package at.jku.ssw.swe.list;

/**
 * 
 * @author V.Jechsmayr 
 * Datum: 16.04.2016
 *
 */

public class ListNode<T> {
	private final T value;
	private ListNode<T> next;
	private ListNode<T> prev;

	public ListNode(T value) {
		this(value, null, null);
	}

	public ListNode(T value, ListNode<T> next) {
		this(value, next, null);
	}

	public ListNode(T value, ListNode<T> next, ListNode<T> prev) {
		this.value = value;
		this.next = next;
		this.prev = prev;
	}

	public void setNext(ListNode<T> node) {
		this.next = node;
	}

	public void setPrev(ListNode<T> node) {
		this.prev = node;
	}

	public ListNode<T> getNext() {
		return next;
	}

	public ListNode<T> getPrev() {
		return prev;
	}

	public T getValue() {
		return value;
	}

}
