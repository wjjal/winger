package algorithm;

//判断整数序列是不是二元查找树的后序遍历结果

public class VerifySquenceOfBST {
	private boolean verifySquenceOfBST(int a[], int length) {
		int root = a[length - 1];
		int i = 0;
		for (; i < length - 1; i++) {
			if (a[i] > root)
				break;
		}
		int j = i;
		for (; j < length - 1; j++) {
			if (a[j] < root)
				return false;
		}

		boolean left = true, right = true;
		if (i > 0)
			left = verifySquenceOfBST(a, i);
		if (i < length - 1)
			right = verifySquenceOfBST(a, length - i - 1);
		return left && right;
	}

	public static void main(String args[]) {
         int array[] = {5,7,6,9,11,10,8};
         int length = array.length;
         System.out.println(new VerifySquenceOfBST().verifySquenceOfBST(array, length));
         
	}
}
