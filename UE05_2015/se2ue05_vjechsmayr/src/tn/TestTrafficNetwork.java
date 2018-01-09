package tn;

import java.util.Collection;
import inout.In;

public class TestTrafficNetwork {

	private static TrafficNetwork tn = new TrafficNetwork();
	private static Location amstetten, linz, wien, wels, enns, stpoelten, 
							tulln, graz, salzburg, klagenfurt;
	private static Link am_li, wien_am, linz_wels, am_enns, am_stpoelten, 
						stpoelten_tulln, wien_tulln, wien_graz, graz_linz, 
						graz_klagenfurt, klagenfurt_salzburg, salzburg_linz;
	
	public static void main(String[] args) {
		init();
		Location aktLoc = amstetten;
		String n = "";
		
		System.out.println("===============================================");
		System.out.println(" Roundtrip starting at " + aktLoc.getName());
		System.out.println("===============================================");
		
		while(n != "Amstetten")
		{
			for(Link lnk : aktLoc.getLinksSorted(Comparators.LENGTH))
			{
				System.out.println("  " + lnk.getTyp().toString() + " " + lnk.getName() + ": " + lnk.getLength() + "km to " + lnk.getOtherLocation(aktLoc).getName() + "  <" + lnk.getHours() + ":" + lnk.getMinutes()  + ">");
			}
	
			System.out.println();
			System.out.println("> Next Location: ");
			n = In.readLine();
			System.out.println();
			
			Collection<Link> neu = aktLoc.getLinks();
			
			for (Link l: neu)
			{
				if(l.getStart().getName().equalsIgnoreCase(n))
				{
					aktLoc = l.getStart();
				}else if(l.getEnd().getName().equalsIgnoreCase(n))
				{
					aktLoc = l.getEnd();
				}
			}
			
			if(aktLoc.equals(amstetten))
			{
				System.out.println("You are back in " + amstetten.getName() + "!");
				break;
			}
		}
		System.out.println("===============================================");
	}
	
	public static void init()
	{
		initLocations();
		initLinks();
		addLinks();
		addTrafficNetwortParts();
	}
	
	public static void initLocations()
	{
		linz = new Location("Linz", 142, 483);
		amstetten = new Location("Amstetten", 148, 481);
		wien = new Location("Wien", 163,482);
		wels = new Location("Wels", 140,481);
		enns = new Location("Enns", 144,482);
		stpoelten = new Location("St.Pölten", 156,482);
		tulln = new Location("Tulln", 160,483);
		graz = new Location("Graz", 154,470);
		salzburg = new Location("Salzburg", 130,478);
		klagenfurt = new Location("Klagenfurt", 143,466);
	}
	
	public static void initLinks()
	{
		am_li = new Link("L1234", LinkType.STRASSE, 60, amstetten, linz);
		wien_am = new Link("A1", LinkType.AUTOBAHN, 130, wien, amstetten);
		linz_wels = new Link("A25", LinkType.AUTOBAHN, 33, linz, wels);
		am_enns = new Link("A1", LinkType.AUTOBAHN, 37, amstetten, enns);
		am_stpoelten = new Link("B1", LinkType.BUNDESSTRASSE, 71, amstetten, stpoelten);
		stpoelten_tulln = new Link("B1", LinkType.BUNDESSTRASSE, 39, stpoelten, tulln);
		wien_tulln = new Link("L120", LinkType.STRASSE, 41, wien, tulln);
		wien_graz = new Link("A2", LinkType.AUTOBAHN, 198, wien, graz);
		graz_linz = new Link("A9", LinkType.AUTOBAHN, 220, graz, linz);
		graz_klagenfurt = new Link("A2", LinkType.AUTOBAHN, 135, graz, klagenfurt);
		klagenfurt_salzburg = new Link("A10", LinkType.AUTOBAHN, 228, klagenfurt, salzburg);
		salzburg_linz = new Link("A1", LinkType.AUTOBAHN, 132, salzburg, linz);
	}
	
	public static void addLinks()
	{
		amstetten.addLink(am_li);
		linz.addLink(am_li);
		wien.addLink(wien_am);
		amstetten.addLink(wien_am);
		linz.addLink(linz_wels);
		wels.addLink(linz_wels);
		amstetten.addLink(am_enns);
		enns.addLink(am_enns);
		amstetten.addLink(am_stpoelten);
		stpoelten.addLink(am_stpoelten);
		stpoelten.addLink(stpoelten_tulln);
		tulln.addLink(stpoelten_tulln);
		wien.addLink(wien_tulln);
		tulln.addLink(wien_tulln);
		wien.addLink(wien_graz);
		graz.addLink(wien_graz);
		graz.addLink(graz_linz);
		linz.addLink(graz_linz);
		graz.addLink(graz_klagenfurt);
		klagenfurt.addLink(graz_klagenfurt);
		klagenfurt.addLink(klagenfurt_salzburg);
		salzburg.addLink(klagenfurt_salzburg);
		salzburg.addLink(salzburg_linz);
		linz.addLink(salzburg_linz); 
	}
	
	public static void addTrafficNetwortParts()
	{
		tn.addLocation(amstetten);
		tn.addLocation(linz);
		tn.addLocation(wien);
		tn.addLocation(wels);
		tn.addLocation(enns);
		tn.addLocation(stpoelten);
		tn.addLocation(tulln);
		tn.addLocation(graz);
		tn.addLocation(salzburg);
		tn.addLocation(klagenfurt);
		tn.addLink(am_li);
		tn.addLink(wien_am);
		tn.addLink(linz_wels);
		tn.addLink(am_enns);
		tn.addLink(am_stpoelten);
		tn.addLink(stpoelten_tulln);
		tn.addLink(wien_tulln);
		tn.addLink(wien_graz);
		tn.addLink(graz_linz);
		tn.addLink(graz_klagenfurt);
		tn.addLink(klagenfurt_salzburg);
		tn.addLink(salzburg_linz);
	}
}
