
/*Melnik Boris
 * IP
 * Practice-3
 * */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Dictionary implements Serializable {
	HashMap<String, Boolean> dict = new HashMap<String, Boolean>();
	Token[] tokens = new Token[100];

	int docnum = 0;
	HashMap<Integer, String> documents = new HashMap<Integer, String>(); // to store doc names

	int tokenCount;
	int wordCount;

	int prev_word_id = -1;

	int current_coordinate = 0;

	public void addDocument(File file) {
		try {
			StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader(file)));

			System.out.println("Beginning to index " + file);

			st.lowerCaseMode(true);
			st.whitespaceChars(',', ',');
			st.whitespaceChars('-', '-');
			st.whitespaceChars('.', '.');

			docnum++;
			documents.put(docnum, file.getName());

			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				if (st.sval == null)
					continue;
				String current_word = clear(st.sval);
				if (isWord(current_word)) {
					current_coordinate++;
					wordCount++;
//					if (prev_word_id != -1)
//						tokens[prev_word_id].addSecond(current_word, docnum);
					consideration(current_word);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No such file!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		prev_word_id = -1; // so next doc will be read correctly
		current_coordinate = 0;
		System.out.println("Words added.");
		Arrays.sort(tokens, 0, tokenCount);
		System.out.println("Dictionary sorted.");
		for (int i = 0; i < tokenCount; i++)
			tokens[i].index = i;
		System.out.println("Tokens indexed.");
	}

	private boolean isWord(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isAlphabetic(s.charAt(i)))
				return true;
		return false;
	}

	private void consideration(String s) {
		if (tokens.length == tokenCount)
			resizeToken();
		if (dict.get(s) != null) {
			for (int i = 0; i < tokenCount; i++) {
				if (tokens[i].getText().equals(s) == true) {
//					tokens[i].countPlus();
//					tokens[i].addCoord(current_coordinate, docnum);
					if (!tokens[i].docs.contains((Integer) docnum))
						tokens[i].docs.add(docnum);
					prev_word_id = i;
					break;
				}
			}
		} else {
			Token n = new Token(s);
			n.docs.add(docnum);
//			n.addCoord(current_coordinate, docnum);
			prev_word_id = tokenCount;
			tokens[tokenCount++] = n;
			dict.put(s, true);
//			forwardBTree.addWord(s);
//			backwardBTree.addWord(backward(s));
		}
	}

	private void resizeToken() {
		Token[] temp = new Token[tokens.length * 2];
		for (int i = 0; i < tokens.length; i++) {
			temp[i] = tokens[i];
		}
		tokens = temp;
	}

	private String clear(String initial) {
		char[] r = new char[initial.length()];
		int count = 0;
		for (int i = 0; i < initial.length(); i++) {
			char c = initial.charAt(i);
			if (Character.isLetter(c))
				r[count++] = c;
		}
		char[] re = new char[count];
		for (int i = 0; i < count; i++)
			re[i] = r[i]; // this way i dont += to String and take much less memory!
		String res = new String(re);
		return res;
	}
//
//	public boolean[][] buildIncident() {
//		boolean[][] incident = new boolean[tokenCount][docnum];
//		for (int i = 0; i < tokenCount; i++) {
//			ArrayList<Integer> al = (ArrayList<Integer>) tokens[i].docs.clone();
//			while (!al.isEmpty())
//				incident[i][al.remove(0) - 1] = true;
//		}
//		return incident;
//	}
//
	public void statistics() {
		//System.out.println("Total word count: " + wordCount);
		System.out.println("Unique word count: " + tokenCount);
	}
//
	public void words() {
		System.out.println("Word list:");
		for (int i = 0; i < tokenCount; i++){
			System.out.println(tokens[i]);
			System.out.println(tokens[i].docs);
		}
	}

//	public void dwords() {
//		System.out.println("Double Word list:");
//		for (int i = 0; i < tokenCount; i++)
//			for (int j = 0; j < tokens[i].dt.size(); j++)
//				System.out.println(tokens[i].getText() + " " + tokens[i].dt.get(j).secondWord + ", in doc "
//						+ documents.get(tokens[i].dt.get(j).doc_id));
//
//	}
	
