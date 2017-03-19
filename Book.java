
public class Book {
	int doc_id;
	String bookTitle;
	String author;
	String genre;
	
	Book(int doc_id, String bookTitle, String author,String genre){
		this.doc_id=doc_id;
		this.bookTitle=bookTitle;
		this.author=author;
		this.genre=genre;	
	}
	
	Book(){}
}
