package library;

import java.util.Date;
import java.text.DateFormat;

public class LendNode {
	
	LendNode next;
	PersonNode person;
	BookNode book;
	Date loanDate;

	public LendNode(PersonNode person, BookNode book)
	{
		this.person = person;
		this.book = book;
		this.loanDate = new Date();
		this.next = null;
		this.loanDate.setTime(loanDate.getTime() + 14*86400000);
	}
	
	public void printPerson()
	{
		System.out.println(person.getID() + " " + person.getfirstname() + " " + person.getlastname());
	}
	
	public void printBook()
	{
		System.out.println(book.getID() + " " + book.getTitle() + " " + book.getLocation());
	}
	
	public Date getDate()
	{
		return loanDate;
	}
	
	public PersonNode getPerson()
	{
		return person;
	}
	
	public BookNode getBook()
	{
		return book;
	}
	
	public LendNode getNext()
	{
		return next;
	}
	
	public void setNext(LendNode next)
	{
		this.next = next;
	}
	
	public void setDate()
	{
		this.loanDate.setTime(loanDate.getTime() - 14*86400000);
	}
}
