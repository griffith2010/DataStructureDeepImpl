package wenyu.learning.Graphs;


/*
 * Check if rectangles overlap
 * Logic:
 * 		A much easier and better approach would be to think from the opposite. 
 * 		How about asking yourself how the two rectangles cannot overlap each other? 
 * 		Two rectangles do not overlap when one is above/below, or to the left/right of the other rectangle.
 */
public class CheckRectanglesOverlap {

	class Rectangle {
		int x1,y1; //left-top corner
		int x2,y2; //right-bottom corner
		public Rectangle(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}
	}
	
	public static boolean checkOverlap(Rectangle rectangle1, Rectangle rectangle2) {
		//rectangle1 is above rectangle2 scenario
		boolean above = rectangle1.y2<rectangle2.y1;
		

		//rectangle1 is below rectangle2 scenario
		boolean below = rectangle1.y1>rectangle2.y2;
		
		//rectangle1 is left rectangle2 scenario
		boolean left = rectangle1.x2<rectangle2.x1;

		//rectangle1 is right rectangle2 scenario
		boolean right = rectangle1.x1>rectangle2.x1;
		
		boolean overlap = !(above||below||left||right);
		return overlap;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
