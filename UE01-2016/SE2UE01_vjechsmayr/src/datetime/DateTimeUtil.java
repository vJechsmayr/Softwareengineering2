package datetime;

/**
 * This class consists exclusively of static methods that provide utility
 * functions for dealing with date and time.
 */
public class DateTimeUtil {
	
	/**
	 * Tests if the given year is a leap year
	 * 
	 * @param year
	 *            the year given as an integer.
	 * @return <code>true</code> if the year is a leap year,
	 *         <code>false</code> otherwise
	 */
	public static boolean isLeapYear(int year) {
		return year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0);
	}

	/**
	 * Returns the number of days in the given year, i.e., 366 for leap years
	 * and 365 for normal years.
	 * 
	 * @param year
	 *            the year given as an integer.
	 * @return 366 for leap years and 365 for normal years.
	 */
	public static int nrDaysInYear(int year) {
		if (isLeapYear(year)) {
			return 366;
		}
		return 365;
	}

	/**
	 * Returns the number of days for a given month in a specific year. Notice
	 * that the number of days for February is different for leap years and
	 * therefore the year is required as an additional parameter.
	 * 
	 * @param month
	 *            the month given as an integer between 1 and 12.
	 * @param year
	 *            the year given as an integer.
	 * @return the number of days in the given month and year; -1 if month is
	 *         not valid
	 */
	public static int nrDaysInMonth(int month, int year) {
		switch (month) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12: 
			return 31;
		case 4: case 6: case 9: case 11:
			return 30;
		case 2:
			return isLeapYear(year) ? 29 : 28;
		default:
			return -1;
		}
	}

	/**
	 * Tests if the time given by hour and minute represents a valid time. A
	 * time is valid if hour is between 0 and 23 and minute is between 0 and 59
	 * or if hour is 24 and minute is 0.
	 * 
	 * @param hour
	 *            the hour value
	 * @param min
	 *            the minute value
	 * @return true if <code>hour</code> and <code>min</code> represent a
	 *         valid time.
	 */
	public static boolean isValidTime(int hour, int min) {
		return hour >= 0 && hour <= 23 && min >= 0 && min < 60 || hour == 24 && min == 0;
	}

	/**
	 * Computes the minutes passed in a day given a <code>Time</code> value.
	 * The minutes of a day are computed by <code>hour * 60 + min</code>. For
	 * example, for time 10:01 the minutes of a day are 601.
	 * 
	 * @param time
	 *            the <Time> object representing a time.
	 * @return the minutes of a day for the given time.
	 */
	public static int minsOfDayFromTime(Time time) {
		return time.getHour() * 60 + time.getMin();
	}

	/**
	 * Returns the <code>Time</code> object from given minutes of a day. For
	 * example, for minutes of a day of 601 the <code>Time</code> object
	 * representing 10:01 is returned.
	 * 
	 * @param minsOfDay
	 *            the minutes of a day
	 * @return the <code>Time</code> object for the minutes of day.
	 */
	public static Time timeFromMinsOfDay(int minsOfDay) {
		int hour = (minsOfDay / 60) % 24;
		int min = minsOfDay % 60;
		return new Time(hour, min);
	}

	/**
	 * Returns a <code>Time</code> object which represents the time after a
	 * specified time span.
	 * 
	 * @param time
	 *            the time given
	 * @param timeSpan
	 *            the time span given in minutes
	 * @return the <code>Time</code> object representing the time after
	 *         <code>timeSpan</code> minutes.
	 */
	public static Time timeAfter(Time time, int timeSpan) {
		return timeFromMinsOfDay(minsOfDayFromTime(time) + timeSpan);
	}

	/**
	 * Tests if the date given by day, month, and year represents a valid date.
	 * A date is valid if month is between 1 and 12 and day is between 1 and the
	 * number of days in the given month. No restrictions apply for year.
	 * 
	 * @param day
	 *            the day value of the date
	 * @param month
	 *            the month value of the date
	 * @param year
	 *            the year value of the date
	 * @return <code>true</code>, if <code>day</code>, <code>month</code>,
	 *         and <code>year</code> represent a valid date,
	 *         <code>false</code> otherwise.
	 */
	public static boolean isValidDate(int day, int month, int year) {
		return month >= 1 && month <= 12 && day >= 1 && day <= nrDaysInMonth(month, year);
	}

	/**
	 * Computes the integer value which represents the day of year for a given
	 * date. For example for Jan 1st, the day of year is 1, for Feb, 1st the day
	 * of year is 32, and for Dec 31st, 2007 (no leap year) the day of year is
	 * 365.
	 * 
	 * @param date
	 *            the <code>Date</code> object
	 * @return the integer value between 365 (366 for leap years) representing
	 *         the day of year for the given date.
	 */
	public static int dayOfYearFromDate(Date date) {
		int dayOfYear = 0;
		for (int month = 1; month < date.getMonth(); month++) {
			dayOfYear += nrDaysInMonth(month, date.getYear());
		}
		dayOfYear += date.getDay();
		return dayOfYear;
	}

	/**
	 * Returns a <code>Date</code> object for a given day of year and a given
	 * year. For example, for year being 2007 and for a day of year value of 1
	 * the date is Jan 1st, 2007, for a day of year value of 32 the date is Feb
	 * 1st, 2007, and for a day of year value of 365 the date is Dec 31st, 2007.
	 * 
	 * @param dayOfYear
	 *            the day in the year which should be a value between 365 (366
	 *            for leap years)
	 * @param year
	 *            the year value
	 * @return the <code>Date</code> object representing the given day of
	 *         year; <code>null</code> if <code>dayOfYear</code> is not
	 *         valid.
	 */
	public static Date dateFromDayOfYear(int dayOfYear, int year) {
		if (dayOfYear < 1 || dayOfYear > nrDaysInYear(year)) {
			return null;
		}
		int days = 0;
		int month = 1;
		while (days + nrDaysInMonth(month, year) < dayOfYear) {
			days += nrDaysInMonth(month, year);
			month++;
		}
		int day = dayOfYear - days;
		return new Date(day, month, year);
	}

	/**
	 * Returns the <code>Date</code> object for the next day of a given a
	 * <code>Date</code> object. Returns <code>null</code> if the given date
	 * is the last day in the year.
	 * 
	 * @param date
	 *            the <code>Date</code> object given
	 * @return the <code>Date</code> object representing the next day;
	 *         <code>null</code> if the given date is the last day in the
	 *         year.
	 */
	public static Date getNextDate(Date date) {
		int next = dayOfYearFromDate(date);
		next++;
		if (next <= nrDaysInYear(date.getYear())) {
			return dateFromDayOfYear(next, date.getYear());
		}
		return null;
	}

	/**
	 * Returns the <code>Date</code> object for the previous day of a given a
	 * <code>Date</code> object. Returns <code>null</code> if the given date
	 * is the first day in the year.
	 * 
	 * @param date
	 *            the <code>Date</code> object given
	 * @return the <code>Date</code> object representing the previous day;
	 *         <code>null</code> if the given date is the first day in the
	 *         year.
	 */
	public static Date getPreviousDate(Date date) {
		int prev = dayOfYearFromDate(date);
		prev--;
		if (prev > 0) {
			return dateFromDayOfYear(prev, date.getYear());
		}
		return null;
	}
}
