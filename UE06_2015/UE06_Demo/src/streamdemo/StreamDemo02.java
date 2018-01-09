package streamdemo;

import inout.Out;

import java.util.Random;
import java.util.stream.IntStream;

public class StreamDemo02 {
	public static void main(String[] args) {
		final Random r = new Random();
		IntStream randStream = IntStream.generate(() -> r.nextInt(100));
		randStream.limit(100).forEach(x -> Out.println(x));
		// streams can be iterated only once

		// THIS FAILS: randStream.limit(200).forEach(x -> Out.println(x));
		randStream = IntStream.generate(() -> r.nextInt(100));
		randStream.limit(200).forEach(x -> Out.println(x));
	}
}
