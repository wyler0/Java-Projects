package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.swing.JProgressBar;

public class Heuristics extends Thread{
	BoardGraphics mainBoard;
	int[][] orbs;
	JProgressBar pb;
	private ArrayList<Board> allPaths;
	private LinkedList<Integer[]> bestPath;
	private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();

	public Heuristics(BoardGraphics board, JProgressBar pb) {
		mainBoard = board;
		this.pb = pb;
	}
	public void run() {
		allPaths = new ArrayList<Board>();
		orbs = new int[Init.b_HEIGHT][Init.b_WIDTH];
		//Init orbs array
		for (int r = 0; r < orbs.length; r++) {
			for (int c = 0; c < orbs[0].length; c++) {
				orbs[r][c]=mainBoard.getOrbAt(r,c).getState();
			}
		}
		//Init starting boards
		for (int r = 0; r < Init.b_HEIGHT; r++) {
			for (int c = 0; c < Init.b_WIDTH; c++) {
				Board newBoard = new Board(new Integer[]{r,c}, false);
				allPaths.add(newBoard);
			}
		}

		iterate();
		reducePaths();
		valuePaths();
		Collections.sort(allPaths);
		System.out.println(allPaths.get(allPaths.size()-1));	
		bestPath = allPaths.get(allPaths.size()-1).getPath();
		fireFinishAction();
	}

	private void iterate(){
		ArrayList<Board> newPaths;
		for(int curDepth = 0; curDepth<Init.depth;curDepth++){
			newPaths = new ArrayList<Board>();
			for (Board curBoard : allPaths) {
				newPaths.addAll(curBoard.getNewBoards());
			}
			allPaths.clear();
			allPaths.addAll(newPaths);
			pb.setValue(pb.getValue()+1);
			pb.setToolTipText(pb.getValue()+"%");
		}
	}
	private void reducePaths() {
	}
	private void valuePaths(){
		for (int j = 0; j < allPaths.size(); j++) {
			/*
			 * Get matches and the matches board
			 * make sure there are matches
			 * if there are then drop the board and test again
			 * add total matches
			 */
			int[][] board = runPathBoard(allPaths.get(j).getPath(), orbs);
			ArrayList<int[]> matchesCountTotal = new ArrayList<int[]>();
			while(true){
				int[][] matchesBoard = getMatchesBoard(board);
				ArrayList<int[]> matchesCount = getMatchesCount(matchesBoard);
				if(matchesCount.size()<=0){break;}
				board= dropBoard(board, matchesBoard);
				matchesCountTotal = addMatchesTotal(matchesCount, matchesCountTotal);
			}
			int value = 0;
			for (int[] is : matchesCountTotal) {
				value+= is[1];
			}
			allPaths.get(j).setValue(value);
		}
	}

