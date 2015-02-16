/**
 * PRIVATE MEMBERS
 * This file demonstrates the basic functionality of private
 * members. Things to note:
 *  --  private members, whether methods or variables, are never
 *      inherited  
 *  --  inherited functions from the class containing a private
 *      member, may access its own private members
 *
 * @author Alvin Wan
 * @website alvinwan.com 
 */

public class PrivateMembers {
	public static void main(String args[]) {

		/**
		 * Basic functionality of private members: they
		 * may not be accessed globally. However, they
		 * may be accessed from within the class itself.
		 */
		
		Burger b = new Burger(true);
//		b.nullifyCondiment(1); // normal behavior - private method cannot be accessed globally
		b.removeCondiment(1); // normal behavior - private method can be accessed from removeCondiment
		System.out.println(b.hasBread); // normal behavior - private variable cannot be accessed globally

		/**
		 * *
		 */
		
		BigBurger bb = new BigBurger();
		b.removeCondiment(1); // removeCondiment is inherited from Burger so removeCondiment has access to private Burger.nullifyCondiment -> valid call
		b.toggleBread(); // toggleBread is overridden in BigBurger so toggleBread does not have access to addBread(), removeBread(), or hasBread -> invalid call
		
	}
	
	public static void print(String msg) {
		System.out.println(msg);
	}
}

class Burger {
	public boolean eaten;
	private boolean hasBread;
	public String[] condiments;
	
	public Burger(boolean _hasBread) {
		hasBread = _hasBread;
		condiments = (_hasBread) ? new String[]{"meat","cheese","lettuce"} : new String[0];
	}
	
	private void nullifyCondiment(int i) {
		System.out.println("Nullfying condiment: "+condiments[i]);
		condiments[i] = null;
	}
	
	public void removeCondiment(int i) {
		System.out.println("Removing...");
		nullifyCondiment(i);
	}
	
	private void removeBread() {
		hasBread = false;
	}
	
	private void addBread() {
		hasBread = true;
	}
	
	public void toggleBread() {
		hasBread = !hasBread;
		System.out.println("bread toggled");
	}
}

class BigBurger extends Burger {
	int i;
	
	public BigBurger() {
		i = 0;
		super(true);
	}
	
	public void simplify() {
//		nullifyCondiments(i); // compile-time error: nullifyCondiments is NOT inherited
//		super.nullifyCondiments(i); // compile-time error: nullifyCondiments canNOT be called
		removeCondiment(i);
		i += 1;
	}
	
	public void toggleBread() {
//		hasBread = !hasBread; // compile-time error: variable is NOT inherited, compiler notes that variable is declared private
		
//		if (hasBread) // compile-time error:
//			removeBread();
//		else
//			addBread();
		
	}
}