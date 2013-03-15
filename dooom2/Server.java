import java.io.*;
import java.net.*;

public class Server implements Runnable {
// uses ideas from:
// http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockServer.java

	static int numOfClients = 0;

	Socket clientSocket;
	
	static ServerSocket serverSocket;
	
	static DODMap map;

	public Server(Socket client) {
		clientSocket = client;
	}

	public void run() {
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
					clientSocket.getInputStream()));
			String inputLine, outputLine;
			Protocol protocol = new Protocol(map);
			while ((inputLine = in.readLine()) != null) {
				 outputLine = protocol.playerCommand(inputLine);
				 out.println(outputLine);
			}
			out.close();
			in.close();
			clientSocket.close();
			numOfClients--;
			if (numOfClients == 0) {
				serverSocket.close();
			}
		}
		catch (Exception e) {
			System.err.println("Error: " + e);
			System.exit(-1);
		}
	}

    public static void main(String[] args) {
    
		try {
        	if (args.length > 0) {
        		serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        	}
			else {
				serverSocket = new ServerSocket(50898);
			}
        }
		catch (Exception e) {
			System.err.println("Error: " + e);
			System.exit(-1);
		}

		Socket client = null;
		
		map = new DODMap();
		
		try {
			do {
				try {
					client = serverSocket.accept();
				}
				catch (SocketException e) {
					System.exit(0);
				}
				(new Thread(new Server(client))).start();
				numOfClients++;
			} while (numOfClients > 0);
			serverSocket.close();
		}
		catch (Exception e) {
			System.err.println("Error: " + e);
			System.exit(-1);
		}
    
    }
}