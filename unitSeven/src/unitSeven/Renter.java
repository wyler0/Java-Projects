package unitSeven;

import java.util.ArrayList;

public class Renter {
	int ID, balance;
	String name;
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	Garage g;
	
	public Renter(Garage garage,int ID, String name, int balance){
		this.ID = ID;
		this.balance = balance;
		this.name = name;
		this.g = garage;
	}
	public void rent(int vehicleNum){
		if(g.getVehicles().size()-1>vehicleNum){
			System.out.println("Incorrect vehicle choice");
			System.out.println(g);
		}
		else{
			if(g.getVehicles().get(vehicleNum).rent(this)){
				vehicles.add(g.getVehicles().get(vehicleNum));
				g.getVehicles().remove(vehicleNum);
				System.out.println(name+" has sufficient funds. The vehicle is now in  " +name+"'s inventory.");
				System.out.println(name+"'s current account information: " + this);
			}
			else{
				System.out.println("You do not have sufficient funds.");
				System.out.println(name+"'s current account information: " + this);
			}
		}
	}
	public void returnVehicle(int vehicleNumber){
		if(vehicles.size()>=vehicleNumber+1){
			vehicles.remove(vehicleNumber);
			System.out.println("The vehicle is no longer in  " +name+"'s inventory.");
			System.out.println(name+"'s current account information: " + this);
		}
		else{
			System.out.println("You do not own that vehicle.");
			System.out.println(name+"'s current account information: " + this);
		}
	}

	
	public String toString(){
		String tr = "\n";
		tr+=("\n-----------------------------");
		tr+=("\n********** RENTER ***********");
		tr+=("\nName: "+ name);
		tr+=("\nID: "+ID);
		tr+=("\nBalance: $"+balance+".00");
		tr+=("\n");
		tr+=("\n******** VEHICLES ***********\n");
		for (int i = 0; i < vehicles.size(); i++) {
			tr+=("\nVehicle Number: "+i);
			tr+="\n"+vehicles.get(i).toString()+"\n";
		}
		tr+=("\n************ END ************");
		return tr+"\n-----------------------------\n";
	}
}
