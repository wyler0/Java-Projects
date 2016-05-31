package Main;

import java.util.ArrayList;
import java.util.LinkedList;

public class Board implements Comparable<Board>{
	LinkedList<Integer[]> path;
	boolean ALLOW_DIAGS = false;
	int value = 0;
	public Board(Integer[] startPos, boolean ALLOW_DIAG){
		path = new LinkedList<Integer[]>();
		path.add(startPos);
		this.ALLOW_DIAGS = ALLOW_DIAG;
	}

	public Board(LinkedList<Integer[]> oldPath, boolean ALLOW_DIAG){
		path = oldPath;
		this.ALLOW_DIAGS = ALLOW_DIAG;
	}
	public ArrayList<Board> getNewBoards(){
		//Code for new boards... checks bounds and current board... updates paths in new boards
		ArrayList<Board> toReturn = new ArrayList<Board>();
		Integer[] curOrb = path.getLast();
		int curR = path.getLast()[0];
		int curC = path.getLast()[1];

		/*Check only 4 spaces*/
		if(!ALLOW_DIAGS){
			//left
			if(curC<=0){
				//top left
				if(curR<=0){
					//right
					LinkedList<Integer[]> newPathR = new LinkedList<Integer[]>();
					newPathR.clear();
					newPathR.addAll(path);
					newPathR.add(new Integer[]{(curR),(curC+1)});
					toReturn.add(new Board(newPathR, ALLOW_DIAGS));
					//bottom
					LinkedList<Integer[]> newPathB = new LinkedList<Integer[]>();
					newPathB.clear();
					newPathB.addAll(path);
					newPathB.add(new Integer[]{(curR+1),(curC)});
					toReturn.add(new Board(newPathB, ALLOW_DIAGS));
				}
				//bottom left
				else if(curR>Init.b_HEIGHT-2){
					//top
					LinkedList<Integer[]> newPathT = new LinkedList<Integer[]>();
					newPathT.clear();
					newPathT.addAll(path);
					newPathT.add(new Integer[]{(curR-1),(curC)});
					toReturn.add(new Board(newPathT, ALLOW_DIAGS));
					//right
					LinkedList<Integer[]> newPathR = new LinkedList<Integer[]>();
					newPathR.clear();
					newPathR.addAll(path);
					newPathR.add(new Integer[]{(curR),(curC+1)});
					toReturn.add(new Board(newPathR, ALLOW_DIAGS));
				}
				//left
				else{
					//top
					LinkedList<Integer[]> newPathT = new LinkedList<Integer[]>();
					newPathT.clear();
					newPathT.addAll(path);
					newPathT.add(new Integer[]{(curR-1),(curC)});
					toReturn.add(new Board(newPathT, ALLOW_DIAGS));
					//bottom
					LinkedList<Integer[]> newPathB = new LinkedList<Integer[]>();
					newPathB.clear();
					newPathB.addAll(path);
					newPathB.add(new Integer[]{(curR+1),(curC)});
					toReturn.add(new Board(newPathB, ALLOW_DIAGS));
					//right
					LinkedList<Integer[]> newPathR = new LinkedList<Integer[]>();
					newPathR.clear();
					newPathR.addAll(path);
					newPathR.add(new Integer[]{(curR),(curC+1)});
					toReturn.add(new Board(newPathR, ALLOW_DIAGS));

				}
			}
			//right
			else if(curC>Init.b_WIDTH-2){
				//top right
				if(curR<=0){
					//bottom
					LinkedList<Integer[]> newPathB = new LinkedList<Integer[]>();
					newPathB.clear();
					newPathB.addAll(path);
					newPathB.add(new Integer[]{(curR+1),(curC)});
					toReturn.add(new Board(newPathB, ALLOW_DIAGS));
					//left
					LinkedList<Integer[]> newPathL = new LinkedList<Integer[]>();
					newPathL.clear();
					newPathL.addAll(path);
					newPathL.add(new Integer[]{(curR),(curC-1)});
					toReturn.add(new Board(newPathL, ALLOW_DIAGS));
				}
				//bottom right
				else if(curR>Init.b_HEIGHT-2){
					//top
					LinkedList<Integer[]> newPathT = new LinkedList<Integer[]>();
					newPathT.clear();
					newPathT.addAll(path);
					newPathT.add(new Integer[]{(curR-1),(curC)});
					toReturn.add(new Board(newPathT, ALLOW_DIAGS));
					//left
					LinkedList<Integer[]> newPathL = new LinkedList<Integer[]>();
					newPathL.clear();
					newPathL.addAll(path);
					newPathL.add(new Integer[]{(curR),(curC-1)});
					toReturn.add(new Board(newPathL, ALLOW_DIAGS));
				}
				//right
				else{
					//top
					LinkedList<Integer[]> newPathT = new LinkedList<Integer[]>();
					newPathT.clear();
					newPathT.addAll(path);
					newPathT.add(new Integer[]{(curR-1),(curC)});
					toReturn.add(new Board(newPathT, ALLOW_DIAGS));
					//bottom
					LinkedList<Integer[]> newPathB = new LinkedList<Integer[]>();
					newPathB.clear();
					newPathB.addAll(path);
					newPathB.add(new Integer[]{(curR+1),(curC)});
					toReturn.add(new Board(newPathB, ALLOW_DIAGS));
					//left
					LinkedList<Integer[]> newPathL = new LinkedList<Integer[]>();
					newPathL.clear();
					newPathL.addAll(path);
					newPathL.add(new Integer[]{(curR),(curC-1)});
					toReturn.add(new Board(newPathL, ALLOW_DIAGS));
				}
			}
			//top
			else if(curR<=0){
				//left
				LinkedList<Integer[]> newPathL = new LinkedList<Integer[]>();
				newPathL.clear();
				newPathL.addAll(path);
				newPathL.add(new Integer[]{(curR),(curC-1)});
				toReturn.add(new Board(newPathL, ALLOW_DIAGS));
				//bottom
				LinkedList<Integer[]> newPathB = new LinkedList<Integer[]>();
				newPathB.clear();
				newPathB.addAll(path);
				newPathB.add(new Integer[]{(curR+1),(curC)});
				toReturn.add(new Board(newPathB, ALLOW_DIAGS));
				//right
				LinkedList<Integer[]> newPathR = new LinkedList<Integer[]>();
				newPathR.clear();
				newPathR.addAll(path);
				newPathR.add(new Integer[]{(curR),(curC+1)});
				toReturn.add(new Board(newPathR, ALLOW_DIAGS));
			}
			//bottom
			else if(curR>Init.b_HEIGHT-2){
				//left
				LinkedList<Integer[]> newPathL = new LinkedList<Integer[]>();
				newPathL.clear();
				newPathL.addAll(path);
				newPathL.add(new Integer[]{(curR),(curC-1)});
				toReturn.add(new Board(newPathL, ALLOW_DIAGS));
				//top
				LinkedList<Integer[]> newPathT = new LinkedList<Integer[]>();
				newPathT.clear();
				newPathT.addAll(path);
				newPathT.add(new Integer[]{(curR-1),(curC)});
				toReturn.add(new Board(newPathT, ALLOW_DIAGS));
				//right
				LinkedList<Integer[]> newPathR = new LinkedList<Integer[]>();
				newPathR.clear();
				newPathR.addAll(path);
				newPathR.add(new Integer[]{(curR),(curC+1)});
				toReturn.add(new Board(newPathR, ALLOW_DIAGS));
			}
			//middle area
			else{
				//left
				LinkedList<Integer[]> newPathL = new LinkedList<Integer[]>();
				newPathL.clear();
				newPathL.addAll(path);
				newPathL.add(new Integer[]{(curR),(curC-1)});
				toReturn.add(new Board(newPathL, ALLOW_DIAGS));
				//top
				LinkedList<Integer[]> newPathT = new LinkedList<Integer[]>();
				newPathT.clear();
				newPathT.addAll(path);
				newPathT.add(new Integer[]{(curR-1),(curC)});
				toReturn.add(new Board(newPathT, ALLOW_DIAGS));
				//bottom
				LinkedList<Integer[]> newPathB = new LinkedList<Integer[]>();
				newPathB.clear();
				newPathB.addAll(path);
				newPathB.add(new Integer[]{(curR+1),(curC)});
				toReturn.add(new Board(newPathB, ALLOW_DIAGS));
				//right
				LinkedList<Integer[]> newPathR = new LinkedList<Integer[]>();
				newPathR.clear();
				newPathR.addAll(path);
				newPathR.add(new Integer[]{(curR),(curC+1)});
				toReturn.add(new Board(newPathR, ALLOW_DIAGS));
			}
		}

		/*Check all 8 spaces*/
		else{
			//NOT IMPLEMENTED
		}

		for (int i = 0; i < toReturn.size(); i++) {
			if(toReturn.get(i).getPath().size()>=3){
				if(
						(toReturn.get(i).getPath().getLast()[0]==toReturn.get(i).getPath().get(toReturn.get(i).getPath().size()-3)[0])&&
						(toReturn.get(i).getPath().getLast()[1]==toReturn.get(i).getPath().get(toReturn.get(i).getPath().size()-3)[1])){
					toReturn.remove(i);
					i--;
				}
			}
		}
		return toReturn;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public LinkedList<Integer[]> getPath() {
		return path;
	}
	public void setPath(LinkedList<Integer[]> path) {
		this.path = path;
	}
	@Override
	public int compareTo(Board arg0) {
		if(arg0.getValue()<getValue()){return 1;}
		else if(arg0.getValue()>getValue()){return -1;}
		return 0;
	}
	public String toString(){
		String ret = "Path:";
		for (Integer[] inte : path) {
			ret+="\npath_: r"+inte[0]+":c"+inte[1];
		}
		return ret+"\n";
	}

}
