package Final_Project;

import java.io.BufferedReader;
import java.lang.System;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ResumeReviewer {
	
	public List<String> positiveWords;
	private Map<String, Integer> resumesKMPGrades;
	private Map<String, Integer> resumesBMGrades;
	private long KMPtime = 0;
	private long BMtime = 0;
	
	public ResumeReviewer() {
		// constructor
		positiveWords = new ArrayList<String>();
		resumesKMPGrades = new TreeMap<String, Integer>();
		resumesBMGrades = new TreeMap<String, Integer>();
	}
	
	public long getKMPtime(){
		return KMPtime;
	}
	public long getBMtime(){
		return BMtime;
	}
	
	
	
	public String getKMPScores(String resume) {
		return resumesKMPGrades.get(resume).toString();
	}
	
	public String getBMScores(String resume) {
		return resumesBMGrades.get(resume).toString();
	}
	
	public void addResume(String resume) throws IOException {
		// gets a resume to be graded
		resumesKMPGrades.put(resume, graderKMP(resume));
		resumesBMGrades.put(resume, graderBM(resume));
	}
	
	public void addWords(String file) throws IOException {
		
		if (file == null) {
			return;
		}
		
		String[] words = readFile(file).split(", ");
		for(int i = 0; i < words.length; i++) positiveWords.add(words[i].toLowerCase());
		System.out.println(positiveWords.toString());
	}
	
	private int graderKMP(String resume) throws IOException {
		// loops through words and grades resume
		String resumeText = readFile(resume).toLowerCase();
		int grade = 0;
		KMP currWord;
		
		long start = System.nanoTime();
		for(int i = 0; i < positiveWords.size(); i++) {
			currWord = new KMP(positiveWords.get(i));
			grade += currWord.search(resumeText);
		}
		long finish = System.nanoTime();
		
		this.KMPtime = finish - start;
		
		return grade;
	}
	
	private int graderBM(String resume) throws IOException {
		// loops through words and grades resume using Boyer Moore algorithm
		String resumeText = readFile(resume).toLowerCase();
		int grade = 0;
		BoyerMoore currWord;
		
		long start = System.nanoTime();
		for(int i = 0; i < positiveWords.size(); i++) {
			currWord = new BoyerMoore(positiveWords.get(i));
			grade += currWord.search(resumeText);
		}
		long finish = System.nanoTime();
		
		this.BMtime = finish - start;
		
		return grade;
	}	
	
	public static String readFile(String filename) throws IOException {
		int readChar;
		// Character Array we're returning, for future parsing.
		StringBuilder data = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			readChar = br.read();
			// reads character-by-character (ch) until we reach the end of file
			while (readChar != -1) {
				char ch = (char)readChar;
				// Only adding non-letter (a-z, A-Z) and non-space characters to list, for simplicity
				if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch == ' ') || (ch == ',')) {
					data.append(ch);
				}
				readChar = br.read();
			}
			br.close();
		} catch (IOException e) {
			System.out.println("An error has occurred while reading the file.");
			System.out.println(e);
			return null;
		}
		return data.toString();
	}
	
	public static void main(String[] args) {
		// for testing
		String goodWords = "";
		ResumeReviewer tester;
		
		try {
			tester = new ResumeReviewer();
			tester.addWords("Resumes/keywordsTest.txt");
			tester.addResume("Resumes/sample6.txt");
			tester.addResume("Resumes/sample2.txt");
			tester.addResume("Resumes/sample3.txt");
			tester.addResume("Resumes/sample4.txt");
			tester.addResume("Resumes/sample5.txt");
			tester.addResume("Resumes/sample6.txt");
			tester.addResume("Resumes/sample7.txt");
			tester.addResume("Resumes/sample8.txt");
    		tester.addResume("Resumes/sample9.txt");
			tester.addResume("Resumes/sample10.txt");
			
			
			System.out.println(tester.resumesKMPGrades.toString());
			System.out.println(tester.resumesKMPGrades.toString());
		} catch (IOException e) {
			System.out.println("An error has occurred while reading the file.");
			System.out.println(e);
		}
	}
}