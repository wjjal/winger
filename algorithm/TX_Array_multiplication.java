package algorithm;

//两个数组a[N]，b[N]，其中A[N]的各个元素值已知，现给b[i]赋值，b[i] = a[0]*a[1]*a[2]...*a[N-1]/a[i]；
//要求：
//1.不准用除法运算
//2.除了循环计数值，a[N],b[N]外，不准再用其他任何变量（包括局部变量，全局变量等）
//3.满足时间复杂度O（n），空间复杂度O（1）。

public class TX_Array_multiplication {
	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 5 };
		array_multiplication(A);
	}

	private static void array_multiplication(int[] a) {
		int n = a.length;
		int[] b = new int[n];
		for (int i = 0; i < n; i++)
			b[i] = 1;
		int left = 1, right = 1;
		for (int i = 0; i < a.length; i++) {
			b[i] *= left;
			b[n - i - 1] *= right;
			left *= a[i];
			right *= a[n - i - 1];
		}
		for (int k : b) {
			System.out.print(k + " ");
		}
	}
}
