package application;


/**
 * Part of the sample solution for assignment 3. An exception class used by both Pizza and LineItem.
 * @author Alan McLeod
 * @version 1.0
 */
public class IllegalPizza extends Exception {

	private static final long serialVersionUID = 4593713417540203652L;

	/**
	 * The exception class' constructor.
	 * @param message A message describing the error.
	 */
	public IllegalPizza (String message) {
		super(message);
	} // end constructor
	
} // end IllegalPizza class