
/**
 * @author Loel Nelson ICS 462
 * 
 *         Consumer class for adding the variable given from producer.
 */
public class Consumer extends Thread {
	private SharedVariable share;
	private int sumNum = 0;

	/** Constructor for creating the Consumer */
	public Consumer(SharedVariable share) {
		this.share = share;
	}

	/** The workhorse of the consumer class */
	@Override
	public void run() {
		for (int loopCounter = 0; loopCounter <= 4; loopCounter++) {
			try {
				int waiting = (int) (Math.random() * 3000);
				sleep(waiting);
			} catch (InterruptedException e) {
				System.out.println("Consumer Interrupted");
			}
			int newSharedVar = this.share.getSharedVar();
			System.out.println("Consumer read: " + newSharedVar);
			Driver.printToFile("Consumer read: " + newSharedVar);
			sumNum += newSharedVar;
		}
		System.out.println("Calculated Sum: " + sumNum);
		Driver.printToFile("Calculated Sum: " + sumNum + "\n");

	}
}
