package algorithm;

//有两个序列a,b，大小都为n,序列元素的值任意整数，无序；
//要求：通过交换a,b中的元素，使[序列a元素的和]与[序列b元素的和]之间的差最小。

public class Swap_sum {
	public static void main(String[] args) {
		int[] A = { 100, 99, 98, 1, 2, 3 };
		int[] B = { 1, 2, 3, 4, 5, 40 };
		swapSum(A, B);
	}

	public static void swapSum(int[] A, int[] B) {
		int sum_A = 0;
		int sum_B = 0;
		for (int i : A)
			sum_A += i;
		for (int i : B)
			sum_B += i;
		if (sum_A == sum_B)
			return;
		boolean shift = true;
		int a = -1;
		int b = -1;
		int curdiff = sum_A - sum_B;
		int min = Math.abs(curdiff);
		while (shift) {
			int newdiff = 0;
			shift = false;
			for (int i = 0; i < A.length; i++) {
				for (int j = 0; j < B.length; j++) {
					int temp = A[i] - B[j];
					if (Math.abs(curdiff - 2 * temp) < min) {
						shift = true;
						min = Math.abs(curdiff - 2 * temp);
						newdiff = curdiff - 2 * temp;
						a = i;
						b = j;

					}
				}
			}
			if (shift) {
				int t = A[a];
				A[a] = B[b];
				B[b] = t;
				curdiff = newdiff;
			}
		}
		System.out.println(min);
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < B.length; i++) {
			System.out.print(B[i] + " ");
		}
	}
}
