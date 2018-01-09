package se2ue08_k1256526;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	private final static int PORT = 3009;
	private static ServerSocket srv = null;
	private static Socket socket = null;

	public static void main(String[] args) throws IOException {
		final Object sync = new Object();
		ChatReader reader;
		ChatWriter writer;

		srv = new ServerSocket(PORT);

		System.out.println(">java chat.ChatServer");
		System.out.println("Port number: " + PORT);
		System.out.println("Waiting for client request");

		socket = srv.accept();

		System.out.println("++ Connected to client");
		System.out.println("-----------------------------------");
		System.out.println("Press enter for input! Enter 'x' to terminate!");
		System.out.println("-----------------------------------");

		reader = new ChatReader(socket);
		writer = new ChatWriter(socket);

		reader.setSync(sync);
		writer.setSync(sync);

		reader.start();
		writer.start();

		reader.interrupt();
		writer.interrupt();

		try {
			reader.join();
			writer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		socket.close();

	}
}
