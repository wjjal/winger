package tree;

import java.util.Stack;

public class EndOrderTraverse {

	public static void endOrderTraverse(Node root) {
		// TODO Auto-generated method stub
		Stack<Node> stack = new Stack<Node>();
		Node node = root;
		while (node != null || !stack.empty()) {
			if (node != null) {
				stack.push(node);
				node = node.lchild;
			} else {
				// flag若为true，表示已访问过其右子树
				if (stack.peek().flag) {
					node = stack.pop();
					node.visit();
					node = null;
				}
				// flag若为false，表示尚未访问其右子树，默认为false
				else {
					node = stack.peek();
					node.flag = true;
					node = node.rchild;
				}
				// if (node.rchild == null) {
				// // node为叶子节点
				// stack.pop();
				// while (!stack.empty() && stack.peek().rchild.equals(node)) {
				// node.visit();
				// node = stack.pop();
				// }
				// node.visit();
				// if (node.equals(root)) {
				// return;
				// } else {
				// node = stack.peek().rchild;
				// }
				// } else {
				// node = node.rchild;
				// }
			}
		}
	}
}
