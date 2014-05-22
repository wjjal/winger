package minispantree;

public class Prim {
	private class assist {
		int adjvex;
		int lowcost;

		private assist(int adjvex, int lowest) {
			this.adjvex = adjvex;
			this.lowcost = lowest;
		}
	}

	void miniSpanTree_PRIM(int[][] map, int vexnum) {
		//int sum = 0;
		assist[] v = new assist[vexnum];
		for (int i = 0; i < vexnum; i++)
			v[i] = new assist(0, map[0][i]);
		for (int i = 1; i < vexnum; i++) {
			int min = Integer.MAX_VALUE;
			int point = 0;
			for (int j = 0; j < vexnum; j++) {
				if (v[j].lowcost > 0 && v[j].lowcost < min) {
					min = v[j].lowcost;
					point = j;
				}
			}
			v[point].lowcost = 0;
			//sum += min;
			System.out.print("(" + v[point].adjvex + "," + point + "),");
			for (int j = 0; j < vexnum; j++) {
				if (map[j][point] != 0 && map[j][point] < v[j].lowcost)
					v[j] = new assist(point, map[j][point]);
			}
		}
	}
}
