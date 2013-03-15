import java.io.*;
import java.net.*;

public class Server implements Runnable {
// uses ideas from:
// http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockServer.java

	Socket clientSocket = null;

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
	
			Protocol protocol = new Protocol();
	
			while ((inputLine = in.readLine()) != null) {
				 outputLine = protocol.playerCommand(inputLine);
				 out.println(outputLine);
			}
	
			out.close();
			in.close();
			clientSocket.close();
		}
		catch (IOException e) {
		}
	}

    public static void main(String[] args) throws IOException {
    
		ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(50898);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

		Socket client = null;
		
		while (true) { // loop should break when number of clients == 0
			try {
				client = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
	
			(new Thread(new Server(client))).start();
		}

        
        /********* serverSocket.close(); <-- should execute when loop ends ***/
    
    }
}
