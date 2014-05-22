package minstack;

import java.util.ArrayList;

public class MyStack<E> {
	ArrayList<E> stack = new ArrayList<E>();

	public E push(E e) {
		stack.add(e);
		return e;
	}

	public E pop() {
		E e = peek();
		stack.remove(stack.size() - 1);
		return e;
	}

	public E peek() {
		if (stack.isEmpty())
			throw new EmptyStackException();
		return stack.get(stack.size() - 1);
	}

	public boolean empty() {
		return stack.isEmpty();
	}

	public int search(Object o) {
		int i = stack.lastIndexOf(o);
		if (i >= 0) {
			return stack.size() - i;
		}
		return -1;
	}
}
