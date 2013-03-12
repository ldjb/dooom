

import java.util.Random;


/**
 * This class plays the game as a very basic bot that will just move around randomly and pick up anything that
 * it lands on.
 * 
 * Note that there is a (very slim) chance that it will go on forever because it's just moving at random. If you have
 * a non-randomly moving bot from the first piece of coursework then it might be better to use that instead of this one!
 * 
 * Or of course you could just put your movement methods in place of randomMove()
 */
public class BotGame 
{
	private DODGame game;
	private boolean hasLantern = false;
	private boolean hasSword = false;
	private boolean hasArmour = false;
	
	/**
	 * Creates a new Bot controlled game
	 */
	public BotGame()
	{
		game = new DODGame();
		play();
	}
	
	
	/**
	 * Creates a new Bot controlled game on a specific map
	 * 
	 * @param map The map to load
	 */
	public BotGame(String map)
	{
		game = new DODGame(map);
		play();
	}
	
	
	/**
	 * Controls the playing logic of the bot
	 */
	private void play()
	{		
		// While true is OK here because DODGame will quit
		while(true)
		{				
			// Work out what the bot can see
			String [] tempVision = game.clientLook().split("\r\n|\n");
			
			int size = hasLantern ? 7 : 5;
			char[][] vision = new char[size][size];
			
			for(int i = 0; i < size; i++)
			{
				for(int j = 0; j < size; j++)
				{
					vision[i][j] = tempVision[i + 1].charAt(j);
				}
			}	
			
			String message = "";
			
			// Check if the cell we are on has anything in it - if it does then pick it up
			switch(vision[size/2][size/2])
			{
				// We can't pick these up if we already have them, so don't even try
				case 'A':
					if(!hasArmour) {
						message = game.clientPickup();
						// We assume that this will be successful, but we could check the reply from the game.
						hasArmour = true;
					}
					break;
			
				case 'L':
					if(!hasLantern) {
						message = game.clientPickup();
						hasLantern = true;
					}
					break;
			
				case 'S':
					if(!hasSword) {
						message = game.clientPickup();
						hasSword = true;
					}
					break;
					
				case 'G':
					message = game.clientPickup();
					break;
					
				case 'H':
					message = game.clientPickup();
					break;
					
				case '.':
					// Don't need to pick anything up
					break;
					
				default:
					// The only other thing it could be is a #
					break;
			}
			
			if(!message.equals(""))
				System.out.println(message);
			
			// Move randomly
			// Note that if we land on an exit then the game will just continue unless we have enough gold to win
			char dir = randomMove(vision, size);
			message = game.clientMove(dir);
			
			// Return the status of any operation that we have performed
			System.out.println(message + ": MOVE " + dir);
		}
	}
	
	
	/**
	 * Return a direction to move in. Note that we do checks to see what it in the square before sending the 
	 * request to move to the game logic
	 * 
	 * @param vision	The area that the bot can "see"
	 * @param size		The size of the area the bot can "see"
	 * @return			The direction in which to move
	 */
	private char randomMove(char[][] vision, int size)
	{
		Random random = new Random();
		int dir = (int) (random.nextFloat() * 4F);
		
		switch(dir)
		{
		case 0: // N
			if(vision[size/2-1][size/2] != '#')
				return 'N';
			break;
			
		case 1: // E
			if(vision[size/2][size/2+1] != '#')
				return 'E';
			break;
			
		case 2: // S
			if(vision[size/2+1][size/2] != '#')
				return 'S';
			break;
			
		case 3: // W
			if(vision[size/2][size/2-1] != '#')
				return 'W';
			break;
		}
		
		// If we don't have a location then recurse
		return randomMove(vision, size);
	}
}