package prims;

import inout.Input;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Buffer {
	ArrayList<String> array = new ArrayList<String>();
	
	public void put(String s)
	{
		if(! isEmpty())
		{
			System.out.println(" put nicht möglich");
		}
		array.add(s);
	}
	
	public String retrieve()
	{
		if(isEmpty())
		{
			System.out.println(" retrieve not possible");
			return null;
		}
		String s = array.get(0);
		array = null;
		return s;
	}
	
	public boolean isEmpty()
	{
		if(array.isEmpty() == true)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean isFull()
	{
		if(array.size() == 3)
		{
			return true;
		}else
		{
			return false;
		}
	}	
}

class MessageWrt extends Thread
{
	private String enter = "";
	private Object syncObj;
	private Socket socket;
	private PrintWriter out = null;
	private final Buffer buffer;
	
	public MessageWrt(Socket socket, Buffer buffer) throws IOException
	{
		this.socket = socket;
		this.buffer = buffer;
		this.out = new PrintWriter(socket.getOutputStream());
	}
	
	//@Override
	public void run()
	{
		while(!enter.equals("x"))
		{
			enter = "";
			if(enter.equals(""))
			{
				synchronized(syncObj)
				{
					System.out.println("Enter number: ");
					String s = Input.readString();
					out.println(s);
					out.flush();
					enter = s;
				}
			}
		}
		System.out.println("Exiting... - Good Bye");
		serverShutdown();
		System.exit(0);
	}

	private void serverShutdown() {
		try{
			socket.shutdownOutput();
		}catch(IOException e)
			{
				e.printStackTrace();
			}
	}
	
	public void setSync(Object sync)
	{
		this.syncObj = sync;
	}
}

class MessageRd extends Thread
{
	private Socket socket;
	private Object syncObj;
	private BufferedReader in;
	private final Buffer buffer;
	
	public MessageRd(Socket socket, Buffer buffer) throws IOException
	{
		this.socket = socket;
		this.buffer = buffer;
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	//@Override
	public void run()
	{
		String input = "";
		while(input != null)
		{
			try
			{
				if(in.ready())
				{
					synchronized(syncObj)
					{
						input = in.readLine();
						communication(input);
					}
				}
			}catch(IOException e)
				{
					e.printStackTrace();
				}
		}
		
		try
		{
			socket.shutdownInput();
		}catch(IOException e)
			{
				e.printStackTrace();
			}
	}
	
	public void setSync(Object sync)
	{
		this.syncObj = sync;
	}
	
	private static void communication(String input) {
		int n = 0;
		
		if(parseIntoInt(input) == true)
		{
			n = Integer.parseInt(input);
			if(superSlowIsPrimeImplementation(n))
			{
				System.out.println(input + " is a prime number!");
			}else
			{
				System.out.println(input + " is not a prime number!");
			}
		}else
		if(input.equals("x"))
		{
			System.out.println("Exiting... - Good Bye");
			System.exit(0);
		}else
			{
				System.out.println(input + " is not a number!");
			}
	}
	
	private static boolean parseIntoInt(String in) {
		try {
			int n = Integer.parseInt(in);
			return true;
		} catch (NumberFormatException e) 
			{
				return false;
			}
	}
	
	private static boolean superSlowIsPrimeImplementation(int n)
	{
		try{
			Thread.sleep(10000);
		}catch(InterruptedException e)
		{
			//Nothing
		}
		
		if(n <= 1)
		{
			return false;
		}
		if(n == 2)
		{
			return true;
		}
		for(int i = 2; i<= Math.sqrt(n)+1; i++)
		{
			if(n%i == 0)
			{
				return false;
			}
		}
		return true;
	}
}
