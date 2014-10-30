package wenyu.learning.Sort;

import java.util.Arrays;
import java.util.Random;


public class CombinedSortingCode {
	static int arrayLen = 11;
	static int arrayValueMax = 100;
	
	public static int[] generateRandomArray() {
		int[] array = new int[arrayLen];
		Random random = new Random();
		for(int i=0;i<arrayLen;i++) {
			array[i] = random.nextInt(arrayValueMax);
		}
		System.out.println(Arrays.toString(array));
		return array;
	}
	
	public static void bubbleSort(int[] array) {
		if(array.length <= 1) {
			System.out.println(Arrays.toString(array));
			return;
		}
		
		for(int i=0; i<array.length; i++) {
			for(int j=1; j<array.length-i; j++) {
				if(array[j-1] > array[j]) {
					int tmp = array[j];
					array[j] = array[j-1];
					array[j-1] = tmp;
				}
			}
		}
		System.out.println(Arrays.toString(array));
	}
	
	public static void insertionSort(int[] array) {
		if(array.length <= 1) {
			System.out.println(Arrays.toString(array));
			return;
		}
		
		for(int i=1; i<array.length; i++) {
			int curr = array[i];
			int j = i-1;
			for(; j>=0&&array[j]>curr; j--) {
				array[j+1] = array[j];
			}
			array[j+1] = curr;
		}
		System.out.println(Arrays.toString(array));
	}
	
	public static void selectionSort(int[] array) {
		for(int i=0; i<array.length; i++) {
			int curr = array[i];
			int minIdx = i;
			for(int j=i+1; j<array.length; j++) {
				if(array[j]<array[minIdx]) {
					minIdx = j;
				}
			}
			
			if(array[minIdx] != curr) {
				array[i] = array[minIdx];
				array[minIdx] = curr;
			}
		}
		System.out.println(Arrays.toString(array));
	}
	
	
	private static int partition(int[] array, int start, int end) {
		int randomIdx = new Random().nextInt(end-start+1)+start;
		
		// Switch first item with the one of randomIdx
		int tmp = array[start];
		array[start] = array[randomIdx];
		array[randomIdx] = tmp;
		
		int piovtV = array[start];
		while(start<end) {
			while(start<end && array[end]>=piovtV) {end--;}
			if(start<end) {array[start++] = array[end];}
			while(start<end && array[start]<piovtV) {start++;}
			if(start<end) {array[end--] = array[start];}
		}
		
		array[start] = piovtV;
		return start;
	}
	public static void quickSort(int[] array, int start, int end) {
		if(start>=end) {
			return;
		}
		int idx = partition(array, start, end);
		quickSort(array, start, idx-1);
		quickSort(array, idx+1, end);
	}
	
	private static void merge(int[] array, int start, int mid, int end) {
		int[] auxArr = new int[end-start+1];
		int auxIdx = 0;
		int i = start;
		int j = mid+1;
		for(; i<=mid&&j<=end; ) {
			if(array[i]<array[j]) {
				auxArr[auxIdx++] = array[i++];
			} else {
				auxArr[auxIdx++] = array[j++];
			}
		}
		
		while(i<=mid) {
			auxArr[auxIdx++] = array[i++];
		}
		
		while(j<=end) {
			auxArr[auxIdx++] = array[j++];
		}
		
		for(i=start, auxIdx=0; i<=end; i++) {
			array[i] = auxArr[auxIdx++];
		}
	}
	public static void mergeSortRec(int[] array, int start, int end) {
		if(start>=end) {
			return;
		}
		
		int mid = start + (end-start)/2;
		mergeSortRec(array, start, mid);
		mergeSortRec(array, mid+1, end);
		merge(array, start, mid, end);
	}
	
	public static void mergeSortNoRec(int[] array) {
		for(int i=1; i<array.length; i*=2) {
			for(int j=0; j<array.length-i; j+=i*2) {
				int start = j;
				int mid = j+i-1;
				int end = j+i*2-1;
				if(end>=array.length) {
					end = array.length-1;
				}
				merge(array, start, mid, end);
			}
		}
		
		System.out.println(Arrays.toString(array));
	}
	
