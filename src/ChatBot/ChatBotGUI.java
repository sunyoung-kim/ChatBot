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

	public ChatBotGUI() {
		JFrame frame = new JFrame("Chat Bot");
		
		frame.setContentPane(createPanel());
		
		frame.setSize(700, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		
		GUITitle = new JLabel("Chat Bot");
		panel.add(GUITitle);
		
		textArea = new JTextArea(15, 60);
		scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		
		textArea.setBackground(Color.lightGray);
		panel.add(scrollPane);
		
		textField = new JTextField(60);
		textField.setEditable(true);
		
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
	
	public void printTextField(String text) {
		textArea.setText(textArea.getText() + text);
		textField.setText(null);
	}

	
	public static void main(String[] args) {
		cbGUI = new ChatBotGUI();
	}

	public void actionPerformed(ActionEvent ae) {
	}
}
