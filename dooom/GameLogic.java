import java.io.*;

class GameLogic {
	
	private static int[] playerCoords = new int[2]; // row and column player is in
	private static int gold = 0; // amount of gold the player has
	
	public static int[] getMapSize() {
		return Map.getSize();
	}
	
	public static char symbolAt(int row, int col) {
		return Map.symbolAt(row, col);
	}
	
	private static void validateMap() {
		int errorFlag = 0; // 0: no error, 1: there is an error
		int goldCounter = 0; // number of gold pieces in the map
		int exitCounter = 0; // number of exits in the map
		for (int i=0 ; i < getMapSize()[0] ; i++) {
			for (int j=0 ; j < getMapSize()[1] ; j++) {
				if (symbolAt(i, j) == 'G') {
					goldCounter++;
				}
				else if (symbolAt(i, j) == 'E') {
					exitCounter++;
				}
			}
		}
		if (goldCounter < Map.getWin()) {
			System.err.println("Error: Invalid map. Not enough gold to win.");
			errorFlag = 1;
		}
		if (exitCounter == 0) {
			System.err.println("Error: Invalid map. There are no exits.");
			errorFlag = 1;
		}
		if (errorFlag != 0) {
			System.exit(-1);
		}
	}
	
	private static void spawnPlayer() {
		int[] mapSize = getMapSize();
		playerCoords[0] = (int) (Math.random() * (mapSize[0]));
		playerCoords[1] = (int) (Math.random() * (mapSize[1]));
		if (symbolAt(playerCoords[0], playerCoords[1]) == '#') {
			spawnPlayer(); // keep moving player until player is not in a wall
		}
	}

	private static String movePlayer(char direction) {
		String returnValue = "FAIL";
		switch (direction) {
			case 'N':	if (symbolAt(playerCoords[0] - 1, playerCoords[1]) != '#') {
							playerCoords[0]--;
							returnValue = "SUCCESS";
						}
						break;
			case 'S':	if (symbolAt(playerCoords[0] + 1, playerCoords[1]) != '#') {
							playerCoords[0]++;
							returnValue = "SUCCESS";
						}
						break;
			case 'E':	if (symbolAt(playerCoords[0], playerCoords[1] + 1) != '#') {
							playerCoords[1]++;
							returnValue = "SUCCESS";
						}
						break;
			case 'W':	if (symbolAt(playerCoords[0], playerCoords[1] -1) != '#') {
							playerCoords[1]--;
							returnValue = "SUCCESS";
						}
						break;
		}
		if (gold >= Map.getWin() & symbolAt(playerCoords[0], playerCoords[1]) == 'E') {
			return "Congratulations, you win!";
		}
		return returnValue;
	}

	public static String processCommand(String command) {
		if (command.toUpperCase().equals("HELLO")) {
			return "GOLD " + Map.getWin();
		}
		else if (command.toUpperCase().startsWith("MOVE ") & command.length() == 6) {
			return movePlayer(command.toUpperCase().charAt(5));
		}
		else if (command.toUpperCase().equals("PICKUP")) {
			if (symbolAt(playerCoords[0], playerCoords[1]) == 'G') {
				Map.setSymbol(playerCoords[0], playerCoords[1], '.');
				gold++;
				return "SUCCESS, GOLD COINS: " + gold;
			}
			else {
				return "FAIL";
			}
		}
		else if (command.toUpperCase().equals("LOOK")) {
			return "LOOKREPLY\n"
				 + "X"
				 + Character.toString(symbolAt(playerCoords[0]-2, playerCoords[1]-1))
				 + Character.toString(symbolAt(playerCoords[0]-2, playerCoords[1]))
				 + Character.toString(symbolAt(playerCoords[0]-2, playerCoords[1]+1))
				 + "X\n"
				 + Character.toString(symbolAt(playerCoords[0]-1, playerCoords[1]-2))
				 + Character.toString(symbolAt(playerCoords[0]-1, playerCoords[1]-1))
				 + Character.toString(symbolAt(playerCoords[0]-1, playerCoords[1]))
				 + Character.toString(symbolAt(playerCoords[0]-1, playerCoords[1]+1))
				 + Character.toString(symbolAt(playerCoords[0]-1, playerCoords[1]+2))
				 + "\n"
				 + Character.toString(symbolAt(playerCoords[0], playerCoords[1]-2))
				 + Character.toString(symbolAt(playerCoords[0], playerCoords[1]-1))
				 + Character.toString(symbolAt(playerCoords[0], playerCoords[1]))
				 + Character.toString(symbolAt(playerCoords[0], playerCoords[1]+1))
				 + Character.toString(symbolAt(playerCoords[0], playerCoords[1]+2))
				 + "\n"
				 + Character.toString(symbolAt(playerCoords[0]+1, playerCoords[1]-2))
				 + Character.toString(symbolAt(playerCoords[0]+1, playerCoords[1]-1))
				 + Character.toString(symbolAt(playerCoords[0]+1, playerCoords[1]))
				 + Character.toString(symbolAt(playerCoords[0]+1, playerCoords[1]+1))
				 + Character.toString(symbolAt(playerCoords[0]+1, playerCoords[1]+2))
				 + "\nX"
				 + Character.toString(symbolAt(playerCoords[0]+2, playerCoords[1]-1))
				 + Character.toString(symbolAt(playerCoords[0]+2, playerCoords[1]))
				 + Character.toString(symbolAt(playerCoords[0]+2, playerCoords[1]+1))
				 + "X";
		}
		else if (command.toUpperCase().equals("QUIT")) {
			return "Thanks for playing!";
		}
		else if (command.toUpperCase().equals("HELP")) {
			return "Available commands are: ABOUT | HELLO | HELP | LOOK | MOVE [N|S|E|W] | PICKUP | QUIT";
		}
		else if (command.toUpperCase().equals("ABOUT")) {
			return "Developed by Leon Byford (2013) <ldjb20@bath.ac.uk>";
		}
		else {
			return "Invalid command. Type \"HELP\" for list of available commands.";
		}
	}

	public static int getGold() {
		return gold;
	}
	
	public static String getMapName() {
		return Map.getName();
	}
	
	public static int getWin() {
		return Map.getWin();
	}
	
	public static void printMap(char playerSymbol) {
		Map.print(playerSymbol, playerCoords[0], playerCoords[1]);
	}

	public static int[] getCoords() {
		return playerCoords;
	}

	public static void init() {
		Map.load();
		validateMap();
		spawnPlayer();
	}

}