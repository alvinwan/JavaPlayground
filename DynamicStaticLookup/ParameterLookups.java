/**
 * PARAMETER LOOKUPS*
 * This file demonstrates how methods are found, according to
 * type.
 * 
 * Method lookups are dynamic.* * 
 */

public class ParameterLookups {
	public static void main(String args[]) {
		
		Animal a = new Animal("General");
		Dog d = new Dog("Douglas");

		// METHOD LOOKUP
		
		/*
		In compile-time, the static type of "d" (temporarily
		casted to Animal) will determine whether or not the
		compilation is successful. In run-time, the dynamic
		type of "d" (Dog), will be used to determine the 
		method called.
		 */
		print(((Animal) d).die()); // dog.die called
//		print(((Animal) d).bark()); // fails on compile-time - Animal has no bark method
		
		// PARAMETER LOOKUP

		Animal d2 = new Dog("David");
		
		/*
		For a parameter, only the static type matters. If
		there is no method signature accepting parameter Dog, 
		will look for method accepting parameter Animal.
		 */
		print(Animal.getName(a)); // static type Animal -> OK
		print(Animal.getName(d)); // static type Dog, but no method signature with Dog -> use method accepting Animal
		print(d.attack(d2)); // static type Animal -> use method accepting Animal

		print(d.attack(a)); // a is Animal, and d is Dog -> Dog attacked Animal
		print(d2.attack(d2)); // compile-time: does Animal.attack(Animal...) exist? run-time: uses dog.attack(Animal ...) if available or animal.attack(Animal...)
 		print(d2.attack((Dog) d2)); // compile-time: does Animal.attack(Dog...) exist? run-time uses dog.attack(Dog...) if available or animal.attack(Dog...)
		print(((Animal) d2).attack(d2)); // (temporary) static type of d2 only used during compile-time
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
	
	public static void print(boolean bool) {
		System.out.println(bool);
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
	
	public String die() {
		isAlive = false;
		return "Animal died";
	}
	
	public static String getName(Animal a) {
		return a.name;
	}

	public String attack(Animal a) {
		a.die();
		return "Animal attacked animal.";
	}

	public String attack(Dog d) {
		d.die();
		return "Animal attacked dog.";
	}
}

class Dog extends Animal {
	public Dog(String name) {
		super(name);
		noise = "woof";
	}
	
	public String bark() {
		return noise;
	}
	
	public String die() {
		super.die();
		return "Dog died";
	}
	
	public String attack(Animal a) {
		a.die();
		return "Dog attacked animal.";
	}
}