package tn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Location implements Comparable<Location>{
	
	private String name;
	private int x;
	private int y;
	private Collection<Link> links;
	
	public Location(String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		this.links = new ArrayList<Link>();
	}
	
	public void addLink(Link l)
	{
		links.add(l);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public Collection<Link> getLinks()
	{
		return this.links;
	}
	
	public Collection<Location> getNeighbors()
	{
		Iterator<Link> it = links.iterator();
		Collection<Location> locColl = new ArrayList<Location>();
		
		while(it.hasNext())
		{
			Link n = it.next();
			if(this.name!= n.getStart().getName())
			{
				locColl.add(n.getStart());
			}else
				if(this.name != n.getEnd().getName())
				{
					locColl.add(n.getEnd());
				}
		}
		return locColl;
	}

	public Location getNeighborFor(Link lnk)
	{		
		if(lnk.getStart().getName() == this.name)
		{
			return lnk.getEnd();
		}else if(lnk.getEnd().getName() == this.name)
		{
			return lnk.getStart();
		}
		return null;
	}
	
	public Link getLinkTo(Location neighbor)
	{
		Iterator<Link> it = links.iterator();
		
		while(it.hasNext())
		{
			Link n = it.next();
			if(n.getEnd().getName() == this.name && n.getStart().getName() != this.name)
			{
				return n;
			}else if(n.getStart().getName() == this.name && n.getEnd().getName() != this.name)
			{
				return n;
			}
		}
		return null;
	}
	
	public List<Link> getLinksSorted(Comparator<Link> comparator)
	{
		List<Link> sorted = new LinkedList<Link>(links);
		Collections.sort(sorted, comparator);
		
		return sorted;
	}
	
	public List<Location> getNeighborsSorted(Comparator<Link> comparator)
	{
		Iterator<Link> it = links.iterator();
		List<Location> sorted = new LinkedList<Location>();
		
		while(it.hasNext())
		{
			Link n = it.next();
			if(this.name!= n.getStart().getName())
			{
				sorted.add(n.getStart());
				
			}else
				if(this.name != n.getEnd().getName())
				{
					sorted.add(n.getEnd());
				}	
		}
		Collections.sort(sorted);
		return sorted;
	}

	@Override
	public int compareTo(Location o) {
		Integer nx = getX();
		Integer ny = getY();
		Integer ox = o.getX();
		Integer oy = o.getY();
		
		int x = nx.compareTo(ox);
		
		if(x == 0)
		{
			int y = ny.compareTo(oy);
			
			if(y == 0)
			{
				return getName().compareTo(o.getName());
			}
			return y;
		}
		return x;
	}
}
