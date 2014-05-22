package minstack;

public class Test {
	public static void main(String[] args) {
		MinStack stack = new MinStack();
		OtherMinStack otherstack = new OtherMinStack();
		for (int i = 10; i >= 1; i--) {
			stack.push(i);
			otherstack.push(i);
		}
		while (!stack.empty()) {
			try {
				System.out.println(stack.Min());
				System.out.println(otherstack.peek());
				stack.pop();
				otherstack.pop();
			} catch (EmptyStackException e) {
				System.out.println("Stack is empty, no min element!");
				e.printStackTrace();
			}
		}
	}
}
