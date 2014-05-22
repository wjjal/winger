package union_find_set;

import java.util.HashMap;
import java.util.Scanner;

//题目： 亲戚(Relations)
//或许你并不知道，你的某个朋友是你的亲戚。他可能是你的曾祖父的外公的女婿的外甥的表姐的孙子。
//如果能得到完整的家谱，判断两个人是否亲戚应该是可行的，但如果两个人的最近公共祖先与他们相隔好几代，使得家谱十分庞大，
//那么检验亲戚关系实非人力所能及.在这种情况下，最好的帮手就是计算机。
//为了将问题简化，你将得到一些亲戚关系的信息，如同Marry和Tom是亲戚，Tom和B en是亲戚，等等。
//从这些信息中，你可以推出Marry和Ben是亲戚。请写一个程序，对于我们的关心的亲戚关系的提问，以最快的速度给出答案。
//参考输入输出格式 输入由两部分组成。
//第一部分以N，M开始。N为问题涉及的人的个数(1 ≤ N ≤ 20000)。这些人的编号为1,2,3,…,N。
//下面有M行(1 ≤ M ≤ 1000000)，每行有两个数ai, bi，表示已知ai和bi是亲戚.
//第二部分以Q开始。以下Q行有Q个询问(1 ≤ Q ≤ 1 000 000)，每行为ci, di，表示询问ci和di是否为亲戚。
//对于每个询问ci, di，若ci和di为亲戚，则输出Yes，否则输出No。
//样例输入与输出
//输入relation.in
//10 7
//2 4
//5 7
//1 3
//8 9
//1 2
//5 6
//2 3
//3
//3 4
//7 10
//8 9
//输出relation.out
//Yes
//No
//Yes
public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input m and n:");
		int n = sc.nextInt();
		int m = sc.nextInt();
//		HashMap map = new HashMap();
//		int num = 1;
		UnionFindSet<?>[] unions = new UnionFindSet<?>[n + 1];
		System.out.println("Please input the " + n + "groups data:");
		for (int i = 1; i <= n; i++) {
			unions[i] = new UnionFindSet(i);
		}
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			UnionFindSet.union(unions[x], unions[y]);
		}
		System.out.println("Please input the number of queries:");
		int q = sc.nextInt();
		System.out.println("Please input " + q + "groups queries:");
		for (int i = 0; i < q; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			System.out.println(UnionFindSet.same(unions[x], unions[y]));
		}
	}
}
