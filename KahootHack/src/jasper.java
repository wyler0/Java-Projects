import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class jasper {
	
	JTextField gameIDField = new JTextField("Game ID");
	JTextField nickField = new JTextField("Nickname");
	JTextArea status = new JTextArea();
	String[] commands = new String[]{"Flood"};
	JComboBox<String> commandChooser = new JComboBox<String>(commands);
	JButton goButton = new JButton("GO");
	String gameID;
	
	public jasper(){
		initJFrame();
	}
	
	private void initJFrame(){
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(200, 500);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Kahoot Hacker by Wyler Zahm");
		frame.getContentPane().setBackground(new Color(200, 200, 200));

		gameIDField.setPreferredSize(new Dimension(frame.getWidth()-30, 30));
		frame.getContentPane().add(gameIDField);
		nickField.setPreferredSize(new Dimension(frame.getWidth()-30, 30));
		frame.getContentPane().add(nickField);

		commandChooser.setPreferredSize(new Dimension(frame.getWidth()-30, 30));
		frame.getContentPane().add(commandChooser);
		
		goButton.setPreferredSize(new Dimension(frame.getWidth()-30, 30));
		frame.getContentPane().add(goButton);
		goButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					verifyInputID();
					System.out.println(gameIDField.getText());
					exec(gameIDField.getText(), nickField.getText(), commandChooser.getSelectedIndex());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}          
		});
		
		frame.getContentPane().add(new JLabel("       Made By Wyler Zahm       "));
		
		status.setPreferredSize(new Dimension(frame.getWidth()-20,200));
		frame.getContentPane().add(status);
	}
	
	private void verifyInputID(){
		String prev = gameIDField.getText();
		gameIDField.setText(prev.replaceAll("[^\\d.]", ""));
		if(prev!= gameIDField.getText()){
			status.setText(status.getText()+"Removed Letters from Game ID\n");
		}
	}
	
	private void exec(String gameID, String nick, int hack){
		
	}
}
