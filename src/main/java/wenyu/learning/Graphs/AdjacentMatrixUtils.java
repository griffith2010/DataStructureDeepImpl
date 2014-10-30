package wenyu.learning.Graphs;

import java.util.Random;

public class AdjacentMatrixUtils {
	
	public static void printGraph(int[][] graphMatrix) {
		for(int i=0; i<graphMatrix.length; i++) {
			for(int j=0; j<graphMatrix[0].length; j++) {
				if(graphMatrix[i][j] == Integer.MAX_VALUE) {
					System.out.format(" %2s ", "-1");
				} else {
					System.out.format(" %2s ", graphMatrix[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	public static int[][] genRandomDirectedGraph(int vertexCount) {
		if(vertexCount <= 0) {
			return null;
		}
		
		int[][] graph = new int[vertexCount][vertexCount];
		Random random = new Random();
		for(int i=0;i<vertexCount;i++) {
			for(int j=0;j<vertexCount;j++) {
				if(i==j) { continue; }
				graph[i][j] = random.nextInt(2);
			}
		}
		return graph;
	}
	
	public static int[][] genRandomDirectedWeightedGraph(int vertexCount) {
		if(vertexCount <= 0) {
			return null;
		}
		
		int[][] graph = new int[vertexCount][vertexCount];
		Random random = new Random();
		for(int i=0;i<vertexCount;i++) {
			for(int j=0;j<vertexCount;j++) {
				if(i==j) { continue; }
				if(random.nextBoolean()) {
					graph[i][j] = random.nextInt(100);
				} else {
					graph[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		return graph;
	}
	
	public static int[][] genRandomUndirectedGraph(int vertexCount) {
		if(vertexCount <= 0) {
			return null;
		}
		
		int[][] graph = new int[vertexCount][vertexCount];
		Random random = new Random();
		for(int i=0;i<vertexCount;i++) {
			for(int j=0;j<i;j++) {
				graph[i][j] = graph[j][i] = random.nextInt(2);
			}
		}
		return graph;
	}
	
	public static int[][] genRandomUndirectedWeightedGraph(int vertexCount) {
		if(vertexCount <= 0) {
			return null;
		}
		
		int[][] graph = new int[vertexCount][vertexCount];
		Random random = new Random();
		for(int i=0;i<vertexCount;i++) {
			for(int j=0;j<i;j++) {
				if(random.nextBoolean()) {
					graph[i][j] = graph[j][i] = random.nextInt(100);
				} else {
					graph[i][j] = graph[j][i] = Integer.MAX_VALUE;
				}
			}
		}
		return graph;
	}
}
