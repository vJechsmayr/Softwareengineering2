package tn;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class TrafficNetwork {
	private SortedMap<String, Location> locations;
	private TreeSet<Link> links;
	
	public TrafficNetwork()
	{
		this.locations = new TreeMap<String, Location>();
		this.links = new TreeSet<Link>();
	}
	
	public void addLocation(Location loc)
	{
		locations.put(loc.getName(), loc);
	}
	
	public void addLink(Link link)
	{
		links.add(link);
	}
}
