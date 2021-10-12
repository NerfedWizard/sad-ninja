import java.io.*;
import java.util.*;

// Class DelivA does the work for deliverable DelivA of the Prog340

/**
 * @author Loel Nelson
 * 
 *         A program to grab the starting node and return the route from
 *         startnode back to startnode if the path/edge does not exist it
 *         returns "Path does not exist" if path does exist it returns the city
 *         name and the total distance traveled on the path taken from the
 *         startNode
 * 
 */

public class DelivA {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	int totalDistance = 0;
	String listOfCities = "";
	Node startNode;
	ArrayList<Node> newNodeList;
	int indexCounter = 0;
	boolean flag = true;

	public DelivA(File in, Graph gr) {
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

		List<Node> newNodeList = new ArrayList<Node>(g.getNodeList());// making a copy of the list

		g.startNode();

		indexCounter = Graph.indexOfStartNode;

		int i = 0;// for incrementing nodes for searching
		int j = 1;
		/**
		 * while loop for getting the 2 nodes and comparing them to see if they have a
		 * connected edge
		 */
		try {
			while (i < newNodeList.size() && flag == true) {

				listOfCities += ((newNodeList.get((indexCounter + i) % newNodeList.size())).abbrev + " -> ");
//				System.out.println((Graph.isConnectedEdge(newNodeList.get((indexCounter + i) % newNodeList.size()),
//						newNodeList.get((indexCounter + j) % newNodeList.size())).getDist()));
				totalDistance += (Graph.isConnectedEdge(newNodeList.get((indexCounter + i) % newNodeList.size()),
						newNodeList.get((indexCounter + j) % newNodeList.size())).getDist());
				i++;
				j++;

			}
		} catch (NullPointerException e) {
			System.out.println(" Path does not exist ");
		}
		if (totalDistance != 0) {
			System.out.println(listOfCities);
			System.out.println("distance: " + totalDistance);

			output.println(listOfCities);
			output.println("Distance: " + totalDistance);
		}
		output.flush();

	}
}
