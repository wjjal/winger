//For a given array A of N integers and a sequence S of N integers from the set {−1, 1}, we define val(A, S) as follows:
//val(A, S) = |sum{ A[i]*S[i] for i = 0..N−1 }|
//(Assume that the sum of zero elements equals zero.)
package algorithm;

public class MinAbsSum {
	public static void main(String[] args) {
		int[] a = {8, 10, 2, 6, 19, 22, 32};
		System.out.println(new MinAbsSum().new Solution().minAbsSum(a));
	}

	class Solution {
		public int minAbsSum(int[] a) {
			int n = a.length;
			int max = 0;
			int sum = 0;
			for (int i = 0; i < n; i++) {
				a[i] = Math.abs(a[i]);
				sum += a[i];
				max = Math.max(max, a[i]);
			}
			int count[] = new int[max + 1];
			for (int i = 0; i < n; i++) {
				count[a[i]] += 1;
			}
			int dp[] = new int[sum + 1];
			for (int i = 1; i <= sum; i++) {
				dp[i] = -1;
			}
			for (int i = 1; i <= max; i++) {
				if (count[i] > 0) {
					for (int j = 0; j <= sum; j++) {
						if (dp[j] >= 0)
							dp[j] = count[i];
						else if (j >= i && dp[j - i] > 0)
							dp[j] = dp[j - i] - 1;

					}
				}
			}
			int target = sum / 2;
			while (dp[target] < 0) {
				target--;
			}
			return sum - 2 * target;
		}
	}
}
