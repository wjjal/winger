package algorithm;

public class Math_Josephus {
	public static void main(String args[]) {
		int n = 30, m = 9;
		System.out.println(Josephus_Solution(n, m));
	}

	static int Josephus_Solution(int n, int m) {
		if (n <= 0 || m <= 0)
			return -1;
		int last = 0;
		for (int i = 2; i <= n; i++) {
			last = (last + m) % i;
		}
		return last;
	}
}
