package se2ue08_k1256526;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import inout.In;

class ChatReader extends Thread {
	private BufferedReader in;
	private Socket socket;
	private Object sync;

	public ChatReader(Socket socket) throws IOException {
		this.socket = socket;
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	public void run() {
		String input = "";
		while (input != null) {
			try {
				if (in.ready()) {
					synchronized (sync) {
						input = in.readLine();
						System.out.print(" Received: ");
						System.out.println(input);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		try {
			socket.shutdownInput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSync(Object sync) {
		this.sync = sync;
	}

}

class ChatWriter extends Thread {
	private PrintWriter out = null;
	private Socket socket;
	private String enter = "";
	private Object sync;

	public ChatWriter(Socket socket) throws IOException {
		this.socket = socket;
		this.out = new PrintWriter(socket.getOutputStream());

	}

	@Override
	public void run() {

		while (!enter.equals("x")) {
			enter = In.readLine();
			if (enter.equals("")) {
				synchronized (sync) {
					System.out.println("----------------------------");
					System.out.print("Your massage: ");
					out.println(In.readLine());
					System.out.println("----------------------------");
					out.flush();
				}
			}
		}
		System.out.println("Good Bye!");
		try {
			socket.shutdownOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setSync(Object sync) {
		this.sync = sync;
	}

}
