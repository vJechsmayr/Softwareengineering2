package datetime;

/**
 * The class <code>Time</code> represents a specific time of day with hour and
 * minutes.
 */
public class Time implements Comparable<Time> {
	private final int hour;
	private final int min;

	/**
	 * Creates a <code>Time</code> object and initializes it with given values
	 * for hour and minutes. It is assumed that hour and minutes represent a
	 * valid time of day.
	 * 
	 * @param hour
	 *            the hour of time between 0 and 24
	 * @param min
	 *            the minutes of time between 0 and 59
	 */
	public Time(int hour, int min) {
		this.hour = hour;
		this.min = min;
	}

	/**
	 * Returns the integer value for the hour of the <code>TIme</code> object.
	 * 
	 * @return the hour as an integer between 0 and 24.
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Returns the integer value for the minutes of the <code>TIme</code>
	 * object.
	 * 
	 * @return the minutes as an integer between 0 and 59.
	 */
	public int getMin() {
		return min;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hour;
		result = prime * result + min;
		return result;
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
		Time other = (Time) obj;
		if (hour != other.hour)
			return false;
		if (min != other.min)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Time t) {
		int compare = getHour() - t.getHour();
		if (compare == 0) {
			compare = getMin() - t.getMin();
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
		return String.format("%1$02d:%2$02d", getHour(), getMin());
	}
}
