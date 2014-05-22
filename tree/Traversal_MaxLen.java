package tree;

public class Traversal_MaxLen {
	static int maxLength;

	public static int traversal_MaxLen(Node node) {
		if (node == null) {
			return 0;
		}
		if (node.lchild == null)
			node.maxLeft = 0;
		else {
			traversal_MaxLen(node.lchild);
			// 左子树上的，某一节点，往左边大，还是往右边大
			if (node.lchild.maxLeft > node.lchild.maxRight) {
				node.maxLeft = node.lchild.maxLeft + 1;
			} else {
				node.maxLeft = node.lchild.maxRight + 1;
			}
		}
		if (node.rchild == null) {
			node.maxRight = 0;
		} else {
			traversal_MaxLen(node.rchild);
			// 右子树上的，某一节点，往左边大，还是往右边大
			if (node.rchild.maxLeft > node.rchild.maxRight) {
				node.maxRight = node.rchild.maxLeft + 1;
			} else {
				node.maxRight = node.rchild.maxRight + 1;
			}
		}
		if (node.maxLeft + node.maxRight > 0) {
			maxLength = node.maxLeft + node.maxRight;
		}
		return maxLength;
	}
}
