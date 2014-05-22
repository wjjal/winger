package algorithm;

public class Twentyfour_Point {
	final static double Threshold = 1E-6;

	public static void main(String[] args) {
		double[] array = { 3, 7, 3, 7 };
		System.out.println(pointsGame(array, 4, 24, "", -1));
	}

	private static boolean pointsGame(double[] array, int n, double sum,
			String exp, int pos) {
		if (n == 1 && Math.abs(sum - 24) == 0) {
			System.out.println(exp);
			return true;
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				double a = array[i];
				double b = array[j];
				array[j] = array[n - 1];
				String temp = exp;
				// 任取两数做加法
				sum = a + b;
				array[i] = a + b;
				// pos是表示上一轮被替换的位置的元素的
				if (i == pos)
					exp = "(" + temp + "+" + b + ")";
				else
					exp = temp + "(" + a + "+" + b + ")";
				if (pointsGame(array, n - 1, sum, exp, i))
					return true;
				// 任取两数做减法
				sum = a - b;
				array[i] = a - b;
				if (i == pos)
					exp = "(" + temp + "-" + b + ")";
				else
					exp = temp + "(" + a + "-" + b + ")";
				if (pointsGame(array, n - 1, sum, exp, i))
					return true;
				sum = b - a;
				array[i] = b - a;
				if (i == pos)
					exp = "(" + b + "-" + temp + ")";
				else
					exp = temp + "(" + b + "-" + a + ")";
				if (pointsGame(array, n - 1, sum, exp, i))
					return true;
				// 任取两数做乘法
				sum = a * b;
				array[i] = a * b;
				if (i == pos)
					exp = temp + "*" + b;
				else
					exp = temp + a + "*" + b;
				if (pointsGame(array, n - 1, sum, exp, i))
					return true;
				// 任取两数做除法
				if (b != 0) {
					sum = a / b;
					array[i] = a / b;
					if (i == pos)
						exp = temp + "/" + b;
					else
						exp = temp + a + "/" + b;
					if (pointsGame(array, n - 1, sum, exp, i))
						return true;
				}
				if (a != 0) {
					sum = b / a;
					array[i] = b / a;
					if (i == pos)
						exp = b + "/" + temp;
					else
						exp = temp + b + "/" + a;
					if (pointsGame(array, n - 1, sum, exp, i))
						return true;
				}
				// 恢复数组值
				array[n - 1] = array[j];
				array[i] = a;
				array[j] = b;
				exp = temp;
			}
		}
		return false;
	}
}
