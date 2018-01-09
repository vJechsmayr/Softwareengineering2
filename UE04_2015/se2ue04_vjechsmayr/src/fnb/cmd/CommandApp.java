package fnb.cmd;

import inout.In;
import inout.Out;
import inout.Window;
import fnb.Branch;
import fnb.CreateCircle;
import fnb.CreateRect;
import fnb.Filter;
import fnb.Sink;
import fnb.Source;
import fnb.Transform;

public class CommandApp {

	public static void main(String[] args) {

		Source<String> input = new Source<String>(() -> {
			Out.print("Input command: ");
			return In.readLine();
		}, s -> {
			Out.println(" -> Input: New value read " + s);
		});
		
		Branch<String> branchRect = new Branch<String>(s->{
			if(s.startsWith("rect"))
			{
				return true;
			}else
				{
					return false;
				}
		}, s->{
			Out.println("-> branchRect: received " + s);
		}, s->{
			Out.println("-> branchRect: sent " + s);
		});
		
		Branch<String> branchCircle = new Branch<String>(s->{
			if(s.startsWith("circle"))
			{
				return true;
			}else
				{
					return false;
				}
		}, s->{
			Out.println("-> branchCircle: received " + s);
		}, s->{
			Out.println("-> branchCircle: sent " + s);
		});
		
		Transform<String, Rect> createRect = new Transform<String,Rect>(new CreateRect(), s->{
			Out.println("-> createRect: received " + s);
		}, r->{
			Out.println("-> createRect: sent " + r);
		});	
		
		Transform<String, Circle> createCircle = new Transform<String,Circle>(new CreateCircle(), s->{
			Out.println("-> createCircle: received " + s);
		}, c->{
			Out.println("-> createCircle: sent " + c);
		});	
		
		Filter<Rect> filterNullRect = new Filter<Rect>(r->{
			if(r != null)
			{
				return true;
			}else
				{
					return false;
				}
		}, r->{
			Out.println("-> filterNullRect: received " + r);
		}, r->{
			Out.println("-> filterNullRect: sent " + r);
		});
		
		Filter<Circle> filterNullCircle = new Filter<Circle>(c->{
			if(c != null)
			{
				return true;
			}else
				{
					return false;
				}
		}, c->{
			Out.println("-> filterNullCircle: received " + c);
		}, c->{
			Out.println("-> filterNullCircle: sent " + c);
		});
		
		Sink<Rect> displayRect = new Sink<Rect>(r->{
			Out.println("-> displayRect: received " + r);
			Window.drawRectangle(r.getX(), r.getY(), r.getW(), r.getH());
		});
		
		Sink<Circle> displayCircle = new Sink<Circle>(c->{
			Out.println("-> displayCircle: received " + c);
			Window.drawCircle(c.getX(), c.getY(), c.getR());
		});
		
		Sink<String> displayError = new Sink<String>(s->{
			Out.println("-> ERROR : unbekanntes Kommando: " + s);
		});
		
		input.setNext(branchRect);
		branchRect.setNextTrue(createRect);
		branchRect.setNextFalse(branchCircle);
		branchCircle.setNextTrue(createCircle);
		branchCircle.setNextFalse(displayError);
		createRect.setNext(filterNullRect);
		filterNullRect.setNext(displayRect);
		createCircle.setNext(filterNullCircle);
		filterNullCircle.setNext(displayCircle);

		Window.clear();
		while (true) {
			input.generate();
		}
	}
}