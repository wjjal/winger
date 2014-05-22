//4、找出绝对值最小的元素

//给定一个有序整数序列（非递减序），可能包含负数，找出其中绝对值最小的元素，比如给定序列 -5、-3、-1、2、8 则返回1。
//分析：由于给定序列是有序的，而这又是搜索问题，所以首先想到二分搜索法，只不过这个二分法比普通的二分法稍微麻烦点，可以分为下面几种情况
//    如果给定的序列中所有的数都是正数，那么数组的第一个元素即是结果。
//    如果给定的序列中所有的数都是负数，那么数组的最后一个元素即是结果。
//    如果给定的序列中既有正数又有负数，那么绝对值的最小值一定出现在正数和负数的分界处。
//为什么？因为对于负数序列来说，右侧的数字比左侧的数字绝对值小，如上面的-5、-3、-1，而对于整整数来说，左边的数字绝对值小，
//比如上面的2、8，将这个思想用于二分搜索，可先判断中间元素和两侧元素的符号，然后根据符号决定搜索区间，逐步缩小搜索区间，
//直到只剩下两个元素。
//单独设置一个函数用来判断两个整数的符号是否相同

package array;

public class MiniNumAbsoluteValue {

	public static void main(String[] args) {
		int array[] = { -5, -3, -1, 2, 8 };
		System.out.println(MiniNumAbsoluteValue(array, array.length));
	}

	static boolean SameSign(int m, int n) {
		if ((m >> 31) == (n >> 31))
			return true;
		else
			return false;
	}

	// 找出一个非递减序整数序列中绝对值最小的数
	static int MiniNumAbsoluteValue(int arr[], int n) {
		if (n == 1)
			return arr[0];
		if (SameSign(arr[0], arr[n - 1]))
			return arr[0] >= 0 ? arr[0] : Math.abs(arr[n - 1]);

		// 二分搜索
		int mid, low = 0, high = n - 1;
		while (low < high) {
			if (low + 1 == high)
				return Math.abs(arr[low]) < Math.abs(arr[high]) ? Math
						.abs(arr[low]) : Math.abs(arr[high]);
			mid = (low + high) >> 1;
			if (SameSign(arr[low], arr[mid])) {
				low = mid; // 有可能分界点就在mid处
				continue;
			}
			if (SameSign(arr[high], arr[mid])) {
				high = mid;
				continue;
			}
		}
		return Math.abs(arr[low]);
	}
}
