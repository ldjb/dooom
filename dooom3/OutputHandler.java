class OutputHandler {

	// initialise the output buffer
	private static String outputBuffer = "";
	
	// add text to output buffer
	public static void addToOutput(String text) {
		outputBuffer += text;
	}
	
	// adding a single character to the output buffer
	public static void addToOutput(char character) {
		outputBuffer += Character.toString(character);
	}
	
	// flush the output buffer
	public static void printOutput() {
		System.out.print(outputBuffer);
		outputBuffer = "";
	}
	
	// scroll the screen's contents to above the viewable area
	public static void clearScreen() {
		String blank = "";
		for (int i=0 ; i<50 ; i++) {
			blank += "\n";
		}
		System.out.print(blank);
	}
	
}
