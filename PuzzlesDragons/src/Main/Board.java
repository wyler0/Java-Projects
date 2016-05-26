package Main;

import java.util.ArrayList;
import java.util.LinkedList;

public class Board implements Comparable<Board>{
	LinkedList<Integer[]> path;
	int[][] orbs;
	boolean ALLOW_DIAGS = false;
	int value = 0;
	//Starting Constructor
	public Board(int[][] orbs2, Integer[] startPos, boolean ALLOW_DIAG){
		path = new LinkedList<Integer[]>();
		path.add(startPos);
		orbs = orbs2;
		this.ALLOW_DIAGS = ALLOW_DIAG;
	}
	//Iterated Constructor
	public Board(int[][] oldOrbs, LinkedList<Integer[]> oldPath, boolean ALLOW_DIAG){
		orbs = oldOrbs;
		path = oldPath;
		this.ALLOW_DIAGS = ALLOW_DIAG;
	}

	public ArrayList<Board> getNewBoards(){
		//Code for new boards... checks bounds and current board... updates paths in new boards
		ArrayList<Board> toReturn = new ArrayList<Board>();
		Integer[] curOrb = path.getLast();
		LinkedList<Integer[]> newPath = new LinkedList<Integer[]>();
		
		/*Check only 4 spaces*/
		if(!ALLOW_DIAGS){
			System.out.println("asd");
			//left
			if(curOrb[0]<=0){
				//top left
				if(curOrb[1]<=0){
					//right
					newPath.clear();
					newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0]+1,curOrb[1]});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
					//bottom
					newPath.clear();
					newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0],curOrb[1]+1});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				}
				//bottom left
				else if(curOrb[1]>Init.b_HEIGHT){
					//top
					newPath.clear();
					newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0],curOrb[1]-1});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
					//right
					newPath.clear();
					newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0]-1,curOrb[1]});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				}
				//left
				else{
					//top
					newPath.clear();newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0],curOrb[1]-1});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
					//bottom
					newPath.clear();newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0],curOrb[1]+1});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
					//right
					newPath.clear();
					newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0]+1,curOrb[1]});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
					
				}
			}
			//right
			else if(curOrb[0]>Init.b_WIDTH){
				//top right
				if(curOrb[1]<=0){
					//bottom
					newPath.clear();newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0],curOrb[1]+1});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
					//left
					newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0]-1,curOrb[1]});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				}
				//bottom right
				else if(curOrb[1]>Init.b_HEIGHT){
					//top
					newPath.clear();newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0],curOrb[1]-1});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
					//left
					newPath.clear();newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0]-1,curOrb[1]});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				}
				//right
				else{
					//top
					newPath.clear();newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0],curOrb[1]-1});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
					//bottom
					newPath.clear();newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0],curOrb[1]+1});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
					//left
					newPath.clear();newPath.addAll(path);
					newPath.add(new Integer[]{curOrb[0]-1,curOrb[1]});
					toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				}
			}
			//top
			else if(curOrb[1]<=0){
				//left
				newPath.clear();newPath.addAll(path);
				newPath.add(new Integer[]{curOrb[0]-1,curOrb[1]});
				toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				//bottom
				newPath.clear();newPath.addAll(path);
				newPath.add(new Integer[]{curOrb[0],curOrb[1]+1});
				toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				//right
				newPath.clear();newPath.addAll(path);
				newPath.add(new Integer[]{curOrb[0]+1,curOrb[1]});
				toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
			}
			//bottom
			else if(curOrb[1]>Init.b_HEIGHT){
				//left
				newPath.clear();newPath.addAll(path);
				newPath.add(new Integer[]{curOrb[0]-1,curOrb[1]});
				toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				//top
				newPath.clear();newPath.addAll(path);
				newPath.add(new Integer[]{curOrb[0],curOrb[1]-1});
				toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				//right
				newPath.clear();newPath.addAll(path);
				newPath.add(new Integer[]{curOrb[0]+1,curOrb[1]});
				toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
			}
			//middle area
			else{
				//left
				newPath.clear();newPath.addAll(path);
				newPath.add(new Integer[]{curOrb[0]-1,curOrb[1]});
				toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				//top
				newPath.clear();newPath.addAll(path);
				newPath.add(new Integer[]{curOrb[0],curOrb[1]-1});
				toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				//bottom
				newPath.clear();newPath.addAll(path);
				newPath.add(new Integer[]{curOrb[0],curOrb[1]+1});
				toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
				//right
				newPath.clear();newPath.addAll(path);
				newPath.add(new Integer[]{curOrb[0]+1,curOrb[1]});
				toReturn.add(new Board(orbs, newPath, ALLOW_DIAGS));
			}
		}

		/*Check all 8 spaces*/
		else{
			//NOT IMPLEMENTED
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
			ret+="\npath_: "+inte[0]+":"+inte[1];
		}
		return ret+"\n";
	}
	
}
