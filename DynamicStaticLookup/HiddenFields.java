/**
 * HIDDEN VARIABLES
 * This file demonstrates how variables are hidden
 * (or not). 
 * 
 * @author Alvin Wan
 * @website alvinwan.com
 */

public class HiddenFields {
	public static void main(String args[]) {

		/**
		 * Notice that name is defined twice, in Ship AND in
		 * SpaceShip. Each variable is "localized," so that
		 * setting the value of name in Ship will do nothing
		 * for the variable "name" in SpaceShip.
		 */
		
		Ship ship = new Ship();
		SpaceShip spaceship = new SpaceShip("Birdie");
		
		print(ship.name); // normal behavior - prints "Catalina"
		print(spaceship.name); // normal behavior - prints "Birdie"
		
		SpaceShip spaceship2 = new SpaceShip(); // initialized without name for SpaceShip
		
		print(spaceship2.name); // name is not defined for the SpaceShip context (returns null)
		spaceship2.getName(); // getName() defined in Ship, which has name of "Catalina"

		/**
		 * 
		 */
	}
	
	public static void print(String msg) {
		System.out.println(msg);
	}
}

class Ship {
	public String name;
	
	public Ship() {
		name = "Catalina";
	}
	
	public void fly() {
		System.out.println("No can do.");
	}
	
	public void getName() {
		System.out.println(name);
	}
}

class SpaceShip extends Ship {
	public String name;
	public boolean isFlying;
	
	public SpaceShip() {
		super();
		isFlying = false;
	}
	
	public SpaceShip(String _name) {
		super();
		name = _name;
		isFlying = false;
	}
	
	public void fly() {
		isFlying = true;
		System.out.println("Flying!");
	}
	
	public void land() {
		isFlying = false;
		System.out.println("Landed!");
	}
}