package criticalpath;

import topologicalsort.TopologicalSort;

public class Test {
	static final int max = Integer.MAX_VALUE;

	public static void main(String[] args) {
		int vexnum = 6;
		int[][] map = { { 0, 3, 2, max, max, max },
				{ max, 0, max, 2, 3, max }, { max, max, 0, 4, max, 3 },
				{ max, max, max, 0, max, 2 }, { max, max, max, max, 0, 1 },
				{ max, max, max, max, max, 0 } };
		new CriticalPath().criticalPath(map, vexnum);
	}
}
