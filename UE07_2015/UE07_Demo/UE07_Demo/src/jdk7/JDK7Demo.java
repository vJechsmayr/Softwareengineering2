package jdk7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@SuppressWarnings("unused")
public class JDK7Demo {

	public static void main(String[] args) throws IOException {

		// /////////////////////////////////
		// Small language changes

		byte b = 0b01111111;
		int i = 0b10010101010;

		int i2 = 12_000_000;
		long l = 0x1234_ffab_0000L;

		String str = "string";

		switch (str) {
		case "string":
			break;
		case "another string":
			break;
		case "test":
			break;
		}

		// /////////////////////////////////
		// try-with-resources

		FileInputStream in = new FileInputStream("in.dat");
		FileOutputStream out = new FileOutputStream("out.dat");

		try {
			// do something ...
		} finally {
			try { in.close(); } catch (Exception e) {}
			try { out.close(); } catch (Exception e) {}
		}

//		try ( FileInputStream in = new FileInputStream("in.dat");
//				 FileOutputStream out = new FileOutputStream("out.dat")) {
//		
//		 // do something ...
//		 }

	}

}
