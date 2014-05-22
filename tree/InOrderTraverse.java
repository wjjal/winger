package tree;

import java.util.Stack;

public class InOrderTraverse {

	public static void inOrderTraverse(Node root) {
		// TODO Auto-generated method stub
		Stack<Node> stack = new Stack<Node>();
		Node node = root;
		while (node!=null || !stack.empty()) {
           if(node!=null){
        	   stack.push(node);
        	   node = node.lchild;
           }else{
        	   node = stack.pop();
        	   node.visit();
        	   node = node.rchild;
           }
		}
	}

}
