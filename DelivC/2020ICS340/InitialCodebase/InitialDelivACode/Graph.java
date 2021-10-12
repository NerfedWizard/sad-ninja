import java.util.*;

public class Graph {

	static ArrayList<Node> nodeList;
	static ArrayList<Edge> edgeList;
	static int indexOfStartNode;
	Edge edge;
	Node node;

	public Graph() {
		nodeList = new ArrayList<Node>();
		edgeList = new ArrayList<Edge>();
	}

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	public ArrayList<Edge> getEdgeList() {
//		Node.edgeCompareNode.new Comparator(edgeList).compare(edge,this.edge);;
		return edgeList;
	}

	/**
	 * For finding if the 2 nodes have a connecting edge
	 * 
	 * @param Node - tail node of the edge
	 * @param Node - head node of the edge
	 * @return Edge - connecting edge if found
	 */
	public static Edge isConnectedEdge(Node tail, Node head) {
		for (Edge e : tail.getOutgoingEdges()) {
			if (e.getTail().equals(tail) && e.getHead().equals(head)) {
				return e;
			}
		}
		return null;
	}

	public Edge getEdge() {
		return this.edge;
	}

	/**
	 * find out if they are connected and work from that boolean if they are and
	 * return true if connected and not the edge
	 */
	public boolean connected(Edge e) {
		if (!e.equals(null)) {
			return true;
		}
		return false;
	}

	public Node startNode() {
		for (Node n : nodeList) {
			indexOfStartNode++;
			if (n.getVal().equals("S")) {
				return n;

			}
		}
		return null;
	}

	/**
	 * For creating the reverse graph swapping the head and tail of the graph and
	 * redirecting the edges
	 */
	public void reverseGraph() {
		Node headTail;
		Node tailHead;

		for (Edge e : edgeList) {
			headTail = e.getHead();
			tailHead = e.getTail();
			e.setHead(tailHead);
			e.setTail(headTail);
		}
		for (Node n : nodeList) {
			n.clearEdges();
			n.setVisit(false);
			for (Edge edge : edgeList) {
				if (edge.getTail().name.equals(n.getName())) {
					n.addOutgoingEdge(edge);
				}
				if (edge.getHead().getName().equals(n.getName())) {
					n.addIncomingEdge(edge);
				}
			}
		}
	}

	public void addNode(Node n) {
		this.node = n;
		nodeList.add(n);
	}

	public void addEdge(Edge e) {
		this.edge = e;
		edgeList.add(e);
	}

	/**
	 * For sorting the edges on two fields by Name and edge weight
	 */
	public static void sortedEdges() {
		Comparator<Edge> compareNameDist = Comparator.comparing(Edge::getDist).thenComparing(Edge::headName);

		for (Node n : nodeList) {
			n.outgoingEdges.sort(compareNameDist);
		}
	}

}
