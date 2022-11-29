package Final_Project;


import java.util.List;

public class BoyerMoore
{
	private int[] right;
	private String pattern;
	BoyerMoore(String pattern)
	{ // Compute skip table.
		
		if (pattern == null) {
			return;
		}
		this.pattern = pattern;
		int M = pattern.length();
		int R = 256;
		right = new int[R];
		for (int c = 0; c < R; c++)
		right[c] = -1; // -1 for chars not in patterntern
		for (int j = 0; j < M; j++) // rightmost position for
		right[pattern.charAt(j)] = j; // chars in patterntern
	}
	public int search(String txt)
	{ // Search for patterntern in txt.
		
		if (this.pattern == null) {
			return 0;
		}
		int count = 0;
		int N = txt.length();
		int M = pattern.length();
		int skip;
		for (int i = 0; i <= N-M; i += skip)
		{ // Does the pattern match the text at position i ?
			skip = 0;
			
			for (int j = M-1; j >= 0; j--)
				
				if (pattern.charAt(j) != txt.charAt(i+j))
				{
					skip = j - right[txt.charAt(i+j)];
					if (skip < 1) skip = 1;
					break;
				}
			if (skip == 0) {
				count++;
				i++;
			}
	
		}

		return count;
	}
}