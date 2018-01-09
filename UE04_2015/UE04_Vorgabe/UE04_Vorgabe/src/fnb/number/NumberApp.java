package fnb.number;

import inout.In;
import inout.Out;
import inout.Window;
import fnb.Source;

public class NumberApp {

	public static void main(String[] args) {

		Source<Float> input = new Source<Float>(() -> {
			Out.print("Input new float value: ");
			return In.readFloat();
		}, f -> {
			Out.println(" -> input: value " + f + " sent!");
		});

		// TODO

		// input.setNext(...);

		// --- start processing ---

		Window.clear();
		while (true) {
			input.generate();
		}
	}
}
