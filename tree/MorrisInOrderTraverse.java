//中序遍历
//步骤：
//
//1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
//
//2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
//
//   a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
//
//   b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
//
//3. 重复以上1、2直到当前节点为空。

package tree;

public class MorrisInOrderTraverse {
	public static void morrisInOrderTraverse(Node root) {
		Node cur = root;
		Node prev = null;
		while (cur != null) {
			if (cur.lchild == null) {
				cur.visit();
				cur = cur.rchild;
			} else {
				prev = cur.lchild;
				while (prev.rchild != null && prev.rchild != cur)
					prev = prev.rchild;
				if (prev.rchild == null) {
					prev.rchild = cur;
					cur = cur.lchild;
				} else {
					cur.visit();
					prev.rchild = null;
					cur = cur.rchild;
				}
			}
		}
	}
}
