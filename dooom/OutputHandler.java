class OutputHandler {

	private static String outputBuffer = "";
	
	public static void addToOutput(String text) {
		outputBuffer += text;
	}
	
	public static void addToOutput(char character) {
		outputBuffer += Character.toString(character);
	}
	
	public static void printOutput() {
		System.out.print(outputBuffer);
		outputBuffer = "";
	}
	
	public static void clearScreen() {
		String blank = "";
		for (int i=0 ; i<50 ; i++) {
			blank += "\n";
		}
		System.out.print(blank);
	}
	
}