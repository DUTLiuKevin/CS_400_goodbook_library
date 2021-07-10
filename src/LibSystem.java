import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class LibSystem {
	
	static String fileName = null;
	static Library lib = new Library();
	static Scanner in = new Scanner(System.in);
	static Boolean running = true;

	public static void main(String[] args) throws Exception {
		while (running) {
			System.out.println("Welcome to Goodbook Library!");
			System.out.println("\nEnter 1 for load a library." + "\nEnter 2 for load from csv file."
			+ "\nEnter 3 for save and quit." + "\nEnter 4 for list all books in the library."
			+ "\nEnter 5 for search by genre." + "\nEnter 6 for add book."
			+ "\nEnter 7 for search book by name." + "\nEnter 8 for delete book by name."
			+ "\nEnter 9 for clean the library." + "\nEnter 0 for quit.");
			int answer = in.nextInt();
			switch(answer) {
			case 0:
				quit();
				break;
			case 1:
				// load a library
				System.out.println("Enter filename to load");
				loadScript(in.next());
				break;
			case 2:
				System.out.println("Enter the csv filename:");
				loadcsv(in.next());
				break;
			case 3:
				// save and quit
				saveAndQuit();
				break;
			case 4:
				// print all list of books
				String header = String.format("%-32s%-24s%-16s%-8s%-5s", "Title", "Author", "Genre", "Rating", "Price");
				System.out.println(header);
				System.out.println(lib.toString());
				break;
			case 5:
				// retrieve by genre
				System.out.println("Enter the genre name:");
				lib.listGenre(in.next());
				break;
			case 6:
				addBook();
				break;
			case 7:
				System.out.println("Enter the book name to search:");
				// get the list of books match the need
				lib.getBookByPart(in.next());
				break;
			case 8:
				// delete book by name
				System.out.println("Enter the book name to delete:");
				String book_partname = in.next(); 
				System.out.println("Books to be deleted:");
				// get the list of books match the need
				ArrayList<Book> delete_book_list = lib.getBookByPart(book_partname);
				// ask whether to delete
				System.out.println("Continue to delete? Enter Y or N");
				if (in.next().equals("Y")) {
					lib.delete(delete_book_list);
					break;
				} else {
					System.out.println("Delete was cancelled.");
					break;
				}
			case 9:
				System.out.println("Continue to clean the library? Enter Y or N");
				if (in.next().equals("Y")) {
					lib.clean(); // clean the library
					break;
				} else {
					System.out.println("Clean library was cancelled.");
					break;
				}
			}
		}
		// exit 
		System.exit(0);
	}


	private static void loadcsv(String csv_filename) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(csv_filename));
        br.readLine(); //Skip the header line
        while(br.ready()) {
        	// System.out.println(br.readLine() + "\n");
        	// split the line by ','
        	String line = br.readLine();
        	String str[] = line.split(",");
        	// sequence: Title, author, price, rating, genre, staff comment
        	String title = str[0];
        	String author = str[1];
        	Double price = Double.parseDouble(str[2]);
        	// System.out.println(price);
        	Integer rating = Integer.parseInt(str[3]);  
        	String genre = str[4];
        	String comment = str[5];
        	
        	// add book into library
        	Book b = new Book(title, author, genre, rating, price, comment);
    		lib.addBook(b);
        }
        
		
	}


	private static void addBook() {
		// TODO Auto-generated method stub
		String title, genre, author, comment;
		int rating;
		double price;
		
		System.out.println("\nEnter Title: ");
		title = in.next();
		
		System.out.println("\nEnter Author: ");
		author = in.next();
		
		System.out.println("\nEnter Genre: ");
		genre = in.next();
		
		System.out.println("\nEnter Price: ");
		price = in.nextDouble();
		
		System.out.println("\nEnter Rating:");
		rating = in.nextInt();
		
		System.out.println("\nEnter Comment:");
		comment = in.next();
		
		Book b = new Book(title, author, genre, rating, price, comment);
		lib.addBook(b);
		
	}

	// function for load library
	private static void loadScript(String name) {
		// TODO Auto-generated method stub

		FileInputStream fis = null;
		ObjectInputStream in = null;
		
		File file = new File(name);
		if (file.exists()) {
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			lib = (Library)in.readObject(); // read the library object
			fis.close();
			in.close();
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
			System.out.println("\nThe file does not exist.");
		}
	}
	
	// save and quit function
	private static void saveAndQuit() {
		System.out.println("Enter file name: ");
		fileName = in.next();
		System.out.println("Library was saved and closed.");
		running = false;
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(lib);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// quit
	private static void quit() {
		System.out.println("Library was closed.");
		running = false;
	}
}
