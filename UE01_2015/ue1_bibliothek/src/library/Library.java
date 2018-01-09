package library;

public class Library {
	
	final private String name;
	private static PersonNode personHead;
	private static BookNode bookHead;
	private static LendNode lendings;
	
	public Library(String name) {
		this.name = name;
		this.personHead = null;
		this.bookHead = null;
		this.lendings = null;
	}
	
	public String getName() {
		return name;
	}
	
	public PersonNode getPersonHead()
	{
		return personHead;
	}
	
	public BookNode getBookHead()
	{
		return bookHead;
	}
	
	public LendNode getLendings()
	{
		return lendings;
	}
	
	public static void addPersonNode(PersonNode node)
	{
		if(personHead== null)
		{
			node.next = personHead;
			personHead = node;
		}else
		{
			PersonNode tmp = personHead;
			while(tmp.next != null)
			{
				tmp = tmp.next;
			}
			node.next = tmp.next;
			tmp.next = node;
		}
	}//end addPersonNode
	
	public static void addBookNode(BookNode node)
	{
		if(bookHead== null)
		{
			node.next = bookHead;
			bookHead = node;
		}else
		{
			BookNode tmp = bookHead;
			while(tmp.next != null)
			{
				tmp = tmp.next;
			}
			node.next = tmp.next;
			tmp.next = node;
		}
	}//end addBookNode
	
	public static void addLending(LendNode node)
	{
		if(lendings== null)
		{
			node.next = lendings;
			lendings = node;
		}else
		{
			LendNode tmp = lendings;
			while(tmp.next != null)
			{
				tmp = tmp.next;
			}
			node.next = tmp.next;
			tmp.next = node;
		}
	}//end addBookNode
	
	public static void setLent(int bookId)
	{
		BookNode node = bookHead;
		while(node != null)
		{
			if(node.getID() != bookId)
			{
				node = node.getNext();
			}else
			{
				node.setAvailable(false);
				node = null;
			}
		}
	}
	
	public static void setAvailable(int bookId)
	{
		BookNode node = bookHead;
		while(node != null)
		{
			if(node.getID() != bookId)
			{
				node = node.getNext();
			}else
			{
				node.setAvailable(true);
				node = null;
			}
		}
	}
	
}
