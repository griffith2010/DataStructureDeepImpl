package wenyu.learning.Graphs;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


class DijkstraSP {
	private static int[] distTo;          			// distTo[v] = distance  of shortest s->v path
    private static Edge[] edgeTo;    				// edgeTo[v] = last edge on shortest s->v path
    private static Queue<Edge> pq;    	 			// priority queue of vertices
    private static Map<Integer, Edge> edgeMapping;  // vertex and edge mapping
	
    private static Comparator<Edge> OrderIsdn =  new Comparator<Edge>(){  
		public int compare(Edge e1, Edge e2) {
			if(e1.weight>e2.weight) return 1;
			else if(e1.weight<e2.weight) return -1;
			else return 0;
		}
	};
    
    public static void SP(int[][] graphMatrix, int startVertice) {
    	// Initialize properties
    	distTo = new int[graphMatrix.length];
        edgeTo = new Edge[graphMatrix.length];
        pq = new PriorityQueue<Edge>(distTo.length, OrderIsdn);
        edgeMapping = new HashMap<Integer, Edge>();
        
        // Initialize distance table
    	for(int i=0;i<graphMatrix.length;i++) {
        	if(i == startVertice) {
        		distTo[i] = 0;
        		continue;
        	}
        	if(graphMatrix[startVertice][i] <= 0) {
        		distTo[i] = Integer.MAX_VALUE;
        	} else {
        		distTo[i] = graphMatrix[startVertice][i];
        		Edge edge = new Edge(startVertice, i, distTo[i]);
        		edgeTo[i] = edge;
        		pq.offer(edge);
        		edgeMapping.put(i, edge);
        	}
        }

        Edge edge = pq.poll();
        int nextVertice = edge.end;
        while(nextVertice >= 0) {
        	for(int i=0;i<graphMatrix.length;i++) {
        		if(distTo[nextVertice]+graphMatrix[nextVertice][i]>0 && distTo[i]>distTo[nextVertice]+graphMatrix[nextVertice][i]) {
    				distTo[i] = distTo[nextVertice]+graphMatrix[nextVertice][i];
    				Edge fromEdge = new Edge(nextVertice, i, distTo[i]);
    				edgeTo[i] = fromEdge;
    				pq.remove(edgeMapping.get(i));
    				Edge newEdge = new Edge(startVertice, i, distTo[i]);
    				pq.offer(newEdge);
            		edgeMapping.put(i, newEdge);
    			}
        	}
        	if(!pq.isEmpty()) {
        		nextVertice = pq.poll().end;
        	} else {
        		break;
        	}
        }
        
        printSP(startVertice);
    }
    
    private static void printSP(int startVertice) {
    	if(distTo != null) {
    		for(int i=0;i<distTo.length;i++) {
    			Stack<Edge> path = new Stack<Edge>();
    			if(distTo[i]<Integer.MAX_VALUE) {
	    	        for(Edge e = edgeTo[i]; e!=null; e=edgeTo[e.start]) {
	    	            path.push(e);
	    	        }
    			}
    			System.out.format("V" + i + " (%3d)", distTo[i]);
    			System.out.print(": V" + startVertice);
    			while(!path.isEmpty()) {
    				System.out.print(" --> V" + path.pop().end);
    			}
    			System.out.println();
    		}
    	}
    }
}


class UnWeightedGraphSP {
	public static void SP(AdjListNode[] graphList) {
		/**
		 * This strategy for searching a graph 
		 * is known as breadth-first search.
		 * 
		 * Default starting from 0 vertex to the others
		 */
		
		if(graphList==null || graphList.length<=0) {
			return;
		}
		
		Map<AdjListNode, Integer> sp = new HashMap<AdjListNode, Integer>();
		Map<AdjListNode, Boolean> visited = new HashMap<AdjListNode, Boolean>();
		Queue<AdjListNode> queue = new LinkedList<AdjListNode>();
		int step = 0;
		sp.put(graphList[0], step);
		queue.offer(graphList[0]);

		while(!queue.isEmpty()) {
			AdjListNode vertex = queue.poll();
			if(!visited.containsKey(vertex) || !visited.get(vertex)) {
				visited.put(vertex, true);
				queue.offer(vertex);

				for(AdjListNode adj : vertex.adjacencies) {
					if(!visited.containsKey(adj) || !visited.get(adj)) {
						visited.put(adj, false);
						queue.offer(adj);
						if(!sp.containsKey(adj)) {
							sp.put(adj, sp.get(vertex)+1);
						}
					}
				}
			}
		}
		
		for(AdjListNode vertex : graphList) {
			if(sp.containsKey(vertex)) {
				System.out.println(vertex + ": " + sp.get(vertex));
			} else {
				System.out.println(vertex + ": -1");
			}
		}
	}
}

