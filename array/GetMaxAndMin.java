//2、试着用最小的比较次数去寻找数组中的最大值和最小值。

//解法一：
//扫描一次数组找出最大值；再扫描一次数组找出最小值。
//比较次数2N-2

//解法二：
//将数组中相邻的两个数分在一组， 每次比较两个相邻的数，将较大值交换至这两个数的左边，较小值放于右边。
//对大者组扫描一次找出最大值，对小者组扫描一次找出最小值。
//比较1.5N-2次，但需要改变数组结构

//解法三：
//每次比较相邻两个数，较大者与MAX比较，较小者与MIN比较，找出最大值和最小值。
//方法如下：先将一对元素互相进行比较，然后把最小值跟当前最小值进行比较，把最大值跟当前最大值进行比较。
//因此每两个元素需要3次比较。如果n为奇数，那么比较的次数是3*(n/2)次比较。如果n为偶数，那么比较的次数是3n/2-2次比较。
//因此，不管是n是奇数还是偶数，比较的次数至多是3*(n/2)，具体的代码如下：

package array;

public class GetMaxAndMin {
	public static void main(String[] args) {
		int array[] = { 7, 10, 2, 6, 19, 22, 32 };
		GetMaxAndMin(array, array.length, 0, 0);
	}

	static void GetMaxAndMin(int arr[], int n, int max, int min) {
		int i = 0;
		if ((n & 1) != 0) // 奇数
		{
			max = min = arr[i++];
		} else {
			if (arr[0] > arr[1]) {
				max = arr[0];
				min = arr[1];
			} else {
				max = arr[1];
				min = arr[0];
			}
			i += 2;
		}

		for (; i < n; i += 2) {
			if (arr[i] > arr[i + 1]) {
				if (arr[i] > max)
					max = arr[i];
				if (arr[i + 1] < min)
					min = arr[i + 1];
			} else {
				if (arr[i + 1] > max)
					max = arr[i + 1];
				if (arr[i] < min)
					min = arr[i];
			}
		}
		System.out.println("max:" + max + ",min:" + min);
	}
}
