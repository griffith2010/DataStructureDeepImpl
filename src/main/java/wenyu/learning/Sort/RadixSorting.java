package wenyu.learning.Sort;

/**
 * Best    Average    Worst   Worst (Space)
 * O(nk)    O(nk)     O(nk)      O(n+k)
 */

public class RadixSorting {
	public static void sortString(String[] strings, int longestBit) {
		int currBit = 1;
		int bucketNum = 256+1; // Additional one bit for the string which is smaller than the longestBit size
		String[][] bucket = new String[bucketNum][strings.length];
		int[] bucketCount = new int[bucketNum];
		
		while (currBit<=longestBit) {
			for (int i=0; i<strings.length; i++) {
				String currString = strings[i];
				if(longestBit-currBit>=currString.length()) {
					// current string is smaller than the longestBit size
					// And then put it into the last bucket
					bucket[256][bucketCount[256]] = strings[i];
					bucketCount[256]++;
				} else {
					char currChar = currString.charAt(longestBit-currBit);
					bucket[currChar][bucketCount[currChar]] = strings[i];
					bucketCount[currChar]++;
				}
			}
			
			int k = 0;
			if(bucketCount[256] != 0) {
				for (int j=0; j<bucketCount[256]; j++) {
					strings[k] = bucket[256][j];
					k++;
				}
				bucketCount[256] = 0;
			}
			for (int i=0; i<bucketNum-1; i++) {
				if (bucketCount[i] != 0)
					for (int j=0; j<bucketCount[i]; j++) {
						strings[k] = bucket[i][j];
						k++;
					}
				bucketCount[i] = 0;
			}
			k = 0;
			currBit++;
		}
		
	}
	
	public static void sortNumberLSD(int[] numbers, int longestBit) {
		int currBit = 1;
		int bucketNum = 10;
		int[][] bucket = new int[bucketNum][numbers.length];
		int[] bucketCount = new int[bucketNum];
		while (currBit<=longestBit) {
			for (int i=0; i<numbers.length; i++) {
				int lsd = ((numbers[i]/(int)(Math.pow(bucketNum, currBit-1))) % bucketNum);
				bucket[lsd][bucketCount[lsd]] = numbers[i];
				bucketCount[lsd]++;
			}
			
			int k = 0;
			for (int i=0; i<bucketNum; i++) {
				if (bucketCount[i] != 0)
					for (int j=0; j<bucketCount[i]; j++) {
						numbers[k] = bucket[i][j];
						k++;
					}
				bucketCount[i] = 0;
			}
			k = 0;
			currBit++;
		}
	}

	public static void sortNumberMSD(int[] numbers, int numCount, int longestBit) {
		if(longestBit<=0) {
			return;
		}
		
		int bucketNum = 10;
		int[][] bucket = new int[bucketNum][numbers.length];
		int[] bucketCount = new int[bucketNum];
		
		for(int i=0;i<numCount;i++) {
			int lsd = numbers[i]/(int)Math.pow(10, longestBit-1);
			lsd = lsd%10;
			bucket[lsd][bucketCount[lsd]] = numbers[i];
			bucketCount[lsd]++;
		}
		
		for(int i=0;i<bucket.length;i++) {
			if(bucketCount[i]>1) {
				sortNumberMSD(bucket[i], bucketCount[i], longestBit-1);
			}
		}
		
		int k = 0;
		for(int i=0; i<bucketNum; i++) {
			if(bucketCount[i] != 0)
				for (int j=0; j<bucketCount[i]; j++) {
					numbers[k] = bucket[i][j];
					k++;
				}
			bucketCount[i] = 0;
		}
	}
	
	public static void main(String[] args) {
		int[] data = { 73, 22, 93, 43, 55, 14, 28, 115, 39, 181, 33, 110 };
		RadixSorting.sortNumberLSD(data, 3);
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		
		System.out.println();
		
		int[] data1 = { 73, 22, 93, 43, 55, 14, 28, 115, 39, 181, 33, 110 };
		RadixSorting.sortNumberMSD(data1, data1.length, 3);
		for (int i = 0; i < data1.length; i++) {
			System.out.print(data1[i] + " ");
		}
		
		System.out.println();
		
		String[] strData = {"73", "22", "93", "221", "65", "10", "101"};
//		String[] strData = {"76", "93", "65"};
		RadixSorting.sortString(strData,2);
		for (int i = 0; i < strData.length; i++) {
			System.out.print(strData[i] + " ");
		}
	}
}