//	public void trigrams(){ //shows trigram list
//		System.out.println("Trigram Word list:");
//		for (int i = 0; i < tokenCount; i++)
//			System.out.println(tokens[i].trigram);
//	}
//	
//	public void shuffles(){ //shows shuufles list (with $)
//		System.out.println("Shuffle Word list:");
//		for (int i = 0; i < tokenCount; i++)
//			System.out.println(tokens[i].shuffle);
//	}
//
//	public ArrayList<Integer> booleanSearch(String request) { // returns list of doc #'s
//		ArrayList<Integer> res = new ArrayList<Integer>();
//		StringTokenizer st = new StringTokenizer(request);
//		ArrayList<String> toks = new ArrayList<String>();
//		while (st.hasMoreTokens())
//			toks.add(st.nextToken("& "));
//		ArrayList<Integer>[] bools = (ArrayList<Integer>[]) new ArrayList[toks.size()];
//
//		for (int i = 0; i < toks.size(); i++) {
//			if (toks.get(i).charAt(0) == '!') {
//				toks.set(i, toks.get(i).substring(1, toks.get(i).length()));
//				bools[i] = inverted(findToken(toks.get(i)).docs, docnum);
//			} else
//				bools[i] = findToken(toks.get(i)).docs;
//		}
//		res.addAll(bools[0]);
//		for (int i = 1; i < toks.size(); i++)
//			res = cross(res, bools[i], docnum);
//		return res;
//	}
//
//	private Token findToken(String s) { // return token by word
//		for (int i = 0; i < tokenCount; i++) {
//			if (tokens[i].getText().equals(s) == true)
//				return tokens[i];
//		}
//		return null;
//	}
//
//	private ArrayList<Integer> inverted(ArrayList<Integer> base, int max) { // returns inverted collection
//		ArrayList<Integer> bs = base;
//		ArrayList<Integer> res = new ArrayList<Integer>();
//		for (int i = 1; i <= max; i++)
//			if (!bs.contains((Integer) i))
//				res.add(i);
//		return res;
//	}
//
//	private ArrayList<Integer> cross(ArrayList<Integer> f, ArrayList<Integer> s, int max) { // returns the crossing of 2 collections
//		ArrayList<Integer> res = new ArrayList<Integer>();
//		for (int i = 0; i <= max; i++) {
//			if (f.contains((Integer) i) && s.contains((Integer) i))
//				res.add(i);
//		}
//		return res;
//	}

