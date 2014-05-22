package topologicalsort;

public class Test {
	static final int max = Integer.MAX_VALUE;

	public static void main(String[] args) {
		int vexnum = 6;
		int[][] map = { { 0, 1, 1, 1, max, max },
				{ max, 0, max, max, max, max }, { max, 1, 0, max, 1, max },
				{ max, max, max, 0, 1, max }, { max, max, max, max, 0, max },
				{ max, max, max, 1, 1, 0 } };
		new TopologicalSort().topologicalSort(map, vexnum);
	}
}
