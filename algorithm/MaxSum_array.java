package algorithm;

//LCS问题
//输入一个整形数组，数组里有正数也有负数。
//数组中连续的一个或多个整数组成一个子数组，每个子数组都有一个和。
//求所有子数组的和的最大值。

public class MaxSum_array {
	public static void main(String[] args) {
		int[] array = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22,
				15, -4, 7 };
		System.out.println(maxSumArray(array));
	}

	static int maxSumArray(int[] array) {
		int sum = 0, max = Integer.MIN_VALUE;
		for (int i : array) {
			sum += i;
			if (sum > max)
				max = sum;
			if (sum < 0)
				sum = 0;
		}
		return max;
	}
}
