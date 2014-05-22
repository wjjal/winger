package scrawl;

//n个空间（其中n<1M），存放a到a+n-1的数，位置随机且数字不重复，a为正且未知。
//现在第一个空间的数被误设置为-1。已经知道被修改的数不是最小的。请找出被修改的数字是多少。

public class Func4 {
	public static void main(String[] args) {
		int[] array = { 5, 3, 7, 6, 2, 4 };
		array[0] = -1;
		System.out.println(func(array));
	}

	static int func(int[] a) {
		int min = Integer.MAX_VALUE, sumfrom2 = 0;
		int n = a.length;
		for (int i = 1; i < n; i++) {
			if(min>a[i])
				min = a[i];
			sumfrom2 += a[i];
		}
		int totalsum = (2*min+n-1)*n/2;
		return  totalsum- sumfrom2;
	}
}
