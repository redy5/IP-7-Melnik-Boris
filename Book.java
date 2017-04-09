import java.util.Arrays;

public class Book {
	int doc_id;
	String bookTitle;
	String author;
	String[] genres;
	
	Book(int doc_id, String bookTitle, String author,String[] genres){
		this.doc_id=doc_id;
		this.bookTitle=bookTitle;
		this.author=author;
		this.genres=genres;	
	}
	
	Book(){}

	@Override
	public String toString() {
		return "Book [doc_id=" + doc_id + ", bookTitle=" + bookTitle + ", author=" + author + ", genres="
				+ Arrays.toString(genres) + "]";
	}
}
