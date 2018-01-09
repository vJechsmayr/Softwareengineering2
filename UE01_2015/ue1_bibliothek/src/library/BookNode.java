package library;

public class BookNode {

	BookNode next;
	int bookID;
	String title;
	String location;
	boolean available; //book is available = true, if lent available = false
	
	//Konstruktor
	public BookNode(int id, String t, String s, BookNode next)
	{
		this.next = next;
		this.bookID = id;
		this.title = t;
		this.location = s;
		this.available = true;
	}
	
	public BookNode(int id, String t, String s)
	{
		this(id, t, s, null);
	}
	//End Konstruktor
	
	public BookNode getNext()
	{
		return next;
	}
	
	public void setNext(BookNode next)
	{
		this.next = next;
	}
	
	public int getID()
	{
		return bookID;
	}
	public String getTitle()
	{
		return title;
	}
	public String getLocation()
	{
		return location;
	}
	
	public boolean getAvailable()
	{
		return available;
	}
	
	public void setAvailable(boolean newStatus)
	{
		this.available = newStatus;
	}
}
