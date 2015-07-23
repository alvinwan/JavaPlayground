/**
 * METHOD LOOKUPS
 * This file demonstrates how methods are found, according to
 * type. Namely, method lookups are dynamic, and parameters
 * are checked for static type.
 * 
 * @author Alvin Wan
 * @website alvinwan.com
 */

public class MethodLookups {
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
		print(((Animal) d).die()); // dog.die called -> Dog died
//		print(((Animal) d).bark()); // fails on compile-time, even though Dog has bark method - Animal has no bark method
		
		// PARAMETER LOOKUP

		Animal d2 = new Dog("David");
		
		/*
		Non-Static Methods:
		For a parameter, only the static type matters. If
		there is no method signature accepting parameter Dog, 
		will look for method accepting parameter Animal.
		 */
		print(Animal.getName(a)); // static type Animal -> OK
		print(Animal.getName(d)); // static type Dog, but no method that accepts Dog as a parameter -> use method accepting Animal
		print(d.attack(d2)); // static type Animal -> use method accepting Animal

		print(d.attack(a)); // a is Animal, and d is Dog -> Dog attack Animal
		print(d2.attack(d2)); // compile-time: does Animal.attack(Animal...) exist? run-time: uses dog.attack(Animal ...) if available or animal.attack(Animal...) -> Dog attack animal.
 		print(d2.attack((Dog) d2)); // compile-time: does Animal.attack(Dog...) exist? run-time uses dog.attack(Dog...) if available or animal.attack(Dog...) -> Animal attack dog.
		print(((Dog) d2).attack(d2)); // (temporary) static type of d2 only used during compile-time -> Dog attack animal.
		print(d.attack(d)); // does it call attack with more specific method in the parent class accepting Dog parameter, or the general method in subclass accepting an Animal? -> Animal attack Dog.
		
		/*
		Static Methods
		Rules for non-static method still apply, but, if the
		method is STATIC, the static method that allows the
		compiler to work successfully will be used during
		run-time.
		 */
		print(a.getName(a)); // normal behavior - object can similarly use static methods -> General
		print(d.getName(a)); // normal behavior - subclasses inherit static methods -> General
		print(d2.die()); // for STATIC methods, the method that allows successful compilation will be used -> Animal died
		print(((Dog) d2).die()); // as a result, the static type matters for this STATIC method -> Dog died
		print(((Animal) d).die()); // casting a Dog to an Animal achieves the same effect -> Animal died
		
		//print(d2.bark()); // same rules apply for compile-time default to static type -> compile-error: no bark method in Animal!
		print(d2.noise(d)); // normal behavior - inherits static method from Animal -> dog.noise(Dog d) -> woof
		print(d.noise(d)); // normal behavior - inherits static method from Animal -> dog.noise(Dog d) -> woof
		
		print(d2.resurrect()); // SEE BELOW - nonstatic method cannot override static method
	}

	/**
	 * Returns the noise of the Dog. 
	 * @param d : Dog object
	 * @return : noise
	 */
	public static String getName(Dog d) {
		return d.noise;
	}
	
	// print string
	public static void print(String msg) {
		System.out.println(msg);
	}
}

class Animal {
	public String name;
	
	public Animal(String _name) {
		name = _name;
	}
	
	public static String die() {
		return "Animal died";
	}
	
	public static String resurrect() {
		return "Animal resurrected";
	}
	
	public static String getName(Animal a) {
		return a.name;
	}

	public static String noise(Dog d) {
		return d.noise;
	}

	public String attack(Animal a) {
		return "Animal attack animal.";
	}

	public String attack(Dog d) {
		return "Animal attack dog.";
	}
}

class Dog extends Animal {
	public String noise;
	
	public Dog(String name) {
		super(name);
		noise = "woof";
	}
	
	public String bark() {
		return "BARK";
	}
	
	public static String die() {
		return "Dog died";
	}
	
//	public String resurrect() {
//		return "Dog resurrected";
//	}
	
	public String attack(Animal a) {
		return "Dog attack animal.";
	}
}