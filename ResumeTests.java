package Final_Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTests {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException{
		
		try {
			ResumeReviewer BasicResumeSearcher = new ResumeReviewer();
			BasicResumeSearcher.addWords("Resumes/keywordsTest.txt");
			BasicResumeSearcher.addResume("Resumes/sample6.txt");
			
			System.out.println("The Resume being searched is Resumes/sample6.txt and looks like:");
			System.out.println(BasicResumeSearcher.readFile("Resumes/sample6.txt"));
			
			System.out.println();
			System.out.println("The keywords we are seaching for are: ");
			System.out.println(BasicResumeSearcher.readFile("Resumes/keywordsTest.txt"));
			System.out.println();
			
			System.out.println("The score it should have is 4. And the score the returned is:");
			
			System.out.print("KMP Algorithm: ");
			System.out.println(BasicResumeSearcher.getKMPScores("Resumes/sample6.txt"));
			System.out.println("Elapsed time:");
			System.out.println(BasicResumeSearcher.getKMPtime() + " nanoseconds");
			System.out.println();
			
			System.out.print("Boyer Moore Algorithm: ");
			System.out.println(BasicResumeSearcher.getBMScores("Resumes/sample6.txt"));
			System.out.println("Elapsed time:");
			System.out.println(BasicResumeSearcher.getBMtime() + " nanoseconds");
			
			System.out.println();
			long difference = BasicResumeSearcher.getKMPtime() / BasicResumeSearcher.getBMtime();
			System.out.println("BM Algorithm is " + difference + " times faster than KMP");
			
		} catch (IOException e) {
			System.out.println("An error has occurred while reading the file.");
			System.out.println(e);
		}
		
		
		
		try {
			
			ResumeReviewer NullResumeSearcher = new ResumeReviewer();
			NullResumeSearcher.addWords(null);
			NullResumeSearcher.addResume("Resumes/sample6.txt");

			 
		} catch (NullPointerException e) {
			System.out.println();
			System.out.println("A Null Pointer Exception has been thrown.");
			System.out.println(e);
		}
		
		
		
		
		
		
		
	}
}