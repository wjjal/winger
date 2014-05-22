package algorithm;

//n支队伍比赛，分别编号为0，1，2。。。。n-1，已知它们之间的实力对比关系，
//存储在一个二维数组w[n][n]中，w[i][j] 的值代表编号为i，j的队伍中更强的一支。
//所以w[i][j]=i 或者j，现在给出它们的出场顺序，并存储在数组order[n]中，
//比如order[n] = {4,3,5,8,1......}，那么第一轮比赛就是 4对3， 5对8。.......
//胜者晋级，败者淘汰，同一轮淘汰的所有队伍排名不再细分，即可以随便排，
//下一轮由上一轮的胜者按照顺序，再依次两两比，比如可能是4对5,直至出现第一名
//编程实现，给出二维数组w，一维数组order 和 用于输出比赛名次的数组result[n]，求出result。

public class RaceResult {
	public static void main(String[] args) {
		// team 2>team 1>team 3>team 0>team 4>team 5
		int[][] pk = { { 0, 1, 2, 3, 0, 0 }, { 1, 1, 2, 1, 1, 1 },
				{ 2, 2, 2, 2, 2, 2 }, { 3, 1, 2, 3, 3, 3 },
				{ 0, 1, 2, 3, 4, 5 } };
		int[] order = { 1, 3, 4, 2, 0, 5 };
		int[] result = new int[order.length];
		raceResult(pk, order, result);
		for (int i : result) {
			System.out.print(i + " ");
		}
	}

	static void raceResult(int[][] pk, int[] order, int[] result) {
		int i = 0, j = order.length - 1, k = 1;
		while (k < order.length) {
			if (i + k < order.length) {
				int temp = pk[order[i]][order[i+k]];
				if (temp == order[i]) {
					result[j] = order[i + k];
					j--;
					System.out.println(order[i] + " kick " + order[i + k]);
				} else {
					System.out.println(order[i + k] + " kick " + order[i]);
					result[j] = order[i];
					order[i] = order[i + k];
					order[i + k] = result[j];
					j--;					
				}
				i = i + 2 * k;
			} else {
				i = 0;
				k *= 2;
			}
		}
		result[0] = order [0];
	}
}
