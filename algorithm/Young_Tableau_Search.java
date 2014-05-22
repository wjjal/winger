package algorithm;
//杨氏矩阵：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
//请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
//例如下面的二维数组就是每行、每列都递增排序。如果在这个数组中查找数字6，则返回true；如果查找数字5，由于数组不含有该数字，则返回false。



public class Young_Tableau_Search {
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 },
				{ 6, 8, 11, 15 } };
		int num = 6;
		search(matrix, num);
	}

	private static void search(int[][] matrix, int num) {
		int i = 0;
		int j = matrix[0].length - 1;
		while (i < matrix.length && j >= 0) {
			if (matrix[i][j] == num) {
				System.out.println("true!");
				return;
			} else if (matrix[i][j] > num) {
				j--;
			} else {
				i++;
			}
		}
		System.out.println("false!");
	}
}
