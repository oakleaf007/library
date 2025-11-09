package library;



public abstract class Books implements LibraryInterface{

	
	public abstract void addBooks();
    public abstract void removeBooks() throws CustomException;
    public abstract void searchBooks(String name) throws CustomException;
    public abstract void searchBooks(int id) throws CustomException;
    public abstract void showBooks();
    public abstract void updateStatus() throws CustomException;

}
