package maxoverlapline;
//一维空间中求最大重叠线段长度，线段表示为(x, y)，x表示起点坐标，y表示终点坐标，
//比如线段(2, 5), (6, 12), (7, 13), 则最大重叠线段为(7, 12), 长度为5。


public class FindMaxOverlap {	
	public static final int LINE_NUMBER = 10;
	 
	public static Line[] lines;
	//已经按开始时间排好序的时间段数组
	private static void prepareData() {
		lines = new Line[LINE_NUMBER];
		lines[0] = new Line(0, 3);
		lines[1] = new Line(5, 8);
		lines[2] = new Line(6, 10);
		lines[3] = new Line(8, 9);
		lines[4] = new Line(15, 23);
		lines[5] = new Line(16, 21);
		lines[6] = new Line(17, 19);
		lines[7] = new Line(19, 20);
		lines[8] = new Line(25, 30);
		lines[9] = new Line(26, 26);
	}

	public static void main(String[] args) {
		prepareData();
		int maxLength = 0;
		int current_max_y = lines[0].getY();
		int current_min_y = 0;
		for(int i = 1;i<LINE_NUMBER;i++){
			if(current_max_y>lines[i].getY()){
				current_min_y = lines[i].getY();
			}
			else{
				current_min_y = current_max_y;
				current_max_y = lines[i].getY();
			}
			if(current_min_y - lines[i].getX()>maxLength){
				maxLength = current_min_y - lines[i].getX();				
			}
		}
		System.out.println(maxLength);
	}
}
