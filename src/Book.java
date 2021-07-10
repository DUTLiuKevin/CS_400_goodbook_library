import java.io.Serializable;

// --== CS400 Book class ==--
// Name: Yuan Liu
// Email: yliu2232@wisc.edu
// Notes to Grader: <optional extra notes>


public class Book implements Serializable, Comparable<Book>{
	
	private static final long serialVersionUID = 1122334534;
	
	private String title;
	private String author;
	private String genre;
	private int rating;
	private double price;
	private String comment;
	
	// constructor
	public Book(String title, String author, String genre, int rating, double price, String comment) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.rating = rating;
		this.price = price;
		this.comment = comment;
	}

	// getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
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
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	// overwrite toString
	public String toString() {
		return String.format("%-32.32s%-24.24s%-16.16s%-8d%-5.2f\n", this.title, this.author, this.genre, this.rating, this.price);
	}
	
//	public static void main(String[] args) {
//		// create two book class
//		Book sense = new Book("Sense", "Fiction", 4);
//		Book pride = new Book("Pride", "History", 5);
//		// create the hash map
//		HashMap<String, Book> directory = new HashMap<>();
//		directory.put(sense.getTitle(), sense);
//		directory.put(pride.getTitle(), pride);
//		// get book information by name
//		Book book = directory.get("Pride");
//		System.out.println(book);
//	}

	
	@Override
	public int compareTo(Book book) {
		// TODO Auto-generated method stub
		// Sort by rating
		if(this.rating == book.rating)
			return 0;
		else if (this.rating > book.rating)
			return 1;
		else
			return -1;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null) {
			return false;
		}
		
		if (obj.getClass() != this.getClass()) { // if the object is not book object, return false
			return false;
		}
		
		Book obj_case = (Book) obj;
		
		String title1 = sanitizedString(this.getTitle());
		String title2 = sanitizedString(obj_case.getTitle());
		
		// compare if the two title is the same
		if (!title1.equals(title2)) {
			return false;
		}
		else {
			return true;
		}
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
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
}

