package algorithm;

//二分查找
public class Binary_Search {
	static int binary_search(int[] data, int num) {
		int begin = 0, end = data.length - 1;
		while (begin <= end) {
			//防止溢出，移位更高效
			int mid = begin + ((end - begin) >> 1);
			if (num == data[mid])
				return mid;
			else if (num < data[mid])
				end = mid - 1;
			else
				begin = mid + 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] data = { 1, 4, 5, 7 };
		int num = 5;
		int pos = binary_search(data, num);
		if (num == -1)
			System.out.println("Not Found");
		else
			System.out.println("The position is " + pos);
	}
}

