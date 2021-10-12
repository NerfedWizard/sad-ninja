
/**
 * @author Loel Nelson ICS 462
 * 
 *         The Producer class for handling the variable value
 */

public class Producer extends Thread {
	private final SharedVariable shared;

	/** Constructor for Producer */
	public Producer(SharedVariable shared) {
		this.shared = shared;
	}

	/** Where the magic happens */
	@Override
	public void run() {
		for (int loopCount = 0; loopCount <= 4; loopCount++) {
			try {
				int waiting = (int) (Math.random() * 3000);
				sleep(waiting);
			} catch (InterruptedException e) {
				System.out.println("Producer Interrupted");
			}
			this.shared.changeSharedVar(loopCount);
			System.out.println("Producer wrote " + loopCount);
			Driver.printToFile("Producer wrote: " + loopCount);

		}

	}

}