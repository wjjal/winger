//8、三色旗排序问题
//一个字符串Color，其中每个元素值为‘R‘’G’‘B’三者之一，实现把数组中元素重新排列为红、绿、蓝的顺序，所有红色在前，
//绿色其后，蓝色最后，要如何移动次数才会最少，编写这么一个程序。

//问题的解法很简单，您可以自己想像一下在移动旗子，从绳子开头进行，遇到红色往前移，遇到绿色留在中间，遇到蓝色往后移。
//设有三个指针rindex、gindex、bindex，其中gindex来遍历这个数组序列
//1、gindex指向G的时候，gindex++，
//2、gindex指向R的时候，与rindex交换，而后gindex++，rindex++，
//3、gindex指向B的时候，与bindex交换，而后，gindex不动，bindex--。
//    为什么，第三步，gindex指向B的时候，与bindex交换之后，gindex不动。
//    因为你最终g的目的是为了让R、G、B成为有序排列，试想，如果第三步，gindex与bindex交换之前，
//万一bindex指向的是R，而gindex交换之后，gindex此刻指向的是R了，此时，gindex能动么？不能动啊，指向的是R，
//还得与rindex交换。

package array;

public class ThreeColorFlagSort {
	public static void main(String[] args) {
		String str = "BRGRBRB";
		char array[] = str.toCharArray();
		mysort(array, array.length);
		System.out.print(array);
	}

	// 三色旗排序问题
	static void mysort(char str[], int n) {
		int rindex = 0, gindex = 0, bindex = n - 1;
		while (gindex <= bindex) {
			if (str[gindex] == 'G')
				++gindex;
			else if (str[gindex] == 'R') {
				char temp = str[gindex];
				str[gindex] = str[rindex];
				str[rindex] = temp;
				++rindex;
				++gindex;
			} else {
				char temp = str[gindex];
				str[gindex] = str[bindex];
				str[bindex] = temp;
				--bindex;
			}
		}
	}
}
