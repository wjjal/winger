package trie_tree;

import java.util.Scanner;

public class TrieTree {
	Node root;

	private TrieTree() {
		root = new Node();
	}

	// 创建一棵仅有根节点的字典树
	public static TrieTree Create_TrieTree() {
		TrieTree t = new TrieTree();
		return t;
	}

	// 插入字符串str到字典树pTree中
	public void Insert_TrieTree(String str) {
		Node p = this.root;
		char[] cs = str.toCharArray();
		for (char c : cs) {
			int id = c - 'a';
			if (p.children[id] == null)
				p.children[id] = new Node();
			p.children[id].count++;
			p = p.children[id];
		}
	}

	// 统计字典树pTree中以str为前缀的字符串的数目
	public int Count_TrieTree(String str) {
		Node p = this.root;
		char[] cs = str.toCharArray();
		for (char c : cs) {
			int id = c - 'a';
			if (p.children[id] == null)
				return 0;
			else
				p = p.children[id];
		}
		return p.count;
	}

	public static void main(String[] args) {
		TrieTree t = TrieTree.Create_TrieTree();
		t.Insert_TrieTree("banana");
		t.Insert_TrieTree("band");
		t.Insert_TrieTree("acm");
		t.Insert_TrieTree("beef");
		t.Insert_TrieTree("absoluate");
		System.out.println("Please input the prefix string:");
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		System.out.println("The num of strings starts with " + s + " :"
				+ t.Count_TrieTree(s));
		 s = scanner.nextLine();
		System.out.println("The num of strings starts with " + s + " :"
				+ t.Count_TrieTree(s));
		s = scanner.nextLine();
		System.out.println("The num of strings starts with " + s + " :"
				+ t.Count_TrieTree(s));
		scanner.close();
	}
}
