package at.jku.ssw.swe.relatives;

/**
 * 
 * @author V.Jechsmayr 
 * Datum: 23.04.2016
 *
 */

public class Person implements Comparable<Person> {

	private final String vorname;
	private Gender geschlecht;
	private Person vater;
	private Person mutter;
	private Person partnerin;

	public Person(String name, Gender geschlecht) {
		this.vorname = name;
		this.geschlecht = geschlecht;
		this.vater = null;
		this.mutter = null;
		this.partnerin = null;
	}

	public String getVorname() {
		return vorname;
	}

	public Gender getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(Gender geschlecht) {
		this.geschlecht = geschlecht;
	}

	public Person getVater() {
		return vater;
	}

	public void setVater(Person vater) {
		this.vater = vater;
	}

	public Person getMutter() {
		return mutter;
	}

	public void setMutter(Person mutter) {
		this.mutter = mutter;
	}

	public Person getPartnerin() {
		return partnerin;
	}

	public void setPartnerin(Person partnerin) {
		this.partnerin = partnerin;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Person)) {
			return false;
		}

		final Person other = (Person) obj;
		if ((this.vorname == null) ? (other.vorname != null) : !this.vorname.equals(other.vorname)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return vorname.hashCode();
	}

	@Override
	public int compareTo(Person other) {

		/*
		 * if(!this.vorname.equals(other.vorname)) { return
		 * this.vorname.compareTo(other.vorname); }
		 */
		return this.vorname.compareTo(other.vorname);
	}
}
