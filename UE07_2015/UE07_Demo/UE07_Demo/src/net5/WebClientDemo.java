package net5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class WebClientDemo {

	public static void main(String[] args) throws UnknownHostException,
			IOException {

		// /////////////////////////////////
		// client socket demo

		Socket socket = new Socket("www.jku.at", 80);

		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			writer.println("GET /content HTTP/1.1");
			writer.println("Host: www.jku.at");
			writer.println();
			writer.flush();

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} finally {
			socket.close();
		}
	}
}
