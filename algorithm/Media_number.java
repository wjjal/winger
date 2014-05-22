package algorithm;

//两个有序数列，求中位数
//偶数序列，中位数应该是有序数列中间两个数字的平均值。 
//奇数序列，中位数是有序数列的中间那个数字。 
public class Media_number {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 11, 15 ,65,66};
		int[] b = { 6, 7, 9, 10, 11 };
		System.out.println(find_median_random_length(a, 0, a.length, b, 0,
				b.length));
		int alen = a.length;
		int blen = b.length;
		int astart = 0;
		int aend = alen - 1;
		int bstart = 0;
		int bend = blen - 1;
		int amid = 0;
		int bmid = 0;
		int mid = 0;
		while (astart <= aend && bstart <= bend) {
			amid = (astart + aend) / 2;
			bmid = (bstart + bend) / 2;
			if (astart == aend) {
				// b.len为偶数，a.len为奇数，取中间数即可
				if ((bend - bstart + 1) % 2 == 0) {
					if (a[amid] <= b[bmid]) {
						mid = b[bmid];
					} else if (a[amid] >= b[bmid + 1])
						mid = b[bmid + 1];
					else
						mid = a[amid];
				}
				// b.len为奇数，a.len为奇数，需取中间两数
				else {
					if (a[amid] <= b[bmid - 1])
						mid = (b[bmid - 1] + b[bmid]) / 2;
					else if (a[amid] >= b[bmid + 1])
						mid = (b[bmid] + b[bmid + 1]) / 2;
					else
						mid = (a[amid] + b[bmid]) / 2;
				}
				astart = aend + 1;
			}
			if (a[amid] == b[bmid]) {
				mid = a[amid];
				astart = aend + 1;
			}
			if (a[amid] < b[bmid]) {
				bend -= amid + 1 - astart;
				astart = amid + 1;
			}
			if (a[amid] > b[bmid]) {
				bstart += aend - amid;
				aend = amid;
			}
		}
		System.out.println(mid);
	}

	static int find_median_random_length(int[] a, int starta, int lengtha,
			int[] b, int startb, int lengthb) {
		int mida = lengtha / 2;
		int midb = lengthb / 2;
		int len = mida <= midb ? mida : midb;
		mida = starta + mida;
		midb = startb + midb;
		if (lengtha == 1) {
			if (lengthb % 2 == 0) {
				if (a[starta] >= b[midb])
					return b[midb];
				else if (a[starta] <= b[midb - 1])
					return b[midb - 1];
				return a[starta];
			} else {
				if (a[mida] <= b[midb - 1])
					return (b[midb - 1] + b[midb]) / 2;
				else if (a[mida] >= b[midb + 1])
					return (b[midb] + b[midb + 1]) / 2;
				else
					return (a[mida] + b[midb]) / 2;
			}
		} else if (lengthb == 1) {
			if (lengtha % 2 == 0) {
				if (b[startb] >= a[mida])
					return a[mida];
				else if (b[startb] <= a[mida - 1])
					return a[mida - 1];
				return b[startb];
			} else {
				if (b[mida] <= a[midb - 1])
					return (a[midb - 1] + a[midb]) / 2;
				else if (b[mida] >= a[midb + 1])
					return (a[midb] + a[midb + 1]) / 2;
				else
					return (a[mida] + b[midb]) / 2;
			}
		}
		if (a[mida] == b[midb])
			return a[mida];
		else if (a[mida] < b[midb])
			return find_median_random_length(a, starta + len, lengtha - len, b,
					startb, lengthb - len);
		else
			return find_median_random_length(a, starta, lengtha - len, b,
					startb + len, lengthb - len);
	}
}
