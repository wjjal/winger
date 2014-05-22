package algorithm;

//输入一个整数，求该整数的二进制表达中有多少个1。

public class NumberOf1_basetwo {
	public static void main(String[] args) {
		int n = -1;
		System.out.println(numberOf1_Solution1(n));
		System.out.println(numberOf1_Solution2(n));
	}


	static int numberOf1_Solution1(int i) {
		int result = 0;
		while (i != 0) {
			if ((i & 1) !=0)
				result++;
			//若为有符号右移>>，输入为负数时陷入死循环，使用无符号右移>>>可解决此问题
			i = i >>> 1;
		}
		return result;
	}
	
	static int numberOf1_Solution2(int i) {
		int result = 0;
		int flag = 1;
		while(flag!=0){
			if((i&flag)!=0)
				result++;
			flag=flag<<1;
		}
		return result;
	}
}