	public static int[][] runPathBoard(LinkedList<Integer[]> path, int[][] oldBoard){
		int[][] toReturn = new int[Init.b_HEIGHT][Init.b_WIDTH];
		for (int r = 0; r < Init.b_HEIGHT; r++) {
			for (int c = 0; c < Init.b_WIDTH; c++) {
				toReturn[r][c] = oldBoard[r][c];
			}
		}
		for (int i = 0; i < path.size()-1; i++) {
			int swapTemp = toReturn[path.get(i)[0]][path.get(i)[1]];
			toReturn[path.get(i)[0]][path.get(i)[1]]=toReturn[path.get(i+1)[0]][path.get(i+1)[1]];
			toReturn[path.get(i+1)[0]][path.get(i+1)[1]]=swapTemp;
		}
		return toReturn;
	}
	public static int[][] getMatchesBoard(int[][] board){
		int[][] matchesBoard = new int[Init.b_HEIGHT][Init.b_WIDTH];
		for (int r = 0; r < Init.b_HEIGHT; r++) {
			for (int c = 0; c < Init.b_WIDTH; c++) {
				matchesBoard[r][c] = -1;
			}
		}
		//add horizontal combos to empty
		for (int i = 0; i < Init.b_HEIGHT; ++ i) {
			int prev_1_orb = -1;
			int prev_2_orb = -1;
			for (int j = 0; j < Init.b_WIDTH; ++ j) {
				int cur_orb = board[i][j];
				if (prev_1_orb == prev_2_orb && prev_2_orb == cur_orb && cur_orb != -1) {
					matchesBoard[i][j] = cur_orb;
					matchesBoard[i][j-1] = cur_orb;
					matchesBoard[i][j-2] = cur_orb;
				}
				prev_1_orb = prev_2_orb;
				prev_2_orb = cur_orb;
			}
		}
		//add vertical combos to empty
		for (int j = 0; j < Init.b_WIDTH; ++ j) {
			int prev_1_orb = -1;
			int prev_2_orb = -1;
			for (int i = 0; i < Init.b_HEIGHT; ++ i) {
				int cur_orb = board[i][j];
				if (prev_1_orb == prev_2_orb && prev_2_orb == cur_orb && cur_orb != -1) {
					matchesBoard[i][j] = cur_orb;
					matchesBoard[i-1][j] = cur_orb;
					matchesBoard[i-2][j] = cur_orb;
				}
				prev_1_orb = prev_2_orb;
				prev_2_orb = cur_orb;
			}
		}
		return matchesBoard;
	}
	public static ArrayList<int[]> getMatchesCount(int[][] matchesBoard){
		int[][] matchesBoardClone = new int[Init.b_HEIGHT][Init.b_WIDTH];
		for (int r = 0; r < Init.b_HEIGHT; r++) {
			for (int c = 0; c < Init.b_WIDTH; c++) {
				matchesBoardClone[r][c] = matchesBoard[r][c];
			}
		}
		ArrayList<int[]> matches = new ArrayList<int[]>();
		//orb state, number of orbs in that match
		for (int r = 0; r < Init.b_HEIGHT; r++) {
			for (int c = 0; c < Init.b_WIDTH; c++) {
				int cur_orb = matchesBoardClone[r][c];
				if (cur_orb == -1) continue;
				ArrayList<int[]> stack = new ArrayList<int[]>();
				stack.add(new int[]{r,c});
				int count = 0;
				while(stack.size()>0){
					int[] curpos = stack.get(stack.size()-1);
					stack.remove(stack.size()-1);
					if(matchesBoardClone[curpos[0]][curpos[1]]!= cur_orb) continue;
					count++;
					matchesBoardClone[curpos[0]][curpos[1]] = -1;
					//add orbs around the current orb to stack
					if (curpos[0] > 0) stack.add(new int[]{curpos[0]-1,curpos[1]});
					if (curpos[0] < Init.b_HEIGHT-1) stack.add(new int[]{curpos[0]+1,curpos[1]});
					if (curpos[1] > 0) stack.add(new int[]{curpos[0],curpos[1]-1});
					if (curpos[1] < Init.b_WIDTH-1) stack.add(new int[]{curpos[0],curpos[1]+1});
				}
				matches.add(new int[]{cur_orb, count});
			}
		}
		return matches;
	}
	public static int[][] dropBoard(int[][] oldBoard, int[][] matchesBoard){
		int[][] board = new int[Init.b_HEIGHT][Init.b_WIDTH];
		for (int r = 0; r < Init.b_HEIGHT; r++) {
			for (int c = 0; c < Init.b_WIDTH; c++) {
				board[r][c] = oldBoard[r][c];
			}
		}
		//remove matches
		for (int r = 0; r < Init.b_HEIGHT; r++) {
			for (int c = 0; c < Init.b_WIDTH; c++) {
				if(matchesBoard[r][c]!=-1){
					board[r][c]=-1;
				}
			}
		}
		//drop board
		for (int j = 0; j < Init.b_WIDTH; ++ j) {
			int dest_i = Init.b_HEIGHT-1;
			for (int src_i = Init.b_HEIGHT-1; src_i >= 0; -- src_i) {
				if (board[src_i][j] != -1) {
					board[dest_i][j] = board[src_i][j];
					-- dest_i;
				}
			}
			for (; dest_i >= 0; -- dest_i) {
				board[dest_i][j] = -1;
			}
		}
		return board;
	}
	public static ArrayList<int[]> addMatchesTotal(ArrayList<int[]> matchesCount, ArrayList<int[]> matchesCountTotal){
		Collections.sort(matchesCount, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return (Integer.valueOf(a[0])).compareTo(Integer.valueOf(b[0]));
			}
		});
		int curVal = -1;
		for (int[] is : matchesCount) {
			curVal = is[0];
			if(matchesCountTotal.size()<=0){
				matchesCountTotal.addAll(matchesCount);
				break;
			}
			else{
				for (int i = 0; i < matchesCountTotal.size(); i++) {
					if(curVal<matchesCountTotal.get(i)[0]){
						matchesCountTotal.add(i, is);
						break;
					}
					else if(curVal==matchesCountTotal.get(i)[0]){
						matchesCountTotal.get(i)[1]+=is[1];
						break;
					}
					else if(i== matchesCountTotal.size()-1){
						matchesCountTotal.add(is);
						break;
					}
				}
			}
		}
		return matchesCountTotal;
	}


	public void addActionListener(ActionListener l){
		listeners.add(l);
	}
	private void fireFinishAction() {
		for (int i = 0; i < listeners.size(); i++) {
			ActionEvent ae = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,"ThreadFinished");
			listeners.get(i).actionPerformed(ae);
		}
	}
	public LinkedList<Integer[]> getBestPath(){
		if(allPaths.size()>0){
			return bestPath;
		}
		else return null;
	}
}
