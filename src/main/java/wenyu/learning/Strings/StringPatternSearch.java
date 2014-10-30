package wenyu.learning.Strings;

import java.math.BigInteger;
import java.util.Random;

/**
 * Searches for occurrences of a "word" W within a main "text string" S
 * @author wenychan
 *
 * Solution1: Brute force
 * Solution2: Rabin Karp
 * Solution3: KnuthMorrisPratt(KMP)  [using next method]
 */

class BruteForce {
	/**
	 * Prepare: O(0)
	 * Find: O(n*m)
	 */
	public static int search(String str, String pattern) {
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i) == pattern.charAt(0)) {
				int j = 1;
				for(;j<pattern.length()&&(i+j)<str.length();j++) {
					if(str.charAt(i+j) != pattern.charAt(j)) {
						break;
					}
				}
				if(j == pattern.length()) {
					return i;
				}
			}
		}
		return -1;
	}
}

class RabinKarp {
	/**
	 * Prepare: O(m)
	 * Worst Find: O(n*m)
	 */
    private String pat;      // the pattern  // needed only for Las Vegas
    private long patHash;    // pattern hash value
    private int M;           // pattern length
    private long Q;          // a large prime, small enough to avoid long overflow
    private int R;           // radix
    private long RM;         // R^(M-1) % Q

    public RabinKarp(int R, char[] pattern) {
        throw new UnsupportedOperationException("Operation not supported yet");
    }

    public RabinKarp(String pat) {
        this.pat = pat; // save pattern (needed only for Las Vegas)
        R = 256;
        M = pat.length();
        Q = longRandomPrime();

        // precompute R^(M-1) % Q for use in removing leading digit
        RM = 1;
        for (int i = 1; i <= M-1; i++)
           RM = (R * RM) % Q;
        patHash = hash(pat, M);
    } 

    // Compute hash for key[0..M-1]. 
    private long hash(String key, int M) { 
        long h = 0; 
        for (int j = 0; j < M; j++) 
            h = (R * h + key.charAt(j)) % Q; 
        return h; 
    } 

    // Las Vegas version: does pat[] match txt[i..i-M+1] ?
    private boolean check(String txt, int i) {
        for (int j = 0; j < M; j++) 
            if (pat.charAt(j) != txt.charAt(i + j)) 
                return false; 
        return true;
    }

    // Monte Carlo version: always return true
    private boolean check(int i) {
        return true;
    }

    // check for exact match
    public int search(String txt) {
        int N = txt.length(); 
        if (N < M) return N;
        long txtHash = hash(txt, M); 

        // check for match at offset 0
        if ((patHash == txtHash) && check(txt, 0))
            return 0;

        // check for hash match; if hash match, check for exact match
        for (int i = M; i < N; i++) {
            // Remove leading digit, add trailing digit, check for match. 
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q; 
            txtHash = (txtHash*R + txt.charAt(i)) % Q; 

            // match
            int offset = i - M + 1;
            if ((patHash == txtHash) && check(txt, offset))
                return offset;
        }

        // no match
        return N;
    }


    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    public static int search(String str, String pattern) {
        RabinKarp searcher = new RabinKarp(pattern);
        int offset = searcher.search(str);
        if(offset == str.length()) {
        	return -1;
        }
        return offset;
    }
}

class KnuthMorrisPratt {
	/**
	 * Prepare: O(m)
	 * Find: O(n)
	 */
	private String string;
	private String pattern;
	private int[] next;

	public static int search(String str, String pattern) {
		KnuthMorrisPratt ins = new KnuthMorrisPratt();
		ins.string = str;
		ins.pattern = pattern;
		ins.initNext();
		int idx = ins.match();
		return idx;
	}

	private void initNext() {
		next = new int[pattern.length()];
		char[] patternChr = pattern.toCharArray();
		int i = 0;
		next[0] = -1;
		for (int j = 1; j < pattern.length(); j++) {
			i = next[j - 1];
			while (i >= 0 && patternChr[j] != patternChr[i + 1]) {
				i = next[i];
			}
			if (patternChr[j] == patternChr[i + 1]) {
				next[j] = i + 1;
			} else {
				next[j] = -1;
			}
		}
	}

	private int match() {
		char[] strChar = string.toCharArray();
		char[] patternChar = pattern.toCharArray();
		int j = 0;
		for (int i = 0; i < strChar.length;) {
			if (strChar[i] == patternChar[j]) {
				if (j == patternChar.length - 1) {
					return i - patternChar.length + 1;
				}
				j++;
				i++;
			} else if (j == 0) {
				i++;
			} else {
				j = next[j - 1] + 1;
			}
		}

		return -1;
	}
}

public class StringPatternSearch {

	public static void main(String[] args) {
		String string = "ABCDAB ABABABCDBAABDE";
		String pattern = "ABABCDBA";
		int result = BruteForce.search(string, pattern);
		System.out.println("BruteForce: " + result);
		result = KnuthMorrisPratt.search(string, pattern);
		System.out.println("KnuthMorrisPratt: " + result);
		result = RabinKarp.search(string, pattern);
		System.out.println("RabinKarp: " + result);

	}

}
