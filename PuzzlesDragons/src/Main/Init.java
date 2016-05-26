package Main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class Init implements ActionListener {
	
	static int b_WIDTH = 3;
	static int b_HEIGHT = 3;
	static int depth = 3;
	
	JFrame frame = new JFrame();
	JButton start = new JButton();
	JProgressBar progressBar;
	Heuristics heur;
	SwingWorker sw;
	
	public Init(){
		
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		start.setText("start");
		start.setActionCommand("start");
		start.addActionListener(this);
		frame.add(start);
		
		//progressBar = new JProgressBar;
		
		BoardGraphics board = new BoardGraphics();
		frame.add(board);
		
		heur = new Heuristics(board);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.contains("start")){
			heur.start();
			
		}
		
	}
}
