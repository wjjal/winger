package algorithm;

public class Reverse {
	static <T> void reverse(T[] data, int k) {
		int len = data.length, i = 0;
		while (k + i < len - 1 - i) {
			Swap.swap(data, k + i, len - 1 - i);
			i++;
		}
	}
}
