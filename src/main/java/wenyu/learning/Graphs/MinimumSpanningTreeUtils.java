package wenyu.learning.Graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * MST should only related to undirected graph
 */


/*
 *  Prime MST
 *  1. Initialize a tree with a single vertex, chosen arbitrarily from the graph.
 *  2. Grow the tree by one edge: of the edges that connect the tree to vertices
	   not yet in the tree, find the minimum-totalWeight edge, and transfer it to the tree.
 *  3. Repeat step 2 (until all vertices are in the tree).
 */
class PrimMST {
	private static double totalWeight;       	// total totalWeight of MST
    private static List<Edge> mst;     			// edges in the MST
    private static boolean[] marked;    		// marked[v] = true if v on tree
    private static Queue<Edge> pq;      		// edges with one endpoint in tree
    
    private static Comparator<Edge> OrderIsdn =  new Comparator<Edge>(){  
		public int compare(Edge e1, Edge e2) {
			if(e1.weight>e2.weight) return 1;
			else if(e1.weight<e2.weight) return -1;
			else return 0;
		}
	};
	
    public static void MST(int[][] graphMatrix) {
    	totalWeight = 0;
        mst = new ArrayList<Edge>();
        marked = new boolean[graphMatrix.length];
        pq = new PriorityQueue<Edge>(graphMatrix.length*graphMatrix.length, OrderIsdn);
        
        for (int node=0; node<graphMatrix.length; node++) {
        	if (!marked[node]) {
        		prim(graphMatrix, node);
        	}
        }
        
        MinimumSpanningTreeUtils.printResult(mst, totalWeight, graphMatrix.length);
    }
    
    // run Prim's algorithm
    private static void prim(int[][] matrix, int node) {
    	addEdgeToPQ(matrix, node);
        while (!pq.isEmpty()) {                        	// better to stop when mst has V-1 edges
            Edge edge = pq.poll();                      // smallest edge on pq
            int start = edge.start;
            int end = edge.end;
            if(marked[start] && marked[end]) {
            	// lazy, both start and end already scanned
            	continue;      
            }
            mst.add(edge);
            totalWeight += edge.weight;
            if (!marked[end]) {
            	addEdgeToPQ(matrix, end);
            }
        }
    }

    // add all edges e incident to v into pq if the other endpoint has not yet been scanned
    private static void addEdgeToPQ(int[][] matrix, int node) {
        if(!marked[node]) {
	        marked[node] = true;
	        for (int i=0;i<matrix.length;i++) {
	        	if(i!=node && matrix[node][i]>0 && matrix[node][i]!=Integer.MAX_VALUE && !marked[i]) {
	        		Edge edge = new Edge(node,i,matrix[node][i]);
	        		pq.offer(edge);
	        	}
	        }
        }
    }
}


/*
 *  Kruskal MST
 *  1.	Sort the edges of G in increasing order by length
 *	2.	keep a sub-graph S of G, initially empty
 *	3.	for each edge e in sorted order
 *	      3.1 If the endpoints of e are disconnected in S
 *	      3.2 Add e to S
 *	4.	return S
 */
class KruskalMST {
	private static double totalWeight;  	// weight of MST
    private static List<Edge> mst;			// edges in MST
    private static boolean[] marked;    	// marked[v] = true if v on tree
    private static UnionFind uf;
    private static class UnionFind {
    	/*
    	 * UnionFind data structure to detect if nodes has loop
    	 * Disjoint Set
    	 */
    	private int[] ufArr;
    	public UnionFind(int len) {
    		ufArr = new int[len];
    		for(int i=0; i<ufArr.length; i++) {
    			ufArr[i] = -1;
    		}
    	}
    	public void union(int v1, int v2) {
    		assert (v1<0 || v1>=ufArr.length || v2<0 || v2>=ufArr.length);
    		ufArr[v2] = v1;
    	}
    	public int find(int v) {
    		if(ufArr[v] == -1) {
    			return v;
    		} else {
    			return find(ufArr[v]);
    		}
    	}
    }
    
    
    private static Comparator<Edge> OrderIsdn =  new Comparator<Edge>(){  
		public int compare(Edge e1, Edge e2) {
			if(e1.weight>e2.weight) return 1;
			if(e1.weight<e2.weight) return -1;
			if(e1.weight==e2.weight) return 0;
			return 0;
		}
	};
	
    public static void MST(int[][] graphMatrix) {
    	totalWeight = 0;
        mst = new ArrayList<Edge>();
        marked = new boolean[graphMatrix.length];
        Queue<Edge> pq = new PriorityQueue<Edge>(graphMatrix.length*2, OrderIsdn);
        uf = new UnionFind(graphMatrix.length);
        
        
        for(int i=0;i<graphMatrix.length;i++) {
        	for(int j=0;j<graphMatrix.length;j++) {
        		if(j!=i && graphMatrix[i][j]>0 && graphMatrix[i][j]!=Integer.MAX_VALUE) {
        			Edge edge = new Edge(i,j,graphMatrix[i][j]);
        			pq.offer(edge);
        		}
        	}
        }
        
        while(!pq.isEmpty() && mst.size()<graphMatrix.length-1) {
        	Edge edge = pq.poll();
        	int start = edge.start;
        	int end = edge.end;
        	if(uf.find(start) == uf.find(end)) {
        		continue;
        	}
        	
        	marked[start] = true;
        	marked[end] = true;
        	mst.add(edge);
        	uf.union(start, end);
        	totalWeight += edge.weight;
        }
        
        MinimumSpanningTreeUtils.printResult(mst, totalWeight, graphMatrix.length);
    }
}


public class MinimumSpanningTreeUtils {
	public static void printResult(List<Edge> mst, double totalWeight, int vertexCount) {
    	if(mst!=null && !mst.isEmpty()) {
    		if(mst.size() != vertexCount-1) {
    			System.out.println("This graph is not all connected.");
    			return;
    		}
    		
    		System.out.println("Minmum Spinning Tree: ");
    		System.out.println("Total totalWeight is " + totalWeight);
    		for(int i=0;i<mst.size();i++) {
    			Edge edge = mst.get(i);
    			System.out.println(edge.start + "-->" + edge.end + "(" + edge.weight + ")");
    		}
    	}
    }
	
	public static void PrimMST(int[][] graphMatrix) {
		PrimMST.MST(graphMatrix);
	}
	public static void KruskalMST(int[][] graphMatrix) {
		KruskalMST.MST(graphMatrix);
	}
	
	
	public static void main(String[] args) {
		int[][] graphMatrix = AdjacentMatrixUtils.genRandomUndirectedWeightedGraph(5);
//		graphMatrix = new int[][] {  {0,  86,  -1, 40 , -1 },
//				 {86,   0,  81 , 69,  -1 },
//				 {-1,  81,   0 , -1 , 23 },
//				 {40,  69,  -1 ,  0,  -1 },
//				 {-1,  -1,  23 , -1 ,  0 }};
		AdjacentMatrixUtils.printGraph(graphMatrix);
		PrimMST(graphMatrix);
		KruskalMST(graphMatrix);
	}
}
