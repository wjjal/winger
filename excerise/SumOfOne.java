package excerise;
//令f(x)=1到x出现的1的次数，求x=f(x)，如x=8时，f(x)=1；x=10时，f(x)=2；x=11时，f(x)=4.
public class SumOfOne {
	public static void main(String args[]) {
		int n = 2;
		int res = 1;
		while (getOnly(n) + res != n) {
			res += getOnly(n);
			System.out.println(n + ":" + res);
			n++;
		}
		System.out.println(n);
	}

	private static int getOnly(int n) {
		// TODO Auto-generated method stub
		int sum = 0;
		while (n > 0) {
			if (n % 10 == 1)
				sum++;
			n = n / 10;
		}
		return sum;
	}
}
