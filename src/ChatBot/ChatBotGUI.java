package ChatBot;

/*
 * Winter 2014
 * CIS 365 Artificial Intelligence
 * SunYoung Kim
 * Thomas Sniadecki
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ChatBotGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel GUITitle;
	private JTextArea textArea;
	private JTextField textField;
	private JScrollPane scrollPane;
	
	private static ChatBotGUI cbGUI;
	ChatBot cb = new ChatBot();

	// create ChatBot GUI
	public ChatBotGUI() {
		JFrame frame = new JFrame("Chat Bot");
		
		frame.setContentPane(createPanel());
		
		frame.setSize(700, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// get panel with textarea and textfield
	public JPanel createPanel() {
		// generate JPanel
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		
		// add title for the program
		GUITitle = new JLabel("Chat Bot");
		panel.add(GUITitle);
		
		// add textArea for printing user's commands and responses
		textArea = new JTextArea(15, 60);
		// add scrollPane for textArea
		scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		
		textArea.setBackground(Color.lightGray);
		panel.add(scrollPane);
		
		// add textField for user to type in
		textField = new JTextField(60);
		textField.setEditable(true);
		
		// add action listener for pushing ENTER to the textfield
		textField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyChar() == KeyEvent.VK_ENTER){
					printTextField(cb.print(textField.getText()));
				}
			}
			public void keyReleased(KeyEvent arg0) {
			}
			public void keyTyped(KeyEvent arg0) {
			}
		});
		
		textField.setBackground(Color.lightGray);
		panel.add(textField);
		
		return panel;
	}
	
	// appends textfield string to textarea
	public void printTextField(String text) {
		textArea.setText(textArea.getText() + text);
		textField.setText(null);
	}

	// call the ChatBotGUI
	public static void main(String[] args) {
		cbGUI = new ChatBotGUI();
	}

	public void actionPerformed(ActionEvent ae) {
	}
}
