package io1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;

import java.io.OutputStream;

public class JavaIoDemo {

	public static void main(String[] args) throws IOException {
		// demoFileInputStream();
		// demoFileReader();
		// demoBufferedReader();
		demoPrintWriter();
	}

	public static void demoFileInputStream() {

		try (InputStream in = new FileInputStream("in.dat");
				OutputStream out = new FileOutputStream("out.txt");) {

			byte[] buffer = new byte[1024];

			int count;
			while ((count = in.read(buffer)) != -1) {
				out.write(buffer, 0, count);
				for (int i = 0; i < count; i++) {
					System.out.println(buffer[i]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void demoFileReader() throws IOException {

		Reader reader = new FileReader("in.txt");

		try {
			// int nextChar;
			// while ( (nextChar = reader.read()) != -1) {
			// char c = (char) nextChar;
			// System.out.println(c);
			// }

			char[] buffer = new char[1024];
			int count;
			while ((count = reader.read(buffer)) != -1) {
				for (int i = 0; i < count; i++) {
					System.out.println(buffer[i]);
				}
			}
		} finally {
			reader.close();
		}
	}

	public static void demoBufferedReader() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("in.txt"));

		try {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} finally {
			reader.close();
		}
	}

	public static void demoPrintWriter() throws IOException {
		PrintWriter writer = new PrintWriter("out.txt");

		try {
			String threadName = Thread.currentThread().getName();
			int cpuCount = Runtime.getRuntime().availableProcessors();

			writer.println("Hello World!");
			writer.printf("The current thread's name is %s, cpu count = %d\n",
					threadName, cpuCount);

		} finally {
			writer.close();
		}
	}
}
