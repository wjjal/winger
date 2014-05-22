//另外一种解法利用存储差值而不需要辅助栈，方法比较巧妙。其中需要说明的几点：
//push(int elem)函数在栈中压入当前元素与当前栈中最小元素的差值，
//然后通过比较当前元素与当前栈中最小元素大小，并将它们中间的较小值压入。
//pop()函数执行的时候，先pop出栈顶的两个值，这两个值分别是当前栈中最小值min和最后压入的元素与栈中最小值的差值diff。
//如果diff<0，则表示最后压入栈的元素是最小的元素，因此只需将min-diff压入栈中，并将min值返回即可。
//min-diff就是当前元素弹出后，栈中剩下元素的最小值。而如果diff>=0且栈不为空，则表示当前值不是最小值，
//所以需要在栈中压入最小值min并将diff+min返回；如果栈为空，则表示已经是最后一个数字，直接返回min即可。

package minstack;

public class OtherMinStack {
	private MyStack<Integer> stack = new MyStack<Integer>();

	void push(int e) {
		if (this.empty()) {
			stack.push(e);
			stack.push(e);
		} else {
			int min = stack.peek();
			stack.pop();
			stack.push(e - min);
			stack.push((e - min < 0) ? e : min);
		}
	}

	int pop() {
		if (this.empty())
			throw new EmptyStackException();
		int min = stack.pop();
		int diff = stack.pop();
		if (diff < 0) {
			stack.push(min - diff);
			return min;
		} else {
			if (!this.empty()) {
				stack.push(min);
				return diff + min;
			}
			return min;
		}
	}

	int peek() {
		if (this.empty())
			throw new EmptyStackException();
		int min = stack.pop();
		int diff = stack.pop();
		int top;
		if (diff < 0)
			top = min;
		else if (stack.empty())
			top = min;
		else
			top = min + diff;
		stack.push(diff);
		stack.push(min);
		return top;
	}

	int Min() {
		return stack.peek();
	}

	boolean empty() {
		return stack.empty();
	}
}
