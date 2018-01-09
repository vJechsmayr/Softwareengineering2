package library.app;

import inout.In;
import library.Library;

public class Application {

	private static Library library = null;
	
	public static void main(String[] args) {		
		library = new Library("JKU");		
		operateMainMenu();
	}

	private static void operateMainMenu() {
		char ch;
		do {
			printMainMenu();
			ch = readOperation("aepbisaltcdfq");
			switch (ch) {
			case 'e':
				enrollPerson();
				break;
			case 'p':
				getPersonById();
				break;
			case 'b':
				addBook();
				break;
			case 'i':
				getBookById();
				break;
			case 'a':
				isAvailable();
				break;
			case 'l':
				loanBook();
				break;
			case 't':
				extendLoan();
				break;
			case 'c':
				returnBook();
				break;
			case 'd':
				loanedBooksByPerson();
				break;
			case 'f':
				getOverdueLoans();
				break;
			case 'q':
				break;
			}
		} while (ch != 'q');
	}
	
	private static void printMainMenu() {
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		System.out.println("-------- Bibliotheksverwaltung der " + library.getName() + " -------");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("  ***** Personenverwaltung *****");
		System.out.println("  Person einschreiben ........ e");
		System.out.println("  Person abfragen ............ p");
		System.out.println();
		System.out.println("  ****** Bücherverwaltung ******");
		System.out.println("  Buch hinzufügen ............ b");
		System.out.println("  Buch abfragen .............. i");
		System.out.println();
		System.out.println("  ******* Leiheverwaltung ******");
		System.out.println("  Buch verfügbar? ............ a");
		System.out.println("  Buch ausleihen ............. l");
		System.out.println("  Buch verlängern ............ t");
		System.out.println("  Buch zurückgeben ........... c");
		System.out.println("  Ausgelieh. Bücher je Person  d");
		System.out.println("  Überfällige Bücher ........  f");
		System.out.println();
		System.out.println("  Beenden .................... q");
		System.out.println();
		System.out.print("  Eingabe [e|p|b|i|a|l|t|c|d|f|q]: ");
		System.out.println();		
	}

	private static char readOperation(String validOperations) {
		char ch;
		do {
			ch = Character.toLowerCase(In.readChar());
		} while (validOperations.indexOf(ch) == -1);
		In.readLine();
		return ch;
	}

	private static void enrollPerson() {
		System.out.println();
		System.out.print("Vornamen eingeben: ");
		String firstName = readString();
		System.out.println();
		System.out.print("Nachnamen eingeben: ");
		String lastName = readString();
		System.out.println();
		System.out.print("Adresse eingeben: ");
		String address = readString();
		
		// TODO
	}	

	private static void getPersonById() {
		int personId = getPersonId();

		// TODO
	}	

	private static void addBook() {
		System.out.println();
		System.out.print("Buchtitel eingeben: ");
		String title = readString();
		System.out.println();
		System.out.print("Standort eingeben: ");
		String location = readString();

		// TODO
	}	

	private static void getBookById() {
		int bookId = getBookId();		

		// TODO
	}	

	private static void isAvailable() {
		int bookId = getBookId();		

		// TODO
	}	

	private static void loanBook() {
		int bookId = getBookId();

		// TODO
	}
	
	private static void extendLoan() {
		int bookId = getBookId();

		// TODO
	}

	private static void returnBook() {
		int bookId = getBookId();

		// TODO
	}

	private static void loanedBooksByPerson() {
		int personId = getPersonId();

		// TODO
	}	

	private static void getOverdueLoans() {

		// TODO
	}

	private static int getBookId() {
		System.out.println();
		System.out.println("Buch-Id eingeben: ");
		int bookId = readNumber();
		return bookId;
	}
	
	private static int getPersonId() {
		System.out.println();
		System.out.println("Personen-Id eingeben: ");
		int personId = readNumber();
		return personId;
	}	

	private static int readNumber() {
		int number;
		do {
			number = In.readInt();
		} while (!In.done());
		In.readLine();
		return number;
	}

	private static String readString(){
		String string;
		string = In.readLine();
		return string;
	}
	
}
