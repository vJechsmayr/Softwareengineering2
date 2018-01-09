package at.jku.ssw.swe.relatives;

/**
 * 
 * @author V.Jechsmayr 
 * Datum: 24.04.2016
 *
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Relatives {

	private Map<String, Person> persons;

	public Relatives(String[] data) {
		this.persons = new TreeMap<String, Person>();

		setData(data);
	}

	private void setData(String[] data) {
		String[] tempData = data;

		for (int i = 0; i < tempData.length; i++) {
			String name;
			String gender;

			String[] d = tempData[i].split(" ");

			name = d[0];
			gender = d[1];

			if (gender.equals(Gender.FEMALE.getGender())) {
				Person temp = new Person(name, Gender.FEMALE);
				persons.put(name, temp);
			} else if (gender.equals(Gender.MALE.getGender())) {
				Person temp = new Person(name, Gender.MALE);
				persons.put(name, temp);
			}
		}

		for (int i = 0; i < data.length; i++) {
			String[] d = data[i].split(" ");

			String pers = d[0];
			String vater = d[2];
			String mutter = d[3];
			String partnerin = d[4];

			if (!vater.equals("-")) {
				persons.get(pers).setVater(persons.get(vater));
			}

			if (!mutter.equals("-")) {
				persons.get(pers).setMutter(persons.get(mutter));
			}

			if (!partnerin.equals("-")) {
				persons.get(pers).setPartnerin(persons.get(partnerin));
			}

		}
	}

	public SortedSet<Person> getPersons() {
		return new TreeSet<Person>(persons.values());
	}

	public void printPersons() {

		for (Map.Entry<String, Person> entry : persons.entrySet()) {
			Person p = entry.getValue();

			System.out.print(p.getVorname() + " " + p.getGeschlecht().getGender());

			if (p.getVater() != null) {
				System.out.print(" " + p.getVater().getVorname());
			} else {
				System.out.print(" -");
			}

			if (p.getMutter() != null) {
				System.out.print(" " + p.getMutter().getVorname());
			} else {
				System.out.print(" -");
			}

			if (p.getPartnerin() != null) {
				System.out.print(" " + p.getPartnerin().getVorname());
			} else {
				System.out.print(" -");
			}

			System.out.println("");
		}
	}

	public Map<String, Person> getPersonMap() {
		return Collections.unmodifiableMap(persons);
	}

	public Collection<Person> getAncestors(Person person) {
		// liefert alle Vorfahren der angegeb. Person
		Collection<Person> anc = new ArrayList<Person>();

		if (person.getMutter() != null) {
			anc.add(person.getMutter());
			anc.addAll(getAncestors(person.getMutter()));
		}

		if (person.getVater() != null) {
			anc.add(person.getVater());
			anc.addAll(getAncestors(person.getVater()));
		}
		return Collections.unmodifiableCollection(anc);
	}

	public Collection<Person> getDescendants(Person person) {
		// liefert alle Nachkommen der angegeb. Person

		Collection<Person> desc = new ArrayList<Person>();

		for (Person p : persons.values()) {
			if (p.getMutter() != null && p.getVater() != null) {
				if (p.getMutter().equals(person) || p.getVater().equals(person)) {
					desc.add(p);
					desc.addAll(getDescendants(p));
				}
			}

		}
		return Collections.unmodifiableCollection(desc);
	}

	public Map<Person, Integer> getNumberOfAncestors() {
		// liefert eine Abbildung von Person auf Anzahl Vorfahren

		Map<Person, Integer> pers = new TreeMap<Person, Integer>();

		for (Person p : persons.values()) {
			int count = getAncestors(p).size();
			pers.put(p, count);
		}
		return Collections.unmodifiableMap(pers);
	}

	public Map<Person, Integer> getNumberOfDescendants() {
		Map<Person, Integer> pers = new TreeMap<Person, Integer>();

		for (Person p : persons.values()) {
			int count = getDescendants(p).size();
			pers.put(p, count);
		}
		return Collections.unmodifiableMap(pers);
	}

	public Collection<Person> getCommonAncestors(Collection<Person> pers) {
		// liefert für mehrere Personen die gemeinsamen Vorfahren

		Collection<Person> canc = getPersons();

		for (Person p : pers) {
			Collection<Person> anc = getAncestors(p);

			canc.retainAll(anc);
		}
		return Collections.unmodifiableCollection(canc);
	}

	public Collection<Person> getCommonDescendants(Collection<Person> pers) {
		// liefert für mehrere Personen die gemeinsamen Nachkommen

		Collection<Person> cdesc = getPersons();

		for (Person p : pers) {
			Collection<Person> desc = getDescendants(p);
			cdesc.retainAll(desc);
		}

		return Collections.unmodifiableCollection(cdesc);
	}

	public Comparator<Person> getAncestorComparator() {
		// liefert Comparator<Person> der zuerst nach Anzahl d.
		// Vorfahren(absteigend), dann nach Namen (aufsteigend) sortiert

		Comparator<Person> cp = new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {

				Map<Person, Integer> mi = getNumberOfAncestors();

				if (mi.get(p2).equals(mi.get(p1))) {
					return p1.getVorname().compareTo(p2.getVorname());
				}
				return mi.get(p2).compareTo(mi.get(p1));
			}
		};
		return cp;
	}

	public Comparator<Person> getDescendantComparator() {
		// liefert Comparator<Person> der zuerst nach Anzahl d.
		// Nachkommen(absteigend), dann nach Namen (aufsteigend) sortiert
		Comparator<Person> cp = new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {

				Map<Person, Integer> mi = getNumberOfDescendants();

				if (mi.get(p2).equals(mi.get(p1))) {
					return p1.getVorname().compareTo(p2.getVorname());
				}
				return mi.get(p2).compareTo(mi.get(p1));
			}
		};
		return cp;
	}

}
