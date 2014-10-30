package wenyu.learning.Tree.BinaryTree;

/*
 * Calculate the distance of given two nodes
 * Logic:
 * 	1. First find the lowest common ancestor
 *  2. calculate the distance between root and ancestor (distance1)
 *  3. calculate the distance between root and child1 (distance2)
 *  4. calculate the distance between root and child2 (distance3)
 *  5. distance is distance2+distance3-2*distance1
 */
public class DistanceOfTwoNodes {
	public static <E> int getDistance(BinaryTreeNode<E> root, BinaryTreeNode<E> child1, BinaryTreeNode<E> child2) throws Exception {
		BinaryTreeNode<E> ancestor = LowestCommonAncestor.LCA(root, child1, child2);
		if(ancestor == null) {
			throw new Exception("Didn't find common ancestor");
		}
		
		int distanceOfAncestor = NodeDepthCalculator.calculateDepth(root, ancestor);
		int distanceOfChild1 = NodeDepthCalculator.calculateDepth(root, child1);
		if(distanceOfChild1==-1) {
			throw new Exception("Didn't find child1");
		}
		int distanceOfChild2 = NodeDepthCalculator.calculateDepth(root, child2);
		if(distanceOfChild2==-1) {
			throw new Exception("Didn't find child2");
		}
		
		return distanceOfChild1 + distanceOfChild2 - 2*distanceOfAncestor;
	}
	
	public static void main(String[] args) throws Exception {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> child1 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> child2 = new BinaryTreeNode<Integer>(8);
		BinaryTreeNode<Integer> child3 = new BinaryTreeNode<Integer>(9);
		
		root.left = new BinaryTreeNode<Integer>(2);
		root.left.right = child1;
		root.right = new BinaryTreeNode<Integer>(5);
		root.right.left = child2;
		root.right.right = new BinaryTreeNode<Integer>(6);
		root.right.right.left = child3;
		
//		root.left = new TreeNode(2);
//		root.right = child1;
//		root.right.left = child2;
//		root.right.right = new TreeNode(6);
//		root.right.right.left = child3;

		int distance = getDistance(root, child3, child1);
		System.out.println(distance);
		
	}

}
