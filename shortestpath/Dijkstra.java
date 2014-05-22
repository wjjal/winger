package shortestpath;

public class Dijkstra {
	void ShortestPath_DIJ(int[][] map, int vexnum, int start) {
		boolean[] point = new boolean[vexnum];
		int[] distance = new int[vexnum];
		int prev[] = new int[vexnum];
		point[start] = true;
		for (int i = 0; i < vexnum; i++) {
			distance[i] = map[start][i];
			prev[i] = start;
		}
		for (int i = 1; i < vexnum; i++) {
			int min = Integer.MAX_VALUE;
			int temp = -1;
			for (int j = 0; j < vexnum; j++) {
				if (!point[j])
					if (distance[j] <= min) {
						temp = j;
						min = distance[j];
					}
			}
			point[temp] = true;
			int next = temp;
			if (min != Integer.MAX_VALUE) {
				System.out.print("v" + temp + ":(" + "v" + temp + ",");
				while (prev[temp] != start) {
					System.out.print("v" + prev[temp] + ",");
					temp = prev[temp];
				}
				System.out.println("v" + start + ")-" + min);
			}else
				System.out.println("v"+ temp + ":" + "unreachable");
			for (int j = 0; j < vexnum; j++) {
				if (!point[j] && map[next][j] != Integer.MAX_VALUE
						&& distance[next] + map[next][j] < distance[j]) {
					distance[j] = distance[next] + map[next][j];
					prev[j] = next;
				}
			}
		}
	}
}
