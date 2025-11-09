package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//use scanner nextline to prevent passing invalid output to the next input or base loop input
//-----------------------------------------------------------------------------------
//dedicated constructor and getter and setter method
class Book implements Comparable<Book>{
	private String name;
	private int id;
	private String author;
	private boolean aval;

	public int input;

	public Book(String name, int id, String author, boolean aval) {
		this.name = name;
		this.id = id;
		this.author = author;
		this.aval = aval;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public boolean getAval() {
		return aval;
	}

	public void setAval(boolean aval) {
		this.aval = aval;
	}

	
	@Override
	public String toString() {
		return "Book name = " + name + " | id = " + id + " | author = " + author + " | aval = " + aval + " | Availability = " + aval + "\n";
	}

	@Override
	public int compareTo(Book o) {
		return Integer.compare(this.getId(), o.getId());
	}

}

//----------------------------------------------------------------------------------


public class BooksMethods extends Books {

	Scanner scanner = new Scanner(System.in);
	int input;

	public BooksMethods() {
		System.out.println("Loading.....");
		// TODO Auto-generated constructor stub
	}

//	------------------------------------------------------------------------------------
	

//	----------------------------------------------------------
//	ArrayList initialization
	LinkedList<Book> booklist = new LinkedList<>();
	ArrayList<Book> books;

//pre-loading books method for testing
	public void preLoaded() {
		booklist.add(new Book("AA", 103, "OAK", true));
		booklist.add(new Book("BB", 102, "PENGUIN", false));
		booklist.add(new Book("HOWL", 105, "OAK", true));
		booklist.add(new Book("BB", 104, "DINOSAUR", false));
		System.out.println("predefined books are loaded");
		syncArrayList();
	}

//	add book method
	@Override
	public void addBooks() {

		try {
			System.out.print("Enter Id/ISBN of the book: ");
			int id = scanner.nextInt();

			scanner.nextLine();
			System.out.print("Enter Name of the book: ");
			String name = scanner.nextLine();

			System.out.print("Enter author of the book: ");
			String author = scanner.nextLine();

			System.out.print("Enter availability of the book: ");
			boolean aval = scanner.nextBoolean();

			booklist.add(new Book(name, id, author, aval));

			System.out.println("Book added successfully!");
		} catch (InputMismatchException ie) {

			System.err.println("Invalid id, Id should be a Number except ISBN(ignore ISBN)");
			scanner.nextLine();

		}

		syncArrayList();
	}

//	updating the array list after each operation
	private void syncArrayList() {
		books = new ArrayList<>(booklist);
	}
	public  List<Book> show() {
		Collections.sort(booklist);
		return new ArrayList<Book>(booklist);
	}

//remove book method
	@Override
	public void removeBooks() throws CustomException {
		Iterator<Book> iterator = booklist.iterator();
		int id;

		try {
			System.out.print("Enter ID to search and delete: ");

			boolean found = false;
			id = scanner.nextInt();
			while (iterator.hasNext()) {
				Book b = iterator.next();
				if (b.getId() == id) {
					found = true;
					iterator.remove();
					System.out.println("Book with " + id + ", name: " + b.getName() + " deleted");
				}
			}
			if (!found) {
				throw new CustomException("Id not found");
			}

		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block
			System.err.println("Invalid Input Type, ID must be a number.");
			scanner.nextLine();
		}

		syncArrayList();
	}

//search book method
	@Override
	public void searchBooks(String name) throws CustomException {
		Collections.sort(books);
		StringBuilder info = new StringBuilder();

		try {

			boolean found = false;
			System.out.println("-----------------------------------------------------------------------");
			for (Book b : books) {
				if (b.getName().toLowerCase().contains(name.toLowerCase())
						|| b.getAuthor().toLowerCase().contains(name.toLowerCase())) {
//					System.out.println("id: " +b.getId() +" | name: "+b.getName()+" | author: " +b.getAuthor() + " | avalablity: " +b.getAval());
					info.append("Id: ").append(b.getId()).append(" | name: ").append(b.getName()).append(" | Author: ")
							.append(b.getAuthor()).append(" | Availablity: ").append(b.getAval()).append("\n");

					found = true;
				}

			}
			System.out.print(info.toString());

			if (!found) {

				throw new CustomException("Book not found!");

			}

		} catch (NullPointerException ne) {
			System.err.println("No books found, probably Empty.");
		}
		System.out.println("-----------------------------------------------------------------------");

	}

//	overloaded search book method with int (Integer) wrapper for convenience

	public void searchBooks(int id) throws CustomException {
		StringBuilder info = new StringBuilder();

		try {

			System.out.println("-----------------------------------------------------------------------");
			boolean found = false;
			for (Book b : books) {
				if (b.getId() == id) {
					info.append("Id: ").append(b.getId()).append(" | name: ").append(b.getName()).append(" | Author: ")
							.append(b.getAuthor()).append(" | Availablity: ").append(b.getAval()).append("\n");
					found = true;
				}

			}
			System.out.print(info.toString());
			if (!found || id <= 0) {

				throw new CustomException("Id not found, check your id");

			}

		} catch (NullPointerException ne) {
			System.err.println("Error No books found, Probably empty.");
		}
		System.out.println("-----------------------------------------------------------------------");

	}

//show books method
	@Override
	public void showBooks() {
		StringBuilder info = new StringBuilder();
		Collections.sort(books);

		try {
			System.out.println("Books List:\n");
			
			for (Book b : books) {

				info.append("Id: ").append(b.getId()).append(" | name: ").append(b.getName()).append(" | Author: ")
						.append(b.getAuthor()).append(" | Availablity: ").append(b.getAval()).append("\n");

			}
			System.out.print(info.toString());
		} catch (NullPointerException ne) {

			System.err.println("No Data found, probably empty");
		}
		System.out.println("-----------------------------------------------------------------------");
	}

//book status update method 
//	using only search by id cause it is the unique id for updating
	@Override
	public void updateStatus() throws CustomException {
		StringBuilder info = new StringBuilder();
		System.out.println("Enter Book id to update status of availability");

		boolean found = false;
		try {

			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("-----------------------------------------------------------------------");

			for (Book b : booklist) {

				if (b.getId() == id) {
					info.append("Id: ").append(b.getId()).append(" | name: ").append(b.getName()).append(" | Author: ")
							.append(b.getAuthor()).append(" | Availablity: ").append(b.getAval()).append("\n");
					System.out.print(info.toString());
					found = true;
					System.out.println("Enter Boolean value for updating status: (supports true/false only)");

					boolean status = scanner.nextBoolean();
					b.setAval(status);
				}

			}

			if (!found || id <= 0) {
				throw new CustomException("Id not found, enter a correct id");
			}
		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block
			System.err.println("Invalid input type.");
			scanner.nextLine();
		} catch (NullPointerException ie) {
			System.err.println("");
		}
		syncArrayList();

		System.out.println("-----------------------------------------------------------------------");

	}

	@Override
	public void welcome() {
		// TODO Auto-generated method stub
		
	}

}
