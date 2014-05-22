package algorithm;

//寻找亲和数
//如果两个数a和b，a的所有真因数之和等于b,b的所有真因数之和等于a,则称a,b是一对亲和数

public class AmicableNumbers {
	public static void findAmicableNumbers(int num) {
		int sum[] = new int[num + 1];
		for (int i = 1; i < num; i++) {
			sum[i] = 1;
		}
		for (int i = 2; i + i <= num; i++) {
			int j = i + i;
			while (j <= num) {
				sum[j] += i;
				j = j + i;
			}
		}
		for (int i = 220; i <= num; i++) {
			if (sum[i] > i && sum[i] <= num && sum[sum[i]] == i)
				System.out.println(i + " " + sum[i]);
		}
	}

	public static void main(String[] args) {
		findAmicableNumbers(500000);
	}
}
