package Main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Init implements ActionListener {

	static int b_WIDTH = 6;
	static int b_HEIGHT = 5;
	static int depth = 9;

	JFrame frame = new JFrame();
	JButton start = new JButton();
	JButton simulateMove = new JButton();
	JButton runMove = new JButton();
	JButton randomizeBoard = new JButton();
	JTextField text = new JTextField();
	JProgressBar pb;
	BoardGraphics board;
	Heuristics heur;

	LinkedList<Integer[]> bestPath = new LinkedList<Integer[]>();
	
	public Init(){
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		start.setText("start");
		start.setActionCommand("start");
		start.addActionListener(this);
		frame.add(start);

		board = new BoardGraphics();
		frame.add(board);

		pb = new JProgressBar(SwingConstants.HORIZONTAL,0,Init.depth);
		pb.setToolTipText("");
		frame.add(pb);

		simulateMove.setText("Simulate Move");
		simulateMove.setActionCommand("siMove");
		simulateMove.addActionListener(this);
		simulateMove.setVisible(false);
		frame.add(simulateMove);
		
		runMove.setText("Run Move");
		runMove.setActionCommand("ruMove");
		runMove.addActionListener(this);
		runMove.setVisible(false);
		frame.add(runMove);
		
		randomizeBoard.setText("Randomize");
		randomizeBoard.setActionCommand("randomize");
		randomizeBoard.addActionListener(this);
		frame.add(randomizeBoard);
		
		text.setText("Calculate First");
		frame.add(text);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.contains("start")){
			pb.setValue(0);
			heur = null;
			heur = new Heuristics(board, pb);
			heur.addActionListener(this);
			heur.start();
		}
		if(cmd.equals("ThreadFinished")){
			bestPath = heur.getBestPath();
			simulateMove.setVisible(true);
			runMove.setVisible(true);
			
		}
		if(cmd.equals("siMove")){
			board.executeMoveList(bestPath);
		}
		if(cmd.equals("ruMove")){
			board.runMove();
		}
		if(cmd.equals("randomize")){
			board.randomize();
		}
	}
}
