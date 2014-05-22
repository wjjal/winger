package algorithm;

//最大连续和

public class MaxSubSeq {
	public static void main(String[] args){
		int data[] = {9,6,-18,3,8};
		int max = maxSubSeq(data);
		System.out.println(max);
	}
	static int maxSubSeq(int[] data) {
		int max = 0;
		int sum = 0;
		int len = data.length;
		for (int i = 0; i < len; i++) {
			sum += data[i];
			if (sum > max) {
				max = sum;
			}
			if (sum < 0)
				sum = 0;
		}
		return max;
	}
}
