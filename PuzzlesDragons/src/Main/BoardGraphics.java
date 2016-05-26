package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

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
		for (int x = 0; x < orbs.length; x++) {
			for (int y = 0; y < orbs.length; y++) {
				int state = 0;
				if((x+y)%2==0) state =1;
				orbs[x][y]=new Orb(new int[]{x,y}, state);
			}
		}
	}
	public void loadGraphics(){
		this.setLayout(new FlowLayout());

		grid.setBackground(new Color(0,0,0));
		GridLayout gr = new GridLayout(Init.b_WIDTH, Init.b_HEIGHT);
		grid.setLayout(gr);
		grid.setPreferredSize(new Dimension(200,200));
		this.add(grid);

		updateGrid();
	}

	//run
	public void start(){

	}

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd.contains("gridClick")){
			cmd = cmd.substring(10);
			String[] ay = cmd.split(" ");
			setOrb(Integer.parseInt(ay[0]),Integer.parseInt(ay[1]));
		}
	}
	
	public void executeMoveList(LinkedList<Integer[]> path){
		for (Integer[] inte: path) {
			System.out.println(inte[0]+":"+inte[1]);
		}
		Integer[] inte;
		Integer[] inteNext;
		for (int i = 0; i < path.size(); i++) {
			inte = path.get(i);
			if(!(i>path.size()-2)){
				Timer t = new Timer(i*100, this);
				inteNext = path.get(i+1);
				t.setActionCommand("orbMove"+inte[0]+"_"+inte[1]+"_"+inteNext[0]+"_"+inteNext[1]);
				t.setRepeats(false);
				t.start();
			}
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
	public void setOrb(int r, int c){
		if(orbs[r][c].getState()<6) orbs[r][c].setState(orbs[r][c].getState()+1);
		else orbs[r][c].setState(0);
		updateGrid();
	}
	public void updateGrid(){
		grid.removeAll();
		for (int x = 0; x < Init.b_WIDTH; x++) {
			for (int y = 0; y < Init.b_HEIGHT; y++) {
				JButton jb = new JButton();
				jb.setBackground(orbs[y][x].toColor());
				jb.setActionCommand("gridClick*"+y+" "+x);		
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
}
