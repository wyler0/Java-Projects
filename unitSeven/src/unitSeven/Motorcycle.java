package unitSeven;

public class Motorcycle extends Vehicle{
	String helmetColor = "white";
	int steerHandleLength;
	int tireShape;
	
	public Motorcycle(int speed, String color, int steerSize, String helmetColor, int tireShape){
		super(speed, color);
		this.steerHandleLength = steerSize;
		if(tireShape>1||tireShape<0){this.tireShape = 0;}else{this.tireShape = tireShape;} //0 = rounded 1=square >1=defualt to 0;
		this.helmetColor = helmetColor;
		startingPrice = 100;
		price = calculatePrice();
	}

	public int calculatePrice() {
		int price = startingPrice;
		price+=5*helmetColor.length();
		if(tireShape==1){
			price+=100;
		}
		else{
			price+=10;
		}
		price+=10*speed;
		return price;
	}

	public String getHelmetColor() {
		return helmetColor;
	}
	public void setHelmetColor(String helmetColor) {
		this.helmetColor = helmetColor;
	}
	public int getSteerHandleLength() {
		return steerHandleLength;
	}
	public void setSteerHandleLength(int steerHandleLength) {
		this.steerHandleLength = steerHandleLength;
	}
	public int getTireShape() {
		return tireShape;
	}
	public void setTireShape(int tireShape) {
		this.tireShape = tireShape;
	}

	public String toString(){
		String tbr =("A "+color + " motorcycle.\n");
		if(speed<=0){tbr+="It lacks an engine\n";}else{tbr+="It has a strong engine that can reach a "+speed+"mph top speed.\n";};
		tbr+="The total price is $"+price+".00 \n";

		if(tireShape==0){tbr+="It also has rounded profile tires, ";}else{tbr+="It also has flatter profile tires, ";}
		tbr+="a "+helmetColor+" colored helmet,";
		if(steerHandleLength<=0){tbr+=" and no steering handles.";}else{tbr+=" and a "+steerHandleLength+"in long steering handle.";};

		return (tbr);
	}
}
