package library;

import java.util.List;

//interface class

public interface LibraryInterface {

	public List<Book> show();
	public void preLoaded();
 
//interface for adding books
	public void addBooks();
	
//interface method for updating availability
	public void updateStatus() throws CustomException;
	
//interface for removing books

	public void removeBooks() throws CustomException;

//	interface for search books
	public void searchBooks(String name) throws CustomException;
	public void searchBooks(int id) throws CustomException;
//	show books
	
	public void showBooks();

	public void welcome();

	

}
