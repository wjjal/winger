//14、字符串移动
//字符串为*号和26个字母、阿拉伯数字的任意组合，把*号都移动到最左侧，把其他字母和数字都移到最右侧并保持相对顺序不变，
//返回字符*的个数，要求时间和空间复杂度最小。

package array;

public class MoveStar {
	public static void main(String[] args) {
		String str = "23**3*14*8";
		char[] cs = str.toCharArray();
		// MoveStar1(cs, cs.length);
		MoveStar2(cs, cs.length);
		System.out.println(cs);
	}

	static void MoveStar1(char[] str, int n) {
		int i, j = n - 1;
		for (i = n - 1; i >= 0; --i) {
			if (str[i] != '*') {
				if (str[j] == '*') {
					str[j] = str[i];
					str[i] = '*';
				}
				--j;
			}
		}
	}

	static void MoveStar2(char str[], int n) {
		int i, count = 0;
		for (i = n - 1; i >= 0; --i) {
			if (str[i] == '*')
				++count;
			else if (count > 0) // str[i] != '*'
				str[i + count] = str[i];
		}
		for (i = 0; i < count; ++i)
			str[i] = '*';
	}
}