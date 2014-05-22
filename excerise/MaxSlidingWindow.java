//给定一个数组A[]，有一个大小为w的滑动窗口，该滑动窗口从最左边滑到最后边。
//在该窗口中你只能看到w个数字，每次只能移动一个位置。我们的目的是找到每个窗口w个数字中的最大值，并将这些最大值存储在数组B中。


//最大堆解法在堆中保存有冗余的元素，比如原来堆中元素为[10 5 3]，新的元素为11，则此时堆中会保存有[11 5 3]。
//其实此时我们可以清空整个队列，然后再将11加入到队列即可，即只在队列中保持[11]。使用双向队列可以满足要求，
//滑动窗口的最大值总是保存在队列首部，队列里面的数据总是从大到小排列。
//当遇到比当前滑动窗口最大值更大的值时，则将队列清空，并将新的最大值插入到队列中。
//如果遇到的值比当前最大值小，则直接插入到队列尾部。
//每次移动的时候需要判断当前的最大值是否在有效范围，如果不在，则需要将其从队列中删除。
//由于每个元素最多进队和出队各一次，因此该算法时间复杂度为O(N)。

package excerise;

import java.util.ArrayDeque;

public class MaxSlidingWindow {
	public static void main(String[] args) {
		int[] a = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int w = 3;
		maxSlidingWindow(a, w);
	}

	private static void maxSlidingWindow(int[] a, int w) {
		// TODO Auto-generated method stub
		int b[] = new int[a.length - w + 1];
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 0; i < w; i++) {
			while (!queue.isEmpty() && a[i] >= a[queue.peek()])
				queue.poll();
			queue.offer(i);
		}
		for (int i = w; i < a.length; i++) {
			b[i - w] = a[queue.peek()];
			while (!queue.isEmpty() && a[i] >= a[queue.peek()])
				queue.poll();
			while (!queue.isEmpty() && queue.peek() < i - w)
				queue.poll();
			queue.offer(i);
		}
		b[a.length - w] = a[queue.peek()];
		for (int i : b) {
			System.out.print(i + " ");
		}
	}
}
