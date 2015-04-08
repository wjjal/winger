package algorithm;

public class Math_Josephus {
	public static void main(String args[]) {
		int n = 10000, m = 8;
		System.out.println(new Math_Josephus().new Solution()
				.Josephus_Solution(n, m));
	}

	class Solution {
		public int Josephus_Solution(int n, int m) {
			if (n <= 0 || m <= 0)
				return -1;
			int last = 1;
			for (int i = 2; i <= n; i++) {
				last = (last + m) % i;
				if (last == 0)
					last = i;
			}
			return last;
		}
	}
}
