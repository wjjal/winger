package algorithm;

import java.util.ArrayList;
import java.util.List;

//输入两个整数n 和m，从数列1，2，3.......n 中随意取几个数,   
//使其和等于m ,要求将其中所有的可能组合列出来.   
//findSum(sum,n)=findSum(sum-n,n-1)+findSum(sum,n-1);  

public class FindSum {
	public static void main(String[] args) {
		int n = 10, m = 20;
		List<Integer> result = new ArrayList<Integer>();
		findSum(m, n, result);
	}

	static void findSum(int sum, int n, List<Integer> result) {
		if (sum <= 0 || n <= 0)
			return;
		if (sum == n) {

			for (int i = 0; i < result.size(); i++) {
				System.out.print(result.get(i) + " ");
			}
			System.out.print(n + " ");
			System.out.println();
		}
		result.add(n);
		findSum(sum - n, n - 1, result);
		result.remove(Integer.valueOf(n));
		findSum(sum, n - 1, result);
	}
}
