package net4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketsServerDemo {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(12345);

		// /////////////////////////////////
		// Version 4: one Thread per connection

		while (true) {
			System.out.println("Waiting for next client to connect ... ");
			final Socket socket = server.accept();
			Thread connectionThread = new Thread() {
				@Override
				public void run() {
					PrintWriter writer = null;
					BufferedReader reader = null;
					try {
						writer = new PrintWriter(socket.getOutputStream());
						writer.println("Hello World!");
						writer.println("Current Time: " + new Date());
						writer.flush();
						socket.shutdownOutput();
						Thread.sleep(10_000);  // do some long computations
						reader = new BufferedReader(new InputStreamReader(
								socket.getInputStream()));
						String readLine = reader.readLine();
						System.out.println(readLine);
					} catch (Throwable t) {
						t.printStackTrace();
					} finally {
						try {
							writer.close();
						} catch (Exception e) {
						}
						try {
							socket.close();
						} catch (Exception e) {
						}

					}
				}
			};
			connectionThread.start();
		}

	}

}
