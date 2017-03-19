import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Dictionary by Melnik Boris");
		Dictionary d = new Dictionary();
		d.BSD();
//		while (true) {
//			System.out.println("Choose action:");
//			System.out.println("1.Import dictionary from file.");
//			System.out.println("2.Export dictionary to file.");
//			System.out.println("3.Load txt file to dictionary.");
//			System.out.println("4.Load 10 test books to dictionary at once.");
//			System.out.println("5.Print statistics in console.");
//			System.out.println("6.Print all words to console.");
//			System.out.println("7.Build Incident Matrix.");
//			System.out.println("8.Build inverted index.");
//			System.out.println("9.Boolean search.");
//			System.out.println("10.Exit.");
//			System.out.println("11.Show Double Word Index.");
//			System.out.println("12.Phrase Search.");
//			System.out.println("13.Coordinate Search.");
//			System.out.println("14.Show all trigramms for existing words.");
//			System.out.println("15.Show all shuffles for existing words.");
//			System.out.println("16.Joker search.");
//			int i = 0;
//			if (s.hasNextInt())
//				i = s.nextInt();
//			else if (s.hasNext() && !s.hasNextInt())
//				s.nextLine();
//			if (i == 1) {
//				System.out.println("Enter file name:");
//				String i_loc = s.next();
//				FileInputStream fis;
//				try {
//					fis = new FileInputStream(i_loc);
//					ObjectInputStream oin = new ObjectInputStream(fis);
//					d = (Dictionary) oin.readObject();
//					oin.close();
//				} catch (FileNotFoundException e) {
//					System.out.println("No such file!");
//				} catch (IOException e) {
//					e.printStackTrace();
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
//			}
//			if (i == 2) {
//				System.out.println("Enter file name:");
//				String o_loc = s.next();
//				FileOutputStream fos;
//				try {
//					File export = new File(o_loc);
//					export.createNewFile();
//					fos = new FileOutputStream(o_loc);
//					ObjectOutputStream oos = new ObjectOutputStream(fos);
//					oos.writeObject(d);
//					oos.flush();
//					oos.close();
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (i == 3) {
//				System.out.println("Enter file name:");
//				String f_loc = s.next();
////				d.addText(f_loc);
//			}
//			if (i == 4) {
////				d.addText("Hyperion-I.txt");
////				d.addText("Hyperion-II.txt");
////				d.addText("Hyperion-III.txt");
////				d.addText("Fall_of_Hyperion-I.txt");
////				d.addText("Fall_of_Hyperion-II.txt");
////				d.addText("Fall_of_Hyperion-III.txt");
////				d.addText("Fall_of_Hyperion-IV.txt");
////				d.addText("Endymion-I.txt");
////				d.addText("Endymion-II.txt");
////				d.addText("Endymion-III.txt");
//			}
//			if (i == 5) {
////				d.statistics();
//			}
//			if (i == 6) {
////				d.words();
//			}
//			if (i == 7) {
////				boolean[][] inc = d.buildIncident();
////				for (int y = 0; y < d.tokenCount; y++) {
////					System.out.print(d.tokens[y]);
////					for (int x = 0; x < d.docnum; x++) {
////						if (inc[y][x])
////							System.out.print(" 1");
////						else
////							System.out.print(" 0");
////					}
////					System.out.println();
////				}
//			}
//			if (i == 8) {
//				for (int y = 0; y < d.tokenCount; y++) {
//					System.out.print(d.tokens[y]);
//					ArrayList<Integer> al = (ArrayList<Integer>) d.tokens[y].docs.clone();
//					while (!al.isEmpty())
//						System.out.print(" " + al.remove(0));
//					System.out.println();
//				}
//			}
//			if (i == 9) {
////				System.out.println("Enter request(&=AND,|=OR,!=AND NOT):");
////				System.out.println("Rules for request - enter words and symbols(& and !) with or without spaces between them.");
////				System.out.println("For example: boris&!grigoriy&andriy - boris AND NOT grigoriy AND andriy");
////				String request = s.next();
////				ArrayList<Integer> result = d.booleanSearch(request);
////				System.out.print("This combination was in next documents: ");
////				for(int k=0;k<result.size();k++)
////					System.out.print(result.get(k)+" ");
////				System.out.println();
//			}
//			if (i == 10) {
//				s.close();
//				return;
//			}
//			if(i==11){
////				d.dwords();
//			}
//			if(i==12){
//				System.out.println("Enter phrase to search for docs(format is 'word1' + ' ' + 'word2'):");
//				System.out.println("For example to search for 'some phrase' you will need to type 'some phrase'");
//				String w1=s.next();
//				String w2=s.next();
////				ArrayList<Integer> result = d.phraseSearch(w1,w2);
////				System.out.print("This combination was in next documents: ");
////				for(int k=0;k<result.size();k++)
////					System.out.print(result.get(k)+" ");
////				System.out.println();
//			}
//			if(i==13){
//				System.out.println("The most complex part so far. Coordinate search.");
//				System.out.println("To search for docs, 1)enter k(max number of words inbetween)");
//				System.out.println("2) enter number of words in your request (due to Sanner.java problems)");
//				System.out.println("3)enter your request, splitted by spaces.example:'Kuyiv Moghila University'");
//				System.out.println("4)???");
//				System.out.println("5)PROFIT!");
//				System.out.println("Enter k:");
//				int k = s.nextInt();
//				System.out.println("Enter n:");
//				int n = s.nextInt();
//				System.out.println("Enter request:");
//				String[] words = new String[n];
//				for(int j=0;j<n;j++)
//					words[j]=s.next();
////				ArrayList<Integer> result = d.coordSearch(k,words);
//				System.out.print("This combination was in next documents: ");
////				if(result.isEmpty())
////					System.out.println("Npt found");
////				else
////					for(int f=0;f<result.size();f++)
////						System.out.print(result.get(f)+" ");
////				System.out.println();
//			}
//			if(i==14){
////				d.trigrams();
//			}
//			if(i==15){
////				d.shuffles();
//			}
//			if(i==16){
////				System.out.println("Joker/wildcard/* search! Tells you document(s) and coordinate(s) of the word you are looking for!");
////				System.out.println("Starting, ending or 1 in the middle jokers work. No jokers results in bad attitude from the programm.");
////				System.out.println("Enter your request:");
////				String request = s.next();
////				ArrayList<Token> res = d.findIncompleteToken(request);
////				if (res == null)
////					System.out.println("Wrong request, baka.");
////				else
////					for (int j = 0; j < res.size(); j++)
////						System.out.println(res.get(j));
//			}
//			if(i==17){
////				d.addText("Hyperion-I.txt");
//				CompressedDictionary cd = d.compression();
////				Dictionary d1 = cd.decompress();
////				cd.printt();
//				InvertedLL ill = new InvertedLL();
//				ill.addNumber(1);
//				System.out.println(ill.decompress());
////				d1.statistics();
////				d1.words();
////				
////				FileOutputStream fos;
////				try {
////					File export = new File("full_dic");
////					export.createNewFile();
////					fos = new FileOutputStream("full_dic");
////					ObjectOutputStream oos = new ObjectOutputStream(fos);
////					oos.writeObject(d);
////					oos.flush();
////					oos.close();
////				} catch (FileNotFoundException e) {
////					e.printStackTrace();
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////				
////				try {
////					File export = new File("compressed_dic");
////					export.createNewFile();
////					fos = new FileOutputStream("compressed_dic");
////					ObjectOutputStream oos = new ObjectOutputStream(fos);
////					oos.writeObject(cd);
////					oos.flush();
////					oos.close();
////				} catch (FileNotFoundException e) {
////					e.printStackTrace();
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////				
////				try {
////					File export = new File("fd1_dic");
////					export.createNewFile();
////					fos = new FileOutputStream("fd1_dic");
////					ObjectOutputStream oos = new ObjectOutputStream(fos);
////					oos.writeObject(d1);
////					oos.flush();
////					oos.close();
////				} catch (FileNotFoundException e) {
////					e.printStackTrace();
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
			}
//		}
	}
	
//}
