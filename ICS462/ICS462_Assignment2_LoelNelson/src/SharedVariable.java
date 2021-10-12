
/**@author Loel Nelson ICS 462
 * Class for storing and manipulating the shared integer variable.
 */

public class SharedVariable {

	private volatile int shared;

	/**
	 * Constructor for creating the shared variable with the initial value of 100
	 */
	public SharedVariable() {
		this.shared = 100;
	}

	/**
	 * Method for updating the shared variable
	 * 
	 * @param int - integer to be used to update shared variable
	 */
	public void changeSharedVar(int producerChange) {
		this.shared = producerChange;
	}

	/**
	 * Method for getting the shared variable
	 * 
	 * @return shared variable - integer
	 */
	public int getSharedVar() {
		return this.shared;
	}
}
