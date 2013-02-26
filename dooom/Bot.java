class Bot {

	private static char desire = 'G';
	private static char[][] mapData;
	private static int[][] lookReplyMapping = new int[21][3];
	private static char direction;
	
	/**TESTING**/
	public static void printBotMap() {
		int lineCounter = 0;
		for (char[] line : mapData) {
			int symbolCounter = 0;
			for (char symbol : line) {
				if (lineCounter == GameLogic.getCoords()[0] & symbolCounter == GameLogic.getCoords()[1]) {
					OutputHandler.addToOutput('B');
				}
				else if (symbol == 'S') {
					OutputHandler.addToOutput('.');
				}
				else if (symbol == '\u0000') {
					OutputHandler.addToOutput(' ');
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

	private static void printScreen(String[] IOData) {
		OutputHandler.addToOutput("DUNGEON OF DOOOM\n");
		OutputHandler.addToOutput(GameLogic.getMapName() + " (" + Math.max(0, GameLogic.getWin() - GameLogic.getGold()) + ")\n\n");
		printBotMap(); // GameLogic.printMap('B'); // allow switching between
		OutputHandler.addToOutput("\n");
		if (IOData[0] != null) {
			OutputHandler.addToOutput("> " + IOData[0] + "\n");
			OutputHandler.addToOutput(IOData[1] + "\n");
			if (IOData[1].length() != 39) {
				OutputHandler.addToOutput("\n\n\n\n\n");
			}
			OutputHandler.printOutput();
			if (IOData[0].toUpperCase().equals("QUIT") | IOData[1].startsWith("Congrat")) {
				System.exit(0);
			}
		}
		else {
			OutputHandler.addToOutput("\n\n\n\n\n\n\n");
		}
	}
	
	private static void lookReplyMappingArrayAssigner(int index, int charAt, int row, int col) {
		lookReplyMapping[index][0] = charAt;
		lookReplyMapping[index][1] = row;
		lookReplyMapping[index][2] = col;
	}
	
	private static void constructLookReplyMappingArray() {
		lookReplyMappingArrayAssigner(0, 11, -2, -1);
		lookReplyMappingArrayAssigner(1, 12, -2, 0);
		lookReplyMappingArrayAssigner(2, 13, -2, 1);
		lookReplyMappingArrayAssigner(3, 16, -1, -2);
		lookReplyMappingArrayAssigner(4, 17, -1, -1);
		lookReplyMappingArrayAssigner(5, 18, -1, 0);
		lookReplyMappingArrayAssigner(6, 19, -1, 1);
		lookReplyMappingArrayAssigner(7, 20, -1, 2);
		lookReplyMappingArrayAssigner(8, 22, 0, -2);
		lookReplyMappingArrayAssigner(9, 23, 0, -1);
		lookReplyMappingArrayAssigner(10, 24, 0, 0);
		lookReplyMappingArrayAssigner(11, 25, 0, 1);
		lookReplyMappingArrayAssigner(12, 26, 0, 2);
		lookReplyMappingArrayAssigner(13, 28, 1, -2);
		lookReplyMappingArrayAssigner(14, 29, 1, -1);
		lookReplyMappingArrayAssigner(15, 30, 1, 0);
		lookReplyMappingArrayAssigner(16, 31, 1, 1);
		lookReplyMappingArrayAssigner(17, 32, 1, 2);
		lookReplyMappingArrayAssigner(18, 35, 2, -1);
		lookReplyMappingArrayAssigner(19, 36, 2, 0);
		lookReplyMappingArrayAssigner(20, 37, 2, 1);
	}

	private static void look() throws Exception {
		String[] response = sendCommand("LOOK");
		String lookReply = response[1];
		for (int[] mapping : lookReplyMapping) {
			if (!(GameLogic.getCoords()[0]+mapping[1] < 0 | GameLogic.getCoords()[0]+mapping[1] >= GameLogic.getMapSize()[0] | GameLogic.getCoords()[1]+mapping[2] < 0 | GameLogic.getCoords()[1]+mapping[2] >= GameLogic.getMapSize()[1])) {
				if (mapData[GameLogic.getCoords()[0] + mapping[1]][GameLogic.getCoords()[1] + mapping[2]] != 'S') {
					mapData[GameLogic.getCoords()[0] + mapping[1]][GameLogic.getCoords()[1] + mapping[2]] = lookReply.charAt(mapping[0]);
				}
			}
		}
		printScreen(response);
	}

	public static void markAsSeen() throws Exception {
		if (relSymbol(0, 0) != 'E') {
			mapData[GameLogic.getCoords()[0]][GameLogic.getCoords()[1]] = 'S';
		}
	}

	public static void initMap() throws Exception {
		mapData = new char[GameLogic.getMapSize()[0]][GameLogic.getMapSize()[1]];
		//markAsSeen();
	}

	public static String[] sendCommand(String command) throws Exception {
		OutputHandler.addToOutput("> " + command);
		OutputHandler.printOutput();
		System.in.read();
		OutputHandler.clearScreen();
		String[] returnArray = {command, GameLogic.processCommand(command)};
		return returnArray;
	}
	
	public static String[] move(char inDir) throws Exception {
		markAsSeen();
		direction = inDir;
		return sendCommand("MOVE " + Character.toString(inDir));
	}
	
	public static void updateDesire() {
		if (GameLogic.getGold() < GameLogic.getWin()) {
			desire = 'G';
		}
		else {
			desire = 'E';
		}
	}

	public static char relSymbol(int y, int x) throws Exception {
		if (GameLogic.getCoords()[0]+y < 0 | GameLogic.getCoords()[0]+y >= GameLogic.getMapSize()[0] | GameLogic.getCoords()[1]+x < 0 | GameLogic.getCoords()[1]+x >= GameLogic.getMapSize()[1]) {
			return '#';
		}
		if (mapData[GameLogic.getCoords()[0]+y][GameLogic.getCoords()[1]+x] != '\u0000') {
			//sendCommand("LOOK"); <-- sort this out
			return mapData[GameLogic.getCoords()[0]+y][GameLogic.getCoords()[1]+x];
		}
		look();
		return GameLogic.symbolAt(GameLogic.getCoords()[0]+y, GameLogic.getCoords()[1]+x);
	}

	public static String[] nextCommand() throws Exception {
		if (relSymbol(0, 0) == desire) {
			mapData[GameLogic.getCoords()[0]][GameLogic.getCoords()[1]] = '.';
			return sendCommand("PICKUP");
		}
		while (true) {
			int possibleMoves = 1;
			while (true) {
				String possibilities = "";
				if (possibleMoves == 1) {
					if (relSymbol(-1, 0) == desire) {
						possibilities += "N";
					}
					if (relSymbol(1, 0) == desire) {
						possibilities += "S";
					}
					if (relSymbol(0, 1) == desire) {
						possibilities += "E";
					}
					if (relSymbol(0, -1) == desire) {
						possibilities += "W";
					}
				}
				if (possibleMoves == 2) {
					if (relSymbol(-1, -1) == desire) {
						if (relSymbol(-1, 0) != '#') {
							possibilities += "N";
						}
/* else if(?) */		if (relSymbol(0, -1) != '#') {
							possibilities += "W";
						}
					}
					if (relSymbol(-1, 1) == desire) {
						if (relSymbol(-1, 0) != '#') {
							possibilities += "N";
						}
/* else if(?) */		if (relSymbol(0, 1) != '#') {
							possibilities += "E";
						}
					}
					if (relSymbol(1, 1) == desire) {
						if (relSymbol(1, 0) != '#') {
							possibilities += "S";
						}
/* else if(?) */		if (relSymbol(0, 1) != '#') {
							possibilities += "E";
						}
					}
					if (relSymbol(1, -1) == desire) {
						if (relSymbol(1, 0) != '#') {
							possibilities += "S";
						}
/* else if(?) */		if (relSymbol(0, -1) != '#') {
							possibilities += "W";
						}
					}
					if (relSymbol(-2, 0) == desire) {
						if (relSymbol(-1, 0) != '#') {
							possibilities += "N";
						}
					}
					if (relSymbol(0, 2) == desire) {
						if (relSymbol(0, 1) != '#') {
							possibilities += "E";
						}
					}
					if (relSymbol(2, 0) == desire) {
						if (relSymbol(1, 0) != '#') {
							possibilities += "S";
						}
					}
					if (relSymbol(0, -2) == desire) {
						if (relSymbol(0, -1) != '#') {
							possibilities += "W";
						}
					}
				}
				if (possibleMoves == 3) {
					if (relSymbol(-2, 1) == desire) {
						if (relSymbol(-2, 0) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
						}
						if (relSymbol(-1, 1) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
					}
					if (relSymbol(-1, 2) == desire) {
						if (relSymbol(0, 2) != '#') {
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
						if (relSymbol(-1, 1) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
					}
					if (relSymbol(1, 2) == desire) {
						if (relSymbol(0, 2) != '#') {
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
						if (relSymbol(1, 1) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
					}
					if (relSymbol(2, 1) == desire) {
						if (relSymbol(2, 0) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
						}
						if (relSymbol(1, 1) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
							if (relSymbol(0, 1) != '#') {
								possibilities += "E";
							}
						}
					}
					if (relSymbol(2, -1) == desire) {
						if (relSymbol(2, 0) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
						}
						if (relSymbol(1, -1) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
					}
					if (relSymbol(1, -2) == desire) {
						if (relSymbol(0, -2) != '#') {
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
						if (relSymbol(1, -1) != '#') {
							if (relSymbol(1, 0) != '#') {
								possibilities += "S";
							}
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
					}
					if (relSymbol(-1, -2) == desire) {
						if (relSymbol(0, -2) != '#') {
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
						if (relSymbol(-1, -1) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
					}
					if (relSymbol(-2, -1) == desire) {
						if (relSymbol(-2, 0) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
						}
						if (relSymbol(-1, -1) != '#') {
							if (relSymbol(-1, 0) != '#') {
								possibilities += "N";
							}
							if (relSymbol(0, -1) != '#') {
								possibilities += "W";
							}
						}
					}
				}
				if (possibleMoves == 4) {
					break;
				}
				if (possibilities.length() > 0) {
					return move(possibilities.charAt((int) (Math.random() * possibilities.length())));
				}
				possibleMoves++;
			}
			if (desire == 'E') { // not sure about this whole part
				desire = 'G';
			}
			else if (desire == 'G') {
				desire = '.';
			}
			else {
				break;
			}
		}
		String possibilities = "";
		if (relSymbol(-1, 0) != '#') {
			possibilities += "N";
		}
		if (relSymbol(1, 0) != '#') {
			possibilities += "S";
		}
		if (relSymbol(0, 1) != '#') {
			possibilities += "E";
		}
		if (relSymbol(0, -1) != '#') {
			possibilities += "W";
		}
		
		if (direction == 'N') {
			if (possibilities.replaceAll("S", "").length() > 0) {
				possibilities = possibilities.replaceAll("S", "");
			}
		}
		else if (direction == 'S') {
			if (possibilities.replaceAll("N", "").length() > 0) {
				possibilities = possibilities.replaceAll("N", "");
			}
		}
		else if (direction == 'E') {
			if (possibilities.replaceAll("W", "").length() > 0) {
				possibilities = possibilities.replaceAll("W", "");
			}
		}
		else if (direction == 'W') {
			if (possibilities.replaceAll("E", "").length() > 0) {
				possibilities = possibilities.replaceAll("E", "");
			}
		}
		
		return move(possibilities.charAt((int) (Math.random() * possibilities.length())));
	}

	public static void main(String[] args) throws Exception {
		OutputHandler.clearScreen();
		GameLogic.init();
		initMap();
		constructLookReplyMappingArray();
		String[] inputOutput = {null, null};
		while (true) {
			updateDesire();
			printScreen(inputOutput);
			inputOutput = nextCommand();
		}
	}
	
}