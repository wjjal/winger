package tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree_level_traverse {
	static void tree_level_traverse(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		if (node == null)
			return;
		queue.add(node);
		while (!queue.isEmpty()) {
			node = queue.poll();
			node.visit();
			if (node.lchild != null)
				queue.add(node.lchild);
			if (node.rchild != null)
				queue.add(node.rchild);
		}
	}
}
