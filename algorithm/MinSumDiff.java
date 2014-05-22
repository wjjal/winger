package algorithm;


//有一个没有排序，元素个数为2N的正整数数组。
//要求把它分割为元素个数为N的两个数组，并使两个子数组的和最接近。 
public class MinSumDiff {
	public static void main(String[] args) {
		int[] array = { 1, 5, 7, 8, 9, 6, 3, 11, 20, 17 };
		int n = 5;
		int sum = 0;
		int a[] = new int[n];
		int b[] = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = array[i];
			b[i] = array[n + i - 1];
		}
		Swap_sum.swapSum(a, b);
	}
}
