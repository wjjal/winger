package algorithm;

//奇偶对换，只用交换，把奇数放到偶数前面
public class ReorderOddEven {
	public static void main(String args[]) {
		int array[] = { 4, 6, 1, 7, 5, 12, 33, 21, 26, 28, 37, 10 };
		reorderOddEven(array);
	}

	static void reorderOddEven(int[] array) {
		int begin = 0, end = array.length - 1;
		while (begin < end) {
			if (!isOdd(array[begin]) && isOdd(array[end])) {
				int temp = array[begin];
				array[begin] = array[end];
				array[end] = temp;
				begin++;
				end--;
			} else if (isOdd(array[begin]))
				begin++;
			else if (!isOdd(array[end]))
				end--;
		}
		for (int i : array)
			System.out.print(i + " ");
	}

	static boolean isOdd(int i) {
		if ((i & 1) != 0)
			return true;
		else
			return false;
	}
}
