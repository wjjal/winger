package excerise;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Combination {
	public static void main(String[] args) {
		String[] a = {"a","b","c","d"};
		ListAll(Arrays.asList(a),"");
	}

	private static void ListAll(List<String> list, String prefix) {
		// TODO Auto-generated method stub
		System.out.println(prefix);
		
		for(int i = 0 ; i<list.size();i++){
			List temp = new LinkedList(list);
			ListAll(temp,prefix+temp.remove(i));
		}
	}
}
