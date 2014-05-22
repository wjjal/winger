//3、重排问题

//给定含有n个元素的整型数组a，其中包括0元素和非0元素，对数组进行排序，要求：
//1、排序后所有0元素在前，所有非零元素在后，且非零元素排序前后相对位置不变
//2、不能使用额外存储空间
//例子如下
//输入 0、3、0、2、1、0、0
//输出 0、0、0、0、3、2、1
//分析
//此排序非传统意义上的排序，因为它要求排序前后非0元素的相对位置不变，或许叫做整理会更恰当一些。
//我们可以从后向前遍历整个数组，遇到某个位置i上的元素是非0元素时，如果arr[k]为0，则将arr[i]赋值给arr[k]，arr[i]赋值为0。
//实际上i是非0元素的下标，而k是0元素的下标。

package array;

public class Arrange {
	public static void main(String[] args) {
		int array[] = { 0, 3, 0, 2, 1, 0, 0 };
		Arrange(array, array.length);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}

	static void Arrange(int arr[], int n) {
		int i, k = n - 1;
		for (i = n - 1; i >= 0; --i) {
			if (arr[i] != 0) {
				if (arr[k] == 0) {
					arr[k] = arr[i];
					arr[i] = 0;
				}
				--k;
			}
		}
	}
}
