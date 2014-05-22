package algorithm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

//输出给定字符串的全部连续子串
public class AllSubStrings {
	public static void main(String[] args) {
		System.out.println("Please input the string:");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		System.out.println("All the substrings of " + str + " :");
		GetAllSubStrings(str);
	}

	public static void GetAllSubStrings(String str) {
		Set<String> set= new HashSet<String>();
		char[] cs = str.toCharArray();
		int len = cs.length;
		for (int i = len; i > 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				set.add(str.substring(j, i));
			}
		}
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
