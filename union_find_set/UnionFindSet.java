package union_find_set;

public class UnionFindSet<T> {
	UnionFindSet<T> parent;
	int rank;
	T data;

	UnionFindSet() {
		
	}
	
	UnionFindSet(T e) {
		data = e;
		parent = this;
		rank = 0;
	}

	public static UnionFindSet find(UnionFindSet x) {
		if (x.parent.equals(x))
			return x;
		else {
			x.parent = find(x.parent);
		}
		return x.parent;
	}

	public static void union(UnionFindSet x, UnionFindSet y) {
		UnionFindSet xRoot = find(x);
		UnionFindSet yRoot = find(y);
		if (xRoot.equals(yRoot))
			return;
		if (xRoot.rank < yRoot.rank)
			xRoot.parent = yRoot;
		else if (xRoot.rank > yRoot.rank)
			yRoot.parent = xRoot;
		else {
			yRoot.parent = xRoot;
			xRoot.rank++;
		}
	}

	public static boolean same(UnionFindSet x, UnionFindSet y) {
		if (find(x).equals(find(y)))
			return true;
		else
			return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o instanceof UnionFindSet)
			return (this.data.equals(((UnionFindSet) o).data));
		else
			return false;
	}
	
	@Override
	public int hashCode(){
		return data.hashCode();	
	}
	
	@Override
	public String toString(){
		return data.toString();	
	}
}
