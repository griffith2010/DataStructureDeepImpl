package wenyu.learning.Graphs;

class fPoint {
	public float x;
	public float y;

	public fPoint(float x, float y) {
		this.x = x;
		this.y = y;
	}
}

public class VerifyPointInTriangle {
	public static float sign(fPoint p1, fPoint p2, fPoint p3) {
		return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
	}

	public static boolean PointInTriangle(fPoint pt, fPoint v1, fPoint v2, fPoint v3) {
		boolean b1, b2, b3;
		// to make sure the point's sign from the edge plane being always positive (or negative) for each planes
		b1 = sign(pt, v1, v2) < 0.0f;
		b2 = sign(pt, v2, v3) < 0.0f;
		b3 = sign(pt, v3, v1) < 0.0f;

		return ((b1 == b2) && (b2 == b3));
	}

	public static void main(String[] args) {
		fPoint v1 = new fPoint(0,0);
		fPoint v2 = new fPoint(1,0);
		fPoint v3 = new fPoint(0,1);
		
		fPoint pt = new fPoint(1f,1f);
		boolean result = PointInTriangle(pt, v1, v2, v3);
		if(result) {
			System.out.println("Inside");
		} else {
			System.out.println("Outside");
		}
	}

}