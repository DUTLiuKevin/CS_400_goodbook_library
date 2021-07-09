import java.io.*;
import java.util.Scanner;


public class LibSystem {
	
	static String fileName = null;
	static Library lib = new Library();
	static Scanner in = new Scanner(System.in);
	static Boolean running = true;

	public static void main(String[] args) {
		while (running) {
			System.out.println("\nEnter 0 for load a library." + "\nEnter 1 for save and quit"
					+ "\nEnter 2 for list all books in library" + "\nEnter 3 for add book to library");
			int answer = in.nextInt();
			switch(answer) {
			case 0:
				// load a library
				System.out.println("Enter filename to load");
				loadScript(in.next());
				break;
			case 1:
				// save and quit
				saveAndQuit();
				break;
			case 2:
				// print all list of books
				System.out.println(lib.toString());
				break;
			case 3:
				addBook();
				break;
			
			}
		}
		// exit 
		System.exit(0);
	}

	private static void addBook() {
		// TODO Auto-generated method stub
		String title, genre;
		int rating;
		
		System.out.println("\nEnter Title: ");
		title = in.next();
		
		System.out.println("\nEnter Genre: ");
		genre = in.next();
		
		System.out.println("\nEnter Rating:");
		rating = in.nextInt();
		
		Book b = new Book(title, genre, rating);
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
}
