package schedule.app;
/**
 * @author V.Jechsmayr
 * Datum: 03.03.2016
 */

import datetime.DateTimeUtil;
import datetime.Time;
import inout.In;
import inout.Out;
import schedule.Lecture;
import schedule.SemesterScheduler;
import schedule.Weekday;

/**
 * Application class for working with semester schedule. The class allows to
 * read in different operation codes from the console and execute operations for
 * defining and reading the semester schedule.
 */
public class SchedulerApplication {

	/**
	 * Starts the application by creating a semester scheduler and starting it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new SchedulerApplication("2016S", 17).interact();
	}

	/** the semester scheduler */
	private SemesterScheduler semester;
	/** the current week */
	private int week;
	/** the current weekday */
	private Weekday weekday;

	/**
	 * Constructor defining a name of the semester scheduler and the number of
	 * weeks.
	 * 
	 * @param name
	 *            the name of the semester schedule
	 * @param nrWeeks
	 *            the number of weeks in this semester
	 */
	SchedulerApplication(String name, int nrWeeks) {
		semester = new SemesterScheduler("SS2016", 17);
		week = 1; // set current week to first week
		weekday = Weekday.MONDAY;
	}

	/**
	 * Implements the main interaction loop.
	 */
	public void interact() {
		// Print operations
		Out.println("Semesterplan ");
		Out.println("============ ");
		Out.println("Folgende Operationen stehen zur Verfügung: ");
		Out.println("  D - Definition einer LVA für das Semester ");
		Out.println("  W - Bestimmen einer Woche ");
		Out.println("  T - Bestimmen eines Tages der Woche ");
		Out.println("  E - Einfügen eines LVA beim aktuellen Tag ");
		Out.println("  L - Löschen einer LVA des aktuellen Tages ");
		Out.println("  A - Ausgeben LVAs des aktuellen Tages ");
		Out.println("  N - Ausgeben der nächsten LVA  ");
		Out.println("  S - Ausgeben der Semestertermine einer LVA  ");
		Out.println("  X - Programm beenden ");
		Out.println();

		// Read operation code and perform operation
		char op = readOperation();
		while (op != 'X') {
			switch (op) {
			case 'D':
				defineLecture();
				break;
			case 'W':
				gotoWeek();
				printLecturesOfDay();
				break;
			case 'T':
				gotoDay();
				printLecturesOfDay();
				break;
			case 'E':
				addLectureOnDay();
				printLecturesOfDay();
				break;
			case 'L':
				removeLecture();
				printLecturesOfDay();
				break;
			case 'A':
				printLecturesOfDay();
				break;
			case 'N':
				printNextAfter();
				break;
			case 'S':
				printAllOfLecture();
				break;
			}
			op = readOperation();
		}

		Out.println();
		Out.println("Auf ein Wiedersehen freut sich Dein Semesterplaner!");
	} // main

	// Methods implementing main operations -----------------------------------

	/**
	 * Defines a lecture for the whole semester.
	 */
	private void defineLecture() {
		Out.println("Eingabe einer LVA");
		String name = readName("LVA Name");
		Weekday weekday = readWeekday();
		Time time = readTime("Zeit");
		int duration = readInt("Dauer");

		System.out.println("--------");
		System.out.println("  " + name + " " + weekday + " " + time + " " + duration + " " + week);
		System.out.println("--------");

		//semester.addLecture(new Lecture(name, week, weekday, time, duration));
		semester.defineLecture(name, weekday, time, duration);
		
	}

	/**
	 * Sets current week.
	 */
	private void gotoWeek() {
		int week = readInt("Woche");
		while (week < 1 || week >= semester.getNrWeeks()) {
			Out.println();
			Out.print("Ungültige Woche! Bitte Eingabe wiederholen: ");
			week = readInt("Woche");
		}
		this.week = week;
	}

	/**
	 * Sets current day.
	 */
	private void gotoDay() {
		this.weekday = readWeekday();
	}

	/**
	 * Adds a new lecture for the current day in the current week.
	 */
	private void addLectureOnDay() {
		Out.println("Eingabe einer LVA für diesen Tag");
		String name = readName("LVA Name");
		Time time = readTime("Zeit");
		int duration = readInt("Dauer");

		semester.addLecture(new Lecture(name, week, weekday, time, duration));
	}