	public static void heapSort(int[] array) {
		// build heap (percolate up)
		for(int i=1; i<array.length; i++) {
			int insert = array[i];
			int hole = i+1;
			for(;hole>1&&insert>array[hole/2-1]; hole/=2) {
				array[hole-1] = array[hole/2-1];
			}
			array[hole-1] = insert;
		}
		
		// sort heap (percolate down)
		for(int i=array.length-1; i>0; i--) {
			int tmp = array[i];
			array[i] = array[0];
			array[0] = tmp;
			
			int hole = 1;
			int current = array[hole-1];
			int child;
			for(; hole*2<=i; hole=child) {
				child = hole*2;
				if(child!=i && array[child]>array[child-1]) {
					child++;
				} 
				
				if(current<array[child-1]) {
					array[hole-1] = array[child-1];
				} else {
					break;
				}
			}
			array[hole-1] = current;
		}
		
		System.out.println(Arrays.toString(array));
	}
	
	public static void bucketSort(int[] array, int rangeMin, int rangeMax) throws Exception {
		int[] bucket = new int[rangeMax-rangeMin+1];
		for(int i=0; i<array.length; i++) {
			if(array[i]>rangeMax || array[i]<rangeMin) {
				throw new Exception("Value in array out of range.");
			}
			
			bucket[array[i]-rangeMin]++;
		}
		
		for(int i=0, j=0; j<bucket.length;) {
			while(i<array.length&&bucket[j]-->0) {
				array[i++] = j + rangeMin;
			}
			j++;
		}
		
		System.out.println(Arrays.toString(array));
	}
	
	public static void radixSortLSD(int[] array, int longestBit) {
		int currBit = 1;
		int bucketNum = 10;
		int[][] bucket = new int[bucketNum][array.length];
		int[] bucketCount = new int[bucketNum];
		while (currBit<=longestBit) {
			for (int i=0; i<array.length; i++) {
				int lsd = ((array[i]/(int)(Math.pow(bucketNum, currBit-1))) % bucketNum);
				bucket[lsd][bucketCount[lsd]] = array[i];
				bucketCount[lsd]++;
			}
			
			int k = 0;
			for (int i=0; i<bucketNum; i++) {
				if (bucketCount[i] != 0)
					for (int j=0; j<bucketCount[i]; j++) {
						array[k] = bucket[i][j];
						k++;
					}
				bucketCount[i] = 0;
			}
			k = 0;
			currBit++;
		}
		
		System.out.println(Arrays.toString(array));
	}

	public static void radixSortMSD(int[] array, int numCount, int longestBit) {
		if(longestBit<=0) {
			return;
		}
		
		int bucketNum = 10;
		int[][] bucket = new int[bucketNum][array.length];
		int[] bucketCount = new int[bucketNum];
		
		for(int i=0;i<numCount;i++) {
			int lsd = array[i]/(int)Math.pow(10, longestBit-1);
			lsd = lsd%10;
			bucket[lsd][bucketCount[lsd]] = array[i];
			bucketCount[lsd]++;
		}
		
		for(int i=0;i<bucket.length;i++) {
			if(bucketCount[i]>1) {
				radixSortMSD(bucket[i], bucketCount[i], longestBit-1);
			}
		}
		
		int k = 0;
		for(int i=0; i<bucketNum; i++) {
			if(bucketCount[i] != 0)
				for (int j=0; j<bucketCount[i]; j++) {
					array[k] = bucket[i][j];
					k++;
				}
			bucketCount[i] = 0;
		}

	}
	
	public static void main(String[] args) throws Exception {
		int[] array = null;
		
		System.out.println("bubbleSort:");
		bubbleSort(generateRandomArray());
		System.out.println();
		
		System.out.println("insertionSort:");
		insertionSort(generateRandomArray());
		System.out.println();
		
		System.out.println("selectionSort:");
		selectionSort(generateRandomArray());
		System.out.println();
		
		System.out.println("quickSort:");
		array = generateRandomArray();
		quickSort(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
		System.out.println();
		
		System.out.println("mergeSortRec:");
		array = generateRandomArray();
		mergeSortRec(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
		System.out.println();
		
		System.out.println("mergeSortNoRec:");
		mergeSortNoRec(generateRandomArray());
		System.out.println();
		
		System.out.println("heapSort:");
		heapSort(generateRandomArray());
		System.out.println();
		
		System.out.println("bucketSort:");
		bucketSort(generateRandomArray(), 0, arrayValueMax);
		System.out.println();
		
		System.out.println("radixSortLSD:");
		radixSortLSD(generateRandomArray(), (int) Math.log10(arrayValueMax-1)+1);
		System.out.println();
		
		System.out.println("radixSortMSD:");
		array = generateRandomArray();
		radixSortMSD(array, arrayLen, (int) Math.log10(arrayValueMax-1)+1);
		System.out.println(Arrays.toString(array));
		System.out.println();
	}
}