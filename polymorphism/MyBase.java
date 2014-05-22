package polymorphism;
public class MyBase extends Base {
	MyBase() {
		add(2);
	}

	void add(int v) {
		i += v * 2;
		System.out.println(i);
	}
}
