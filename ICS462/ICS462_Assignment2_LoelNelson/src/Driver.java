
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Help with code from
 * https://www.geeksforgeeks.org/producer-consumer-solution-using-threads-java/
 * 
 * @author Loel Nelson 9/22/20 Assignment 2
 * 
 *         Program takes the value 100 and writes to a shared variable. Next,
 *         the program starts 2 threads Producer and Consumer. The producer gets
 *         the shared variable and writes the current count of the for loop 0 -
 *         4. The consumer reads the shared variable and stores it in a variable
 *         that will keep track of the sum after also executing the loop 5
 *         times. During consumer/producer loops the program may wait for 1 to 3
 *         seconds via sleep(). Output of the program will be displayed via the
 *         console and will make and write to a file where ever the program is
 *         executed from and will keep adding to the created file upon re-tests.
 * 
 * 
 *         Driver for the program and writing to the file
 */

public class Driver {
	static BufferedWriter bfWrite;
	static String fileName = "Assignment2_out.txt";

	public static void main(String[] args) {

		SharedVariable share = new SharedVariable();
		Producer producer = new Producer(share);
		Consumer consumer = new Consumer(share);
		try {
			bfWrite = new BufferedWriter(new FileWriter(fileName, true));
			bfWrite.write("\nLoel Nelson\nThe results are: " + "\n");
		} catch (Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}
		try {
			bfWrite.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		producer.start();
		consumer.start();
	}

	/**
	 * Method for printing to the file to show output
	 * 
	 * @param String - string of output to be written to file
	 */
	public static void printToFile(String write) {
		String wrote = write;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
			writer.append("\n");
			writer.append(wrote);
			writer.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
