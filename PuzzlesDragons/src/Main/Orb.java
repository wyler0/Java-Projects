package Main;

import java.awt.Color;



public class Orb{
	int[] myPos = {0,0};
	int state = 0;//In to string, represents color

	String red = "R"; 
	String green = "G";
	String blue = "B";
	String purple = "P";
	String yellow = "Y";
	char heal = '\u002B'; //plus
	
	public Orb(int[] myPos, int state){
		this.myPos = myPos;
		this.state= state;
	}


	public int[] getPos(){
		return myPos;
	}
	public void setPos(int[] myPos){
		this.myPos = myPos;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String toString(){
		switch (state) {
		case 0:
			return red;
		case 1:
			return green;
		case 2:
			return blue;
		case 3:
			return purple;
		case 4:
			return yellow;
		case 5:
			return heal+"";
		default:
			return "eh";
		}
	}
	public Color toColor(){
		switch (state) {
		case 0:
			return Color.red;
		case 1:
			return Color.green;
		case 2:
			return Color.blue;
		case 3:
			return Color.magenta;
		case 4:
			return Color.yellow;
		case 5:
			return Color.pink;
		default:
			return Color.black;
		}
	}
}
