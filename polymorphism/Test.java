package polymorphism;
public class Test {
	public static void main(String[] args) {
		go(new MyBase());
	}

	static void go(Base b) {
		// TODO Auto-generated method stub
		b.add(8);
	}
}
