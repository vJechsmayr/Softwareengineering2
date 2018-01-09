package at.jku.ssw.swe.list.test;

/**
 * 
 * @author V.Jechsmayr 
 * Datum: 16.04.2016
 *
 */

import static org.junit.Assert.*;

import org.junit.Test;

import at.jku.ssw.swe.list.LinkedList;
import at.jku.ssw.swe.list.List;

public class LinkedListTest {

	@Test
	public void add() {
		List<String> l = new LinkedList<>();

		l.add("A");
		l.add("B");

		assertEquals(2, l.size());
		assertEquals("A", l.get(1));
		assertEquals("B", l.get(0));
	}

	@Test
	public void addIndex() {
		List<String> l = new LinkedList<>();

		l.add("A");
		l.add("B");
		l.add(1, "C");

		assertEquals(3, l.size());
		assertEquals("A", l.get(2));
		assertEquals("B", l.get(0));
		assertEquals("C", l.get(1));
	}

	@Test
	public void remove() {
		List<String> l = new LinkedList<>();

		l.add("A");
		l.add("B");
		l.add(1, "C");

		try {
			l.remove(-1);
			fail("Ungültiger Index");
		} catch (IndexOutOfBoundsException e1) {
		}

		l.remove(1);

		assertEquals(2, l.size());
		assertEquals("A", l.get(1));
		assertEquals("B", l.get(0));

		l.remove(0);
		assertEquals(1, l.size());
		assertEquals("A", l.get(0));

		l.remove(0);
		assertEquals(0, l.size());

		try {
			l.remove(0);
			fail("Kein Element!");
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void removeLast() {
		List<String> l = new LinkedList<>();

		l.add("A");
		l.add("B");
		l.add(1, "C");
		l.removeLast();

		assertEquals(2, l.size());
		assertEquals("B", l.get(0));
		assertEquals("C", l.get(1));

		l.removeLast();
		assertEquals(1, l.size());
		assertEquals("B", l.get(0));

		l.removeLast();
		assertEquals(0, l.size());

		try {
			l.removeLast();
			fail("Kein Element!");
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void indexOf() {
		List<String> l = new LinkedList<>();

		l.add("A");
		l.add("B");
		l.add(1, "C");

		assertEquals(0, l.indexOf("B"));
		assertEquals(1, l.indexOf("C"));
		assertEquals(2, l.indexOf("A"));

		assertEquals(-1, l.indexOf("Z"));
	}

	@Test
	public void map() {
		List<String> l = new LinkedList<>();
		l.add("A");
		l.add("AA");
		l.add("AAA");

		List<Integer> i = l.map(s -> s.length());

		assertEquals(3, i.size());
		assertEquals(new Integer(1), i.get(0));
		assertEquals(new Integer(2), i.get(1));
		assertEquals(new Integer(3), i.get(2));

	}

	@Test
	public void filter() {
		List<Integer> l = new LinkedList<>();

		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);

		List<Integer> i = l.filter(m -> m % 2 == 0); // {2 4}

		assertEquals(2, i.size());
		assertEquals(new Integer(2), i.get(0));
		assertEquals(new Integer(4), i.get(1));
	}

	@Test
	public void reduce() {
		List<Integer> l = new LinkedList<>();

		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);

		Integer i = l.reduce(0, (a, b) -> a + b); // 10

		assertEquals(new Integer(10), i);
	}

	@Test
	public void forEach() {
		List<String> l = new LinkedList<>();
		l.add("A");
		l.add("BB");
		l.add("CCC");

		StringBuilder s = new StringBuilder();

		l.forEach(r -> s.append(r));

		assertEquals("CCCBBA", s.toString());

	}
}
