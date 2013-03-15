import java.io.*;
import java.net.*;

public class Listener implements Runnable {
	
	PrintWriter server;
	BufferedReader in;
	String fromUser;
	boolean bot = false;
	
	public Listener(PrintWriter out, BufferedReader stdIn) {
		server = out;
		in = stdIn;
	}
	
	public Listener(PrintWriter out) {
		server = out;
		bot = true;
	}

	public void run() {
		if (bot) {
			System.out.println("asdf");
			while (true) {
				server.println(Bot.getCommand());
			}
		}
		else {
			try {
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