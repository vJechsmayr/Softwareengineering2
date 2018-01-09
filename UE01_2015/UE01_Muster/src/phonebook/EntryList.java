package phonebook;

/**
 * Class EntryList implements a linked list for phone book entries. Stores the entries in 
 * alphabetical order. 
 */
class EntryList {
	
	/** head of the list */
	private EntryNode head;
	
	/** Constructor */
	EntryList() {
		head = null;
	} // PhoneBook
	
	/** 
	 * Insert a new entry with name, area code and phone number. 
	 * Maintains an alphabetical order. 
	 * @param name name of the new entry
	 * @param areaCode area code of the new entry
	 * @param number phone number of the new entry
	 * @return the newly inserted node
	 */
	EntryNode insertEntry(String name, String areaCode, String number) {
		return insertEntry(new Entry(name, areaCode, number)); 
	}

	/** 
	 * Inserts an entry value or replaces existing with equal name.  
	 * Replaces an existing entry with equal name. 
	 * Maintains an alphabetical order. 
	 * @param entry the new entry 
	 * @return the newly inserted node
	 */
	EntryNode insertEntry(Entry entry) {
		EntryNode pred = null;
		EntryNode curr = head;

		// find correct position to insert new entry
		while (curr != null
				&& curr.getEntry().getName().compareToIgnoreCase(entry.getName()) < 0) {
			pred = curr;
			curr = curr.getNext();
		} // while

		if (curr != null && curr.getEntry().getName().equalsIgnoreCase(entry.getName())) {
			// replace entry with equal name
			curr.setEntry(entry); 
			return curr; 
		} else {
			// insert new entry in alphabetical order 
			EntryNode node = new EntryNode(entry);
			if (pred == null) { // insert in front of head
				head = node;
			} else {
				pred.setNext(node);
			}
			node.setNext(curr);
	
			return node; 
		}
	} // insert

	/**
	 * Looks up the entry with name. 
	 * @param name the name of the entry
	 * @return the entry node found, null if not contained 
	 */
	EntryNode lookup(String name) {
		EntryNode node = head;

		// search for entry with name >= name
		while (node != null && node.getEntry().getName().compareToIgnoreCase(name) < 0) {
			node = node.getNext();
		} // while

		if (node == null || !node.getEntry().getName().equalsIgnoreCase(name)) {
			return null; // not found
		}

		return node;
	} // lookupEntry

	/**
	 * Returns the first entry node in the list.
	 * @return the first node in the list, null if empty
	 */
	EntryNode firstEntry() {
		return head; 
	} // firstEntry

	/** 
	 * Returns the next entry node for the given current node. 
	 * @param current the given current node
	 * @return the next node after the node current 
	 */
	EntryNode nextEntry(EntryNode current) {
		if (current == null) {
			return null; // no next entry
		}
		return current.getNext(); // return next entry
	} // nextEntry

	/**
	 * Returns true if list is empty, false otherwise.  
	 * @return true if list is empty, false otherwise
	 */
	boolean isEmpty() {
		return head == null; 
	}
	
} // PhoneBook
