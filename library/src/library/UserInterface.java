package library;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
	
		LibraryInterface lib;

		public UserInterface(LibraryInterface lib) {
			this.lib = lib;

		}

		Scanner scanner = new Scanner(System.in);
		int input;

		public void welcome() {
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("|                         WELCOME TO LIBRARY                          |");
			System.out.println("-----------------------------------------------------------------------");

//		pre-loading books for testing
			lib.preLoaded();
			for(Book e: lib.show()) {
				System.out.println(e);
				
			}

//		loop for method selection	
			do {
				System.out.println("Select an option:\n");
				System.out.println(
						"1.Add Books\n2.Remove Books\n3.Search Books\n4.Show Books\n5.Update available status\n0. Exit");
				System.out.println("-----------------------------------------------------------------------");

				try {
					input = scanner.nextInt();
					System.out.println("-----------------------------------------------------------------------");

					if (input == 1) {

						lib.addBooks();
					} else if (input == 2) {
						try {
							lib.removeBooks();
						} catch (CustomException e) {
							// TODO Auto-generated catch block
							System.err.println("Error: " + e.getMessage());
						}
					} else if (input == 3) {

						while (true) {

							System.out.println("Search books ");
							System.out.println("1.By name");
							System.out.println("2.By id:");
							System.out.println("0.Exit");

							try {
								int select = scanner.nextInt();

								System.out
										.println("-----------------------------------------------------------------------");
								if (select == 1) {
									System.out.println("1.By Book name(support by author name too).");

									try {

										System.out.println("Search Book by name/Author name: ");
										scanner.nextLine();
										String name = scanner.nextLine();
										lib.searchBooks(name);
									} catch (CustomException e) {
										
										System.err.println("Error:" + e.getMessage());
									}
								} else if (select == 2) {
									System.out.println("2.By id:");
									try {
										System.out.println("Search Book by Id: ");
										int id = scanner.nextInt();

										lib.searchBooks(id);
									} catch (InputMismatchException e) {
										System.err.println("Invalid Input type, Must be a number");

									}

									catch (CustomException e) {

										System.err.println("Error:" + e.getMessage());
									}
								} else if (select == 0) {
									break;
								} else {
									System.err.println("Invalid Selection");
								}

							} catch (InputMismatchException ie) {
								System.err.println("Invalid Input type, Enter integer only.");
								scanner.nextLine();

							}
						}

					} else if (input == 4) {
						lib.showBooks();

					} else if (input == 5) {
						try {
							lib.updateStatus();
						} catch (CustomException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if (input == 0) {
						System.err.println("Exiting...");
					}

					else {
						System.err.println("Invalid Selection");
						scanner.nextLine();
					}

				} catch (InputMismatchException ie) {
					System.err.println("Invalid Input, input must be a number.");
					scanner.nextLine();
					input = -1;

				}

			} while (input != 0);

		}

	}


