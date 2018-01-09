package phonebook;

/**
 * Class for entries in the phone book. Entries consist of name and phone number with area code
 */
public final class Entry {
	
	/** the name for this phone book entry */
	private final String name;
	/** the area code */
	private final String areaCode;
	/** the phone number (without area code) */
	private final String number;

	/**
	 * Constructor initializing name, area code and phone number
	 * @param name the name for this phone book entry 
	 * @param areaCode the area code
	 * @param number the phone number (without area code)
	 */
	Entry(String name, String areaCode, String number) {
		this.name = name;
		this.areaCode = areaCode;
		this.number = number;
	}

	/**
	 * Gets the area code of this phone entry
	 * @return the area code 
	 */
	public String getAreaCode() {
		return areaCode;
	}
	
	/**
	 * Gets the number of this phone entry
	 * @return the phone number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * The name for this phone entry
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + ": (" + areaCode + ") " + number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((areaCode == null) ? 0 : areaCode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entry other = (Entry) obj;
		if (areaCode == null) {
			if (other.areaCode != null)
				return false;
		} else if (!areaCode.equals(other.areaCode))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

} // Entry
