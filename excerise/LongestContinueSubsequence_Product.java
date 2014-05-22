package excerise;

public class LongestContinueSubsequence_Product {
	public static void main(String[] args) {
		int a[] = { 3, -4, -5, 6, -2 };
		System.out.println(max_multiple(a));
	}

	static int max_multiple(int[] date) {
		int max[] = new int[date.length];
		int min[] = new int[date.length];
		max[0] = min[0] = date[0];
		int maxval = date[0];
		for (int i = 1; i < date.length; i++) {
			max[i] = max(max[i - 1] * date[i], min[i - 1] * date[i], date[i]);
			min[i] = min(max[i - 1] * date[i], min[i - 1] * date[i], date[i]);
			maxval = Math.max(max[i], maxval);
		}
		return maxval;
	}

	private static int max(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}

	private static int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
}
