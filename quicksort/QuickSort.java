package quicksort;

import java.util.LinkedList;

public class QuickSort {
	void quickSort(int[] data) {
		try {
			if (data.length == 0)
				throw new NullPointerException();
			int low = 0, high = data.length - 1;
			LinkedList<QSPass> stack = new LinkedList<QSPass>();
			stack.push(new QSPass(low, high));
			while (!stack.isEmpty()) {
				QSPass temp = stack.pop();
				low = temp.getLow();
				high = temp.getHigh();
				int pivotloc = partition(data, low, high);
				if (low < pivotloc - 1)
					stack.push(new QSPass(low, pivotloc - 1));
				if (high > pivotloc + 1)
					stack.push(new QSPass(pivotloc + 1, high));
			}
		}catch(NullPointerException e){
			System.out.println("The array is not unassigned!");
		}
	}

	private int partition(int[] data, int low, int high) {
		int pos = mid(data, low, (low + high) / 2, high);
		int pivotkey = data[pos];
		data[pos] = data[low];
		while (low < high) {
			while (low < high && data[high] > pivotkey)
				high--;
			data[low] = data[high];
			while (low < high && data[low] <= pivotkey)
				low++;
			data[high] = data[low];
		}
		data[low] = pivotkey;
		return low;
	}

	private int mid(int[] data, int i, int j, int k) {
		int max, min;
		if (data[i] >= data[j]) {
			max = i;
			min = j;
		} else {
			max = j;
			min = i;
		}
		if (data[k] > data[max])
			return max;
		else if (data[k] < min)
			return min;
		else
			return k;
	}
}
