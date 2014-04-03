/*
 * Winter 2014
 * CIS 365 Artificial Intelligence
 * SunYoung Kim
 * Thomas Sniadecki
 */
import java.util.*;

public class ChatBot {
	
	private String input;
	private String output;
	
	public ChatBot() {
		
	}
	
	public String printOutput() {
		
		return output;
	}
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String input;
		
		while(true){
			System.out.print("You > ");
			input = (scanner.nextLine()).toLowerCase();
			System.out.print("ChatBot > ");
			System.out.println(input);
		}
		
	}
}
