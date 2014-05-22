//使用一个辅助栈来保存最小元素，这个解法简单不失优雅。设该辅助栈名字为minimum stack，其栈顶元素为当前栈中的最小元素。这意味着
//要获取当前栈中最小元素，只需要返回minimum stack的栈顶元素即可。
//每次执行push操作，检查push的元素是否小于或等于minimum stack栈顶元素。如果是，则也push该元素到minimum stack中。
//当执行pop操作的时候，检查pop的元素是否与当前最小值相等。如果相同，则需要将改元素从minimum stack中pop出去。

package minstack;

public class MinStack {
	private MyStack<Integer> elements = new MyStack<Integer>();
	private MyStack<Integer> minstack = new MyStack<Integer>();

	void push(int e) {
		elements.push(e);
		if (minstack.empty() || e < minstack.peek())
			minstack.push(e);
	}

	int pop() {
		if (this.empty())
			throw new EmptyStackException();
		int e = elements.pop();
		if (e == minstack.peek())
			minstack.pop();
		return e;
	}

	int peek() {
		if (this.empty())
			throw new EmptyStackException();
		return elements.peek();
	}

	int Min() {
		if (minstack.empty())
			throw new EmptyStackException();
		return minstack.peek();
	}

	boolean empty() {
		return elements.empty();
	}
}
