package wenyu.learning.Maths;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Solution1: Straight forward way. Simulate the whole process. O(n^2)
 * Solution2: Mathematics
 */

public class JosephusCycle {

	public static int solution1(int n, int m, int starter) {
		if(n < 1 || m < 1) {
			return -1;
		}
		List<Integer> numbers = new ArrayList<Integer>();
		for(int i=0; i<n; ++i) {
			numbers.add(i);
		}
		
		int current = starter-1;
		while(numbers.size()>1) {
			for(int i=0; i<m; ++i) {
				current = (current+1)%n;
			}
			
			numbers.remove(current);
			n--;
			current = (current-1)%n;
		}
		return numbers.get(current);
	}
	
	public static int solution2(int n, int m, int starter) {
		int s = 0;
		for (int i=2; i<=n; i++) {
			s=(s+m)%i;
		}
		return (s+starter) % n;
	}

	public static void main(String[] args) {
		int testCount = 10;
		Random random = new Random();
		while(testCount-- > 0) {
			int N = random.nextInt(1000);
			int M = random.nextInt(N)+1;
			int Starter = random.nextInt(N);
			int last = solution1(N, M, Starter);
			last = solution2(N, M, Starter);
			System.out.println("Solution1 - " + last);
			System.out.println("Solution2 - " + last);
			System.out.println("================================");
		}
	}

}
