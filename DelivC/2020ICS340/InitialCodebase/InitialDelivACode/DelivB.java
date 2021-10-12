import java.io.*;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Stack;

//import javax.swing.text.html.HTMLDocument.Iterator;
/**
 * @author Loel Nelson
 * 
 *         Deliv B for doing DFS of a directed graph and finding the inverse
 *         graph and looking for connected components
 * 
 * 
 * 
 * 
 */
public class DelivB {
	String names = "";
	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	Graph newGraph;

	LinkedList<Node> nodeStack = new LinkedList<Node>();
	LinkedList<Node> nodesFound = new LinkedList<Node>();
	private int time;

	Stack<Integer> discoverReverse = new Stack<Integer>();
	Stack<Integer> finishReverse = new Stack<Integer>();
	LinkedList<Node> inverseStack = new LinkedList<Node>();

	public DelivB(File in, Graph gr) {
		inputFile = in;
		g = gr;
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
		Graph.sortedEdges();
		dfs(gr);
		for (Node n : Graph.nodeList) {
			if (!n.isVisited()) {
				dfsVisit(gr, n);
			}
		}

		edgeType(gr);

		printList();

		printEdges();
		gr.reverseGraph();

		revDFS(gr);
		for (Node n : Graph.nodeList) {
			if (!n.isVisited()) {
				revDFSVisit(n);
			}
		}

		revPrintList();
		strongComponents(nodesFound);

		output.flush();
	}

	/**
	 * same as DFS for the original but used for the inverse graph
	 */
	public void revDFS(Graph graph) {

		time = 0;

		for (Node n : graph.getNodeList()) {
			if (n.getVal().equals("S")) {
				revDFSVisit(n);
			}
		}
	}

	public void revDFSVisit(Node newNode) {

		time++;
		newNode.setDiscover(time);
		newNode.visit();
		inverseStack.push(newNode);
		nodesFound.push(newNode);
		Iterator<Edge> newEdges = newNode.getOutgoingEdges().iterator();

		while (newEdges.hasNext()) {
			Edge nextEdge = newEdges.next();
			Node nextNode = nextEdge.getHead();
			if (!nextNode.isVisited()) {
				nextNode.visit();
				revDFSVisit(nextNode);
			}
		}
		time++;
		newNode.setFinish(time);

	}

	/**
	 * entering the dfs from the starting node
	 * 
	 * @param Graph - graph of the beginning graph
	 */
	public void dfs(Graph graph) {

		time = 0;
		// Graph.sortedEdges();
		for (Node n : Graph.nodeList) {

			if (n.getVal().equals("S")) {
				dfsVisit(graph, n);
			}
		}
	}

	/**
	 * Changed my dfs (which worked the same) to match yours and it works for file 6
	 * and goes through 7 but does not get the correct answer
	 * 
	 * recursive dfs for the original graph
	 * 
	 * @param Graph - original graph
	 * @param Node  - node to be searched
	 */
	public void dfsVisit(Graph gr, Node nde) {
		time++;
		nde.setDiscover(time);
		nde.visit();
		ListIterator<Edge> node = nde.getOutgoingEdges().listIterator();
		while (node.hasNext()) {
			Edge e = node.next();
			Node n = e.getHead();
			if (!n.isVisited()) {
				e.setType("Tree");
				dfsVisit(gr, n);
			}
		}
		time++;
		nde.setFinish(time);
		nodeStack.push(nde);
	}

	/**
	 * Method for calculating the strongly connected components of the given graph
	 * 
	 * @param LinkedList - stack of DFS on inverse graph
	 */

	public void strongComponents(LinkedList<Node> sccStack) {
		sortStackOnFinish(sccStack);
		int strong = 1;
		String scc = "";

		for (int counter = 1; counter < sccStack.size(); counter++) {
			scc += sccStack.get(counter - 1).getAbbrev() + " ";
			if (sccStack.get(counter - 1).getFinish() < sccStack.get(counter).getDiscover()) {
				scc += "---- ";
				strong += 1;
				if (sccStack.get(counter).equals(sccStack.getLast())) {
					scc += sccStack.getLast().getAbbrev();
				}
			} else if (counter + 1 == sccStack.size()) {
				scc += sccStack.get(counter).getAbbrev();
			}
		}
		System.out.println("Strongly connected components: " + strong);
		output.println("Strongly connected components: " + strong);
		System.out.println(scc+"\n");
		output.println(scc+"\n");
	}