	/**
	 * Removes a lecture on the current day in the current week.
	 */
	private void removeLecture() {
		Out.println("Löschen einer LVA für diesen Tag");
		String name = readName("LVA Name");

		// TODO: Remove lecture with given name in current week and at current
		// weekday
		semester.removeLecture(name, week, weekday);
	}

	/**
	 * Prints all lectures on the current day in the current week.
	 */
	private void printLecturesOfDay() {
		Out.println();
		Out.println(String.format("Day: %1$s, week %2$d", weekday, week));

		semester.printSchedule(weekday, week);

		Out.println();
	}

	/**
	 * Prints the next lecture on the current day in the current week starting
	 * after some given time.
	 */
	private void printNextAfter() {
		Out.println("Nächste LVA für diesen Tag");
		Time time = readTime("Zeit");
		Lecture l = semester.getNextLectureAfter(week, weekday, time);

		if (l != null) {
			printLecture(l);
		} else {
			System.out.println("Keine LVAs an diesem Tag mehr!");
		}
	}

	/**
	 * Prints all the lectures in the whole semester.
	 */
	private void printAllOfLecture() {
		Out.println("Termine einer LVA im Semester");
		String name = readName("LVA Name");
		Lecture[] lectures = null; // TODO: Get all lecture dates for the
									// lecture with given name

		lectures = semester.getAllOfLecture(name);

		printLectures(lectures);
	}

	// Auxiliary methods ------------------------------------------------------

	/**
	 * Prints a lecture on the console.
	 * 
	 * @param l
	 *            the lecture to print
	 */
	private void printLecture(Lecture l) {
		Out.println("  " + l.toString());
	}

	/**
	 * Prints an array of lectures on the console.
	 * 
	 * @param lectures
	 *            the lectures to print
	 */
	private void printLectures(Lecture[] lectures) {
		Out.println();
		for (Lecture l : lectures) {
			printLecture(l);
		}
		Out.println();
	}

	/**
	 * Reads the operation code from the console.
	 * 
	 * @return the operation code
	 */
	private static char readOperation() {
		Out.println();
		Out.print("Bitte Operation auswaehlen (D, W, T, E, L, A, N, S, X): ");

		char op = Character.toUpperCase(In.readChar());
		while (op != 'D' && op != 'W' && op != 'T' && op != 'E' && op != 'L' && op != 'A' && op != 'N' && op != 'S'
				&& op != 'X') {
			// wrong operation code, repeat input of operation code
			Out.println();
			Out.print("Falsche Eingabe! Bitte Eingabe wiederholen (D, W, T, E, L, A, N, S, X): ");
			op = Character.toUpperCase(In.readChar());
		}
		return op;
	}

	/**
	 * Reads in a weekday from the console.
	 * 
	 * @return the weekday
	 */
	private Weekday readWeekday() {

		Out.print("Wochentag (1-7)");
		int val = In.readInt();

		while (val < 1 || val > 7) {
			Out.print("Fehlerhafte Eingabe!");
			val = In.readInt();
		}

		return Weekday.values()[val - 1];
	}

	/**
	 * Reads in a name from the console.
	 * 
	 * @param msg
	 *            the message prompt
	 * @return the name read
	 */
	private static String readName(String msg) {
		Out.print("   " + msg + ": ");
		String name = In.readWord();
		return name;
	}

	/**
	 * Reads in an integer value.
	 * 
	 * @param msg
	 *            the message prompt
	 * @return the integer read
	 */
	private static int readInt(String msg) {
		Out.print("   " + msg + ": ");
		int number = In.readInt();
		return number;
	}

	/**
	 * Reads in a time from the console.
	 * 
	 * @param msg
	 *            the message prompt
	 * @return the time read
	 */
	private static Time readTime(String msg) {
		Out.print("   Bitte " + msg + " eingeben (hh:mm): ");
		int hour = In.readInt();
		In.read();
		int min = In.readInt();
		while (!DateTimeUtil.isValidTime(hour, min)) {
			Out.println("Falsche Zeitangabe! Bitte wiederholen!");
			Out.print("   Bitte " + msg + " eingeben (hh:mm): ");
			hour = In.readInt();
			In.read();
			min = In.readInt();
		}
		return new Time(hour, min);
	}

}
