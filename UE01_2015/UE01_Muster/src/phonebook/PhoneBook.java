package phonebook;

/**
 * Class PhoneBook implements the phone book. Stores the entries in an 
 * alphabetically sorted linear list. 
 * Additionally, maintains an index to quickly navigate to the 
 * first entry with a given character. 
 * Moreover, owns a current entry for scrolling throw the phone book. 
 */
public final class PhoneBook {
		
	/** the linear linked list for storing the entries in alphabetical order */
	private final EntryList list; 
	/** Reference to a current node for scrolling */
	private EntryNode current; 
	/** The index for referencing the first entries of a given character */
	private final PhoneBookIndex index;

	/** 
	 * Default constructor
	 */
	public PhoneBook() {
		list = new EntryList(); 
		index = new PhoneBookIndex();
		current = null; 
	} 
	
	/** 
	 * Insert a new entry with name, area code and phone number. 
	 * Maintains an alphabetical order. Updates the index. 
	 * @param name name of the new entry
	 * @param areaCode area code of the new entry
	 * @param number phone number of the new entry
	 */
	public void insertEntry(String name, String areaCode, String number) {
		current = list.insertEntry(new Entry(name, areaCode, number)); 
		index.updateFirstOfChar(current); 
	}
	
	/**
	 * Checks if there this phonebook contains entry with given name.  
	 * @param name the name of the entry to search for 
	 * @return true if entry with name contained, false otherwise
	 */
	public boolean containsEntry(String name) {
		return list.lookup(name) != null; 
	}

	/**
	 * Looks up the entry with given name
	 * @param name the name of the entry
	 * @return the entry found, null if not contained 
	 */
	public Entry gotoEntry(String name) {
		current = list.lookup(name); 
		if (current != null) {
			return current.getEntry(); 
		} else {
			return null; 
		}
	} // lookupEntry

	/** 
	 * Gets the first entry in the list and sets the current cursor to the first entry. 
	 * @return the first entry in the list if not empty, null otherwise. 
	 */
	public Entry gotoFirstEntry() {
		current = list.firstEntry(); 
		if (current != null) {
			return current.getEntry(); 
		} else {
			return null; 
		}
	} // firstEntry

	/**
	 * Gets the entry after the current. Sets the current to the next. 
	 * If no next entry exists, returns the first and set the current to the first. 
	 * @return the next after current, first if no next after current. 
	 */
	public Entry gotoNextEntry() {
		if (hasMoreEntries()) {
			current = current.getNext(); 
			return current.getEntry(); 
		} else {
			return gotoFirstEntry(); 
		}
	} // nextEntry

	/**
	 * Returns the first entry with or after the given character. 
	 * Sets the current to this node. 
	 * @param ch the given character for lookup
	 * @return the first entry with or after the given character
	 */
	public Entry gotoFirstOfChar(char ch) {
		EntryNode firstOfChar = index.getFirstOfChar(ch);
		if (firstOfChar == null) {
			return null; // no entry after character ch
		}

		current = firstOfChar; 
		return current.getEntry(); 
	} // firstOfChar

	/** 
	 * Returns if there are more entries after current. 
	 * @return true if current has next entry, false otherwise
	 */
	public boolean hasMoreEntries() {
		return current != null && current.getNext() != null; 
	}
	
} // PhoneBook
