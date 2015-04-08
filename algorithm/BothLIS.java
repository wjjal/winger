package algorithm;

import java.util.Scanner;
//从一列数中筛除尽可能少的数使得从左往右看，这些数是从小到大再从大到小的,求该附合要求的数列的最大长度
public class BothLIS {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input the length of array!");
		int len = scan.nextInt();
		int[] array = new int[len];
		int[] doarray = new int[len];
		int temp = 0;
		System.out.println("Please input the array!");
		for(int i = 0; i < len; i++) {
			temp = scan.nextInt();
			array[i] = temp;
			doarray[len - 1 - i] = temp;
		}
		int[] b = new int[len+1];
		int[] c = new int[len+1];
		LIS(array, len, b);
		LIS(doarray,len,c);
		int max = 0;
		for (int j = 0; j <= len; j++) {
			if (b[j] + c[len - j] > max)
				max = b[j] + c[len - j];
		}
		System.out.println(max);
		scan.close();
	}

	static void LIS(int[] data, int len, int[] record) {
		int[] assist = new int[len + 1];
		assist[1] = data[0];
		record[1] = 1;
		int max = 1;
		for (int i = 1; i < len;) {
			int low = 1, high = max, mid = 0;
			while (low <= high) {
				mid = (low + high) / 2;
				if (assist[mid] < data[i])
					low = mid + 1;
				else if (assist[mid] == data[i]) {
					low = mid;
					break;
				} else
					high = mid - 1;
			}
			assist[low] = data[i];
			if (low > max)
				max++;
			record[++i] = max;
		}
	}
}
