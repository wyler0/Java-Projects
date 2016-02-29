package unitSeven;

public class Car extends Vehicle{
	int steerWheelRadius;
	boolean fourWheelDrive;
	boolean skyLight;
	
	public Car(int speed, String color, int steerWheelRadius, boolean fourWheelDrive, boolean skyLight){
		super(speed, color);
		this.steerWheelRadius = steerWheelRadius;
		this.fourWheelDrive = fourWheelDrive;
		this.skyLight = skyLight;
		startingPrice = 500;
		price = calculatePrice();
	}
	
	public int calculatePrice() {
		int price = startingPrice;
		if(fourWheelDrive){
			price+=100;
		}
		if(skyLight){
			price+=100;
		}
		price+=10*speed;
		return price;
	}
	
	public int getSteerSize() {
		return steerWheelRadius;
	}
	public void setSteerSize(int steerWheelSize) {
		this.steerWheelRadius = steerWheelSize;
	}
	public boolean isFourWheelDrive() {
		return fourWheelDrive;
	}
	public void setFourWheelDrive(boolean fourWheelDrive) {
		this.fourWheelDrive = fourWheelDrive;
	}
	public boolean isSkyLight() {
		return skyLight;
	}
	public void setSkyLight(boolean skyLight) {
		this.skyLight = skyLight;
	}
	
	public String toString(){
		String tbr =("A "+color + " car.\n");
		if(speed<=0){tbr+="It lacks an engine\n";}else{tbr+="It has a strong engine that can reach a "+speed+"mph top speed.\n";};
		tbr+="The total price is $"+price+".00 \n";
		
		if(fourWheelDrive){tbr+="It also has four wheel drive, ";}else{tbr+="It also has two wheel drive, ";}
		if(skyLight){tbr+="a skylight,";}else{tbr+="no skylight,";}
		if(steerWheelRadius<=0){tbr+=" and no steering wheel.";}else{tbr+=" and a "+steerWheelRadius+"in radius steering wheel.";};
		
		return (tbr);
	}
}
