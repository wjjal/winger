package tree;

import java.util.Stack;

public class PreOrderTraverse {
	public static void preOrderTraverse(Node root) {
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while (!stack.empty()) {
			Node node = stack.pop();
			node.visit();
			if (node.rchild != null)
				stack.push(node.rchild);
			if (node.lchild != null)
				stack.push(node.lchild);
		}
	}
}
