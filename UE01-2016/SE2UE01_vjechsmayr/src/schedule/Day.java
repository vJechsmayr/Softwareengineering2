package schedule;

/**
 * @author V.Jechsmayr
 * Datum: 03.03.2016
 */

import datetime.Time;

class Day {
	LectureList list;

	public Day() {
		list = new LectureList();
	}

	public void add(Lecture l) {
		LectureNode node = list.firstLecture();
		boolean inList = false;
		while (node != null) {
			if (inList == false) {
				if (node.lecture.getName().equals(l.getName())) {
					inList = true;
				}

				node = node.next;
			} else {
				break;
			}
		}

		if (inList == false) {
			list.insertLecture(l);
		} else {
			System.out.println("Name (" + l.getName() + ") bereits in Liste!");
		}
	}

	public void printLectures() {
		if (list.isEmpty()) {
			System.out.println("Keine LVAs vorhanden!");
		} else {
			LectureNode n = list.firstLecture();
			while (n != null) {

				Time lectureEnd = getLectureEnd(n);

				System.out.println(n.lecture.getName() + ": week " + n.lecture.getWeek() + ", on " + n.lecture.getDay()
						+ ", from " + n.lecture.getTime() + " to " + lectureEnd);
				n = n.next;
			}
		}

	}

	private Time getLectureEnd(LectureNode n) {
		int h = n.lecture.getTime().getHour();
		int m = n.lecture.getTime().getMin();
		int d = n.lecture.getDuration();
		int tempM = m + d;

		while (tempM >= 60) {
			h = h + 1;
			tempM = tempM - 60;
		}
		m = tempM;

		return new Time(h, m);
	}

	public Lecture getLectureAfter(Time time) {
		int timeH = time.getHour();
		int timeM = time.getMin();

		LectureNode node = list.firstLecture();
		while (node != null) {
			int tempH = node.lecture.getTime().getHour();
			int tempM = node.lecture.getTime().getMin();

			if (tempH >= timeH) {
				if (tempM >= timeM) {
					return node.lecture;
				}
			}

			node = node.next;
		}

		return null;
	}

	public void removeLecture(String name) {

		list.deleteLecture(name);

	}

	public Lecture getAllOfLecture(String name) {

		return list.getLectureOf(name);
	}
}
