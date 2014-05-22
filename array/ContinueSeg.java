//9、一个整型数组，含有N个数，将这N个数分成连续的M段，使得这M段每段的和中的最大值最小，输出最小值。
//（1<=N<=100000,1<=M<=N,每个数在1到10000之间）   POJ  3273

//解题思路：不管分成多少段，每种分法和的最大值都在N个数的最大值和N个数的和之间，所求答案也在这之间。
//我们可以以此为上下界，二分M段和的最大值进行尝试。对每次二分的值，将数组扫描累加。
//若当前和大于二分的这个值，则段数加一，由此统计出在当前值下，N个数能够分成的最小段数。
//若这个段数小于或等于M，则上界变为mid-1，并记下当前mid的值。否则，下界变为mid+1。继续二分，直到退出循环。
//最后记录的low值即为答案。

package array;

import java.io.Console;
import java.util.Scanner;

public class ContinueSeg {

	static int m;
	static int n;
	static int a[] = new int[10001];

	public static void main(String[] args) {
		int i, max, sum;
		String input = null;

		// Console console = System.console();
		// if (console == null) {
		// throw new IllegalStateException("不能使用控制台");
		// }
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input m:");
		m = scanner.nextInt();
		System.out.println("Please input n:");
		n = scanner.nextInt();
		max = 0x80000000;
		sum = 0;
		System.out.println("Please input the array:");
		for (i = 0; i < n; ++i) {
			a[i] = scanner.nextInt();
			sum += a[i];
			if (a[i] > max)
				max = a[i];
		}
		System.out.println((bsearch(max, sum)));
	}

	static int bsearch(int low, int high) {
		int i, mid, group, sum;
		while (low <= high) {
			mid = (low + high) >> 1;
			sum = 0;
			group = 1;
			for (i = 0; i < n; ++i) {
				if (sum + a[i] <= mid)
					sum += a[i];
				else {
					group++;
					sum = a[i];
				}
			}
			if (group <= m)
				high = mid - 1;
			else if (group > m)
				low = mid + 1;
		}
		return low;
	}
}
