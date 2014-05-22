package shortestpath;

import java.util.ArrayList;
import java.util.List;

public class Floyd {
	void ShortestPath_FLOYD(int[][] map, int vexnum) {
		int[][] distance = new int[vexnum][vexnum];
		@SuppressWarnings("unchecked")
		List<Integer>[][] pass = new ArrayList[vexnum][vexnum];
		for (int i = 0; i < vexnum; i++) {
			for (int j = 0; j < vexnum; j++) {
				pass[i][j] = new ArrayList<Integer>();
				distance[i][j] = map[i][j];
				if (distance[i][j] < Integer.MAX_VALUE) {
					pass[i][j].add(i);
					pass[i][j].add(j);
				}
			}
		}
		for (int k = 0; k < vexnum; k++) {
			for (int i = 0; i < vexnum; i++) {
				for (int j = 0; j < vexnum; j++) {
					if (distance[i][k] + distance[k][j] < distance[i][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
						merge(pass[i][j], pass[i][k], pass[k][j]);
					}
				}
			}
		}
		for (int i = 0; i < vexnum; i++) {
			for (int j = 0; j < vexnum; j++) {
				if (i == j)
					continue;
				System.out.print("v" + i + "-v" + j + ":(");
				for (int k = 0; k < pass[i][j].size(); k++)
					System.out.print("v" + pass[i][j].get(k) + ",");
				System.out.println(")-" + distance[i][j]);
			}
		}
	}

	private void merge(List<Integer> tr, List<Integer> srb, List<Integer> sre) {
		tr.clear();
		tr.addAll(srb);
		tr.remove(tr.size() - 1);
		tr.addAll(sre);
	}
}
