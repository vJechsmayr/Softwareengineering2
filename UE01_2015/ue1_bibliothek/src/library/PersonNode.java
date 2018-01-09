package library;

public class PersonNode {
	PersonNode next;
	LendNode nextL;
	
	int personID;
	String firstname;
	String lastname;
	String address;
	
	public PersonNode(int id, String vn, String nn, String a, PersonNode next)
	{
		this.personID = id;
		this.firstname = vn;
		this.lastname = nn;
		this.address = a;
		this.next = next;
	}
	
	public PersonNode(int id, String vn, String nn, String a)
	{
		this(id, vn, nn, a, null);
	}
	
	public PersonNode getNext()
	{
		return next;
	}
	
	public void setNext(PersonNode next)
	{
		this.next = next;
	}
	
	public int getID()
	{
		return personID;
	}
	
	public String getfirstname()
	{
		return firstname;
	}
	
	public String getlastname()
	{
		return lastname;
	}
	
	public String getaddress()
	{
		return address;
	}
}
