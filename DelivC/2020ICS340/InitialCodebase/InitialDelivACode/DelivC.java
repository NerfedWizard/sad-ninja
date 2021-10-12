import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Class DelivC does the work for deliverable DelivC of the Prog340

/**
 * @author Loel Nelson
 * 
 * 
 *         Code fragments credited to Simulated Annealing 7/7: JAVA
 *         Implementation 3/3 by Noureddin Sadawi
 * 
 *         Program to find the best solution for a CSP of the traveling salesman
 *         Program loops for 30 mins or if no new better solution found after 15
 *         mins it breaks every 5 mins if no improvement tour is shuffled
 *         randomly
 * 
 * 
 */
public class DelivC {
	static long start;
	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	int indexCounter;

	ArrayList<Node> tourList = new ArrayList<Node>();

	public DelivC(File in, Graph gr) {
		inputFile = in;
		g = gr;

		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring(0, inputFileName.length() - 4); // Strip off ".txt"
		String outputFileName = baseFileName.concat("_out.txt");
		outputFile = new File(outputFileName);
		if (outputFile.exists()) { // For retests
			outputFile.delete();
		}

		try {
			output = new PrintWriter(outputFile);
		} catch (Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}

		startStopWatch();
		fakeEdges();
		optimizeTour();
		System.out.println("Results were completed in " + elapsedTime() + " minutes");
		output.println("Results were completed in " + elapsedTime() + " minutes");
		output.flush();
	}

	/**
	 * 
	 * Simulated annealing method for finding best solution
	 * 
	 */
	public void optimizeTour() {

		// set initial value
		double temp = 1000000;
		int checker = 0;

		// cooling rate
		double coolingRate = 0.0000003;

		// create a tour from starting Node
		Tour currentTour = new Tour(grabbingTour());
		currentTour.singleNode();
		System.out.println("Tour: " + currentTour);
		output.println("Tour: " + currentTour);
		System.out.println("Total Distance of Tour: " + currentTour.getTotalDistance() + "\n");
		output.println("Total Distance of Tour: " + currentTour.getTotalDistance() + "\n");

		// We would like to keep track if solution is best
		// Assume best solution is current solution
		Tour bestTour = new Tour(currentTour.getTour());
		int counter = 0;

		// Loop until system has cooled in 30 mins if let run fulltime but has break points to stop sooner
		while (elapsedTime() <= 30) {
			counter++;

			// create new neighbor tour
			Tour newSolution = new Tour(currentTour.getTour());

			// Get random position in tour 
			int tourPos1 = randomInt(0, newSolution.tourSize());
			int tourPos2 = randomInt(0, newSolution.tourSize());

			// Get the nodes at the selected position in tour
			Node nodeSwap = newSolution.getNode(tourPos1);
			Node nodeSwap2 = newSolution.getNode(tourPos2);

			// Swap nodes
			newSolution.setNode(tourPos2, nodeSwap);
			newSolution.setNode(tourPos1, nodeSwap2);

			// Get distances of solutions
			int currentDistance = currentTour.getTotalDistance();
			int neighborTour = newSolution.getTotalDistance();

			// Decide if we should accept neighbor
			double rand = randomDouble();
			if (acceptProb(currentDistance, neighborTour, temp) > rand) {
				currentTour = new Tour(newSolution.getTour());
			}
			// Keep track of the best solution
			if (currentTour.getTotalDistance() < bestTour.getTotalDistance()) {
				bestTour = new Tour(currentTour.getTour());
				System.out.println("Next best tour: " + bestTour);
				System.out.println("Total Distance: " + bestTour.getTotalDistance() + "\n TimeStamp: " + elapsedTime());
				output.println("Next best tour: " + bestTour);
				output.println("Total Distance: " + bestTour.getTotalDistance() + "\n TimeStamp: " + elapsedTime());
				checker = counter;
			}
			// if 5mins has gone by without a better solution shuffle tour
			if (elapsedTime() % 5 == 0 && currentTour.getTotalDistance() > bestTour.getTotalDistance()) {
				Collections.shuffle(currentTour.getTour());
			}
			// Checks if there has been over 20000000 since last solution and breaks loop
			if (temp < 1 && counter - checker > 20000000) {
				break;
			}
			// if 15 mins passed and no better solution stop while loop
			//Hardly makes it to this because of the stopping after 20 million iterations
			if (elapsedTime() % 3 == 0 && elapsedTime() % 5 == 0) {
				if (currentTour.getTotalDistance() > bestTour.getTotalDistance()) {
					break;
				}

			}
			temp *= 1 - coolingRate;
		}
		// Printing the final solution after the proper time or tries since last
		// solution
		System.out.println("Best Tour:   " + bestTour);
		output.println("Best Tour:   " + bestTour);
		System.out.println("Final solution distance:   " + bestTour.getTotalDistance());
		output.println("Final solution distance:   " + bestTour.getTotalDistance());
		System.out.println(
				"After a Total of " + counter + " iterations a best solution was found at iteration " + checker);
		output.println("After a Total of " + counter + " iterations a best solution was found at iteration " + checker);

	}

