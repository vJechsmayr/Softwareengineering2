package schedule;

/**
 * @author V.Jechsmayr
 * Datum: 04.03.2016
 */

import datetime.Time;

public class SemesterScheduler {

	private String nameOfSemester;
	private int nrOfWeeks;
	private Day schedule[][];

	private int numLectures;
	private int capacity;
	private Lecture[] lectures;

	public SemesterScheduler() {
		this("", 0);
		schedule = null;
	}

	public SemesterScheduler(String name, int nr) {
		nameOfSemester = name;
		nrOfWeeks = nr;
		capacity = nr;
		schedule = new Day[nr][7];
		lectures = new Lecture[capacity];

		for (int i = 0; i < nr; i++) {
			for (int j = 0; j < 7; j++) {
				schedule[i][j] = new Day();
			}
		}
		//insertTestLVAs();
	}

	private void insertTestLVAs() {
		System.out.println("Testdaten eingefügt!");
		for (int i = 0; i < nrOfWeeks; i++) {
			schedule[i][3].add(new Lecture("SWE2", i, Weekday.THURSDAY, new Time(8, 30), 90));
			// System.out.println("SWE2 - week " + (i+1) + " on " +
			// Weekday.THURSDAY + " from 8:30 to 10:00");
		}
	}

	public String getName() {
		return nameOfSemester;
	}

	public int getNrWeeks() {
		return nrOfWeeks;
	}

	public void addLecture(Lecture lecture) {
		schedule[lecture.getWeek()][lecture.getDay().getValue()].add(lecture);
	}

	/**
	 * Prints all lectures on the current day in the current week.
	 */
	public void printSchedule(Weekday weekday, int week) {

		if (schedule[week][weekday.getValue()] != null) {
			schedule[week][weekday.getValue()].printLectures();
		}
	}

	public Lecture getNextLectureAfter(int week, Weekday weekday, Time time) {

		if (schedule[week][weekday.getValue()] != null) {
			return schedule[week][weekday.getValue()].getLectureAfter(time);
		}
		return null;
	}

	public void removeLecture(String name, int week, Weekday weekday) {
		if (schedule[week][weekday.getValue()] != null) {
			schedule[week][weekday.getValue()].removeLecture(name);
		}
	}

	public Lecture[] getAllOfLecture(String name) {

		for (int i = 0; i < nrOfWeeks; i++) {
			for (int j = 0; j < 7; j++) {
				LectureNode head = schedule[i][j].list.firstLecture();
				LectureNode curr = null;

				if (head != null) {
					curr = head;

					if (curr.lecture.getName().equals(name)) {
						checkCapacity();
						lectures[numLectures++] = curr.lecture;
					}
				}
			}
		}
		return lectures;
	}

	private void checkCapacity() {
		if (numLectures == lectures.length) {
			int newSize = lectures.length * 2;
			Lecture[] newLectures = new Lecture[newSize];

			System.arraycopy(lectures, 0, newLectures, 0, lectures.length);
			lectures = newLectures;
		}
	}

	public void defineLecture(String name, Weekday weekday, Time time, int duration) {
		for (int i = 0; i < nrOfWeeks; i++) {
			schedule[i][weekday.getValue()].add(new Lecture(name, i, weekday, time, duration));
		}
		
	}
}
