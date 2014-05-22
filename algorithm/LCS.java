package algorithm;

import java.util.Stack;

//Longest Common Subsequence，最长公共子序列

//如果字符串一的所有字符按其在字符串中的顺序出现在另外一个字符串二中，
//则字符串一称之为字符串二的子串。
//注意，并不要求子串（字符串一）的字符必须连续出现在字符串二中。
//请编写一个函数，输入两个字符串，求它们的最长公共子串，并打印出最长公共子串。

public class LCS {
	public static void main(String[] args) {
		String a = "abcbdab";
		String b = "bdcaba";
		int max = LCS_Lehgth(a, b);
		System.out.println(max);
	}

	private static int LCS_Lehgth(String a, String b) {
		int len_a = a.length();
		int len_b = b.length();
		int max = 0;
		int[][] opt = new int[len_a + 1][len_b + 1];
		StringBuilder[][] result = new StringBuilder[len_a + 1][len_b + 1];
		for (int i = 0; i < len_a + 1; i++) {
			for (int j = 0; j < len_b + 1; j++) {
				result[i][j] = new StringBuilder();
			}
		}
		for (int i = 1; i <= len_a; i++) {
			for (int j = 1; j <= len_b; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					opt[i][j] = opt[i - 1][j - 1] + 1;
					result[i][j] = result[i-1][j-1].append(a.charAt(i - 1));
				} else if (opt[i - 1][j] > opt[i][j - 1]) {
					opt[i][j] = opt[i - 1][j];
					result[i][j] = result[i - 1][j];
				} else {
					opt[i][j] = opt[i][j - 1];
					result[i][j] = result[i][j - 1];
				}
			}
		}
		System.out.println(result[len_a][len_b]);
		return opt[len_a][len_b];
	}
}
