package topologicalsort;

import java.util.Stack;

public class TopologicalSort {
	void topologicalSort(int[][] map, int vexnum) {
		int indegree[] = new int[vexnum];
		int count = 0;
		Stack<Integer> stack = new Stack<Integer>();
		findInDegree(map, indegree, stack);
		while (!stack.empty()) {
			int temp = stack.pop();
			System.out.print("v" + temp + ",");
			count++;
			for (int i = 0; i < vexnum; i++) {
				if (i != temp && map[temp][i] != Integer.MAX_VALUE)
					if(--indegree[i]==0)
						stack.push(i);
			}
		}
		System.out.println();
		if(count<vexnum)
			System.out.println("该有向图有环路");
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
