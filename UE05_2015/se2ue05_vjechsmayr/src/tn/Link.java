package tn;

public class Link implements Comparable<Link>{

	private String name;
	private LinkType typ;
	private int length = 0;
	private Location start = null;
	private Location end = null;
	
	public Link(String name, LinkType typ, int length, Location start, Location end)
	{
		this.name = name;
		this.typ = typ;
		this.length = length;
		this.start = start;
		this.end = end;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public LinkType getTyp()
	{
		return this.typ;
	}
	
	public int getTypSpeed()
	{
		return this.typ.getSpeed();
	}
	
	public int getLength()
	{
		return this.length;
	}
	
	public Location getStart()
	{
		return this.start;
	}
	
	public Location getEnd()
	{
		return this.end;
	}
	
	public float getTime()
	{
		int l = this.getLength();
		int s = this.getTypSpeed();
		float t = (float) l/s;
		return t;
	}
	
	public int getHours()
	{
		int h;
		float t = (float) this.getLength()/ this.getTypSpeed();
		h=(int)t;
		return h;
	}
	
	public int getMinutes()
	{
		int m;
		float t = (float) this.getLength()/ this.getTypSpeed()*60;
		m=(int)t;
		if(m>59)
		{
			m= m%60;
		}
		return m;
	}
	
	public Location getOtherLocation(Location loc)
	{
		if(loc == start)
		{
			return end;
		}else if(loc == end)
		{
			return start;
		}else
		{
			return null;
		}
	}
	
	public String toString()
	{
		return this.name + " " + this.typ.toString() + " " + this.length;
	}
	
	@Override
	public int compareTo(Link o) {
		Integer l = length;
		Integer olength = o.getLength();
		return l.compareTo(olength);
	}
}
