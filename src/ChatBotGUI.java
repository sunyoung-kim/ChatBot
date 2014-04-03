import java.awt.*;
import javax.swing.*;


public class ChatBotGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static ChatBotGUI cb;

	public ChatBotGUI() {
		JFrame frame = new JFrame("Chat Bot");
		
		frame.setContentPane(createPanel());
		
		frame.setSize(700, 450);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		
		JTextArea textArea = new JTextArea(20, 60);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		
		textArea.setBackground(Color.lightGray);
		panel.add(textArea);
		
		JTextArea typeArea = new JTextArea(5, 50);
		typeArea.setEditable(true);
		typeArea.setLineWrap(false);
		
		typeArea.setBackground(Color.lightGray);
		panel.add(typeArea);
		
		return panel;
	}
	
	public static void main(String[] args) {
		cb = new ChatBotGUI();
	}
}
