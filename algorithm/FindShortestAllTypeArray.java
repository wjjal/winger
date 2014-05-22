package algorithm;

import java.util.HashMap;
import java.util.Map;

//给一个很长的字符串str 还有一个字符集比如{a,b,c} 找出str里包含{a,b,c}的最短子串。

public class FindShortestAllTypeArray {
	public static void main(String[] args) {
		char[] source = { 'a', 'b', 'd', 'c', 'a', 'a', 'b', 'c', 'x' };
		char[] target = { 'd', 'a', 'b' };
		System.out.println(findShortestAllTypeArray(source, target));
	}

	static int findShortestAllTypeArray(char[] source, char[] target) {
		int type_total = target.length;
		int min = 0;
		int type_now = 0;
		Map<Character, Integer> count = new HashMap<Character, Integer>();
		for (char c : target) {
			count.put(c, 0);
		}
		int begin = 0, end = 0;
		while (end <= source.length) {
			if (end == source.length && type_now < type_total)
				return min;
			if (type_now < type_total) {
				if (count.containsKey(source[end])) {
					int old = count.get(source[end]);
					if (old == 0)
						type_now++;
					count.put(source[end], ++old);
				}
				end++;
			}
			if (type_now == type_total) {
				if (count.containsKey(source[begin])) {
					int old = count.get(source[begin]);
					count.put(source[begin], --old);
					if (old == 0) {
						type_now--;
						int length = end - begin;
						if (min == 0 || min > length)
							min = length;
					}
				}
				begin++;
			}
		}
		return min;
	}
}
