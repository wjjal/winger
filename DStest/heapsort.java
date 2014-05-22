package DStest;
public class heapsort {
	public static void main(String[] args) {
		int a[] = { 9, 4, 5, 1, 8, 3, 2, 7 };
		sort(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

	private static void sort(int[] a) {
		// TODO Auto-generated method stub
		int len = a.length;
		for (int i = (len - 1) / 2; i >= 0; i--) {
			adjust(a, i, len - 1); 
		}
		for (int i = len-1; i >=0; i--) {
			swap(a, 0, i);
			adjust(a, 0, i-1);
		}
	}

	private static void adjust(int[] a, int i, int range) {
		// TODO Auto-generated method stub
		int temp = a[i];
		for (int j = i * 2 + 1; j <= range; j = i * 2 + 1) {
			if (j < range && a[j] > a[j + 1])
				j = j + 1;
			if (temp > a[j]) {
				a[i] = a[j];
				i = j;
			}else
				break;
		}
		a[i] = temp;
	}
	
	private static void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
}
