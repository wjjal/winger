package tree;

public class Test {
	public static void main(String args[]) {
		Node nodea = new Node("a");
		Node nodeb = new Node("b");
		Node nodec = new Node("c");
		Node noded = new Node("d");
		Node nodee = new Node("e");
		Node nodef = new Node("f");
		Node node1 = new Node("-", nodec, noded);
		Node node2 = new Node("*", nodeb, node1);
		Node node3 = new Node("+", nodea, node2);
		Node node4 = new Node("/", nodee, nodef);
		Node root = new Node("-", node3, node4);
		System.out.println("树的深度：");
		System.out.println(TreeDepth.treeDepth(root));
		System.out.print("前序遍历：");
		PreOrderTraverse.preOrderTraverse(root);
		System.out.println();
		MorrisPreOrderTraverse.morrisPreOrderTraverse(root);
		System.out.println();
		System.out.print("中序遍历:");
		InOrderTraverse.inOrderTraverse(root);
		System.out.println();
		MorrisInOrderTraverse.morrisInOrderTraverse(root);
		System.out.println();
		System.out.print("后序遍历:");
		EndOrderTraverse.endOrderTraverse(root);
		System.out.println();
		MorrisEndOrderTraverse.morrisEndOrderTraverse(root);
		System.out.println();
		System.out.print("层次遍历:");
		Tree_level_traverse.tree_level_traverse(root);
		System.out.println();
		System.out.println("二叉树中节点的最大距离");
		System.out.println(Traversal_MaxLen.traversal_MaxLen(root));
	}
}
