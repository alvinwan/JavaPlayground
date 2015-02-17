/**
 * PROTECTED MEMBERS
 * This file demonstrates the basic functionality of protected
 * members. Not very interesting...
 *
 * @author Alvin Wan
 * @website alvinwan.com
 */

public class ProtectedMembers {
	public static void main(String args[]) {
		Manager m = new Manager(150);
		
		print(m.getSalary()); // CAN be invoked in the package scope (but not in the global scope)
		print(m.getPay()); // getSalary() can be invoked from subclass Employee -> valid call
		print(m.raise(150)); // super.raise is allowed
	}
	
	public static void print(String msg) {
		System.out.println(msg);
	}
	
	public static void print(double db) {
		System.out.println(db);
	}
}

class Employee {
	private double salary;
	
	public Employee(double _salary) {
		salary = _salary;
	}
	
	protected double getSalary() {
		return salary;
	}
	
	protected double raise(double increase) {
		salary += increase;
		return salary;
	}
}

class Manager extends Employee {
	
	public Manager(double _salary) {
		super(_salary);
	}
	
	public double raise(double increase) {
		return super.raise(increase);
	}
	
	public double getPay() {
		return getSalary();
	}
}