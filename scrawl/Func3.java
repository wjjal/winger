package scrawl;

//找出数组中出现次数超过一半的数，现在有一个数组，已知一个数出现的次数超过了一半
//请用O(n)的复杂度的算法找出这个数

public class Func3 {
	public static void main(String[] args) {
		int[] array = { 5, 5, 4, 3, 5, 3, 5, 1, 5, 1, 5 };
		System.out.println(func(array));
	}

	static int func(int[] array) {
		int a = array[0];
		int b = 1;
		for (int i = 1; i < array.length; i++) {
			if (b == 0) {
				a = array[i];
				b++;
			} else if (a == array[i])
				b++;
			else
				b--;
		}
		return a;
	}
}
