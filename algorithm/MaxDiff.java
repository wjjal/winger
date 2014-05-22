package algorithm;

//有无序的实数列V[N]，要求求里面大小相邻的实数的差的最大值
//关键是要求线性空间和线性时间
//桶排序

public class MaxDiff {
	public static void main(String[] args) {
		int[] array = { 3, 89, 76, 23, 23, 87 };
		System.out.println(maxDiff(array));
	}

	private static int maxDiff(int[] array) {
		int max = array[0];
		int min = array[0];
		int N = array.length;
		for (int i = 1; i < N; i++) {
			max = max > array[i] ? max : array[i];
			min = min < array[i] ? min : array[i];
		}
		int[][] bucket = new int[N][2];
		int bar = (max - min) / (N - 1);
		int pos = 0;
		for (int i = 0; i < N; i++) {
			pos = (array[i] - min) / bar;
			if (bucket[pos][0] == 0) {
				bucket[pos][0] = array[i];
				bucket[pos][1] = array[i];
			} else {
				if (array[i] > bucket[pos][1])
					bucket[pos][1] = array[i];
				if (array[i] < bucket[pos][0])
					bucket[pos][0] = array[i];
			}
		}
		int max_diff = 0;
		for (int i = 1; i < N; i++) {
			if (bucket[i][0] != 0) {
				int diff = bucket[i][0] - bucket[i - 1][1];
				if (max_diff < diff)
					max_diff = diff;
			}
		}
		return max_diff;
	}
}
