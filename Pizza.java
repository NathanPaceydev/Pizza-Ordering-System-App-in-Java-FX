package application;
import java.io.Serializable;

/**
 * The class represents a pizza, containing
 * the size, the amount of cheese, pineapple, green pepper and ham. It can also produce the cost 
 * of that pizza using hard-coded prices.
 * This version uses enums to control the legal choices for the pizza configuration.
 */

public class Pizza implements Serializable {

	private static final long serialVersionUID = 406705374689411755L;

	// Cannot use lower case double for a member, so we'll make them all capitalized:
	public enum Size {Small, Medium, Large};
	public enum Cheese {Single, Double, Triple};
	// Use this enum for the green pepper, pineapple and ham toppings:
	public enum Topping {None, Single};

	private Size size;
	private boolean vegetarian;
	private Cheese cheese;
	private Topping pineapple;
	private Topping greenPepper;
	private Topping ham;
	
	private final float SMALL_COST = 7.00F;	// Includes one cheese
	private final float MEDIUM_COST = 9.00F;
	private final float LARGE_COST = 11.00F;
	private final float COST_PER_TOPPING = 1.50F;
		
	/**
	 * The Pizza constructor.
	 * @param size Must be "Small", "Medium" or "Large".
	 * @param vegetarian true if it is to be a vegetarian pizza.
	 * @param cheese Must be "Single", "Double" or "Triple".
	 * @param pineapple Must be "None" or "Single".
	 * @param greenPepper Must be "None" or "Single".
	 * @param ham Must be "None" or "Single".
	 * @throws IllegalPizza If any of the parameters are not legal or if vegetarian
	 * is true and ham is not none.
	 */
	public Pizza(Size size, boolean vegetarian, Cheese cheese, Topping pineapple, 
			Topping greenPepper, Topping ham) throws IllegalPizza {
		this.vegetarian = vegetarian; 
		if (size == null)
			throw new IllegalPizza("Size not provided!");
		this.size = size;
		if (cheese == null)
			throw new IllegalPizza("Cheese not provided!");
		this.cheese = cheese;
		if (pineapple == null)
			throw new IllegalPizza("Pineapple not provided!");
		this.pineapple = pineapple;
		if (greenPepper == null)
			throw new IllegalPizza("Green pepper not provided!");
		this.greenPepper = greenPepper;
		if (ham == null)
			throw new IllegalPizza("Ham not provided!");
		this.ham = ham;
		var None = Topping.None;
		if (this.ham != None && this.vegetarian)
			throw new IllegalPizza("You cannot have ham on a vegetarian pizza!");
	} // end full parameter constructor
	
	/**
	 * Five parameter Pizza constructor.  This constructor assumes a non-vegetarian
	 * pizza.
	 * @param size Must be "Small", "Medium" or "Large".
	 * @param cheese Must be "Single", "Double" or "Triple".
	 * @param pineapple Must be "None" or "Single".
	 * @param greenPepper Must be "None" or "Single".
	 * @param ham Must be "None" or "Single".
	 * @throws IllegalPizza If any of the parameters are not legal.
	 */
	public Pizza(Size size, Cheese cheese, Topping pineapple, 
			Topping greenPepper, Topping ham) throws IllegalPizza {
		this(size, false, cheese, pineapple, greenPepper, ham);
	} // end five parameter constructor
	
	/**
	 * The default Pizza constructor.
	 * The pizza is configured as a small pizza with single cheese and single ham.
	 * @throws IllegalPizza Should not be thrown.
	 */
	public Pizza() throws IllegalPizza {
		this(Size.Small, false, Cheese.Single, Topping.None, Topping.None, Topping.Single);
	} // end default constructor
	
	private float getSizeCost() {
		switch (size) {
		case Small:
			return SMALL_COST;
		case Medium:
			return MEDIUM_COST;
		default:
			return LARGE_COST;		
		}
	} // end getSizeCost
	
	private int translateCheese() {
		switch (cheese) {
		case Single:
			return 0;
		case Double:
			return 1;
		default:
			return 2;
		}
	} // end translateCheese
	
	private int translateTopping(Topping top) {
		switch (top) {
		case None:
			return 0;
		default:
			return 1;
		}
	} // end translateTopping
	
	/**
	 * Returns the cost of the current Pizza object.
	 * @return The cost in dollars.  No tax.
	 */
	public float getCost() {
		float cost = getSizeCost();
		cost += (translateCheese() + translateTopping(pineapple) + 
				  translateTopping(greenPepper) + translateTopping(ham)) * COST_PER_TOPPING;
		return cost;
	} // end getCost
	
	/**
	 * Returns a string representation of the current object.
	 * @return A string description of the current Pizza object.
	 */
	public String toString() {
		var single = Topping.Single;
		String out = size.toString();
		if (vegetarian) out += " vegetarian";
		out += " pizza, " + cheese + " cheese";
		if (pineapple == single)
			out += ", pineapple";
		if (greenPepper == single)
			out += ", green pepper";
		if (ham == single)
			out += ", ham";
		out += String.format(". Cost: $%.2f each.", getCost());
		return out;
	} // end toString
	
	/**
	 * Tests to see if the current object is equal to the supplied Pizza object. Equality is
	 * defined as all attributes being exactly equal.
	 * @param other The supplied object for comparison.
	 * @return false if the objects are not equal or the supplied object is not a Pizza, true
	 * otherwise.
	 */
	public boolean equals(Object other) {
		if (other instanceof Pizza) {
			Pizza otherP = (Pizza)other;
			return size == otherP.size && vegetarian == otherP.vegetarian && cheese == otherP.cheese && 
				   pineapple == otherP.pineapple && greenPepper == otherP.greenPepper && 
				   ham == otherP.ham;
		}
		return false;
	} // end equals
	
	/**
	 * Creates and returns a clone of the current Pizza object.
	 * @return A deep copy or clone of the current object. 
	 */
	public Pizza clone() {
		Pizza outP = null;
		try {
			outP = new Pizza(size, vegetarian, cheese, pineapple, greenPepper, ham);
		} catch (IllegalPizza e) {
		}
		return outP;
	} // end clone
	
} // end Pizza class
