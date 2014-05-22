package criticalpath;

import java.util.Stack;

public class CriticalPath {
	void criticalPath(int[][] map, int vexnum) {
		int[] ve = new int[vexnum];
		int[] vl = new int[vexnum];
		Stack<Integer> stack = new Stack<Integer>();
		if (!topologicalOrder(map, ve, stack)) {
			System.out.println("该图为有向有环图");
			return;
		}
		for (int i = 0; i < vexnum; i++)
			vl[i] = ve[vexnum - 1];
		while (!stack.empty()) {
			int temp = stack.pop();
			for (int i = 0; i < vexnum; i++) {
				if (i != temp && map[temp][i] != Integer.MAX_VALUE
						&& vl[i] - map[temp][i] < vl[temp])
					vl[temp] = vl[i] - map[temp][i];
			}
		}
		System.out.print("关键路径：v0,");
		for (int i = 0; i < vexnum;) {
			for (int j = 0; j < vexnum; j++) {
				if (j != i) {
					int e = ve[i];
					int l = vl[j] - map[i][j];
					if (e == l) {
						System.out.print("v" + j + ",");
						i = j;
						break;
					}
				}
			}
		}
	}

	private boolean topologicalOrder(int[][] map, int[] ve, Stack<Integer> s) {
		int vexnum = ve.length;
		int indegree[] = new int[vexnum];
		int count = 0;
		Stack<Integer> stack = new Stack<Integer>();
		findInDegree(map, indegree, stack);
		while (!stack.empty()) {
			int temp = stack.pop();
			s.push(temp);
			count++;
			for (int i = 0; i < vexnum; i++) {
				if (i != temp && map[temp][i] != Integer.MAX_VALUE) {
					if (--indegree[i] == 0)
						stack.push(i);
					if (ve[temp] + map[temp][i] > ve[i])
						ve[i] = ve[temp] + map[temp][i];
				}
			}
		}
		if (count < vexnum)
			return false;
		else
			return true;
	}

	private void findInDegree(int[][] map, int[] indegree, Stack<Integer> stack) {
		for (int i = 0; i < indegree.length; i++) {
			int sum = 0;
			for (int j = 0; j < indegree.length; j++) {
				if (j != i && map[j][i] != Integer.MAX_VALUE)
					sum++;
			}
			indegree[i] = sum;
			if (sum == 0)
				stack.push(i);
		}
	}
}
