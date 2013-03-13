import java.io.*;
import java.net.*;

public class Listener implements Runnable {
// uses ideas from:
// http://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoClient.java
// http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockClient.java

// 	public static void main(String[] args) {
// 		
// 		try {
// 		
// 			Socket echoSocket = new Socket("localhost", 50898);
// 			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
// 			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
// 			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
// 			String fromServer;
// 			String fromUser;
// 			
// 			while ((fromServer = in.readLine()) != null) {
// 				System.out.println(fromServer);
// 				fromUser = stdIn.readLine();
// 				if (fromUser != null) {
// 					out.println(fromUser);
// 				}
// 			}
// 			out.close();
// 			in.close();
// 			stdIn.close();
// 			echoSocket.close();
// 			
// 		}
// 		catch (ConnectException e) {
// 			System.err.println("Error: Could not connect. " + e.getMessage());
// 			System.exit(-1);
// 		}
// 		catch (Exception e) {
// 			System.err.println(e);
// 			System.exit(-1);
// 		}
// 		
// 	}
	
	PrintWriter server;
	BufferedReader in;
	
	public Listener(PrintWriter out, BufferedReader stdIn) {
		server = out;
		in = stdIn;
	}

	public void run() {
		try{
			server.println(in.readLine());
		}
		catch (Exception e) {}
	}
	
}