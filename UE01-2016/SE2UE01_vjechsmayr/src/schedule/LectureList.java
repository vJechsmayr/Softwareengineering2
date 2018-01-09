package schedule;

/**
 * @author V.Jechsmayr
 * Datum: 04.03.2016
 */

import datetime.Time;

public class LectureList {

	private LectureNode head;

	public LectureList() {
		head = null;
	}

	/**
	 * Returns the first lecture node in the list.
	 * 
	 * @return the first node in the list, null if empty
	 */
	LectureNode firstLecture() {
		return head;
	}

	public LectureNode insertLecture(String name, int week, Weekday day, Time time, int duration) {
		return insertLecture(new Lecture(name, week, day, time, duration));
	}

	public LectureNode insertLecture(Lecture lecture) {
		LectureNode pred = null;
		LectureNode curr = head;

		while (curr != null && curr.lecture.getTime().compareTo(lecture.getTime()) < 0) {
			pred = curr;
			curr = curr.next;
		} // while

		if (curr != null && curr.lecture.getTime().equals(lecture.getTime())) {

			curr.lecture = lecture;
			return curr;
		} else {

			LectureNode node = new LectureNode(lecture);
			if (pred == null) {
				head = node;
			} else {
				pred.next = node;
			}
			node.next = curr;

			return node;
		}
	}

	/**
	 * Looks up the lecture with name.
	 * 
	 * @param name
	 *            the name of the lecture
	 * @return the lecture node found, null if not contained
	 */
	private LectureNode lookup(String name) {
		LectureNode node = head;

		// while (node != null &&
		// node.lecture.getName().compareToIgnoreCase(name) < 0 ) {
		while (node != null && !node.lecture.getName().equalsIgnoreCase(name)) {
			node = node.next;
		} // while

		if (node == null || !node.lecture.getName().equalsIgnoreCase(name)) {
			return null;
		}

		return node;
	}

	public Lecture getLectureOf(String name) {
		LectureNode search;
		try {
			search = lookup(name);
			return search.lecture;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public boolean deleteLecture(String name) {
		LectureNode n = head;
		LectureNode pre = null;
		LectureNode search;

		try {
			search = lookup(name);
		} catch (NullPointerException e) {
			return false;
		}
		if (search == null) {
			System.out.println("Nicht gefunden!");
			return false;
		}

		while (n != null && !search.equals(n)) {
			pre = n;
			n = n.next;
		}

		if (n != null) {
			if (n == head) {
				head = n.next;
				System.out.println("LVA gelöscht!");
				return true;
			} else {
				pre.next = n.next;
				System.out.println("LVA gelöscht!");
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the next entry node for the given current node.
	 * 
	 * @param current
	 *            the given current node
	 * @return the next node after the node current
	 */
	LectureNode nextLecture(LectureNode current) {
		if (current == null) {
			return null; // no next lecture
		}
		return current.next; // return next entry
	} // nextLecture

	/**
	 * Returns true if list is empty, false otherwise.
	 * 
	 * @return true if list is empty, false otherwise
	 */
	boolean isEmpty() {
		return head == null;
	}

}
