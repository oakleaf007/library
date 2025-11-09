package library;

public class Library {

	public static void main(String args []) {
		LibraryInterface b1 = new BooksMethods();
		
		UserInterface ui = new UserInterface(b1);
		
		
	      ui.welcome();
		
		
	}
}
