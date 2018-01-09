package datetime;

/**
 * The class <code>Date</code> represents a specific date with day, month, and
 * year.
 */
public class Date implements Comparable<Date> {

	private final int day;
	private final int month;
	private final int year;

	/**
	 * Creates a <code>Date</code> object and initializes it with given values
	 * for day, month and year. It is assumed that day, month, and year
	 * represent a valid date.
	 * 
	 * @param day
	 *            the month between 1-31.
	 * @param month
	 *            the month between 1-12.
	 * @param year
	 *            the year as an integer (e.g. 2008).
	 */
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
	 * Returns the integer value for the year of the <code>Date</code> object.
	 * 
	 * @return the year as an integer (e.g. 2008).
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Returns the integer value for the month of the <code>Date</code>
	 * object.
	 * 
	 * @return the month as an integer between 1 and 12.
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Returns the integer value for the day of the <code>Date</code> object.
	 * 
	 * @return the day as an integer between 1 and 31.
	 */
	public int getDay() {
		return day;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Date d) {
		int compare = getYear() - d.getYear();
		if (compare == 0) {
			compare = getMonth() - d.getMonth();
		}
		if (compare == 0) {
			compare = getDay() - d.getDay();
		}
		return compare;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%1$d.%2$d.%3$d.", getDay(), getMonth(), getYear());
	}
}
