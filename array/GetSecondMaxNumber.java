//1、快速找出一个数组中的最大数、第二大数。
// 思路：如果当前元素大于最大数 max，则让第二大数等于原来的最大数 max，再把当前元素的值赋给 max。
//如果当前的元素大于等于第二大数secondMax的值而小于最大数max的值，则要把当前元素的值赋给 secondMax。
package array;

public class GetSecondMaxNumber {
	public static void main(String[] args){
		int array[] = {7, 10, 2, 6, 19, 22, 32};
		GetSecondMaxNumber(array,array.length);
	}
	static void GetSecondMaxNumber(int arr[], int n) {
		int i, max, second_max;
		max = arr[0];
		second_max = 0x80000000;
		for (i = 1; i < n; ++i) {
			if (arr[i] > max) {
				second_max = max;
				max = arr[i];
			} else {
				if (arr[i] > second_max)
					second_max = arr[i];
			}
		}
		System.out.println("max:" + max + ",secondmax:" + second_max);
	}
}
