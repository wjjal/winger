package polymorphism;
public class Base {
	int i;

	Base() {
		add(1);
	}

	void add(int v) {
		i += v;
		System.out.println(i);
	}

//	void print() {
//		System.out.println(i);
//	}
}
