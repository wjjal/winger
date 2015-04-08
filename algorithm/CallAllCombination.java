package algorithm;

public class CallAllCombination {

	public static void main(String[] args) {
		String str[] = { "a", "b", "c", "d" };
		int nCnt = str.length;
		int nBit = 1 << nCnt;
		for (int i = 1; i < nBit; i++) {
			for (int j = 0; j < nCnt; j++) {
				if ((1 << j & i) != 0) {
					System.out.print(str[j]);
				}
			}
			System.out.println("");
		}
	}
}