//	public ArrayList<Integer> phraseSearch(String w1, String w2) {
//		ArrayList<Integer> result = new ArrayList<Integer>();
//		Token word = findToken(w1);
//		for (int j = 0; j < word.dt.size(); j++) {
//			if (word.dt.get(j).secondWord.equals(w2) && !result.contains((Integer) word.dt.get(j).doc_id))
//				result.add(word.dt.get(j).doc_id);
//		}
//		return result;
//	}
//
//	public ArrayList<Integer> coordSearch(int k, String[] words) {
//		ArrayList<Integer> result = new ArrayList<Integer>();
//		int n = words.length;
//		ArrayList<Integer>[][] mat = (ArrayList<Integer>[][]) new ArrayList[n][docnum+1];
//		
//		for(int i=0;i<n;i++){
//			Token t = findToken(words[i]);
//			if(t==null)
//				return result;
//			for(int j=0;j<t.coords.size();j++){
//				//System.out.println("word "+i+" "+ words[i]+" doc id "+t.coords.get(j).doc_id+" coords "+t.coords.get(j).places);
//				mat[i][t.coords.get(j).doc_id] = (ArrayList<Integer>)t.coords.get(j).places.clone();
//				}
//		}
//		
//		for(int i=1;i<=docnum;i++){
//			boolean doc_match=true;
//			for(int j=0;j<n;j++)
//				if(mat[j][i]==null)
//					doc_match=false;
//			if(!doc_match)
//				continue;
//			doc_match=true;
//			for(int f=0;f<n-1;f++)
//				if(!inRange(mat[f][i],mat[f+1][i],k))
//					doc_match=false;
//			if(!doc_match)
//				continue;
//			result.add(i);
//		}
//		return result;
//	}
//	
//	private boolean inRange(ArrayList<Integer> rr1,ArrayList<Integer> rr2, int range){
//		ArrayList<Integer> r1 = (ArrayList<Integer>) rr1.clone();
//		ArrayList<Integer> r2 = (ArrayList<Integer>) rr2.clone();
//		int rem1 = r1.remove(0);
//		int rem2 = r2.remove(0);
//		if(Math.abs(rem1-rem2)<=range)
//			return true;
//		while(!(r1.isEmpty()&&r2.isEmpty())){
//			if(r1.isEmpty())
//				rem2=r2.remove(0);
//			else if(r2.isEmpty())
//				rem1=r1.remove(0);
//			else if(rem1>=rem2)
//				rem2=r2.remove(0);
//			else
//				rem1=r1.remove(0);
//			if(Math.abs(rem1-rem2)<=range)
//				return true;
//		}
//		return false;
//	}
//	
//	public ArrayList<Token> findIncompleteToken(String s){ //returns token from a request with joker/wildcard/*
//		ArrayList<Token> results = new ArrayList<Token>();
//		if(s.charAt(0)=='*'){
//			String request=backward(s).substring(0, s.length()-1);
//			ArrayList<String> r = backwardBTree.query(request);
//			for(int i=0;i<r.size();i++)
//				results.add(findToken(backward(r.get(i))));
//		}
//		else if(s.charAt(s.length()-1)=='*'){
//			String request=s.substring(0, s.length()-1);
//			ArrayList<String> r = forwardBTree.query(request);
//			for(int i=0;i<r.size();i++)
//				results.add(findToken(r.get(i)));
//		}
//		else{
//			if(!s.contains("*")){
//				return null;
//			}
//			String[] ws = s.split("\\*");
//			String part1=ws[0];
//			String part2=backward(ws[1]);
//			ArrayList<String> r1 = forwardBTree.query(part1);
//			ArrayList<String> r2 = backwardBTree.query(part2);
//			for(int i=0;i<r2.size();i++)
//				r2.set(i, backward(r2.get(i)));
//			ArrayList<String> r = crossing(r1,r2);
//			for(int i=0;i<r.size();i++)
//				results.add(findToken(r.get(i)));
//		}
//		return results;
//	}
//	
//	private String backward(String s){
//		char[] mat = new char[s.length()];
//		for(int i=0;i<s.length();i++){
//			mat[i]=s.charAt(s.length()-1-i);
//		}
//		return new String(mat);
//	}
//	
//	private ArrayList<String> crossing(ArrayList<String> a1, ArrayList<String> a2){
//		ArrayList<String> res = new ArrayList<String>();
//			for(int i=0;i<a1.size();i++){
//				String word = a1.get(i);
//				for(int j=0;j<a2.size();j++)
//					if(word.equals(a2.get(j)))
//						res.add(word);
//			}
//		return res;
//	}
	
	public CompressedDictionary compression(){
		CompressedDictionary cd = new CompressedDictionary();
		for(int i=0;i<tokenCount;i++)
			cd.addWord(tokens[i]);
		return cd;
	}
	
	ArrayList<Book> al = new ArrayList<Book>();
	
	public void BSD(){
		File folder = new File("result");
		File[] listOfFiles = folder.listFiles();
		folder.listFiles();
		int n = listOfFiles.length;
		int count = 1;
		while(count!=n){
		for(int i=1;i<=1000&&count!=n;i++)
			addDocument(listOfFiles[count++]);
		}
		try {	
	         File inputFile = listOfFiles[0];
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         UserHandler userhandler = new UserHandler();
	         saxParser.parse(inputFile, userhandler);     
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	
}