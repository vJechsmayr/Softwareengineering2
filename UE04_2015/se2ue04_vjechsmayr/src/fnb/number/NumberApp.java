package fnb.number;

import inout.In;
import inout.Out;
import inout.Window;
import fnb.Average;
import fnb.Round;
import fnb.Scale;
import fnb.Sink;
import fnb.Source;
import fnb.Transform;

public class NumberApp {

	public static void main(String[] args) {

		Source<Float> input = new Source<Float>(() -> {
			Out.print("Input new float value: ");
			return In.readFloat();
		}, f -> {
			Out.println(" -> input: value " + f + " sent!");
		});

		Transform<Float, Float> scale = new Transform<Float,Float>(new Scale(), f->{
			Out.println("-> scale10: value " + f + " received!");
		}, f->{
			Out.println("-> scale10: value " + f + " sent!");
		});
		
		Transform<Float, Float> avg = new Transform<Float,Float>(new Average(), f->{
			Out.println("-> average: value " + f + " received!");
		}, f->{
			Out.println("-> average: value " + f + " sent!");
		});
		
		Transform<Float, Integer> round = new Transform<Float,Integer>(new Round(), f->{
			Out.println("-> round: value " + f + " received!");
		}, i->{
			Out.println("-> round: value " + i + " sent!");
		});
		
		Sink<Integer> sink = new Sink<Integer>(i->{
			Out.println("-> display: value" + i + " received!");
			Window.clear();
			Window.drawRectangle(100, 100, 50, i);
		});
		
		scale.setNext(avg);
		avg.setNext(round);
		round.setNext(sink);
		input.setNext(scale);
		
		Window.clear();
		while (true) {
			input.generate();
		}
	}
}
