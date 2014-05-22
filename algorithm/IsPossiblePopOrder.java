package algorithm;

import java.util.Stack;

//输入两个整数序列。其中一个序列表示栈的push顺序，判断另一个序列有没有可能是对应的pop顺序。

public class IsPossiblePopOrder {
	public static void main(String[] args) {
		int[] pushorder = { 1, 2, 3, 4, 5, 6, 7 };
		int[] poporder = { 7, 5, 6, 4, 3, 2, 1 };
		System.out.println(isPossiblePopOrder(pushorder, poporder));
	}

	static boolean isPossiblePopOrder(int[] pushorder, int[] poporder) {
		Stack<Integer> stack = new Stack<Integer>();
		int i = 0, j = 0;
		for (i = 0; i < pushorder.length; i++) {
			stack.push(pushorder[i]);
			if (pushorder[i] != poporder[j]) {
				continue;
			}
			while (!stack.empty() && stack.peek() == poporder[j]) {
				stack.pop();
				j++;
			}
		}
		if (j == poporder.length)
			return true;
		else
			return false;
	}
}
