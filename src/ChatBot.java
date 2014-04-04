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
	private String output;
	
	public ChatBot() {
		
	}
	
	public String printOutput(String in) {
		output = "You > " + in.toLowerCase() + newline
				+ "ChatBot > " + getOutput(in) + newline;
		
		return output;
	}
	
	public String getOutput(String in) {
		output = in.toUpperCase();
		return output;
	}
	
	public static void main(String[] args) throws IOException {
		
		// TODO: Find the wiki doc url from user input
		URL url = new URL("http://en.wikipedia.org");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(url.openStream()));
		
		String inputLine;
		while((inputLine = in.readLine()) != null) {
			
			// TODO: Tokenize the user input to find the keywords
			if(inputLine.contains("div"))
				System.out.println("\t" + inputLine);
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
