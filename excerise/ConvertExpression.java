package excerise;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import minstack.MyStack;

public class ConvertExpression {
	public static void main(String args[]) {
		System.out.print("Please input the infix expression:");
		Scanner scanner = new Scanner(System.in);
		String infix = scanner.nextLine();
		scanner.close();
		ArrayList suffix = comverttosuffix(infix);
		System.out.print("suffix expression = ");
		for (int i = 0, len = suffix.size(); i < len; i++) {
			System.out.print(suffix.get(i) + " ");
		}
		System.out.println();
		System.out.println("result=" + getResult(suffix));
	}

	private static ArrayList comverttosuffix(String infix) {
		ArrayList suffix = new ArrayList();
		MyStack<Character> stack = new MyStack<Character>();
		char[] chars = infix.toCharArray();
		int temp = 0;
		boolean flag = false;
		for (char c : chars) {
			if (c >= '0' && c <= '9') {
				temp = (c - '0') + 10 * temp;
				flag = true;
			} else {
				if (flag) {
					suffix.add(temp);
					temp = 0;
					flag = false;
				}
				switch (c) {
				case '(':
					stack.push(c);
					break;
				case ')':
					while (stack.peek() != '(') {
						suffix.add(stack.pop());
					}
					stack.pop();
					break;
				default:
					while (!stack.empty() && compare(stack.peek(), c)) {
						suffix.add(stack.pop());// 不断弹栈，直到当前的操作符的优先级高于栈顶操作符
					}
					stack.push(c);// 最后的标识符'#'是不入栈的
					break;
				}
			}
		}
		if (flag) {
			suffix.add(temp);
			flag = false;
		}
		while (!stack.empty()) {
			suffix.add(stack.pop());
		}
		return suffix;
	}

	// 比较运算符之间的优先级
	public static boolean compare(char peek, char cur) {// 如果是peek优先级高于cur，返回true，默认都是peek优先级要低
		if (peek == '*'
				&& (cur == '+' || cur == '-' || cur == '/' || cur == '*')) {// 如果cur是'('，那么cur的优先级高,如果是')'，是在上面处理
			return true;
		} else if (peek == '/'
				&& (cur == '+' || cur == '-' || cur == '*' || cur == '/')) {
			return true;
		} else if (peek == '+' && (cur == '+' || cur == '-')) {
			return true;
		} else if (peek == '-' && (cur == '+' || cur == '-')) {
			return true;
		} else if (cur == '#') {// 这个很特别，这里说明到了中缀表达式的结尾，那么就要弹出操作符栈中的所有操作符到后缀表达式中
			return true;// 当cur为'#'时，cur的优先级算是最低的
		}
		return false;// 开括号是不用考虑的，它的优先级一定是最小的,cur一定是入栈
	}

	private static int getResult(ArrayList suffix) {
		MyStack<Integer> stack = new MyStack<Integer>();
		for (int i = 0, len = suffix.size(); i < len; i++) {
			if (suffix.get(i).getClass() == Integer.class) {
				stack.push((Integer) suffix.get(i));
			} else {
				char s = (char) suffix.get(i);
				int a = stack.pop();
				int b = stack.pop();
				int c = 0;
				switch (s) {
				case '+':
					c = a + b;
					break;
				case '-':
					c = b - a;
					break;
				case '*':
					c = a * b;
					break;
				case '/':
					c = b / a;
					break;
				default:
					throw new RuntimeException();
				}
				stack.push(c);
			}
		}
		int result = stack.pop();
		if (!stack.empty())
			throw new RuntimeException();
		return result;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
}
