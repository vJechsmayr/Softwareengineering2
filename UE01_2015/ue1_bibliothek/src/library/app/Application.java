package library.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import inout.In;
import library.BookNode;
import library.LendNode;
import library.Library;
import library.PersonNode;

public class Application {

	private static Library library = null;
	private static int personC = 1; //personID-Counter
	private static int bookC = 1; //personID-Counter
	
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
		PersonNode node = new PersonNode(personC, firstName, lastName, address);
		
		library.addPersonNode(node);
		personC++;
	}	

	private static void getPersonById() {
		int personId = getPersonId();

		try
		{
			PersonNode node = library.getPersonHead();
			while(node != null)
			{
				if(node.getID() != personId)
				{
					node = node.getNext();
				}else
				{
					System.out.println(node.getID() + " " + node.getfirstname() + " " + node.getlastname());
					node = null;
				}
			}
		}catch(Exception e)
		{
			System.out.println("Keine Person mit dieser Nr vorhanden.");
		}
	}	

	private static void addBook() {
		System.out.println();
		System.out.print("Buchtitel eingeben: ");
		String title = readString();
		System.out.println();
		System.out.print("Standort eingeben: ");
		String location = readString();

		// TODO
		BookNode node = new BookNode(bookC, title, location);
		library.addBookNode(node);
		bookC++;
	}	

	private static void getBookById() {
		int bookId = getBookId();		

		try
		{
			BookNode node = library.getBookHead();
			while(node != null)
			{
				if(node.getID() != bookId)
				{
					node = node.getNext();
				}else
				{
					System.out.println(node.getID() + " " + node.getTitle() + " " + node.getLocation());
					node = null;
				}
			}
		}catch(Exception e)
		{
			System.out.println("Kein Buch mit dieser Nr vorhanden.");
		}
	}	

	private static void isAvailable() {
		int bookId = getBookId();		
		boolean available = false;
		
		BookNode node = library.getBookHead();
		while(node != null)
		{
			if(node.getID() != bookId)
			{
				node = node.getNext();
			}else
			{
				System.out.println("ID: " + node.getID());
				System.out.println("Titel: " + node.getTitle());
				System.out.println("Location: " + node.getLocation());
				available = node.getAvailable();
				
				if(available == true)
				{
					System.out.println("Das Buch ist verfügbar!");
				}else
				{
					LendNode lNode = library.getLendings();
					while(lNode != null)
					{
						if(lNode.getBook().getID() != bookId)
						{
							lNode = lNode.getNext();
						}else
						{
							System.out.println("Rückgabedatum: " + new SimpleDateFormat("dd.MM.yyy").format(lNode.getDate()));		
							lNode = null;
						}
					}//end while
					System.out.println("Das Buch ist nicht verfügbar!");
				}
				node = null;
			}
		}//end while
	}	

	private static void loanBook() {
		int bookId = getBookId();
		int personId = getPersonId();
		
		// TODO
		PersonNode pNode = library.getPersonHead();
		BookNode bNode = library.getBookHead();
		
		PersonNode node1 = null;
		BookNode node2 = null;
		while(pNode != null)
		{
			if(pNode.getID() != personId)
			{
				pNode = pNode.getNext();
			}else
			{
				node1 = new PersonNode(pNode.getID(),pNode.getfirstname(), pNode.getlastname(), pNode.getaddress());
				pNode = null;
			}
		}
		while(bNode != null)
		{
			if(bNode.getID() != bookId)
			{
				bNode = bNode.getNext();
			}else
			{
				node2 = new BookNode(bNode.getID(), bNode.getTitle(), bNode.getLocation());
				bNode = null;
			}
		}
		
		LendNode lNode = new LendNode(node1, node2);
		library.addLending(lNode);
		node2.setAvailable(false);
		library.setLent(node2.getID());
	}
	
	private static void extendLoan() {
		int bookId = getBookId();		
		boolean available = false;
		
		BookNode node = library.getBookHead();
		while(node != null)
		{
			if(node.getID() != bookId)
			{
				node = node.getNext();
			}else
			{
				available = node.getAvailable();
				
				if(available == false)
				{
					LendNode lNode = library.getLendings();
					while(lNode != null)
					{
						if(lNode.getBook().getID() != bookId)
						{
							lNode = lNode.getNext();
						}else
						{
							lNode.setDate();
							lNode = null;
						}
					}//end while
				}
				node = null;
			}
		}//end while
	}

	private static void returnBook() {
		int bookId = getBookId();
		LendNode node = library.getLendings();
		
		try
		{
			while(node != null && node.getBook().getID() != bookId)
			{
				node = node.getNext();
			}
			node.setNext(node.getNext());
			library.setAvailable(node.getBook().getID());
		}catch(Exception e)
		{
			System.out.println("Buch kann nicht zurückgegeben werden!");
		}
	}

	private static void loanedBooksByPerson() {
		int personId = getPersonId();

		PersonNode p = library.getPersonHead();
		while(p != null)
		{
			if(p.getID() != personId)
			{
				p = p.getNext();
			}else
			{
				System.out.println("ID: " + p.getID());
				System.out.println("Vorname: " + p.getfirstname());
				System.out.println("Nachname: " + p.getlastname());
				System.out.println("Adresse: " + p.getaddress());
				p = p.getNext();
			}
		}
		
		LendNode node = library.getLendings();

		while(node != null)
		{
			if(node.getPerson().getID() != personId)
			{
				node = node.getNext();
			}else
			{
				System.out.println("  -BuchID: " + node.getBook().getID());
				System.out.println("  -Titel: " + node.getBook().getTitle());
				System.out.println("  -Location: " + node.getBook().getLocation());
				System.out.println(" ");
				node = node.getNext();
			}
		}
	}	

	private static void getOverdueLoans() {

		LendNode lNode = library.getLendings();
		while(lNode != null)
		{
			if(lNode.getDate().getTime() <= new Date().getTime())
			{
				System.out.print("Buch mit der ID: (" + lNode.getBook().getID() + ") ist überfällig!");
				lNode = lNode.getNext();
			}else{
				lNode = lNode.getNext();
			}
		}//end while
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
