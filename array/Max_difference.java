//15、求数组中两个元素差的最大值
//后面的元素减去前面的元素（你可以认为你在炒股票，买入价格和卖出价格就是你的盈利），要求：O(N)时间复杂度，O(1)空间复杂度 
//思路：首先从包含两个元素的数组开始考虑问题，当这个包含两个元素的问题解决了，那么加一个新的元素会造成什么影响？
//要改变哪些值？每次都添加一个元素，每次都将这些可能的改变考虑进来，这样就能做到遍历整个数组的时候，问题就解决了。
//max_difference1

//16、求数组中两个元素差的最大值
//前面的元素减去后面的元素，要求：O(N)时间复杂度，O(1)空间复杂度
//思路：跟上一题的思路很相近
//max_difference2

package array;

public class Max_difference {
	public static void main(String[] args) {
		int array[] = { -5, -3, -10, 2, 8 };
		int n = array.length;
		System.out.println(max_difference1(array, n));
		System.out.println(max_difference2(array, n));
	}

	// 后面的元素减去前面的元素 差的最大值
	static int max_difference1(int arr[], int n) {
		if (n < 2) // 非法输入
			return 0;
		int min = arr[0];
		int maxDiff = arr[1] - arr[0];
		for (int i = 2; i < n; ++i) {
			if (arr[i - 1] < min)
				min = arr[i - 1];
			if (arr[i] - min > maxDiff)
				maxDiff = arr[i] - min;
		}
		return maxDiff;
	}

	// 前面的元素减去后面的元素 差的最大值
	static int max_difference2(int arr[], int n) {
		if (n < 2) // 非法输入
			return 0;
		int max = arr[0];
		int maxDiff = arr[0] - arr[1];
		for (int i = 2; i < n; ++i) {
			if (arr[i - 1] > max)
				max = arr[i - 1];
			if (max - arr[i] > maxDiff)
				maxDiff = max - arr[i];
		}
		return maxDiff;
	}
}
