import java.io.*;
import java.net.*;

public class Listener implements Runnable {
	
	PrintWriter server;
	BufferedReader in;
	
	public Listener(PrintWriter out, BufferedReader stdIn) {
		server = out;
		in = stdIn;
	}

	public void run() {
		try {
			server.println(in.readLine());
		}
		catch (IOException e) {
			System.err.println("Error: " + e);
		}
	}
	
}