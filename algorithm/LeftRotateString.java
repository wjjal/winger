package algorithm;

//把字符串前面的n个字符移动到字符串的尾部,如把字符串abcdef左旋转2位得到字符串cdefab。
//要求时间对长度为n的字符串操作的复杂度为O(n)，辅助内存为O(1)。

public class LeftRotateString {
	public static void main(String[] args) {
		String s = "abcdef";
		int num = 2;
		leftRotateString(s.toCharArray(), num);
	}

	static void leftRotateString(char[] str, int num) {
		reverseString(str, 0, num - 1);
		reverseString(str, num, str.length - 1);
		reverseString(str, 0, str.length - 1);
		for (char c : str)
			System.out.print(c);
	}

	static void reverseString(char[] chs, int start, int end) {
		while (start <= end) {
			char temp = chs[start];
			chs[start] = chs[end];
			chs[end] = temp;
			start++;
			end--;
		}
	}
}
