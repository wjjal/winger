package shellsort;

public class ShellSort {
	private void ShellInsert(int[] array, int len, int ader) {
		for (int i = ader; i < len; i++) {
			if (array[i] < array[i - ader]) {
				int key = array[i];
				int j = i - ader;
				for (; j >= 0 && array[j] > key; j -= ader)
					array[j + ader] = array[j];
				array[j + ader] = key;
			}
		}
	}

	public void shellSort(int[] array, int len) {
		int ader = len / 2;
		while (ader >= 1) {
			ShellInsert(array, len, ader);
			ader /= 2;
		}
	}
}
