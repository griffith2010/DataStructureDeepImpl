package wenyu.learning.Graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BreadthFirstSearchUtils {
	/*
	 * BFS for adjacent list graph
	 */
	public static void BreadthFirstSearch(AdjListNode[] graphList, boolean recursion) {
		if(graphList==null || graphList.length<=0) {
			return;
		}
		Map<AdjListNode, Boolean> visited = new HashMap<AdjListNode, Boolean>();
		for(int i=0; i<graphList.length; i++) {
			if(!visited.containsKey(graphList[i])) {
				visited.put(graphList[i], false);
			}
			
			if(!visited.get(graphList[i])) {
				if(recursion) {
					// No way to implement
				} else {
					BFSWithoutRecursion(graphList[i], visited);
				}
				System.out.println("END");
			}
			
		}
	}
	private static void BFSWithoutRecursion(AdjListNode currVertex, Map<AdjListNode, Boolean> visited) {
		Queue<AdjListNode> queue = new LinkedList<AdjListNode>();
		queue.offer(currVertex);
		
		while(!queue.isEmpty()) {
			AdjListNode vertex = queue.poll();
			if(!visited.containsKey(vertex) || !visited.get(vertex)) {
				System.out.print(vertex + " --> ");
				visited.put(vertex, true);
				
				for(AdjListNode adj : vertex.adjacencies) {
					if(!visited.containsKey(adj) || !visited.get(adj)) {
						visited.put(adj, false);
						queue.offer(adj);
					}
				}
			}
		}
	}
	
	/*
	 * BFS for adjacent matrix graph
	 */
	public static void BreadthFirstSearch(int[][] graphMatrix, boolean recursion) {
		if(graphMatrix==null || graphMatrix.length<=0 || graphMatrix[0].length<=0 || graphMatrix.length!=graphMatrix[0].length) {
			return;
		}
		boolean[] visited = new boolean[graphMatrix.length];
		for(int i=0; i<graphMatrix.length; i++) {
			if(visited[i] != true) {
				if(recursion) {
					// No way to implement
				} else {
					BFSWithoutRecursion(graphMatrix, i, visited);
				}
				System.out.println("END");
			}
			
		}
	}
	private static void BFSWithoutRecursion(int[][] graphMatrix, int currVertex, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(currVertex);
		
		while(!queue.isEmpty()) {
			int vertex = queue.poll();
			if(visited[vertex] != true) {
				System.out.print("V" + vertex + " --> ");
				visited[vertex] = true;
			
				for(int i=0; i<graphMatrix[vertex].length ;i++) {
					if(graphMatrix[vertex][i]>0 && visited[i] != true) {
						queue.offer(i);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = AdjacentMatrixUtils.genRandomUndirectedGraph(6);
		AdjacentMatrixUtils.printGraph(matrix);
		BreadthFirstSearch(matrix, false);
	}
}
