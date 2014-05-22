package algorithm;

import java.util.Scanner;

public class WubiBianMa {
	static final int[] assist = { 1 + 25 + 25 * 25 + 25 * 25 * 25,
			1 + 25 + 25 * 25, 1 + 25, 1 };

	public static void main(String[] args) {
		int index = 0;
		System.out.print("please input code or index:");
		String in = new Scanner(System.in).next();
		try {
			index = Integer.parseInt(in);
			char code[] = new char[4];
			int i = 0;
			while(index>=0){
				code[i] = (char) ('a' + index / (assist[i]));
				index = index % assist[i];
				index--;
				System.out.print(code[i]);
				i++;
			}
		} catch (NumberFormatException e) {
			String code = in;
			if (code.length() > 4) {
				System.out.println("Code is invalid!");
				return;
			}
			for (int i = 0; i < code.length(); i++) {
				index += (code.charAt(i) - 'a') * assist[i];
			}
			index = index + code.length() - 1;
			System.out.println("index:" + index);
		}
	}
}
