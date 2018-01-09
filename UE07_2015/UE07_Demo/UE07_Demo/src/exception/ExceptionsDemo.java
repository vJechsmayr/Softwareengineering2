package exception;

import java.io.FileNotFoundException;
import java.net.ConnectException;

public class ExceptionsDemo {

	public static void main(String[] args) {
		try {
			exceptionsTest(1);
		} catch (ConnectException | FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void exceptionsTest(int x) throws ConnectException,
			FileNotFoundException {
		if (x == 1) {
			throw new ConnectException("something bad happened!");
		} else {
			throw new FileNotFoundException();
		}
		// System.out.println("some code...");
	}
}
