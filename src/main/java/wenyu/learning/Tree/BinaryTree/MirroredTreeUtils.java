package wenyu.learning.Tree.BinaryTree;

import java.util.Arrays;
import java.util.Random;

public class MirroredTreeUtils {
	
	public static <E> boolean verifyTwoTreesAreMirrored(BinaryTreeNode<E> node1, BinaryTreeNode<E> node2) {
		if(node1==null && node2==null) {
			return true;
		} else if((node1==null&&node2!=null) || (node1!=null&&node2==null) || (node1.value!=node2.value)){
			return false;
		}
		
		boolean result = verifyTwoTreesAreMirrored(node1.left, node2.right);
		if(result) {
			result = verifyTwoTreesAreMirrored(node1.right, node2.left);
		}
		
		return result;
	}
	
	public static <E> void generateMirroredTree(BinaryTreeNode<E> node, BinaryTreeNode<E> mirroredNode) {
		if(node == null) {
			return;
		}
		mirroredNode.value = node.value;
		
		if(node.left!=null) {
			mirroredNode.right = new BinaryTreeNode<E>();
			generateMirroredTree(node.left, mirroredNode.right);
		}
		
		if(node.right!=null) {
			mirroredNode.left = new BinaryTreeNode<E>();
			generateMirroredTree(node.right, mirroredNode.left);
		}
	}
	
	public static void main(String[] args) {
		int treeNodeCount = 20;
		Integer[] values = new Integer[treeNodeCount];
		Random random = new Random();
		for(int i=0;i<treeNodeCount;i++) {
			values[i] = random.nextInt(100);
		}
		Arrays.sort(values);
		BinaryTreeNode<Integer> root = BSTUtils.genFromSortedArr(values, 0, values.length-1);
		TraversalBFS.traversalWithLine(root, null);
		
		//=======================================================================
		System.out.println("======================================================");
		
		BinaryTreeNode<Integer> mirroredTree = new BinaryTreeNode<Integer>();
		generateMirroredTree(root, mirroredTree);
		TraversalBFS.traversalWithLine(mirroredTree, null);

		//=======================================================================
		System.out.println("======================================================");
		if(verifyTwoTreesAreMirrored(root, mirroredTree)) {
			System.out.println("Two trees are mirrored.");
		} else {
			System.out.println("Two trees are not mirrored.");
		}
	}

}
