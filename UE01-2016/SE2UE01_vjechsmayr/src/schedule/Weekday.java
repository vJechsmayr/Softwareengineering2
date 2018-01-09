package schedule;

/**
 * @author V.Jechsmayr
 * Datum: 03.03.2016
 */

public enum Weekday {

	MONDAY(0), TUESDAY(1), WEDNESDAY(2), THURSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(6);

	private final int value;

	Weekday(final int nVal) {
		value = nVal;
	}

	public int getValue() {
		return value;
	}
}
