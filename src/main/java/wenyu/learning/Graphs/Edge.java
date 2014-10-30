package wenyu.learning.Graphs;

public class Edge implements Comparable<Edge> {
	public int start;
	public int end;
	public int weight;
	
	public Edge(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public int compareTo(Edge o) {
		if(weight < o.weight) {
			return 1;
		} else if(weight > o.weight){
			return -1;
		} else {
			return 0;
		}
	}
}