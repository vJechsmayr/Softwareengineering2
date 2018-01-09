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
import java.util.List;
import java.util.Map;

public class RelativesTest {

	private static Relatives relatives = new Relatives(PersonData.DATA);

	public static void main(String[] args) {

		System.out.print("Descendants of Pauletta: ");
		printPersons(relatives.getDescendants(relatives.getPersonMap().get("Pauletta")));

		System.out.print("Descendants of Darius: ");
		printPersons(relatives.getDescendants(relatives.getPersonMap().get("Darius")));

		System.out.print("Descendants of Joni: ");
		printPersons(relatives.getDescendants(relatives.getPersonMap().get("Joni")));

		Collection<Person> colDarJo = new ArrayList<Person>();
		colDarJo.add(relatives.getPersonMap().get("Darius"));
		colDarJo.add(relatives.getPersonMap().get("Joni"));
		System.out.print("Common Descendants of Darius + Joni: ");
		printPersons(relatives.getCommonDescendants(colDarJo));

		Collection<Person> colAdLe = new ArrayList<Person>();
		colAdLe.add(relatives.getPersonMap().get("Adelia"));
		colAdLe.add(relatives.getPersonMap().get("Lelah"));
		System.out.print("Common Ancestors of Adelia + Lelah: ");
		printPersons(relatives.getCommonAncestors(colAdLe));

		List<Person> csort = new ArrayList<Person>(relatives.getPersons());
		Collections.sort(csort, relatives.getDescendantComparator());
		System.out.print("Sorted by number of descendants and name: ");
		printPersons(csort);

		Map<Person, Integer> numtwo = relatives.getNumberOfAncestors();
		Collection<Person> coltwo = new ArrayList<Person>();
		for (Person p : numtwo.keySet()) {
			if (numtwo.get(p) == 2) {
				coltwo.add(p);
			}
		}
		System.out.print("Persons with exactly 2 ancestors: ");
		printPersons(coltwo);

		Map<Person, Integer> a1 = relatives.getNumberOfAncestors();
		Map<Person, Integer> a2 = relatives.getNumberOfDescendants();
		Person allena = relatives.getPersonMap().get("Allena");
		int asize = a1.get(allena) + a2.get(allena);
		System.out.print("Family-Size of Allena: " + asize);
		System.out.print(" (Ancestors: " + a1.get(allena));
		System.out.println(" & Descendants: " + a2.get(allena) + ")");

		Collection<Person> all = relatives.getPersons();
		all.removeIf(p -> p.getGeschlecht().equals(Gender.MALE));
		all.removeIf(p -> relatives.getDescendants(p).size() > 0);
		List<Person> foksort = new ArrayList<Person>(all);
		Collections.sort(foksort, relatives.getAncestorComparator());
		System.out.print("Woman without Children: ");
		printPersons(foksort);

	}

	private static void printPersons(Collection<Person> p) {
		System.out.print("[");
		boolean first = true;
		for (Person px : p) {
			if (!first) {
				System.out.print(", ");
			}
			System.out.print(px.getVorname());
			first = false;
		}
		System.out.println("]");
	}
}
