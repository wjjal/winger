package excerise;

import java.util.Scanner;

public class SomeAboutBit {
	public static void main(String[] args){
		System.out.print("Please input the number:");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		System.out.println(Integer.toBinaryString(n));
		System.out.println(powOf2(n));
		System.out.println(numOf1(n));
		System.out.println(Integer.toBinaryString(reverseMask(n)));
		scanner.close();
	}

	// 判断一个正整数是否是2的整数次幂
	static boolean powOf2(int n) {
		return n != 0 && ((n & (n - 1)) == 0);
	}

	// 求一个整数二进制表示中1的个数
	static int numOf1(int n) {
		int cnt = 0;
		while (n != 0) {
			n = n & (n - 1);
			cnt++;
		}
		return cnt;
	}
	
	//反转一个无符号整数的所有比特位
	//采用分治策略，首先交换数字x的相邻位，然后再是2个位交换，然后是4个位交换...
	//比如给定一个8位数01101010， 则首先交换相邻位，变成10 01 01 01，然后交换相邻的2个位，得到01 10 01 01，
	//然后再是4个位交换，得到0101 0110。总的时间复杂度为O(lgN)，其中N为整数的位数。
	//下面给出一个反转32位整数的代码，如果整数是64位的可以以此类推。
	static int reverseMask(int n) 
	{    
	   //assert(n==1); // special case: only works for 4 bytes (32 bits).
	    n = ((n & 0x55555555) << 1) | ((n & 0xAAAAAAAA) >>> 1);  
	    n = ((n & 0x33333333) << 2) | ((n & 0xCCCCCCCC) >>> 2);   
	    n = ((n & 0x0F0F0F0F) << 4) | ((n & 0xF0F0F0F0) >>> 4);      
	    n = ((n & 0x00FF00FF) << 8) | ((n & 0xFF00FF00) >>> 8);   
	    n = ((n & 0x0000FFFF) << 16) | ((n & 0xFFFF0000) >>> 16);   
	    return n; 
	}
}
