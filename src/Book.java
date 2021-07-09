import java.util.HashMap;
import java.io.Serializable;

// --== CS400 Book class ==--
// Name: Yuan Liu
// Email: yliu2232@wisc.edu
// Notes to Grader: <optional extra notes>


public class Book implements Serializable{
	
	private String title;
	private String genre;
	private int rating;
	
	// constructor
	public Book(String title, String genre, int rating) {
		this.title = title;
		this.genre = genre;
		this.rating = rating;
	}

	// getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	// overwrite toString
	public String toString() {
		return "\nTitle:" + this.title + "\nGenre:" + this.genre
				+ "\nRating:" + this.rating;
	}
	
	public static void main(String[] args) {
		// create two book class
		Book sense = new Book("Sense", "Fiction", 4);
		Book pride = new Book("Pride", "History", 5);
		// create the hash map
		HashMap<String, Book> directory = new HashMap<>();
		directory.put(sense.getTitle(), sense);
		directory.put(pride.getTitle(), pride);
		// get book information by name
		Book book = directory.get("Pride");
		System.out.println(book);
	}
}

