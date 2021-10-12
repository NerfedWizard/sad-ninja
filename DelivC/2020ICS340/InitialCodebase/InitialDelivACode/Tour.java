import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for establishing a tour and methods needed to maintain and find the
 * best tour
 */
public class Tour {
	private int distance = 0;
	private ArrayList<Node> tour = new ArrayList<Node>();

	/**
	 * constructor used for creating an empty tour
	 */
	public Tour() {
		for (int i = 0; i < Graph.nodeList.size(); i++) {
			tour.add(null);
		}
	}

	/** constructor for making an existing tour */
	@SuppressWarnings("unchecked")
	public Tour(ArrayList<Node> tour) {
		this.tour = (ArrayList<Node>) tour.clone();
	}

	/** getter for grabbing the current tour */
	public ArrayList<Node> getTour() {
		return this.tour;
	}

	/** setting nodes for simulated annealing */

	public void singleNode() {
		for (int nodeIndex = 0; nodeIndex < Graph.nodeList.size(); nodeIndex++) {
			setNode(nodeIndex, Graph.nodeList.get(nodeIndex));
		}
		Collections.shuffle(tour);
	}

	/**
	 * getting the node from index
	 * 
	 * @param index - integer of position of node
	 * @return Node - the node at indicated index
	 */

	public Node getNode(int index) {
		return tour.get(index);
	}

	/** Setting the node for current tour */
	public void setNode(int index, Node node) {
		tour.set(index, node);
		distance = 0;
	}

	/**
	 * Getting the total distance of the current tour
	 * 
	 * @return int - total distance
	 */
	public int getTotalDistance() {
		distance = 0;
		int indexCounter = Graph.indexOfStartNode;
		int firstCounter = 0;
		int secondCounter = 1;
		while (firstCounter < tourSize()) {
			distance += (Graph.isConnectedEdge(getTour().get((indexCounter + firstCounter) % getTour().size()),
					getTour().get((indexCounter + secondCounter) % getTour().size())).getDist());
			firstCounter++;
			secondCounter++;
		}

		return distance;
	}

	public int tourSize() {

		return tour.size();
	}

	/**
	 * For printing the tour in a string format
	 * 
	 * @return String - string of the current tour
	 */
	@Override
	public String toString() {
		String s = getNode(0).getAbbrev();
		for (int i = 1; i < tourSize(); i++) {
			s += " -> " + getNode(i).getAbbrev();
		}
		return s;

	}
}
