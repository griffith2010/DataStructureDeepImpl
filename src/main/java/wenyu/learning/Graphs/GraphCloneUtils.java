package wenyu.learning.Graphs;

public class GraphCloneUtils {
	public static int[][] clone(int[][] graphMatrix) {
		if(graphMatrix==null || graphMatrix.length==0 || graphMatrix[0].length==0) {
			return null;
		}
		
		int[][] cloneMatrix = new int[graphMatrix.length][graphMatrix[0].length];
		for(int i=0; i<graphMatrix.length; i++) {
			for(int j=0; j<graphMatrix[i].length; j++) {
				cloneMatrix[i][j] = graphMatrix[i][j];
			}
		}
		
		return cloneMatrix;
	}
	
	
	public static AdjListNode[] clone(AdjListNode[] graphList) {
		AdjListNode[] clone = new AdjListNode[graphList.length];
		
		// Step 1: clone all adjacent list node and put them in as the first adjacent of original node
		for(int i=0; i<graphList.length; i++) {
			AdjListNode cloneVertex = new AdjListNode(graphList[i].name);
			graphList[i].adjacencies.add(0, cloneVertex);
			for(Integer weight : graphList[i].edgesWeight) {
				cloneVertex.edgesWeight.add(weight.intValue());
			}
			clone[i] = cloneVertex;
		}
		
		// Step 2: add adjacents for clone vertice
		for(int i=0; i<graphList.length; i++) {
			AdjListNode oriVertex = graphList[i];
			AdjListNode cloneVertex = oriVertex.adjacencies.get(0);
			
			for(int j=1; j<oriVertex.adjacencies.size(); j++) {
				AdjListNode adj = graphList[i].adjacencies.get(j);
				cloneVertex.adjacencies.add(adj.adjacencies.get(0));
			}
		}
		
		// Step 3: remove clone vertice
		for(int i=0; i<graphList.length; i++) {
			graphList[i].adjacencies.remove(0);
		}
		
		return clone;
	}
	
	public static void main(String[] args) {
		AdjListNode[] graphList = AdjacentListUtils.genRandomUndirectedGraph(10);
		AdjacentListUtils.printGraph(graphList);
		
		System.out.println("=====================================");
		
		AdjListNode[] clone = clone(graphList);
		AdjacentListUtils.printGraph(clone);
	}
}
