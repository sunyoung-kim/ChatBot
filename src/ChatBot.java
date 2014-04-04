/*
 * Winter 2014
 * CIS 365 Artificial Intelligence
 * SunYoung Kim
 * Thomas Sniadecki
 */
import java.util.*;
import java.net.*;
import java.io.*;

public class ChatBot {
	
	private static final String newline = "\n";
	private List<String> userInput;
	
	public ChatBot() {
		
	}
	
	public void setUserInput(String s) {
		userInput.add(s);
	}
	
	public List<String> getUserInput() {
		return userInput;
	}
	
	// TODO: get the result and print
	public String print(String in) {
		String out;
		out = "You > " + in.toLowerCase() + newline
				+ "ChatBot > " + in.toUpperCase() + newline;
				
		return out;
	}
	
	public static void tokenizedResult(String in) {
		int start = in.indexOf("<div");
		int end = in.indexOf(">");
		
		if(start > 0)
			System.out.println("Start: " + start);
		if(end > 0)
			System.out.println("End: " + end);
	}
	
	public static void main(String[] args) throws IOException {
		
		// TODO: Find the wiki doc url from user input
		URL url = new URL("http://en.wikipedia.org");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(url.openStream()));
		
		String inputLine;
		while((inputLine = in.readLine()) != null) {
			
			// TODO: Tokenize the user input to find the keywords
			if(inputLine.contains("Thaddeus Stevens")) {
				//tokenizedResult(inputLine);
				System.out.println("\t" + inputLine);
			}
		}
		in.close();
		
	/*
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String input;
		
		while(true){
			System.out.print("You > ");
			input = (scanner.nextLine()).toLowerCase();
			System.out.print("ChatBot > ");
			System.out.println(input);
		}
	*/
		
	}
}
