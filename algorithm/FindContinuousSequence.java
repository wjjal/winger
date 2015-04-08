package algorithm;

//输入一个正数n，输出所有和为n连续正数序列。
//例如输入15，由于1+2+3+4+5=4+5+6=7+8=15，所以输出3个连续序列1-5、4-6和7-8。

public class FindContinuousSequence {
	public static void main(String[] args) {
		int n = 15;
		findContinuousSequence(n);
	}

	static void findContinuousSequence(int n) {
		int begin = 1, end = 1, sum = 0;
		while (end < n) {
			if (sum < n) {
				sum += end;
				end++;
			} else if (sum > n) {
				sum -= begin;
				begin++;
			} else {
				for (int i = begin; i < end; i++) {
					System.out.print(i);
				}
				System.out.println();
				sum -= begin;
				begin++;
			}
		}
	}
}
