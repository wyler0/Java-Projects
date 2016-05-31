package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BoardGraphics extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Orb[][] orbs;
	
	JButton start = new JButton();
	JPanel grid = new JPanel();

	public BoardGraphics(){
		loadBoard();
		loadGraphics();
	}
	public void loadBoard(){
		orbs = new Orb[Init.b_HEIGHT][Init.b_WIDTH];
		for (int x = 0; x < Init.b_HEIGHT; x++) {
			for (int y = 0; y < Init.b_WIDTH; y++) {
				int state = 0;
				if((x+y)%2==0) state =1;
				orbs[x][y]=new Orb(new int[]{x,y}, state);
			}
		}
	}
	public void loadGraphics(){
		this.setLayout(new FlowLayout());

		grid.setBackground(new Color(0,0,0));
		GridLayout gr = new GridLayout(Init.b_HEIGHT, Init.b_WIDTH);
		grid.setLayout(gr);
		grid.setPreferredSize(new Dimension(200,200));
		this.add(grid);

		updateGrid();
	}

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd.contains("gridClick")){
			cmd = cmd.substring(9);
			String[] ay = cmd.split(" ");
			rotOrb(Integer.parseInt(ay[0]),Integer.parseInt(ay[1]));
		}
		if(cmd.contains("orbMove")){
			cmd = cmd.substring(7);
			int r1 = Integer.parseInt(cmd.substring(0,cmd.indexOf('_')));
			cmd = cmd.substring(cmd.indexOf('_')+1);
			int c1 = Integer.parseInt(cmd.substring(0,cmd.indexOf('_')));
			cmd = cmd.substring(cmd.indexOf('_')+1);
			int r2 = Integer.parseInt(cmd.substring(0,cmd.indexOf('_')));
			cmd = cmd.substring(cmd.indexOf('_')+1);
			int c2 = Integer.parseInt(cmd);
			executeSingleMove(r1, c1, r2, c2);
		}
	}

	//necessary board methods
	public String toString(){
		String ret = "";
		for (int x = 0; x < Init.b_WIDTH; x++) {
			for (int y = 0; y < Init.b_HEIGHT; y++) {
				ret+=" "+orbs[x][y].toString()+" ";
			}
			ret+="\n";
		}
		return ret;
	}
	public void rotOrb(int r, int c){
		if(orbs[r][c].getState()<6) orbs[r][c].setState(orbs[r][c].getState()+1);
		else orbs[r][c].setState(0);
		updateGrid();
	}
	public void setOrb(int r, int c, int state){
		orbs[r][c].setState(state);
	}
	public void updateGrid(){
		grid.removeAll();
		for (int r = 0; r < Init.b_HEIGHT; r++) {
			for (int c = 0; c < Init.b_WIDTH; c++) {
				JButton jb = new JButton();
				jb.setBackground(orbs[r][c].toColor());
				jb.setActionCommand("gridClick"+r+" "+c);		
				jb.addActionListener(this);
				grid.add(jb);
			}
		}
		grid.revalidate();
		grid.repaint();
	}
	public Orb getOrbAt(int r, int c){
		return orbs[r][c];
	}
	
	public void executeMoveList(LinkedList<Integer[]> path){
		/*for (Integer[] inte: path) {
			System.out.println(inte[0]+":"+inte[1]);
		}*/
		Integer[] inte;
		Integer[] inteNext;
		for (int i = 0; i < path.size(); i++) {
			inte = path.get(i);
			if(!(i>path.size()-2)){
				Timer t = new Timer(i*500, this);
				inteNext = path.get(i+1);
				t.setActionCommand("orbMove"+inte[0]+"_"+inte[1]+"_"+inteNext[0]+"_"+inteNext[1]);
				t.setRepeats(false);
				t.start();
			}
		}
	}
	public void executeSingleMove(int r1, int c1, int r2, int c2){
		Orb swapTemp = orbs[r1][c1];
		orbs[r1][c1]=orbs[r2][c2];
		orbs[r2][c2]=swapTemp;
		updateGrid();
	}
	public void runMove(){
		int[][] orbsV = new int[Init.b_HEIGHT][Init.b_WIDTH];
		for (int i = 0; i < Init.b_HEIGHT; i++) {
			for (int j = 0; j < Init.b_WIDTH; j++) {
				orbsV[i][j] = orbs[i][j].getState();
			}
		}
		while(true){
			int[][] matchesBoard = Heuristics.getMatchesBoard(orbsV);
			ArrayList<int[]> matchesCount = Heuristics.getMatchesCount(matchesBoard);
			if(matchesCount.size()<=0){break;}
			orbsV= Heuristics.dropBoard(orbsV, matchesBoard);
		}
		for (int i = 0; i < Init.b_HEIGHT; i++) {
			for (int j = 0; j < Init.b_WIDTH; j++) {
				setOrb(i,j,orbsV[i][j]);
			}
		}
		updateGrid();
	}
	public void randomize(){
		Random rand = new Random();
		for (int i = 0; i < Init.b_HEIGHT; i++) {
			for (int j = 0; j < Init.b_WIDTH; j++) {
				int state = rand.nextInt(6);
				setOrb(i,j,state);
			}
		}
		updateGrid();
	}
}
