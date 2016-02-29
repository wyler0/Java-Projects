package unitSeven;

public class Truck extends Vehicle{
	boolean refrigerated;
	boolean fuelType;
	int rampLength;
	
	public Truck(int speed, String color, boolean fuelType, boolean refrigerated, int rampLength){
		super(speed, color);
		this.fuelType = fuelType;
		this.rampLength = rampLength;
		if(rampLength<0){rampLength=0;}
		this.refrigerated = refrigerated;
		startingPrice = 300;
		price = calculatePrice();
	}

	public int calculatePrice() {
		int price = startingPrice;
		if(fuelType){price+=500;}
		price+=1*rampLength;
		if(refrigerated){price+=1000;}
		price+=30*speed;
		return price;
	}

	public boolean refrigerated() {
		return refrigerated;
	}
	public void refrigerated(boolean refrigerated) {
		this.refrigerated = refrigerated;
	}
	public boolean getFuelType() {
		return fuelType;
	}
	public void setFuelType(boolean fuelType) {
		this.fuelType = fuelType;
	}
	public int rampLength() {
		return rampLength;
	}
	public void setTireShape(int rampLength) {
		this.rampLength = rampLength;
	}

	public String toString(){
		String tbr =("A "+color + " truck.\n");
		if(speed<=0){tbr+="It lacks an engine\n";}else{tbr+="It has a strong engine that can reach a "+speed+"mph top speed.\n";};
		tbr+="The total price is $"+price+".00 \n";


		if(rampLength==0){tbr+="It also has no ramp to access the truck bed, ";}else{tbr+="It also has a "+rampLength+"in ramp to access the truck bed, ";}
		if(refrigerated){tbr+=" a refrigerated truck bed, ";}else{tbr+=" no refrigerated truck bed, ";}
		if(fuelType){tbr+="and runs on diesel.";}else{tbr+="and runs on gasoline.";}
		return (tbr);
	}
}
