/*IP4 - full - Melnik Boris*/
import java.io.Serializable;
import java.util.ArrayList;

public class Token implements Comparable<Token>, Serializable {
	private String text; // token text (the word)
//	private int count = 0; // how many times this word is repeated
	int index;
	
//	ArrayList<String> trigram;
//	
//	ArrayList<String> shuffle;

//	ArrayList<Coordinate> coords = new ArrayList<Coordinate>();

	ArrayList<Integer> docs = new ArrayList<Integer>();

//	ArrayList<DoubleToken> dt = new ArrayList<DoubleToken>();

	Token(String text) { // basic constructor
//		this.trigram=trigram(text);
//		this.shuffle=shuffle(text);
		this.text = text;
//		count = 1;
	}

//	public void countPlus() {
//		count++;
//	}

	public String getText() {
		return text;
	}

//	@Override
//	public String toString() {
//		return text + ", repeated " + count + " times.";
//	}

	@Override
	public int compareTo(Token o) {
		return this.text.compareTo(o.text);
	}

//	public void addSecond(String s, int doc_id) {
//		dt.add(new DoubleToken(s, doc_id));
//	}
//
//	public void addCoord(int place, int doc_id) {
//		for (int i = 0; i < coords.size(); i++)
//			if (coords.get(i).doc_id == doc_id) {
//				coords.get(i).addPlace(place);
//				return;
//			}
//		Coordinate n = new Coordinate(doc_id);
//		n.addPlace(place);
//		coords.add(n);
//	}
	
//	private ArrayList<String> trigram(String word){ //generates array of trigrams from input word
//		ArrayList<String> result = new ArrayList<String>();
//		char[] mat = new char[word.length()+2];
//		mat[0]='$';
//		mat[mat.length-1]='$';
//		for(int i=0;i<word.length();i++)
//			mat[i+1]=word.charAt(i);
//		for(int i=0;i<word.length();i++){
//			char[] k = {mat[i],mat[i+1],mat[i+2]};
//			result.add(new String(k));
//		}
//		return result;
//	}
//	
//	private ArrayList<String> shuffle(String word){ //creates array of shuffles from input word
//		ArrayList<String> result = new ArrayList<String>();
//		int n=word.length()+1;
//		String w$ = word+'$'+word+'$';
//		//System.out.println(w$);
//		for(int i=0;i<n;i++){
//			char[] r = new char[n];
//			for(int j=0;j<n;j++)
//				r[j]=w$.charAt(j+i);
//			result.add(new String(r));
//		}
//		return result;
//	}

}