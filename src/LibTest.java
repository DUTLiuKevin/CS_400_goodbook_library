import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class LibTest {

	private Library directory;
	private Book book;
	private ArrayList<Book> books;
	private List<Book> book_list;
	
	@BeforeEach
	public void createInstanse() {
		directory = new Library();
	}
	
	@Test
	@DisplayName("Test add and search function.")
	public void testAdd() {
		// create a book record
		String title = "Wuthering_Heights";
		String author = "Emily_Bronte";
		String genre = "Novel";
		int rating = 5;
		double price = 15.0;
		String comment = "NA";
		book = new Book(title, author, genre, rating, price, comment);
		directory.addBook(book);
		// search for the book 
		books = directory.getBookByPart(title);
		// check if the book record match the search
		assertEquals(book, books.get(0));
	}
	
	@Test
	@DisplayName("Test delete function.")
	public void testDelete() {
		// add the book to the library
		String title = "Wuthering_Heights";
		String author = "Emily_Bronte";
		String genre = "Novel";
		int rating = 5;
		double price = 15.0;
		String comment = "NA";
		book = new Book(title, author, genre, rating, price, comment);
		directory.addBook(book);
		// find the book from library
		books = directory.getBookByPart(title);
		// delete the book record
		directory.delete(books);
		// check if the book has been deleted
		assertEquals(null, directory.getBookByPart(title));
	}
	
	@Test
	@DisplayName("Test list by genre.")
	public void testGenreList() {
		// Book book1 = new Book("Wuthering_Heights", "Emily_Bronte", "Novel", 5, 15, "NA");
		// Book book2 = new Book("Ballinby_Boys", "Arthur_McCrumb", "Novel", 3, 25, "NA");
		// Book book3 = new Book("Nothing_But_Capers", "Abraham_Stackhouse", "Novel", 4, 35, "NA");
		// add books to the library
		directory.addBook(new Book("Wuthering_Heights", "Emily_Bronte", "Novel", 5, 15, "NA"));
		directory.addBook(new Book("Ballinby_Boys", "Arthur_McCrumb", "Novel", 3, 25, "NA"));
		directory.addBook(new Book("Nothing_But_Capers", "Abraham_Stackhouse", "Novel", 4, 35, "NA"));
		// get book list by genre
		book_list = directory.listGenre("Novel");
		// compare ratings
		assertTrue((book_list.get(0).getRating() >= book_list.get(1).getRating()) & (book_list.get(0).getRating() >= book_list.get(2).getRating())
				 & (book_list.get(1).getRating() >= book_list.get(2).getRating()));
	}
	
	@Test
	@DisplayName("Test clean.")
	public void testClean() {
		Book book1 = new Book("Wuthering_Heights", "Emily_Bronte", "Novel", 5, 15, "NA");
		Book book2 = new Book("Ballinby_Boys", "Arthur_McCrumb", "Novel", 3, 25, "NA");
		Book book3 = new Book("Nothing_But_Capers", "Abraham_Stackhouse", "Novel", 4, 35, "NA");
		// add books to the library
		directory.addBook(book1);
		directory.addBook(book2);
		directory.addBook(book3);
		// clean the library
		directory.clean();
		// get the book list
		assertEquals("",directory.toString());
	}
	
	@Test
	@DisplayName("Test update record.")
	public void testUpdate() {
		Book book1 = new Book("Wuthering_Heights", "Emily_Bronte", "Novel", 5, 15, "NA");
		// add books to the library
		directory.addBook(book1);
		// add book of the same name
		Book book2 = new Book("Wuthering_Heights", "Emily", "Novel", 5, 16, "NA");
		directory.addBook(book2);
		// find the book from library
		ArrayList<Book> books = directory.getBookByPart("Wuthering_Heights");
		assertEquals("Emily", books.get(0).getAuthor());
		
	}

}
