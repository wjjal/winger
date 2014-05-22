package scrawl;

//实现一个挺高级的字符匹配算法：
//给一串很长字符串，要求找到符合要求的字符串，例如目的串：123
//1******3***2 ，12*****3 这些都要找出来，其实就是类似一些和谐系统。。。。。 
public class Func9 {
	public static void main(String[] args) {
          String src = "123";
          String des = "198372";
          System.out.println(is_contain(src,des));
	}
	static boolean is_contain(String src,String des){
		final int tablesize =256;
		int hashtable[] = new int[tablesize];
		for(int i =0;i<des.length();i++){
			hashtable[des.charAt(i)]=1;
		}
		for(int i=0;i<src.length();i++){
			if(hashtable[src.charAt(i)]==0){
				return false;
			}
		}
		return true;
	}	
}
