package phonebook;

/**
 * Class implementing the nodes for the linear list of phone book entries
 */
final class EntryNode {
	
	/** the entry value */
	private Entry entry;
	/** the next node */
	private EntryNode next;

	/**
	 * Constructor for setting entry and next node
	 * @param entry the entry value
	 * @param next the next node reference
	 */
	EntryNode(Entry entry, EntryNode next) {
		this.setEntry(entry);
		this.setNext(next);
	}

	/**
	 * Constructor for setting entry
	 * @param entry the entry value
	 */
	EntryNode(Entry entry) {
		this(entry, null);
	}

	Entry getEntry() {
		return entry;
	}

	void setEntry(Entry entry) {
		this.entry = entry;
	}

	EntryNode getNext() {
		return next;
	}

	void setNext(EntryNode next) {
		this.next = next;
	}

} // Node
