package wenyu.learning.Sort;

/**
 * Bucket is only suit for some values in some range
 * Since we need to create buckets
 *
 *  Best    Average     Worst    Worst (Space)
 * O(n+k)    O(n+k)     O(n*n)      O(nk)
 */

public class BucketSorting {
	public static int rangeMin = 0;
	public static int rangeMax = 100;
	
	public static int[] sort(int[] numbers) { 
		int[] buckets = new int[rangeMax-rangeMin+1];
		
		for(int i=0;i<numbers.length;i++) {
			if(numbers[i]<rangeMin || numbers[i]>rangeMax) {
				System.out.println("Number out of range...");
				return null;
			}
			buckets[numbers[i]-rangeMin]++;
		}
		return buckets;
	}
	
	private static void printNumbers(int[] buckets) {
		if(buckets == null) {
			return;
		}
		for(int i=0;i<buckets.length;i++) {
			while(buckets[i]>0) {
				System.out.print(i + " ");
				buckets[i]--;
			}
		}
	}
	
	
	public static void main(String[] args) {
		int[] data = {73, 22, 93, 43, 55, 14, 28, 15, 39, 11, 33, 11};
		printNumbers(BucketSorting.sort(data));
	}
}
