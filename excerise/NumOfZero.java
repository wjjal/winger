//给定一个整数N，那么N的阶乘N！末尾有多少个0呢？
package excerise;

import java.util.Scanner;

public class NumOfZero {
	public static void main(String[] args) {
		System.out.println("Please input the number:");
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		System.out.println(numOfzero(num));
		scanner.close();
	}

	static int numOfzero(int n) {
		int count = 0;
		while (n > 5) {
			count += n / 5;
			n /= 5;
		}
		return count;
	}
}
