import java.io.*;
import java.net.*;

public class Listener implements Runnable {
	
	PrintWriter server;
	BufferedReader in;
	String fromUser;
	boolean bot = false;
	
	// human player
	public Listener(PrintWriter out, BufferedReader stdIn) {
		server = out;
		in = stdIn;
	}
	
	// bot player
	public Listener(PrintWriter out) {
		server = out;
		bot = true;
	}

	public void run() {
		if (bot) {
			while (true) { // keep getting command for bot
				server.println(Bot.getCommand());
			}
		}
		else {
			try {
				// keep getting input from user and send to server
				while ((fromUser = in.readLine()) != null) {
					server.println(fromUser);
				}
			}
			catch (IOException e) {
				System.err.println("Error: " + e);
				System.exit(-1);
			}
		}
	}
	
}