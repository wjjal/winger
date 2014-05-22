package scrawl;

//序列Seq=[a,b,…z,aa,ab…az,ba,bb,…bz,…,za,zb,…zz,aaa,…]类似与excel的排列
//任意给出一个字符串s=[a-z]+(由a-z字符组成的任意长度字符串）
//请问s是序列Seq的第几个。

public class Func5 {
	public static void main(String[] args) {
		String s = "abc";
		System.out.println(func5(s));
	}

	static int func5(String s) {
		int pos = 0;
		int n = s.length();
		for (int i = 0; i < n; i++) {
            char temp= s.charAt(i);
            pos +=(temp-'a'+1)*Math.pow(26, (n-i-1));
		}
		return pos;
	}
}
