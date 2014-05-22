package algorithm;
//约瑟夫环
public class Josephus {
	public static void main(String[] args) {
		boolean[] people = new boolean[30];
		int temp = -1;
		for (int i = 0; i < 15; i++) {
			for(int j =0 ;j<9;j++){
				temp++;
				if(temp==30)
					temp = 0;
				while(people[temp]==true){
					temp++;
					if(temp==30)
						temp = 0;
				}
			}
			people[temp] = true;
		}
		
		for (int i = 0; i < 30; i++) {
			if (people[i] == true) {
				System.out.print(i + " ");
			}
		}
	}
}
