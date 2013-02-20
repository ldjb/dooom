import java.io.*;

class GameLogic {
	
	public static int[] playerCoords = new int[2]; // this should be private in final build
	private static int gold = 0;
	
	private static void spawnPlayer() {
		int[] mapSize = Map.getSize();
		playerCoords[0] = (int) (Math.random() * (mapSize[0]));
		playerCoords[1] = (int) (Math.random() * (mapSize[1]));
		if (Map.symbolAt(playerCoords[0], playerCoords[1]) == '#') {
			spawnPlayer();
		}
	}

	public static String movePlayer(char direction) {
		Map.setSymbol(playerCoords[0], playerCoords[1], 's');
		String returnValue = "FAIL";
		switch (direction) {
			case 'N':	if (Map.symbolAt(playerCoords[0] - 1, playerCoords[1]) != '#') {
							playerCoords[0]--;
							returnValue = "SUCCESS";
						}
						break;
			case 'S':	if (Map.symbolAt(playerCoords[0] + 1, playerCoords[1]) != '#') {
							playerCoords[0]++;
							returnValue = "SUCCESS";
						}
						break;
			case 'E':	if (Map.symbolAt(playerCoords[0], playerCoords[1] + 1) != '#') {
							playerCoords[1]++;
							returnValue = "SUCCESS";
						}
						break;
			case 'W':	if (Map.symbolAt(playerCoords[0], playerCoords[1] -1) != '#') {
							playerCoords[1]--;
							returnValue = "SUCCESS";
						}
						break;
		}
		if (gold >= Map.getWin() & Map.symbolAt(playerCoords[0], playerCoords[1]) == 'E') {
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
			if (Map.symbolAt(playerCoords[0], playerCoords[1]) == 'G') {
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
				 + Character.toString(Map.symbolAt(playerCoords[0]-2, playerCoords[1]-1))
				 + Character.toString(Map.symbolAt(playerCoords[0]-2, playerCoords[1]))
				 + Character.toString(Map.symbolAt(playerCoords[0]-2, playerCoords[1]+1))
				 + "X\n"
				 + Character.toString(Map.symbolAt(playerCoords[0]-1, playerCoords[1]-2))
				 + Character.toString(Map.symbolAt(playerCoords[0]-1, playerCoords[1]-1))
				 + Character.toString(Map.symbolAt(playerCoords[0]-1, playerCoords[1]))
				 + Character.toString(Map.symbolAt(playerCoords[0]-1, playerCoords[1]+1))
				 + Character.toString(Map.symbolAt(playerCoords[0]-1, playerCoords[1]+2))
				 + "\n"
				 + Character.toString(Map.symbolAt(playerCoords[0], playerCoords[1]-2))
				 + Character.toString(Map.symbolAt(playerCoords[0], playerCoords[1]-1))
				 + Character.toString(Map.symbolAt(playerCoords[0], playerCoords[1]))
				 + Character.toString(Map.symbolAt(playerCoords[0], playerCoords[1]+1))
				 + Character.toString(Map.symbolAt(playerCoords[0], playerCoords[1]+2))
				 + "\n"
				 + Character.toString(Map.symbolAt(playerCoords[0]+1, playerCoords[1]-2))
				 + Character.toString(Map.symbolAt(playerCoords[0]+1, playerCoords[1]-1))
				 + Character.toString(Map.symbolAt(playerCoords[0]+1, playerCoords[1]))
				 + Character.toString(Map.symbolAt(playerCoords[0]+1, playerCoords[1]+1))
				 + Character.toString(Map.symbolAt(playerCoords[0]+1, playerCoords[1]+2))
				 + "\nX"
				 + Character.toString(Map.symbolAt(playerCoords[0]+2, playerCoords[1]-1))
				 + Character.toString(Map.symbolAt(playerCoords[0]+2, playerCoords[1]))
				 + Character.toString(Map.symbolAt(playerCoords[0]+2, playerCoords[1]+1))
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

	public static void init() throws Exception {
		Map.load();
		spawnPlayer();
	}

}