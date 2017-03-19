import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler extends DefaultHandler {

	boolean bGenre = false;
	boolean bFirstName = false;
	boolean bLastName = false;
	boolean bBookTitle = false;
	Book book = new Book();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("genre")) {
			bGenre = true;
		} else if (qName.equalsIgnoreCase("first-name")) {
			bFirstName = true;
		} else if (qName.equalsIgnoreCase("last-name")) {
			bLastName = true;
		} else if (qName.equalsIgnoreCase("book-title")) {
			bBookTitle = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("book-title")) {
			bBookTitle = true;
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (bGenre) {
			book.genre=new String(ch, start, length);
			bGenre = false;
		} else if (bFirstName) {
			book.author=new String(ch, start, length);
			bFirstName = false;
		} else if (bLastName) {
			book.author+=" "+new String(ch, start, length);
			bLastName = false;
		} else if (bBookTitle) {
			book.bookTitle=new String(ch, start, length);
			bBookTitle = false;
		}
	}
}