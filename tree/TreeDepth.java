package tree;

public class TreeDepth {
	public static int treeDepth(Node root) {
        if(root==null)
        	return 0;
        int left = treeDepth(root.lchild);
        int right = treeDepth(root.rchild);
		return (left>=right)?(left+1):(right+1);
	}
}
