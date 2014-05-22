package heapsort;

public class HeapSort {
	public static final HeapSort INSTANCE = new HeapSort();

	private HeapSort() {
	}

	void heapSort(int[] data) {
		int len = data.length;
		for (int i = (len - 1) / 2; i >= 0; i--)
			heapAdjust(data, i, len - 1);
		for (int i = len - 1; i > 0; i--) {
			swap(data, 0, i);
			heapAdjust(data, 0, i - 1);
		}
	}

	private void heapAdjust(int[] data, int root, int range) {
		int i = root;
		int temp = data[i];
		//构建大顶堆
		for (int j = 2 * i + 1; j <= range; j = j * 2 + 1) {
			if (j < range && data[j] < data[j + 1])
				j++;
			if (temp < data[j]) {
				data[i] = data[j];
				i = j;
			} else
				break;
		}
		data[i] = temp;
	}

	private void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

}
