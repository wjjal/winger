package kmp;

public class KMP {
	private static final KMP INSTANCE = new KMP();

	private KMP() {
	}

	public static KMP getInstance() {
		return INSTANCE;
	}

	int[] next;

	void get_next(String t) {
		int len = t.length(), i = 0, j = -1;
		next = new int[len];
		next[i] = -1;
		while (i < t.length() - 1) {
			if (j == -1 || t.charAt(i) == t.charAt(j)) {
				++i;
				++j;
				if (t.charAt(i) != t.charAt(j))
					next[i] = j;
				else
					next[i] = next[j];
			} else
				j = next[j];
		}
		for(int k:next){
			System.out.print(k+" ");
		}
	}

	int Index_KMP(String s, String t, int pos) {
		int i = pos, j = 0;
		while (i < s.length() && j < t.length()) {
			if (j == -1 || s.charAt(i) == t.charAt(j)) {
				++i;
				++j;
			} else
				j = next[j];
		}
		if (j >= t.length())
			return i - t.length();
		else
			return 0;
	}
}
