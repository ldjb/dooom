import java.io.*;

class Map {

	private static String name;
	private static int win;
	private static int[] mapSize = new int[2];
	private static char[][] mapData;

	private static String selectMap() {
		int fileCount;
		Integer fileNumber;
		String[] filteredFileList;
		String input = null;
		do {
			OutputHandler.addToOutput("WELCOME TO DUNGEON OF DOOOM!\n\n");
			OutputHandler.addToOutput("Type the number to select a map:\n");
			File[] fileList = new File("maps").listFiles();
			filteredFileList = new String[fileList.length];
			fileCount = 0;
			for (int i=0 ; i<fileList.length ; i++) {
				if (fileList[i].isFile() & !fileList[i].getName().startsWith(".")) {
					filteredFileList[fileCount] = fileList[i].getName();
					OutputHandler.addToOutput("[" + (fileCount + 1) + "] " + fileList[i].getName() + "\n");
					fileCount++;
				}
			}
			if (input == null) {
				OutputHandler.addToOutput("\n\n\n> ");
			}
			else if (input.toUpperCase().equals("QUIT")) {
				OutputHandler.addToOutput("\n> " + input + "\nThanks for playing!\n");
				OutputHandler.printOutput();
				System.exit(0);
			}
			else {
				OutputHandler.addToOutput("\n> " + input + "\nInvalid input.\n> ");
			}
			OutputHandler.printOutput();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = in.readLine();
			}
			catch (IOException e) {
				System.err.println("Error: Could not read input. " + e);
				System.exit(-1);
			}
			OutputHandler.clearScreen();
			try {
				fileNumber = Integer.parseInt(input) - 1;
			}
			catch (NumberFormatException e) {
				fileNumber = -1;
			}
		} while (fileNumber > fileCount - 1 | fileNumber < 0);
		
		
		return "maps/" + filteredFileList[fileNumber];
	}
	
	private static void determineMapSize(String mapName) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(mapName));
			String line;
			while ((line = in.readLine()) != null) {
				if (!line.startsWith("name ") & !line.startsWith("win ") & line.length() != 0) {
					mapSize[0]++;
					mapSize[1] = line.trim().length();
				}
			}
		}
		catch (FileNotFoundException e) {
			System.err.println("Error: File not found. " + e);
		}
		catch (IOException e) {
			System.err.println("Error: Could not read input. " + e);
		}
	}
	
	public static void load() {
		String mapName = selectMap();
		determineMapSize(mapName);
		mapData = new char[mapSize[0]][mapSize[1]];
		try {
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
		}
		catch (FileNotFoundException e) {
			System.err.println("Error: File not found. " + e);
		}
		catch (IOException e) {
			System.err.println("Error: Could not read input. " + e);
		}
	}
	
	public static void print(char playerSymbol, int playerRow, int playerCol) {
		int lineCounter = 0;
		for (char[] line : mapData) {
			int symbolCounter = 0;
			for (char symbol : line) {
				if (lineCounter == playerRow & symbolCounter == playerCol) {
					OutputHandler.addToOutput(playerSymbol);
				}
				else {
					OutputHandler.addToOutput(symbol);
				}
				symbolCounter++;
			}
			lineCounter++;
			OutputHandler.addToOutput("\n");
		}
	}
	
	public static char symbolAt(int row, int col) {
		if (row < 0 | row >= mapSize[0] | col < 0 | col >= mapSize[1]) {
			return '#';
		}
		return mapData[row][col];
	}

	public static void setSymbol(int row, int col, char symbol) {
		mapData[row][col] = symbol;
	}
	
	public static int[] getSize() {
		return mapSize;
	}
	
	public static int getWin() {
		return win;
	}
	
	public static String getName() {
		return name;
	}

}