package wenyu.learning.Graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class CycleDetectionForAdjMatrix {
	/*
	 * Directed/Undirected graph loop can be find using DFS by detecting back edge
	 * if undirected:
	 * 1. detect self loop (not implement)
	 * 2. detect parallel loop (not implement)
	 * 3. using DFS to detect loop
	 */
	public static String findCycleFromGraph(int[][] graphMatrix, boolean directed) {
		Stack<Integer> path = new Stack<Integer>();
		boolean[] visited = new boolean[graphMatrix.length];
		for(int i=0; i<graphMatrix.length; i++) {
			if(visited[i] != true) {
				String cycle = DFSForDetectCycle(graphMatrix, i, visited, path, directed);
				if(cycle.length() != 0) {
					return cycle;
				}
			}
		}
		return "";
	}
	
	private static String DFSForDetectCycle(int[][] graphMatrix, int currVertex, boolean[] visited, Stack<Integer> path, boolean directed) {
		visited[currVertex] = true;
		int preVertex = (path.isEmpty())? -1 : path.peek();
		path.push(currVertex);
		for(int i=0; i<graphMatrix[currVertex].length; i++) {
			if(graphMatrix[currVertex][i]>0 && visited[i] != true) {
				return DFSForDetectCycle(graphMatrix, i, visited, path, directed);
			} else if(graphMatrix[currVertex][i]>0 && path.contains(i) && (directed || preVertex!=i)) {
				StringBuilder cycle = new StringBuilder();
				cycle.append(i + " <-- ");
				while(!path.isEmpty() && path.peek()!=i) {
					int vertex = path.pop();
					cycle.append(vertex + " <-- ");
				}
				cycle.append(i);
				return cycle.toString();
			}
		}
		path.pop();
		return "";
	}
}

class CycleDetectionForAdjList {
	/*
	 * Directed/Undirected graph loop can be find using DFS by detecting back edge
	 * if undirected:
	 * 1. detect self loop (not implement)
	 * 2. detect parallel loop (not implement)
	 * 3. using DFS to detect loop
	 */
	public static String findCycleFromGraph(AdjListNode[] graphList, boolean directed) {
		Stack<AdjListNode> path = new Stack<AdjListNode>();
		Map<AdjListNode, Boolean> visited = new HashMap<AdjListNode, Boolean>();
		for(int i=0; i<graphList.length; i++) {
			if(!visited.containsKey(graphList[i])) {
				visited.put(graphList[i], false);
			}
			
			if(!visited.get(graphList[i])) {
				String cycle = DFSForDetectCycle(graphList[i], visited, path, directed);
				if(cycle.length() != 0) {
					return cycle;
				}
			}
			
		}
		return "";
	}
	
	private static String DFSForDetectCycle(AdjListNode currVertex, Map<AdjListNode, Boolean> visited, Stack<AdjListNode> path, boolean directed) {
		visited.put(currVertex, true);
		AdjListNode preVertex = (path.isEmpty())? null : path.peek();
		path.push(currVertex);
		for(AdjListNode vertex : currVertex.adjacencies) {
			if(!visited.containsKey(vertex)) {
				visited.put(vertex, false);
			}
			
			if(!visited.get(vertex)) {
				return DFSForDetectCycle(vertex, visited, path, directed);
			} else if(path.contains(vertex) && (directed || preVertex!=vertex)) {
				StringBuilder cycle = new StringBuilder();
				cycle.append(vertex + " <-- ");
				while(!path.isEmpty() && path.peek()!=vertex) {
					AdjListNode tmpVertex = path.pop();
					cycle.append(tmpVertex + " <-- ");
				}
				cycle.append(vertex);
				return cycle.toString();
			}
		}
		path.pop();
		return "";
	}
}

public class CycleDetection {
	/*
	 * For Adjacent Matrix
	 */
	public static boolean hasCycle(int[][] graphMatrix, boolean directed) {
		if(graphMatrix==null || graphMatrix.length<=0 || graphMatrix[0].length<=0) {
			return false;
		}
		return findOneCycle(graphMatrix, directed).length() != 0;
	}
	public static String findOneCycle(int[][] graphMatrix, boolean directed) {
		if(graphMatrix==null || graphMatrix.length<=0 || graphMatrix[0].length<=0) {
			return "";
		}
		return CycleDetectionForAdjMatrix.findCycleFromGraph(graphMatrix, directed);
	}
	
	/*
	 * For Adjacent List
	 */
	public static boolean hasCycle(AdjListNode[] graphList, boolean directed) {
		if(graphList==null || graphList.length<=0) {
			return false;
		}
		return findOneCycle(graphList, directed).length() != 0;
	}
	
	
	public static String findOneCycle(AdjListNode[] graphList, boolean directed) {
		if(graphList==null || graphList.length<=0) {
			return "";
		}
		return CycleDetectionForAdjList.findCycleFromGraph(graphList, directed);
	}
	
	
	
	public static void main(String[] args) {
//		int[][] graphMatrix = AdjacentMatrixUtils.genRandomUndirectedGraph(10);
//		AdjacentMatrixUtils.printGraph(graphMatrix);
//		String cycle = findOneCycle(graphMatrix, false);
//		System.out.println(cycle);
//		
//		AdjListNode[] graphList = AdjacentListUtils.genRandomUndirectedGraph(3);
//		AdjacentListUtils.printGraph(graphList);
//		String cycle = findOneCycle(graphList, false);
//		System.out.println(cycle);
	}
}