	/**
	 * probability if we keep the current solution
	 * 
	 * @param curentDistance - integer of current distance
	 * @param newDistance    - integer of new distance to be checked
	 * @param value          - double for probabilities
	 * 
	 * @return either 1 for accepting the newDistance
	 * @return returning the probability if not choosing newDistance
	 */
	public static double acceptProb(int currentDistance, int newDistance, double value) {
		if (newDistance < currentDistance) {
			return 1.0;
		}
		return Math.exp((currentDistance - newDistance) / value);
	}

	/**
	 * Method for creating a random double for the simulated annealing
	 * 
	 * @return a random number used for annealing
	 */
	static double randomDouble() {
		Random r = new Random();
		return r.nextInt(1000) / 1000.0;
	}

	/**
	 * Creating a random integer
	 * 
	 * @return random double cast as an integer
	 */
	public static int randomInt(int min, int max) {
		Random r = new Random();
		double d = min + r.nextDouble() * (max - min);
		return (int) d;
	}

	/**
	 * Used for getting the original starting tour
	 * 
	 * @return tour - returns the tour created in the beginning
	 */
	public ArrayList<Node> grabbingTour() {

		g.startNode();

		indexCounter = Graph.indexOfStartNode;

		int i = 0;// for incrementing nodes for searching

		while (i < Graph.nodeList.size()) {// while loop for grabbing a tour

			tourList.add((Graph.nodeList.get((indexCounter + i) % Graph.nodeList.size())));
			i++;
		}
		return tourList;// return the tour in an ArrayList
	}

	/**
	 * For filling the graph if edges aren't connected if edge is not connected a
	 * value of 999,999 is added to edge
	 */
	public void fakeEdges() {
		for (Node n : Graph.nodeList) {
			for (Node to : Graph.nodeList) {
				if (!isConnected(n.getOutgoingEdges(), n, to)) {
					n.outgoingEdges.add(new Edge(n, to, "999999"));
					Graph.edgeList.add(new Edge(n, to, "999999"));
				}
			}
		}
	}

	/** Method used only for creating the stopwatch to check runtime of program */
	public void startStopWatch() {
		start = System.currentTimeMillis();
	}

	/**
	 * finding the time elapsed since starting the program
	 * 
	 * @return minutes since stopwatch was created
	 */
	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return ((now - start) / 1000.0) / 60;
	}

	/**
	 * Checking whether the nodes are connected by an edge
	 * 
	 * @param edges - ArrayList of edges from graph
	 * @param Node  - tail of the edge to be checked if connected
	 * @param Node  - head of edge to see if connected
	 * @return boolean - if a connected edge or not
	 */
	private boolean isConnected(ArrayList<Edge> edges, Node current, Node next) {
		for (Edge e : edges) {

			if (e.getTail() == current && e.getHead() == next) {
				return true;
			}
		}
		return false;
	}
}