class FloydWarshallSP {
	private static boolean hasNegativeCycle; 	// is there a negative cycle?
    private static int[][] distTo;  			// distTo[v][w] = length of shortest v->w path
    private static Edge[][] edgeTo;  			// edgeTo[v][w] = last edge on shortest v->w path
	
    public static void SP(int[][] graphMatrix) {
    	distTo = new int[graphMatrix.length][graphMatrix.length];
        edgeTo = new Edge[graphMatrix.length][graphMatrix.length];
        
        // initialize distances to infinity
        for (int v=0; v<graphMatrix.length; v++) {
            for (int w=0; w<graphMatrix.length; w++) {
                distTo[v][w] = Integer.MAX_VALUE;
            }
        }

        // initialize distances using edge-weighted digraph's
        for (int v=0; v<graphMatrix.length; v++) {
        	for(int j=0;j<graphMatrix.length;j++) {
        		if(v==j) {
        			distTo[v][v] = 0;
                    edgeTo[v][v] = null;
        		} else if(graphMatrix[v][j]>0) {
        			distTo[v][j] = graphMatrix[v][j];
                    edgeTo[v][j] = new Edge(v, j, distTo[v][j]);
        		}
        	}
        	
            // in case of self-loops
            if (distTo[v][v] >= 0) {
                distTo[v][v] = 0;
                edgeTo[v][v] = null;
            }
        }
        
        // Floyd-Warshall updates
        for (int i=0; i<graphMatrix.length; i++) {
            // compute shortest paths using only 0, 1, ..., i as intermediate vertices
            for (int v=0; v<graphMatrix.length; v++) {
                if (edgeTo[v][i] == null) continue;  // optimization
                for (int w=0; w<graphMatrix.length; w++) {
                    if (distTo[v][w] > distTo[v][i] + distTo[i][w] && distTo[v][i] + distTo[i][w]>0) {
                        distTo[v][w] = distTo[v][i] + distTo[i][w];
                        edgeTo[v][w] = edgeTo[i][w];
                    }
                }
                // check for negative cycle
                if (distTo[v][v] < 0.0) {
                    hasNegativeCycle = true;
                    return;
                }
            }
        }
        
        printSP();
    }
    
    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }
    
    public static void printSP() {
    	for(int i=0; i<distTo.length; i++) {
    		for(int j=0; j<distTo[i].length; j++) {
    			path(i, j);
    		}
    	}
    }
    
    public static void path(int start, int terminal) {
        if(start!=terminal && distTo[start][terminal]<Integer.MAX_VALUE) {
        	Stack<Edge> path = new Stack<Edge>();
            for(Edge edge=edgeTo[start][terminal]; edge!=null; edge=edgeTo[start][edge.start]) {
                path.push(edge);
            }
            
            System.out.print("V" + start);
            while(!path.isEmpty()) {
            	Edge edge = path.pop();
            	System.out.print("-->V" + edge.end);
            }
            System.out.println(" (" + distTo[start][terminal] + ")");
        } else {
        	System.out.println("Has no path between V"+ start + " and V" + terminal +"...");
        }
    }
}

public class ShortestPathUtils {
	public static void UnWeightedGraphSP(AdjListNode[] graphList) {
		UnWeightedGraphSP.SP(graphList);
	}
	public static void DijkstraSP(int[][] graphMatrix, int starter) {
		DijkstraSP.SP(graphMatrix, starter);
	}
	public static void FloydWarshallSP(int[][] graphMatrix) {
		FloydWarshallSP.SP(graphMatrix);
	}
	
	public static void main(String[] args) {
//		AdjListNode[] graphList = AdjacentListUtils.genRandomDirectedGraph(10);
//		AdjacentListUtils.printGraph(graphList);
//		UnWeightedGraphSP(graphList);
		
		int[][] graphMatrix = AdjacentMatrixUtils.genRandomUndirectedWeightedGraph(5);
		AdjacentMatrixUtils.printGraph(graphMatrix);
		//DijkstraSP(graphMatrix, 0);
		FloydWarshallSP(graphMatrix);
	}
}
