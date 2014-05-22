package algorithm;

//输入一个已经按升序排序过的数组和一个数字，
//在数组中查找两个数，使得它们的和正好是输入的那个数字。
//要求时间复杂度是O(n)。

public class FindTwoNumbersWithSum {
	public static void main(String[] args) {
		int[] array = { 1, 2, 4, 7, 11, 15 };
		int sum = 15;
		findTwoNumberWithSum(array, sum);
	}

	static void findTwoNumberWithSum(int[] array, int sum) {
		int len = array.length;
		int[] temp = new int[len];
		for (int i = 0; i < len; i++) {
			temp[i] = sum - array[i];
		}
		int i = 0, j = len - 1;
		while (i < len && j >= 0) {
			if (array[i] == temp[j]) {
				System.out.println(array[i] + " and " + array[j]);
				return;
			} else if (array[i] > temp[j])
				j--;
			else
				i++;
		}
		System.out.println("Not Found!");
	}
}
