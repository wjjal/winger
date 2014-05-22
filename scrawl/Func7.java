package scrawl;

//编程实现两个正整数的除法，当然不能用除法操作符
public class Func7 {
	public static void main(String[] args) {
		System.out.println(div(60, 10));
	}

	static int div(int x, int y) {
		int left_num = x;
		int result = 0;
		int multi = 0;
		while (left_num >= y) // 模拟小学学过的竖式除法运算
		{
			multi = 1;
			while (y * multi <= (left_num >> 1)) {
				multi = multi << 1;
			}
			result += multi;
			left_num -= y * multi;
		}
		return result;
	}

}
