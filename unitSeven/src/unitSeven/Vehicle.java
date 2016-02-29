package unitSeven;

import java.awt.Color;

public abstract class Vehicle {
	int speed;
	int startingPrice;
	int price;
	Renter renter;
	String color = "White";
	public Vehicle(int speed, String color){
		this.speed = speed;
		this.color = color;
	}
	public abstract int calculatePrice();
	public boolean rent(Renter renter){
		if(renter.balance>price){this.renter = renter; this.renter.balance-=price; return true;}
		return false;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(int startingPrice) {
		this.startingPrice = startingPrice;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Renter getRenter() {
		return renter;
	}
	public void setRenter(Renter renter) {
		this.renter = renter;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
