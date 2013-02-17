import java.io.*;

class PlayGame {
	
	private static String name;
	private static int win;
	private static int[] mapSize = new int[2];
	private static char[][] mapData;
	private static int[] playerCoords = new int[2];
	private static int gold = 0;
	private static String outputBuffer = "";
	
	private static void printOutput() {
		System.out.print(outputBuffer);
		outputBuffer = "";
	}
	
	private static void clearScreen() {
		String blank = "";
		for (int i=0 ; i<50 ; i++) {
			blank += "\n";
		}
		System.out.print(blank);
	}
	
	private static String selectMap() throws Exception {
		int fileCount;
		Integer fileNumber;
		String[] filteredFileList;
		String input = null;
		do {
			outputBuffer += "WELCOME TO DUNGEON OF DOOOM!\n\n";
			outputBuffer += "Type the number to select a map:\n";
			File[] fileList = new File("maps").listFiles();
			filteredFileList = new String[fileList.length];
			fileCount = 0;
			for (int i=0 ; i<fileList.length ; i++) {
				if (fileList[i].isFile() & !fileList[i].getName().startsWith(".")) {
					filteredFileList[fileCount] = fileList[i].getName();
					outputBuffer += "[" + (fileCount + 1) + "] " + fileList[i].getName() + "\n";
					fileCount++;
				}
			}
			if (input == null) {
				outputBuffer += "\n\n\n> ";
			}
			else if (input.toUpperCase().equals("QUIT")) {
				outputBuffer += "\n> " + input + "\nThanks for playing!\n";
				printOutput();
				System.exit(0);
			}
			else {
				outputBuffer += "\n> " + input + "\nInvalid input.\n> ";
			}
			printOutput();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			input = in.readLine();
			clearScreen();
			try {
				fileNumber = Integer.parseInt(input) - 1;
			}
			catch (NumberFormatException e) {
				fileNumber = -1;
			}
		} while (fileNumber > fileCount - 1 | fileNumber < 0);
		
		
		return "maps/" + filteredFileList[fileNumber];
	}
	
	private static void determineMapSize(String mapName) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(mapName));
		String line;
		while ((line = in.readLine()) != null) {
			if (!line.startsWith("name ") & !line.startsWith("win ") & line.length() != 0) {
				mapSize[0]++;
				mapSize[1] = line.trim().length();
			}
		}
	}
	
	private static void spawnPlayer() {
		playerCoords[0] = (int) (Math.random() * (mapSize[0]));
		playerCoords[1] = (int) (Math.random() * (mapSize[1]));
		if (mapData[playerCoords[0]][playerCoords[1]] == '#') {
			spawnPlayer();
		}
	}
	
	private static void load() throws Exception {
		String mapName = selectMap();
		determineMapSize(mapName);
		mapData = new char[mapSize[0]][mapSize[1]];
		BufferedReader in = new BufferedReader(new FileReader(mapName));
		String line;
		int mapLineCounter = 0;
		while ((line = in.readLine()) != null) {
			if (line.startsWith("name ")) {
				name = line.substring(5);
			}
			else if (line.startsWith("win ")) {
				win = Integer.parseInt(line.substring(4));
			}
			else if (line.length() != 0) {
				int lineSymbolCounter = 0;
				for (char symbol : line.toCharArray()) {
					mapData[mapLineCounter][lineSymbolCounter] = symbol;
					lineSymbolCounter++;
				}
				mapLineCounter++;
			}
		}
		spawnPlayer();
	}

	public static void printMap() {
		int lineCounter = 0;
		for (char[] line : mapData) {
			int symbolCounter = 0;
			for (char symbol : line) {
				if (lineCounter == playerCoords[0] & symbolCounter == playerCoords[1]) {
					outputBuffer += "P";
				}
				else {
					outputBuffer += symbol;
				}
				symbolCounter++;
			}
			lineCounter++;
			outputBuffer += "\n";
		}
	}

	public static boolean isWall(int row, int col) {
		return mapData[row][col] == '#';
	}

	public static String movePlayer(char direction) {
		String returnValue = "FAIL";
		switch (direction) {
			case 'N':	if (!isWall(playerCoords[0] - 1, playerCoords[1])) {
							playerCoords[0]--;
							returnValue = "SUCCESS";
						}
						break;
			case 'S':	if (!isWall(playerCoords[0] + 1, playerCoords[1])) {
							playerCoords[0]++;
							returnValue = "SUCCESS";
						}
						break;
			case 'E':	if (!isWall(playerCoords[0], playerCoords[1] + 1)) {
							playerCoords[1]++;
							returnValue = "SUCCESS";
						}
						break;
			case 'W':	if (!isWall(playerCoords[0], playerCoords[1] -1)) {
							playerCoords[1]--;
							returnValue = "SUCCESS";
						}
						break;
		}
		if (gold >= win & mapData[playerCoords[0]][playerCoords[1]] == 'E') {
			return "Congratulations, you win!";
		}
		return returnValue;
	}

	public static String processCommand(String command) {
		if (command.toUpperCase().equals("HELLO")) {
			return "GOLD " + win;
		}
		else if (command.toUpperCase().startsWith("MOVE ") & command.length() == 6) {
			return movePlayer(command.toUpperCase().charAt(5));
		}
		else if (command.toUpperCase().equals("PICKUP")) {
			if (mapData[playerCoords[0]][playerCoords[1]] == 'G') {
				mapData[playerCoords[0]][playerCoords[1]] = '.';
				gold++;
				return "SUCCESS, GOLD COINS: " + gold;
			}
			else {
				return "FAIL";
			}
		}
		else if (command.toUpperCase().equals("LOOK")) {
			return Character.toString(mapData[playerCoords[0]-1][playerCoords[1]]) + Character.toString(mapData[playerCoords[0]+1][playerCoords[1]]) + Character.toString(mapData[playerCoords[0]][playerCoords[1]+1]) + Character.toString(mapData[playerCoords[0]][playerCoords[1]-1]);
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
			return "No such command. Type \"HELP\" for list of available commands.";
		}
	}

	public static String[] promptUser() throws Exception {
		outputBuffer += "> ";
		printOutput();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		clearScreen();
		String[] returnArray = {input, processCommand(input)};
		return returnArray;
	}

	public static void main(String[] args) throws Exception {
		clearScreen();
		load();
		String[] inputOutput = {null, null};
		while (true) {
			outputBuffer += "DUNGEON OF DOOOM\n";
			outputBuffer += name + " (" + Math.max(0, win - gold) + ")\n\n";
			printMap();
			outputBuffer += "\n";
			if (inputOutput[0] != null) {
				outputBuffer += "> " + inputOutput[0] + "\n";
				outputBuffer += inputOutput[1] + "\n";
				printOutput();
				if (inputOutput[0].toUpperCase().equals("QUIT") | inputOutput[1].startsWith("Congrat")) {
					System.exit(0);
				}
			}
			else {
				outputBuffer += "\n\n";
			}
			inputOutput = promptUser();
		}
	}
	
}