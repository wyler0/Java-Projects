package unitSeven;

import java.util.ArrayList;

public class Garage {
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	public Garage(){
		
	}
	public void makeCar(int speed, String color, int steerSize, boolean fourWheelDrive, boolean skyLight){
		Vehicle tbr = new Car(speed, color, steerSize, fourWheelDrive, skyLight);
		System.out.println("Now now making a..."+tbr );
		vehicles.add(tbr);
		System.out.println(this);
	}
	
	public void makeMotorcycle(int speed, String color, int steeringHandleLength, String helmetColor, int tireShape){
		Vehicle tbr = new Motorcycle(speed, color, steeringHandleLength, helmetColor, tireShape);
		System.out.println("Now now making a..."+tbr );
		vehicles.add(tbr);
		System.out.println(this);
	}

	public void makeTruck(int speed, String color, int rampLength, boolean refrigerated, boolean gasType){
		Vehicle tbr = new Truck(speed, color, gasType, refrigerated, rampLength);
		System.out.println("Now now making a..."+tbr );
		vehicles.add(tbr);
		System.out.println(this);
	}
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public String toString(){
		String tr = "\n";
		tr+=("\n-----------------------------");
		tr+=("\n***** GARAGE VEHICLES *******\n");
		for (int i = 0; i < vehicles.size(); i++) {
			tr+=("\nVehicle Number: "+i);
			tr+="\n"+vehicles.get(i).toString()+"\n";
		}
		tr+=("\n************ END ************");
		return tr+"\n-----------------------------\n";
	}
}
