package phonebook;

/**
 * Class realizing an index for the phone book. Consists of an  
 * array for the 26 characters. Each field in the array points 
 * to the first entry with the respective character. If such an
 * entry does not exist, null is contained. 
 */
final class PhoneBookIndex { 
	
	/** Number of characters */
	private static final int NUM_CHARS = 26;

	/** Array to store references to first entry node of characters. */
	private final EntryNode[] index = new EntryNode[NUM_CHARS];

	/**
	 * Gets the first entry node with or after the given character. 
	 * @param ch the given character
	 * @return the entry node with or after the given character
	 */
	EntryNode getFirstOfChar(char ch) {
		// compute index for character ch (using lower case letters only)
		int i = convertCharToIndex(ch);
		if (i < 0 || i >= NUM_CHARS) {
			return null;
		}

		// find first node after character
		while (index[i] == null && i < NUM_CHARS - 1) {
			i++;
		}
		return index[i];
	} // getFirstOfChar


	/**
	 * Updates the index when a new entry node has been inserted. 
	 * Looks if this node is the first entry with the respective character
	 * and then updates the index. 
	 * @param newNode the newly inserted node
	 */
	void updateFirstOfChar(EntryNode newNode) {
		char ch = newNode.getEntry().getName().charAt(0); 
		EntryNode firstOfChar = getFirstOfChar(ch); 
		if (firstOfChar == null || 
				newNode.getEntry().getName().compareToIgnoreCase(firstOfChar.getEntry().getName()) < 0) {
			setFirstOfChar(ch, newNode); 
		}
	}

	/**
	 * Sets the first entry for the given character
	 * @param ch the character 
	 * @param node the entry node
	 */
	private void setFirstOfChar(char ch, EntryNode node) {
		// compute index for character ch (using lower case letters only)
		int i = convertCharToIndex(ch);
		if (i < 0 || i >= NUM_CHARS) {
			return;
		}
		index[i] = node;
	} // setFirstOfChar

	private static int convertCharToIndex(char ch) {
		return Character.toLowerCase(ch) - 'a';
	}
	
} // PhoneBookIndex
