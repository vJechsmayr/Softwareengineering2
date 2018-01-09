package at.jku.ssw.swe.relatives;

/**
 * 
 * @author V.Jechsmayr 
 * Datum: 23.04.2016
 *
 */

public enum Gender {
	MALE("M"), FEMALE("F");

	private final String g;

	private Gender(String g) {
		this.g = g;
	}

	public String getGender() {
		return this.g;
	}

}
