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


public class Library implements Serializable{
	
	private static final long serialVersionUID = 112398934;
	
	private HashTableMap<String, List<Book>> directory;
	
	public Library() {
		this.directory = new HashTableMap<String, List<Book>>(100);
	}
	
	public void addBook(Book book) {
		String genre = sanitizedString(book.getGenre()); // get the book genre: captial insensitive
		String name = sanitizedString(book.getTitle()); // get the book name

		if (this.directory.containsKey(genre)) {
			// System.out.println("The genre is already in the library!");
			List<Book> book_list = directory.get(genre); // extract the book list object
			// check if the book exists
			if (book_list.contains(book)) { // book exists
				System.out.println("Exist book record overwrite: " + book.getTitle());
				book_list.remove(book);
				book_list.add(book);
			} else {
				System.out.println("Book record added: "+ book.getTitle());
				book_list.add(book); // add new book record
			}
			
			directory.put(genre, book_list); // update the list into hashmap
		}
		// if there is no genre in the library
		else {
			System.out.println("New genre created:" + genre);
			// create a new array list
			List<Book> book_list = new ArrayList<>();
			book_list.add(book);
			System.out.println("Book record added: "+ book.getTitle());
			directory.put(genre, book_list); // add new book list to the library
		}
	}
	
	// list all books within the genre
	public List<Book> listGenre(String Type) {
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
		
		return book_list;
	}
	
	
	// get book object by part of its name
	public ArrayList<Book> getBookByPart(String titlePart){
		titlePart = sanitizedString(titlePart);
		
		ArrayList<Book> books = new ArrayList<>();
		// loop through the table
		for (Node<String, List<Book>> book_node : directory.values()) {
			for (Book book_obj: book_node.value) {
				// get the title from book object
				String bookTitle = book_obj.getTitle();
				bookTitle = sanitizedString(bookTitle);
				// if the title contains the part
				if (!bookTitle.contains(titlePart)) {
					continue;
				}else {
					books.add(book_obj);
				}
			}
		}
		// see if book name exist:
		if (books.isEmpty()){
			System.out.println("The book does not exist.");
			return null;
		} else {
		// sort book by rating
		Collections.sort(books, Collections.reverseOrder());
		
		// print the book list
		String total = "";
		for (Book book:books) {
			total = total + book.toString();
		}
		// print the list header
		String header = String.format("%-32s%-24s%-16s%-8s%-5s", "Title", "Author", "Genre", "Rating", "Price");
		System.out.println(header);
		System.out.println(total);
		return books;
		}
	}
	
	// delete book object 
	public void delete(ArrayList<Book> book_list) {
		
		if (book_list == null) {
			System.out.println("No book to delete.");
		} else {
			// find and remove each book object in the list
			for (Book book: book_list) {
				// get genre name
				// System.out.println(book.getTitle());
				String genre = sanitizedString(book.getGenre());
				// locate the target linked list
				List<Book> sub_book_list = directory.get(genre);
				sub_book_list.remove(book);
				// update the list to hashtable
				directory.put(genre, sub_book_list);
				System.out.println("Book: " + book.getTitle() + " has been deleted.");
			}
		}
		
	}
	
	// clean the library
	public void clean() {
		// clear the directory
		directory.clear();
		System.out.println("The library was cleaned.");
	}
	
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
		String total = "";
		
		// look through values 
		for (Node<String, List<Book>> node : directory.values()) {
			// look thourgh list
			for (Book book : node.value) {
				total = total + book.toString();
			}
		}
		return total;
	}
}
