package algorithm;
//Longest Increasing Subsequence，最长上升（不下降）子序列
public class LIS_DP {
	static void LIS(int[] data) {
		int len = data.length;
		int[] assist = new int[len + 1];
		assist[1] = data[0];
		int max = 1;
		for (int i = 1; i < len; i++) {
			int low = 1, high = max, mid = 0;
			while (low <= high) {
				mid = (low + high) / 2;
				if (assist[mid] < data[i])
					low = mid + 1;
				else if (assist[mid] == data[i]) {
					low = mid;
					break;
				} else
					high = mid - 1;
			}
			assist[low] = data[i];
			System.out.println(low + " "+ assist[low]);
			if (low > max)
				max++;
		}
		System.out.println(max);
	}
	public static void main(String[] args){
		int[] data= {7,8,1,2,3};
		LIS(data);
	}
}
