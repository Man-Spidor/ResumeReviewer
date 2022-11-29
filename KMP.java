package Final_Project;

public class KMP
{
	private String pattern;
	private int[][] dfa;
	
	public KMP(String pattern)
	{ // Build DFA from pattern.
		
		if (pattern == null) {
			return;
		}
		
		
		this.pattern = pattern;
		int M = pattern.length();
		int R = 256;
		dfa = new int[R][M];
		dfa[pattern.charAt(0)][0] = 1;
		
		for (int X = 0, j = 1; j < M; j++)
			{ // Compute dfa[][j].
			for (int c = 0; c < R; c++)
				dfa[c][j] = dfa[c][X]; // Copy mismatch cases.
			
			dfa[pattern.charAt(j)][j] = j+1; // Set match case.
			X = dfa[pattern.charAt(j)][X]; // Update restart state.
		}
	}
	public int search(String txt)
		{ // Simulate operation of DFA on txt.c
		
		if (this.pattern == null) {
			return 0;
		}
		
		int count = 0;
		int i, j, N = txt.length(), M = pattern.length();
		
		for (i = 0, j = 0; i < N && j < M; i++) {
			j = dfa[txt.charAt(i)][j];
		}

		if(i < N) {
			count = search(txt.substring(i)) + 1;
		}

		return count;
	}
}