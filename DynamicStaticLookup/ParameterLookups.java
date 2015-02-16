/**
 * PARAMETER LOOKUPS*
 * This file demonstrates how methods are found, according to
 * type.
 */

public class ParameterLookups {
	public static void main(String args[]) {
		Animal a = new Animal("General");
		Monkey m = new Monkey("Melody");
		Dog d = new Dog("Douglas");
		print(getName(m)); // Uses getName(Animal a) - no method for Monkey
		print(getName(d)); // Uses getName(Dog d)
	}

	/**
	 * Returns the name of the specified Animal. All subclasses
	 * of Animal are also accepted as valid paramters.
	 * @param a : Animal object
	 * @return : name
	 */
	public static String getName(Animal a) {
		return a.name;
	}

	/**
	 * Returns the noise of the Dog. 
	 * @param d : Dog object
	 * @return : noise
	 */
	public static String getName(Dog d) {
		return d.noise;
	}
	
	public static void print(String msg) {
		System.out.println(msg);
	}
}

class Animal {
	public String name;
	public boolean isAlive;
	public String noise;
	
	public Animal(String _name) {
		isAlive = true;
		name = _name;
	}
	
	public void die() {
		isAlive = false;
	}
}

class Monkey extends Animal {
	boolean isAlive;
	
	public Monkey(String name) {
		super(name);
		noise = "hoohoohooo";
	}
}

class Dog extends Animal {
	public Dog(String name) {
		super(name);
		noise = "woof";
	}
}