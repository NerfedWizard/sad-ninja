import java.util.*;

// A node of a graph for the Spring 2018 ICS 340 program

public class Node {
	Integer discover;
	int finish;
	String name;
	String val; // The value of the Node
	String abbrev; // The abbreviation for the Node
	ArrayList<Edge> outgoingEdges;
	ArrayList<Edge> incomingEdges;
	static ArrayList<Integer> discoverTimes = new ArrayList<Integer>();
	private boolean visit = false;

	Integer[] sortedEdges;
	int manyItems = 0;

	public Node(String theAbbrev) {
		setAbbrev(theAbbrev);
		val = null;
		name = null;
		outgoingEdges = new ArrayList<Edge>();
		incomingEdges = new ArrayList<Edge>();
		discover = 0;
		finish = 0;

	}

	public String getAbbrev() {
		return abbrev;
	}

	public String getName() {
		return name;
	}

	public String getVal() {
		return val;
	}

	public void clearEdges() {
		this.outgoingEdges.clear();
		this.incomingEdges.clear();
	}

	public ArrayList<Edge> getOutgoingEdges() {
		return this.outgoingEdges;
	}

	public ArrayList<Edge> getIncomingEdges() {
		return this.incomingEdges;
	}

	public void setAbbrev(String theAbbrev) {
		abbrev = theAbbrev;
	}

	public void setName(String theName) {
		name = theName;
	}

	public void setVal(String theVal) {
		val = theVal;
	}

	public void addOutgoingEdge(Edge e) {
		outgoingEdges.add(e);

	}

	public void addIncomingEdge(Edge e) {
		incomingEdges.add(e);
	}

	public void setDiscover(Integer discover) {
		this.discover = discover;
		Node.discoverTimes.add(discover);
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}

	public Integer getDiscover() {
		return this.discover;
	}

	public int getFinish() {
		return this.finish;
	}

	public void visit() {
		this.visit = true;
	}

	public void setVisit(boolean flag) {
		this.visit = flag;
	}

	public boolean isVisited() {
		return this.visit;
	}
}