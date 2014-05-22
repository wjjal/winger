package shortestpath;

public class Test {
	static final int max = Integer.MAX_VALUE;

	public static void main(String[] args) {
		int vexnum1 = 6;
		int[][] map1 = { { 0, max, 10, max, 30, 100 },
				{ max, 0, 5, max, max, max }, { max, max, 0, 50, max, max },
				{ max, max, max, 0, max, 10 }, { max, max, max, 20, 0, 60 },
				{ max, max, max, max, max, 0 } };
		new Dijkstra().ShortestPath_DIJ(map1, vexnum1, 0);
		
		int vexnum2 = 3;
		int[][] map2 = {{0,4,11},{6,0,2},{3,max,0}};
		new Floyd().ShortestPath_FLOYD(map2, vexnum2);
	}
}
