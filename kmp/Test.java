package kmp;

public class Test {
	public static void main(String[] srgs) {
		String t = "abaabcac";
		String s = "acabaabaabcacaabc";
		KMP kmp = KMP.getInstance();
		kmp.get_next(t);
		System.out.print(kmp.Index_KMP(s, t, 0));
	}
}
