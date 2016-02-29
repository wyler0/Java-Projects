package unitSeven;

public class Main {
	public static void main(String[] args) {
		Garage g = new Garage();
		Renter renter1 = new Renter(g,0, "Wyler", 1000000);
		g.makeCar(199, "blue", 10, true, true);
		renter1.rent(0);
		g.makeMotorcycle(25, "green", 10, "teel", 0);
		g.makeTruck(15, "green", 45, false, true);
		renter1.rent(0);
		renter1.rent(1);
		renter1.returnVehicle(0);
	}
}
