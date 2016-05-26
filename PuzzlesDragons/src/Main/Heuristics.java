package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

public class Heuristics extends Thread{
	BoardGraphics mainBoard;
	private ArrayList<Board> allPaths;

	public Heuristics(BoardGraphics board) {
		mainBoard = board;
	}
	
	public void run() {
		allPaths = new ArrayList<Board>();
		int[][] orbs = new int[Init.b_HEIGHT][Init.b_WIDTH];
		//Init orbs array
		for (int r = 0; r < orbs.length; r++) {
			for (int c = 0; c < orbs[0].length; c++) {
				orbs[r][c]=mainBoard.getOrbAt(r,c).getState();
			}
		}
		//Init starting boards
		for (int r = 0; r < Init.b_HEIGHT; r++) {
			for (int c = 0; c < Init.b_WIDTH; c++) {
				Board newBoard = new Board(orbs, new Integer[]{r,c}, false);
				allPaths.add(newBoard);
			}
		}
		
		iterate();
		reducePaths();
		valuePaths();
		Collections.sort(allPaths);
		System.out.println(allPaths.get(0));
		
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
			
		}
	}
	private void reducePaths() {
		//unimplemented
	}
	private void valuePaths(){
		for (int j = 0; j < allPaths.size(); j++) {
			//Count combos for each
			int[] stateCombos = new int[6];
			stateCombos[0] = countCombos(allPaths.get(j), 0);
			stateCombos[1] = countCombos(allPaths.get(j), 1);
			stateCombos[2] = countCombos(allPaths.get(j), 2);
			stateCombos[3] = countCombos(allPaths.get(j), 3);
			stateCombos[4] = countCombos(allPaths.get(j), 4);
			stateCombos[5] = countCombos(allPaths.get(j), 5);
			
			//Weight Combos
			//Add Combos to Total
			
			allPaths.get(j).setValue(10*j);
		}
	}
	private int countCombos(Board curBoard, int state){
		//count value
		int combos = 0;
		return combos;
	}
}
