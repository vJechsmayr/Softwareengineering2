package schedule;

/**
 * @author V.Jechsmayr
 * Datum: 03.03.2016
 */

import datetime.DateTimeUtil;
import datetime.Time;

public class Lecture {

	// TODO
	private String name;
	private Weekday day;
	private Time time;
	private int duration;
	private int week;

	public Lecture() {
		this("", 0, null, null, 0);
	}

	public Lecture(String name, int week, Weekday day, Time time, int duration) {
		this.name = name;
		this.week = week;
		this.day = day;
		this.time = time;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public Weekday getDay() {
		return day;
	}

	public void setDay(Weekday day) {
		this.day = day;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	private Time getLectureEnd(Time t, int dur) {
		int h = t.getHour();
		int m = t.getMin();
		int d = dur;
		int tempM = m + d;

		while (tempM >= 60) {
			h = h + 1;
			tempM = tempM - 60;
		}
		m = tempM;

		return new Time(h, m);
	}

	@Override
	public String toString() {
		// return this.name + ": (" + this.day.toString() + ", " +
		// this.time.getHour() + ":" + this.time.getMin() + ")";
		return this.name + ": week " + (this.week + 1) + ", on " + this.day.toString() + ", from " + this.time.getHour()
				+ ":" + this.time.getMin() + " to " + getLectureEnd(this.time, this.duration);
	}

}