	/**
	 * Method for getting the edge types of the original graph connection
	 * 
	 * @param Graph - graph to get edge types from
	 */
	public void edgeType(Graph gr) {
		try {

			for (Edge e : gr.getEdgeList()) {

				if (e.getType().equals("")) {
					if (e.getTail().getDiscover() < e.getHead().getDiscover()
							&& e.getHead().getFinish() < e.getTail().getFinish() && !e.getType().equals("Tree")) {
						e.setType("Forward");
					} else if (e.getHead().getDiscover() < e.getTail().getDiscover()
							&& e.getTail().getFinish() < e.getHead().getFinish()) {
						e.setType("Back");
					} else if (e.getHead().getDiscover() < e.getHead().getFinish()
							&& e.getTail().getDiscover() < e.getTail().getFinish()) {
						e.setType("Cross");
					}
				}
			}
		} catch (NullPointerException e) {

		}
	}

	/**
	 * Prints the reverse list of nodes discovered in the inverse graph
	 */
	public void revPrintList() {

		System.out.println("DFS of complimentary graph");
		System.out.printf("%-20s", "Node");
		System.out.printf("%-10s", "Disc");
		System.out.printf("%s", "Finish\n");

		sortStackOnDiscover(inverseStack);

		for (Node n : inverseStack) {
			System.out.printf("%-20s", n.getName());
			System.out.printf("%-10s", n.getDiscover());
			System.out.printf("%s", n.getFinish() + "\n");
			output.printf("%-20s", n.getName());
			output.printf("%-10s", n.getDiscover());
			output.printf("%s", n.getFinish() + "\n");
		}

	}

	/**
	 * Method for printing formatted nodes from the original graph
	 */
	public void printList() {

		System.out.println("DFS of graph");
		System.out.printf("%-20s", "Node");
		System.out.printf("%-10s", "Disc");
		System.out.printf("%s", "Finish\n");

		sortStackOnDiscover(nodeStack);

		for (Node n : nodeStack) {
			System.out.printf("%-20s", n.getName());
			System.out.printf("%-10s", n.getDiscover());
			System.out.printf("%s", n.getFinish() + "\n");
			output.printf("%-20s", n.getName());
			output.printf("%-10s", n.getDiscover());
			output.printf("%s", n.getFinish() + "\n");
		}

	}

	/**
	 * Method for sorting the nodeStack on the discoverTimes
	 * 
	 * @param LinkedList - list to be sorted by discover times
	 * @param Node       - node used for recursive sorting
	 */
	public static void sortedDiscoverTimes(LinkedList<Node> sorted, Node n) {
		if (sorted.isEmpty() || n.getDiscover() < sorted.peek().getDiscover()) {
			sorted.push(n);
			return;
		}
		Node temp = sorted.pop();
		sortedDiscoverTimes(sorted, n);

		sorted.push(temp);
	}

	/**
	 * Method used to conjunction with sortedDiscoverTimes for sorting the nodes
	 * according to the discover times
	 * 
	 * @param
	 */

	public static void sortStackOnDiscover(LinkedList<Node> nodes) {
		if (!nodes.isEmpty()) {
			Node top = nodes.pop();
			sortStackOnDiscover(nodes);
			sortedDiscoverTimes(nodes, top);
		}

	}

	/**
	 * Recursive method for sorting the nodeStack on finishTimes
	 * 
	 * @param finishSort - linkedList to be sorted
	 * @param n          - node to be push back through according to recursion
	 */
	public static void sortedFinishTimes(LinkedList<Node> finishSort, Node n) {
		if (finishSort.isEmpty() || n.getFinish() < finishSort.peek().getFinish()) {
			finishSort.push(n);
			return;
		}
		Node temp = finishSort.pop();
		sortedFinishTimes(finishSort, n);

		finishSort.push(temp);
	}

	/**
	 * Method used for calling the sort recursive method
	 * 
	 * @param finishedNodes - linkedList to be sorted
	 */

	public static void sortStackOnFinish(LinkedList<Node> finishedNodes) {
		if (!finishedNodes.isEmpty()) {
			Node top = finishedNodes.pop();

			sortStackOnFinish(finishedNodes);

			sortedFinishTimes(finishedNodes, top);

		}
	}

	/** Printing the edge types in a formatted pattern */
	public void printEdges() {

		System.out.println("Edge Classification: ");
		System.out.println("Edge Type:");
		output.println("Edge Classification: \n" + "Edge Type: ");

		for (Node n : nodeStack) {

			for (Edge e : n.getOutgoingEdges()) {
				if (e.getType().equals(null)) {
					e.setType("None");
				}
				if (!e.getType().equals("")) {
					System.out.println(e.getTail().getAbbrev() + "->" + e.getHead().getAbbrev() + " " + e.getType());
					output.println(e.getTail().getAbbrev() + "->" + e.getHead().getAbbrev() + " " + e.getType());
				}
			}
		}
	}

//	public void sortEdges() {
////		ArrayList<Node> sortedList = new ArrayList<Node>();
//		List sortedList;
//		int counter = 0;
//		for (Node n : nodeStack) {
//			sortedList.add(n);
//			counter++;
//		}
//		Collections.sort(sortedList);
//	}
}
