


import java.io.*;

/**
 * Class to control the interaction with the game through the command line. 
 */
public class Protocol 
{
	public static final int MODE_PLAYER = 0;
	public static final int MODE_BOT = 1;
	
	// The game object for this player.
	private DODGame game;
	
	
	/**
	 * Creates a new instance of PlayGame.
	 */
	public Protocol()
	{
		//create an instance of the DODGame
		game = new DODGame();
		//play();
	}
	
	
	/**
	 * Creates a new instance of PlayGame with the specified map.
	 * 
	 * @param map The name of the file to read the map from.
	 */
	public Protocol(String map)
	{		
		//create an instance of the DODGame
		game = new DODGame(map);
		//play();
	}
	
	
	/**
	 * Starts the game
	 * 	 - reads input from the command line and calls the relevant function in the game logic.
	 */
	private void play()
	{
		// Set up Buffered Reader so we can get user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Keep listening forever
		while (true)
		{
			try 
			{
				// Try to grab a command from the command line 
		        String command = br.readLine();
		        playerCommand(command);
		    } 
			catch (IOException ioe) 
			{
				// Die if something goes wrong.
	         	System.out.println(ioe.toString());
	         	System.exit(1);
		    }
		}
	}
	
	
	/**
	 * Interpret the commands passed to the command line. The commands that are accepted by the client
	 * should be in the wire-spec.
	 * 
	 * @param command The command entered.
	 */
	public String playerCommand(String command)
	{	
		// Because continuously pressing the shift key while testing made my finger hurt...
		command = command.toUpperCase();
		
		//process the command string e.g. MOVE N 
		String tmp[] = command.split(" ", 2);
		String com = tmp[0];
		String arg = ((tmp.length == 2) ? tmp[1] : null);
		
		String message = "";
		
		//call the relevant method in the game object
		if (com.equals("HELLO")) 
		{
			if (tmp.length != 2) 
			{
				return null;
			}
			message = game.clientHello(sanitise(arg));
		} 
		else if (com.equals("LOOK")) 
		{
			message = game.clientLook();
		}
		else if (com.equals("PICKUP")) 
		{
			message = game.clientPickup();
		} 
		else if (com.equals("MOVE")) 
		{
			// We need to know which direction to move in.
			if (tmp.length != 2) 
			{
				System.err.println("Move needs direction");
				return null;
			}
			
			String dir = sanitise(arg, "[NESW]");
			if (dir.length() > 0) 
			{
				message = game.clientMove(dir.charAt(0));
			}
			else 
			{
				System.err.println("Invalid direction");
			}
		} 
		else if (com.equals("ATTACK")) 
		{
			// They have to tell us what direction to attack in
			if (tmp.length != 2) 
			{
				System.err.println("Attack needs direction");
				return null;
			}
			
			String dir = sanitise(arg, "[NESW]");
			if (dir.length() > 0) 
			{
				message = game.clientAttack(dir.charAt(0));
			} 
			else 
			{
				System.err.println("Invalid direction");
			}
		} 
		else if (com.equals("ENDTURN")) 
		{
			message = game.clientEndTurn();
		}
		else if (com.equals("SHOUT")) 
		{
			// Ensure they have given us something to shout.
			if (tmp.length != 2) 
			{
				System.err.println("Need message to shout");
				return null;
			}
			
			message = game.clientShout(sanitizeMessage(arg));
		}
		else 
		{
			// If it is none of the above then it must be a bad command.
			System.err.println("Invalid command");
		}
		
		// Print the response from the game.
		return message;
	}
	
	
	
	/** HELPER METHODS **/
	
	
	/**
	 * Strip out anything that isn't alphanumeric
	 */
	private static String sanitise(String s)
	{
		return sanitise(s, "[a-zA-Z0-9-_:,]");
	}
	
	
	/**
	 * Strip out anything that isn't in the specified regex.
	 * 
	 * @param s		The string to be sanitised
	 * @param regex The regex to use for sanitisiation
	 * @return		The sanitised string
	 */
	private static String sanitise(String s, String regex)
	{
		String rv = "";
		
		for(int i = 0; i < s.length(); i++) 
		{
			String tmp = s.substring(i, i + 1);
			
			if (tmp.matches(regex)) 
			{
				rv += tmp;
			}
		}
		
		return rv;
	}
	
	
	/**
	 * Sanitises the given message - there are some characters that we can put in the messages that
	 * we don't want in other stuff that we sanitise.
	 * 
	 * @param s	The message to be sanitised
	 * @return 	The sanitised message
	 */
	private static String sanitizeMessage(String s)
	{
		return sanitise(s, "[a-zA-Z0-9-_ \\.,:!\\(\\)#]");
	}

	/**
	 * Main method.
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) 
	{
		switch(args.length)
		{
			case 0:
				// No Command line arguments - default map
				System.out.println("Starting Game with Default Map");
				new Protocol();
				break;
				
			case 1:
				// Either -b or the name of the map
				if(args[0].equals("-b"))
				{
					System.out.println("Starting bot game with default Map");
					new BotGame();
				}
				else
				{
					// Try to load the specified map
					System.out.println("Starting Game with Map " + args[0]);
					new Protocol(args[0]);
				}
				break;
				
			case 2:
				// The first one needs to be -b
				if(args[0].equals("-b"))
				{
					System.out.println("Starting bot game with Map " + args[1]);
					new BotGame(args[1]);
				}
				else
				{
					System.out.println("The wrong number of arguments have been provided, you can either specify \"-b\" \n" +
							   "to play with a bot, the name of the map you want to play on, or the \"-b\" followed " +
							   "\nby the name of the map you want the bot to play on");
				}
				break;
				
			default:
				System.out.println("The wrong number of arguments have been provided, you can either specify \"-b\" \n" +
						   "to play with a bot, the name of the map you want to play on, or the \"-b\" followed " +
						   "\nby the name of the map you want the bot to play on");
				break;
		}
	}
}