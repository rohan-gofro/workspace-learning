
public class DoComputation {
	
	public static void main(String[] args) {
		
		float a[] = {12 , 13 , 14};
		float x = 12 , y = 9;
		Compute obj = new Compute();
		System.out.println(obj.add(a));
		System.out.println(obj.divide(x, y));
		System.out.println(obj.subtract(x, y));
		
	}

}
