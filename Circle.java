import java.util.Scanner;

public class Circle {

	private static double radius;
	
	public Circle() {
	
	}
	
	public int getRadius() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please Enter in a radius:");
		
		this.radius(scanner);
		
		return this.getRadius();
	}
	
	private void radius(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
