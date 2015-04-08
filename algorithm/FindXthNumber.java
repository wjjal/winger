package algorithm;

//找出第k大的数字所在的位置。
//写一段程序，找出数组中第k大小的数，输出数所在的位置。
//例如{2，4，3，4，7}中，第一大的数是7，位置在4。第二大、第三大的数都是4，位置在1、3随便输出哪一个均可。

public class FindXthNumber {
	public static void main(String[] args) {
		int[] array = { 2, 5, 3, 4, 9, 1, 7 };
		int[] copy = array.clone();
		int k = 3;
		int num = findKLarge(copy, 0, array.length - 1, k);
		for (int i = 0; i < array.length; i++) {
			if (array[i] == num)
				System.out.println("第" + k + "大的数是" + num + ",位置是" + i);
		}
	}

	public static int findKLarge(int[] arr, int start, int end, int k) {
		int ret = 0;
		if (end - start == 0) {
			return arr[start];
		}
		int sep = partition(arr, start, end);
		if (end - sep >= k) {
			ret = findKLarge(arr, sep + 1, end, k);
		} else {
			k = k - (end - sep);
			ret = findKLarge(arr, start, sep, k);
		}
		return ret;
	}

	public static int partition(int[] arr, int start, int end) {
		int value = arr[start];
		while (start < end) {
			while (start < end && arr[end] >= value) {
				end--;
			}
			if (start < end) {
				arr[start] = arr[end];
				start++;
			}

			while (start < end && arr[start] <= value) {
				start++;
			}
			if (start < end) {
				arr[end] = arr[start];
				end--;
			}
		}

		arr[start] = value;
		return start;
	}

}
