package minispantree;

public class Test {
	static final int max = Integer.MAX_VALUE;

	public static void main(String[] args) {
		int vexnum = 6;
		int[][] map = { { 0, 6, 1, 5, max, max }, { 6, 0, 5, max, 3, max },
				{ 1, 5, 0, 5, 6, 4 }, { 5, max, 5, 0, max, 2 },
				{ max, 3, 6, max, 0, 6 }, { max, max, 4, 6, 2, 0 } };
        new Prim().miniSpanTree_PRIM(map, vexnum);
        
		Graph g = new Graph();
		int[][] edge = {{0,1,6},{0,2,1},{0,3,5},{1,2,5},{1,4,3},{2,3,5},{2,4,6},{2,5,4},{3,5,2},{4,5,6}}; 
		g.add(edge);
		new Kruskal().miniSpanTree_KRUSKAL(g,vexnum);
	}
}
