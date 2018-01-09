package tn;

import java.util.Comparator;

public class Comparators{
	
	public static Comparator<Link> LENGTH = new Comparator<Link>(){
		@Override
		public int compare(Link o1, Link o2)
		{
			return new Integer(o1.getLength()).compareTo(o2.getLength());
		}
	};
	
	public static Comparator<Link> TIME = new Comparator<Link>(){
		@Override
		public int compare(Link o1, Link o2)
		{
			return new Float(o1.getTime()).compareTo(o2.getTime());
		}
	};
	
	public static Comparator<Link> TYP = new Comparator<Link>(){
		@Override
		public int compare(Link o1, Link o2)
		{
			return o1.getTyp().compareTo(o2.getTyp());
		}
	};
	
	public static Comparator<Link> BEZEICHNUNG = new Comparator<Link>(){
		@Override
		public int compare(Link o1, Link o2)
		{
			int i = o1.getName().compareTo(o2.getName());
			if(i==0)
			{
				i = o1.getLength() - o2.getLength();
			}
			return i;
		}
	};
}
