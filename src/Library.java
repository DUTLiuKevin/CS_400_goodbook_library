import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.io.Serializable;
import java.util.*;

// --== CS400 library class ==--
// Name: Yuan Liu
// Email: yliu2232@wisc.edu
// Notes to Grader: <optional extra notes>


public class Library implements Serializable {
	
	private HashMap<String, List<Book>> directory;
	
	public Library() {
		this.directory = new HashMap<String, List<Book>>();
	}
	
	public void addBook(Book book) {
		String genre = sanitizedString(book.getGenre()); // get the book genre: captial insensitive
		String name = sanitizedString(book.getTitle()); // get the book name
		
		if (this.directory.containsKey(genre)) {
			// System.out.println("The genre is already in the library!");
			List<Book> book_list = directory.get(genre); // extract the book list object
			book_list.add(book); // add new book record
			directory.put(genre, book_list); // update the list into hashmap
		}
		// if there is no genre in the library
		else {
			// create a new array list
			List<Book> book_list = new ArrayList<>();
			book_list.add(book);
			directory.put(genre, book_list); // add new book list to the library
		}
	}
	
	// list all books within the genre
	public void listGenre(String Type) {
		String genre = sanitizedString(Type); // sanitize the genre
		List<Book> book_list = this.directory.get(genre); // retrieve book_list
		String total = "\n";
		// sort the list using collections.sort() method
		// following descending order of rating
		Collections.sort(book_list, Collections.reverseOrder());
		int Count = 0;
		for (Book book:book_list) {
			total = total + book.toString();
			// print comments for the top 5 books
			if (Count <= 4){
				String comment = book.getComment();
				total = total + "Comment: " + comment + "\n";
			}
			Count = Count + 1;
		}
		// print the list header
		String header = String.format("%-32s%-24s%-16s%-8s%-5s", "Title", "Author", "Genre", "Rating", "Price");
		System.out.println(header);
		// print details
		System.out.println(total);
	}
	

//	// get book object by its full name
//	public Book getBook(String bookTitle) {
//		bookTitle = sanitizedString(bookTitle); // change the book title to lower case
//		return this.directory.get(bookTitle); // get the book object from hashmap by its name as key
//	}
//	
//	// get book object by part of its name
//	public ArrayList<Book> getBookByPart(String titlePart){
//		/*
//		 * Get a list of book object that contains the part name
//		 * params: titlePart: part of the book title
//		 * return: a list of book object that match the name
//		 */
//		
//		titlePart = sanitizedString(titlePart);
//		
//		ArrayList<Book> books = new ArrayList<>();
//		
//		for(String bookTitle : this.directory.keySet()) {
//			if (!bookTitle.contains(titlePart)) {
//				continue
//			}
//			
//			// if the key contains the given string
//			// we add the book object to the array list
//			
//			books.add(this.directory.get(bookTitle));
//		}
//		
//		return books;
//	}

	
	// change string to lower case
	public static String sanitizedString(String string) {
		if (string == null) {
			return "";
		}
		string = string.toLowerCase(); // change to lower case
		return string.trim(); // trim space in the end
	}
	
	@Override
	public String toString() {
		String total = "\n";
		
		// look through values 
		for (List<Book> list : directory.values()) {
			// look thourgh list
			for (Book book : list) {
				total = total + book.toString();
			}
		}
		return total;
	}
}
