import java.io.*;
import java.net.*;

public class Listener implements Runnable {
	
	PrintWriter server;
	BufferedReader in;
	String fromUser;
	
	public Listener(PrintWriter out, BufferedReader stdIn) {
		server = out;
		in = stdIn;
	}

	public void run() {
		try {
			while ((fromUser = in.readLine()) != null) {
				server.println(fromUser);
			}
		}
		catch (IOException e) {
			System.err.println("Error: " + e);
		}
	}
	
}