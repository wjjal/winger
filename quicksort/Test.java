package quicksort;

public class Test {
	public static void main(String[] args) {
		int A[] = { 78, 102, 90, 8, 7, 6, 5, 40, 3, 11, 1 };
		System.out.println("排序前，数组为：");
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
		//new QuickSort().quickSort(A);
		new Recursion_QuickSort().quickSort(A, 0, A.length-1);
		System.out.println("排序后，数组为：");
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
	}
}
