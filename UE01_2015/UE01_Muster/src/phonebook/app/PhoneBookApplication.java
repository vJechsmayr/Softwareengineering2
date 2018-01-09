package phonebook.app;

import phonebook.Entry;
import phonebook.PhoneBook;
import inout.In;
import inout.Out;

/**
 * Implements the main application for the phone book with the user dialog
 */
public class PhoneBookApplication {

  /**
   * Main method starting the application.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // Initialize phone book
    PhoneBook myPhoneBook = new PhoneBook();
    myPhoneBook.insertEntry("herbert", "0732", "24688894");
    myPhoneBook.insertEntry("alois", "0446", "2342323");
    myPhoneBook.insertEntry("franz", "0565", "234342");
    myPhoneBook.insertEntry("gustav", "05566", "6454565");
    myPhoneBook.insertEntry("hannes", "07474", "342342345");

    // Put out operations
    Out.println("Telefonbuch ");
    Out.println("=========== ");
    Out.println("Folgende Operationen stehen zur Verfügung: ");
    Out.println("  i - insert: Einfügen eines Eintrags ");
    Out.println("  f - first: ersten Eintrag anzeigen ");
    Out.println("  n - next: nächsten Eintrag anzeigen ");
    Out.println("  s - search: eine Eintrag nach Namen nachschlagen");
    Out.println("  c - character: ersten Eintrag nach bestimmten Buchstaben anzeigen");
    Out.println("  q - quit: Programm verlassen");

    // Read operation code and perform operation
    char op = readOperation();
    while (op != 'q') {
      switch (op) {
      case 'i': // insert new entry
        Out.print("   Bitte Namen eingeben: ");
        String name = In.readWord();
        Out.print("   Bitte Vorwahl eingeben: ");
        String areacode = In.readWord();
        Out.print("   Bitte Nummer eingeben: ");
        String number = In.readWord();
        myPhoneBook.insertEntry(name, areacode, number);
        break;
      case 'f': // go to first entry
        printEntry(myPhoneBook.gotoFirstEntry());
        break;
      case 'n': // go to next entry
        printEntry(myPhoneBook.gotoNextEntry());
        break;
      case 's': // look for entry with a particular name
        Out.print("   Bitte Namen eingeben: ");
        name = In.readWord();
        printEntry(myPhoneBook.gotoEntry(name));
        break;
      case 'c': // go to first entry with a particular first character
        Out.print("   Bitte Buchstaben eingeben: ");
        char ch = In.readChar();
        printEntry(myPhoneBook.gotoFirstOfChar(ch));
        break;
      }
      op = readOperation();
    }

    Out.println();
    Out.println("Ciao! Auf ein Wiedersehen freut sich Dein Telefonbuch!");

  } // main

  /**
   * Reads in the operation character.
   * 
   * @return the character specifying the next operation.
   */
  private static char readOperation() {
    Out.println();
    Out.print("Bitte Operation auswaehlen: (i, f, n, s, c, q): ");

    char op = In.readChar();
    while (op != 'i' && op != 'f' && op != 'n' && op != 's' && op != 'c'
        && op != 'q') {
      // wrong operation code, repeat input of operation code
      Out.println();
      Out.print("   Falsche Eingabe! Bitte Eingabe wiederholen: (i, f, n, s, c, q): ");
      op = In.readChar();
    }
    return op;
  } // readOperation

  /**
   * Print out the entry.
   * 
   * @param e
   *          the entry
   */
  private static void printEntry(Entry e) {
    if (e != null) {
      Out.println("--> " + e.toString());
    } else {
      Out.println("--> " + "--");
    }
  } // writeEntry

} // PhoneBookApplication
