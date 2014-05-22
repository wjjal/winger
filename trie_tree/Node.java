package trie_tree;

public class Node {
	private final int max = 26;
    Node[] children;
    int count; //以从根节点到当前节点的字符串为公共前缀的字符串数目  
    
    Node(){
    	children = new Node[max];
    	count = 0;
    }
}
