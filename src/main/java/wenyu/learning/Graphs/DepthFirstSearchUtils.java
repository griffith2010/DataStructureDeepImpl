package wenyu.learning.Graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DepthFirstSearchUtils {
	/*
	 * DFS for Adjacent list graph
	 */
	public static void DepthFirstSearch(AdjListNode[] graphList, boolean recursion) {
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
					DFSWithRecursion(graphList[i], visited);
				} else {
					DFSWithoutRecursion(graphList[i], visited);
				}
				System.out.println("END");
			}
			
		}
	}
	private static void DFSWithRecursion(AdjListNode currVertex, Map<AdjListNode, Boolean> visited) {
		System.out.print(currVertex + " --> ");
		visited.put(currVertex, true);
		for(AdjListNode vertex : currVertex.adjacencies) {
			if(!visited.containsKey(vertex)) {
				visited.put(vertex, false);
			}
			
			if(!visited.get(vertex)) {
				DFSWithRecursion(vertex, visited);
			}
		}
	}
	private static void DFSWithoutRecursion(AdjListNode currVertex, Map<AdjListNode, Boolean> visited) {
		Stack<AdjListNode> stack = new Stack<AdjListNode>();
		stack.push(currVertex);
		
		while(!stack.isEmpty()) {
			AdjListNode vertex = stack.pop();
			if(!visited.containsKey(vertex) || !visited.get(vertex)) {
				System.out.print(vertex + " --> ");
				visited.put(vertex, true);
			}
			
			for(int i=vertex.adjacencies.size()-1; i>=0 ;i--) {
				if(!visited.containsKey(vertex.adjacencies.get(i)) || !visited.get(vertex.adjacencies.get(i))) {
					visited.put(vertex.adjacencies.get(i), false);
					stack.push(vertex.adjacencies.get(i));
				}
			}
		}
	}
	
	
	/*
	 * DFS for Adjacent Matrix graph
	 */
	public static void DepthFirstSearch(int[][] graphMatrix, boolean recursion) {
		if(graphMatrix==null || graphMatrix.length<=0 || graphMatrix[0].length<=0 || graphMatrix.length!=graphMatrix[0].length) {
			return;
		}
		boolean[] visited = new boolean[graphMatrix.length];
		for(int i=0; i<graphMatrix.length; i++) {
			if(visited[i] != true) {
				if(recursion) {
					DFSWithRecursion(graphMatrix, i, visited);
				} else {
					DFSWithoutRecursion(graphMatrix, i, visited);
				}
				System.out.println("END");
			}
			
		}
	}
	private static void DFSWithRecursion(int[][] graphMatrix, int currVertex, boolean[] visited) {
		System.out.print("V" + currVertex + " --> ");
		visited[currVertex] = true;
		for(int i=0; i<graphMatrix[currVertex].length; i++) {
			if(graphMatrix[currVertex][i]>0 && visited[i] != true) {
				DFSWithRecursion(graphMatrix, i, visited);
			}
		}
	}
	private static void DFSWithoutRecursion(int[][] graphMatrix, int currVertex, boolean[] visited) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(currVertex);
		
		while(!stack.isEmpty()) {
			int vertex = stack.pop();
			if(visited[vertex] != true) {
				System.out.print("V" + vertex + " --> ");
				visited[vertex] = true;
			}
			
			for(int i=graphMatrix[vertex].length-1; i>=0 ;i--) {
				if(graphMatrix[vertex][i]>0 && visited[i] != true) {
					stack.push(i);
				}
			}
		}
	}
	
	public static Stack<AdjListNode> PostDepthFirstSearch(AdjListNode[] graphList) {
		return PostDepthFirstSearch(graphList, graphList[0]);
	}
	public static Stack<AdjListNode> PostDepthFirstSearch(AdjListNode[] graphList, AdjListNode starter) {
		Stack<AdjListNode> stack = new Stack<AdjListNode>();
		Map<AdjListNode, Boolean> visited = new HashMap<AdjListNode, Boolean>();
		if(!visited.containsKey(starter) || !visited.get(starter)) {
			postDFSRec(stack, starter, visited);
		}
		for(AdjListNode vertex : graphList) {
			if(!visited.containsKey(vertex) || !visited.get(vertex)) {
				postDFSRec(stack, graphList[0], visited);
			}
		}
		return stack;
	}
	private static void postDFSRec(Stack<AdjListNode> stack, AdjListNode currentVertex, Map<AdjListNode, Boolean> visited) {
		visited.put(currentVertex, true);
		for(int i=0; i<currentVertex.adjacencies.size(); i++) {
			AdjListNode tmpVertex = currentVertex.adjacencies.get(i);
			if(!visited.containsKey(tmpVertex) || !visited.get(tmpVertex)) {
				postDFSRec(stack, tmpVertex, visited);
			}
		}
		stack.push(currentVertex);
	}
}
