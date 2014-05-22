package quicksort;

public class Recursion_QuickSort {
	void quickSort(int[] data, int low, int high) {
		if (low < high) {
			int pos = partition(data, low, high);
			quickSort(data, low, pos - 1);
			quickSort(data, pos + 1, high);
		}
	}

	private int partition(int[] data, int low, int high) {
		int pos = mid(data, low, (low + high) / 2, high);
		int key = data[pos];
		data[pos] = data[low];
		while (low < high) {
			while (high > low && data[high] > key)
				high--;
			data[low] = data[high];
			while (low < high && data[low] <= key)
				low++;
			data[high] = data[low];
		}
		data[low] = key;
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
