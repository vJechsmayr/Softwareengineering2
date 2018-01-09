package fnb.cmd;

import inout.In;
import inout.Out;
import inout.Window;
import fnb.Source;

public class CommandApp {

	public static void main(String[] args) {

		Source<String> input = new Source<String>(() -> {
			Out.print("Input command: ");
			return In.readLine();
		}, s -> {
			Out.println(" -> Input: New value read " + s);
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
