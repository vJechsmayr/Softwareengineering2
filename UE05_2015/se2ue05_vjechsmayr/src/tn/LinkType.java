package tn;

public enum LinkType {

	AUTOBAHN (130),
	BUNDESSTRASSE (100),
	STRASSE (80);
	
	private final int speed;
	
	private LinkType(int speed)
	{
		this.speed = speed;
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
}
