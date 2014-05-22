package avl_tree;

public class AVLTree<T extends Comparable> {
	private static final int LH = 1; // 左高
	private static final int EH = 0; // 等高
	private static final int RH = -1; // 右高
	private Node root;

	public AVLTree() {
		root = null;
	}

	public class Node {
		Node parent;
		Node left;
		Node right;
		int balance;
		Object data;

		public Node(Object data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public String toString() {
			return "[data=" + data + "]";
		}

		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj.getClass() == Node.class) {
				Node target = (Node) obj;
				return data.equals(target.data) && left == target.left
						&& right == target.right;
			}
			return false;
		}
	}

	public void insert(T ele) {
		if (root == null)
			root = new Node(ele, null, null);
		else {
			Node current = root;
			Node pre = null;
			int cmp = 0;
			while (current != null) {
				cmp = ele.compareTo(current.data);
				pre = current;
				if (cmp < 0)
					current = current.left;
				else if (cmp > 0)
					current = current.right;
				else
					return;
			}
			Node newNode = new Node(ele, null, null);
			current = pre;
			if (cmp > 0)
				current.right = newNode;
			else
				current.left = newNode;
			newNode.parent = current;
			while (current != null) {
				cmp = ele.compareTo(current.data);
				if (cmp < 0)
					current.balance++;
				else
					current.balance--;
				if (current.balance == 0)
					break;
				if (Math.abs(current.balance) == 2) {
					fixAfterInsertion(current);
					break;
				}
				current = current.parent;
			}
		}
	}
	
	private void deleteEntry(Node p){   
        //如果p左右子树都不为空，找到其直接后继，替换p，之后p指向s，删除p其实是删除s  
        //所有的删除左右子树不为空的节点都可以调整为删除左右子树有其一不为空，或都为空的情况。  
        if (p.left != null && p.right != null) {  
             Node s = successor(p);  
             p.data = s.data;  
             p = s;  
        }  
        Node replacement = (p.left != null ? p.left : p.right);  
  
        if (replacement != null) {      //如果其左右子树有其一不为空  
            replacement.parent = p.parent;  
            if (p.parent == null)   //如果p为root节点  
                root = replacement;  
            else if (p == p.parent.left)    //如果p是左孩子  
                p.parent.left  = replacement;     
            else                            //如果p是右孩子  
                p.parent.right = replacement;  
  
            p.left = p.right = p.parent = null;     //p的指针清空  
              
            //这里更改了replacement的父节点，所以可以直接从它开始向上回溯  
            fixAfterDeletion(replacement);    
  
        } else if (p.parent == null) { // 如果全树只有一个节点  
            root = null;  
        } else {  //左右子树都为空  
            fixAfterDeletion(p);    //这里从p开始回溯  
            if (p.parent != null) {  
                if (p == p.parent.left)  
                    p.parent.left = null;  
                else if (p == p.parent.right)  
                    p.parent.right = null;  
                p.parent = null;  
            }  
        }     
    }  
	
	/** 
     * 调整的方法： 
     * 1.当最小不平衡子树的根(以下简称R)为2时，即左子树高于右子树： 
     * 如果R的左子树的根节点的BF为1时，做右旋； 
     * 如果R的左子树的根节点的BF为-1时，先左旋然后再右旋 
     *  
     * 2.R为-2时，即右子树高于左子树： 
     * 如果R的右子树的根节点的BF为1时，先右旋后左旋 
     * 如果R的右子树的根节点的BF为-1时，做左旋 
     *  
     * 至于调整之后，各节点的BF变化见代码 
     */  
	private void fixAfterInsertion(Node p) {
		if (p.balance == 2) {
			leftBalance(p);
		}
		if (p.balance == -2) {
			rightBalance(p);
		}
	}
	
	 /** 
     * 删除某节点p后的调整方法： 
     * 1.从p开始向上回溯，修改祖先的BF值，这里只要调整从p的父节点到根节点的BF值， 
     * 调整原则为，当p位于某祖先节点(简称A)的左子树中时，A的BF减1，当p位于A的 
     * 右子树中时A的BF加1。当某个祖先节点BF变为1或-1时停止回溯，这里与插入是相反的， 
     * 因为原本这个节点是平衡的，删除它的子树的某个节点并不会改变它的高度 
     *  
     * 2.检查每个节点的BF值，如果为2或-2需要进行旋转调整，调整方法如下文， 
     * 如果调整之后这个最小子树的高度降低了，那么必须继续从这个最小子树的根节点(假设为B)继续 
     * 向上回溯，这里和插入不一样，因为B的父节点的平衡性因为其子树B的高度的改变而发生了改变， 
     * 那么就可能需要调整，所以删除可能进行多次的调整。 
     *  
     */  
	private void fixAfterDeletion(Node p){  
        boolean heightLower = true;     //看最小子树调整后，它的高度是否发生变化，如果减小，继续回溯  
        Node t = p.parent;  
        T e = (T) p.data; 
        int cmp;  
        //自下向上回溯，查找不平衡的节点进行调整  
		while (t != null && heightLower) {
            cmp = e.compareTo(t.data);  
            /** 
             * 删除的节点是右子树，等于的话，必然是删除的某个节点的左右子树不为空的情况 
             * 例如：                     10 
             *          /    \ 
             *         5     15 
             *       /   \ 
             *      3    6  
             * 这里删除5，是把6的值赋给5，然后删除6，这里6是p，p的父节点的值也是6。 
             * 而这也是右子树的一种 
             */   
            if(cmp >= 0 ){     
                t.balance ++;  
            } else{  
                t.balance --;  
            }  
            if(Math.abs(t.balance) == 1){   //父节点经过调整平衡因子后，如果为1或-1，说明调整之前是0，停止回溯。  
                break;  
            }  
            Node r = t;  
            //这里的调整跟插入一样  
            if(t.balance == 2){  
                heightLower = leftBalance(r);  
            }else if(t.balance==-2){  
                heightLower = rightBalance(r);  
            }  
            t = t.parent;  
        }  
    }  
	
	 /** 
     * 做左平衡处理 
     * 平衡因子的调整如图： 
     *         t                       rd 
     *       /   \                   /    \ 
     *      l    tr   左旋后右旋                     l       t 
     *    /   \       ------->    /  \    /  \ 
     *  ll    rd                ll   rdl rdr  tr 
     *       /   \ 
     *     rdl  rdr 
     *      
     *   情况2(rd的BF为0) 
     *  
     *    
     *         t                       rd 
     *       /   \                   /    \ 
     *      l    tr   左旋后右旋                      l       t 
     *    /   \       ------->    /  \       \ 
     *  ll    rd                ll   rdl     tr 
     *       /    
     *     rdl   
     *      
     *   情况1(rd的BF为1) 
     *   
     *    
     *         t                       rd 
     *       /   \                   /    \ 
     *      l    tr   左旋后右旋                     l       t 
     *    /   \       ------->    /       /  \ 
     *  ll    rd                ll       rdr  tr 
     *           \ 
     *          rdr 
     *      
     *   情况3(rd的BF为-1) 
     *  
     *    
     *         t                         l 
     *       /       右旋处理                                     /    \ 
     *      l        ------>          ll     t 
     *    /   \                             / 
     *   ll   rd                           rd 
     *   情况4(L等高) 
     */  
	private boolean leftBalance(Node t) {
		boolean heightLower = true;
		Node l = t.left;
		//Node r = t.right;
		switch (l.balance) {
		// 旋转前，h(t)=x+2,h(l)=x+1,h(r)=x,h(l.right)=x,
		// 旋转后h(t.left)=h(t.right)=x,h(l.left)=h(l.right)=x+1
		case LH: // 左高，右旋调整,旋转后树的高度减小
			t.balance = l.balance = EH;
			rotateRight(t);
			break;
		case RH: // 右高，分情况调整                                            
            Node rd = l.right;  
            switch (rd.balance) {   //调整各个节点的BF  
            case LH:    //情况1  
                t.balance = RH;  
                l.balance = EH;  
                break;  
            case EH:    //情况2  
                t.balance = l.balance = EH;  
                break;  
            case RH:    //情况3  
                t.balance = EH;  
                l.balance = LH;  
                break;  
            }  
            rd.balance = EH;  
            rotateLeft(t.left);  
            rotateRight(t);  
            break;  
        case EH:      //特殊情况4,这种情况在添加时不可能出现，只在移除时可能出现，旋转之后整体树高不变  
            l.balance = RH;  
            t.balance = LH;  
            rotateRight(t);  
            heightLower = false;  
            break;  
        }  
        return heightLower;  
	}
	
	 /** 
     * 做右平衡处理 
     * 平衡因子的调整如图： 
     *           t                               ld 
     *        /     \                          /     \ 
     *      tl       r       先右旋再左旋     t       r 
     *             /   \     -------->      /   \    /  \ 
     *           ld    rr                 tl   ldl  ldr rr 
     *          /  \ 
     *       ldl  ldr 
     *       情况2(ld的BF为0) 
     *        
     *           t                               ld 
     *        /     \                          /     \ 
     *      tl       r       先右旋再左旋                           t       r 
     *             /   \     -------->      /   \       \ 
     *           ld    rr                 tl   ldl      rr 
     *          /   
     *       ldl   
     *       情况1(ld的BF为1) 
     *        
     *           t                               ld 
     *        /     \                          /     \ 
     *      tl       r       先右旋再左旋                            t       r 
     *             /   \     -------->      /        /  \ 
     *           ld    rr                 tl        ldr rr 
     *             \ 
     *             ldr 
     *       情况3(ld的BF为-1) 
     *        
     *           t                                  r 
     *             \          左旋                                                   /   \ 
     *              r        ------->           t      rr      
     *            /   \                          \ 
     *           ld   rr                         ld 
     *        情况4(r的BF为0)    
     */  
	private boolean rightBalance(Node t) {
		boolean heightLower = true;
		Node r = t.right;
		switch (r.balance) {
		case LH: // 左高，分情况调整
			Node ld = r.left;
			switch (ld.balance) { // 调整各个节点的BF
			case LH: // 情况1
				t.balance = EH;
				r.balance = RH;
				break;
			case EH: // 情况2
				t.balance = r.balance = EH;
				break;
			case RH: // 情况3
				t.balance = LH;
				r.balance = EH;
				break;
			}
			ld.balance = EH;
			rotateRight(t.right);
			rotateLeft(t);
			break;
		case RH: // 右高，左旋调整
			t.balance = r.balance = EH;
			rotateLeft(t);
			break;
		case EH: // 特殊情况4
			r.balance = LH;
			t.balance = RH;
			rotateLeft(t);
			heightLower = false;
			break;
		}
		return heightLower;
	}

	public Node getNode(T ele) {
		Node p = root;
		while (p != null) {
			int cmp = ele.compareTo(p.data);
			if (cmp < 0)
				p = p.left;
			else if (cmp > 0)
				p = p.right;
			else
				return p;
		}
		return null;
	}
	
	Node successor(Node t) {
		if (t == null)
			return null;
		else if (t.right != null) { // 往右，然后向左直到尽头
			Node p = t.right;
			while (p.left != null)
				p = p.left;
			return p;
		} else { // right为空，如果t是p的左子树，则p为t的直接后继
			Node p = t.parent;
			Node ch = t;
			while (p != null && ch == p.right) { // 如果t是p的右子树，则继续向上搜索其直接后继
				ch = p;
				p = p.parent;
			}
			return p;
		}
	}

	private void rotateLeft(Node p) {
		if (p != null) {
			Node r = p.right;
			Node q = r.left;
			p.right = q;
			if (q != null)
				q.parent = p;
			r.parent = p.parent;
			if (p.parent == null)
				root = r;
			else if (p.parent.left == p)
				p.parent.left = r;
			else
				p.parent.right = r;
			r.left = p;
			p.parent = r;
		}
	}

	private void rotateRight(Node p) {
		if (p != null) {
			Node l = p.left;
			Node q = l.right;
			p.left = q;
			if (q != null)
				q.parent = p;
			l.parent = p.parent;
			if (p.parent == null)
				root = l;
			else if (p.parent.left == p)
				p.parent.left = l;
			else
				p.parent.right = l;
			l.right = p;
			p.parent = l;
		}
	}
	
	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}
	
	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
		System.out.println();
		printTree2(root);
	}

	public void printTree(Node t) {
		if (t != null) {
			printTree(t.left);
			System.out.print(t.data + " ");
			printTree(t.right);
		}
	}
	
	public void printTree2(Node t) {
		if (t != null) {
			System.out.print(t.data + " ");
			printTree2(t.left);
			printTree2(t.right);
		}
	}
	
	public static void main(String[] args) {
		AVLTree<Integer> t = new AVLTree<Integer>();
		final int NUMS = 10;
		final int GAP = 3;
		for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
			// System.out.print(i + " ");
			t.insert(i);
			t.printTree();
			System.out.println();
		}
		t.printTree();
		System.out.println();
        AVLTree.Node p = t.getNode(3);
		t.deleteEntry(p);
		t.printTree();
//		t.delete(5);
//		System.out.println();
//		t.printTree();
//		t.delete(4);
//		System.out.println();
//		t.printTree();
	}
}
