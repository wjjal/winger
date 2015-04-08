package algorithm;

public class Fibonacci {
	static int fibonacci(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		int[] f = new int[n + 1];
		f[0] = 0;
		f[1] = 1;
		int i = 2;
		while (i <= n) {
			f[i] = f[i - 1] + f[i - 2];
			i++;
		}
		return f[i - 1];
	}

	static int[][] quickFibonacci(int n) {
		assert (n > 0);
		final int[][] start_matrix = { { 1, 1 }, { 1, 0 } };
		int[][] matrix = new int[2][2];
		if (n == 1)
			matrix = start_matrix;
		else if (n % 2 == 0) {
			matrix = quickFibonacci(n / 2);
			matrix = MatrixMultiply(matrix, matrix);
		} else if (n % 2 == 1) {
			matrix = quickFibonacci((n - 1) / 2);
			matrix = MatrixMultiply(matrix, matrix);
			matrix = MatrixMultiply(matrix, start_matrix);
		}
		return matrix;
	}

	private static int[][] MatrixMultiply(int[][] matrix1, int[][] matrix2) {
		int[][] matrix = {
				{
						matrix1[0][0] * matrix2[0][0] + matrix1[0][1]
								* matrix2[1][0],
						matrix1[0][0] * matrix2[0][1] + matrix1[0][1]
								* matrix2[1][1] },
				{
						matrix1[1][0] * matrix2[0][0] + matrix1[1][1]
								* matrix2[1][0],
						matrix1[1][0] * matrix2[0][1] + matrix1[1][1]
								* matrix2[1][1] } };
		return matrix;
	}

	public static void main(String[] args) {
		int n = 8;
		System.out.println(fibonacci(n));
		int result[] = { 0, 1 };
		if (n < 2)
			System.out.println(result[n]);
		int[][] matrix = quickFibonacci(n - 1);
		System.out.println(matrix[0][0]);
	}
}
