package bcu.cmp5332.librarysystem.commands;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import bcu.cmp5332.librarysystem.data.DataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;

public class BorrowBook implements Command, DataManager {
	
	private int patronID;
	private int bookID;
	public final String RESOURCE = "./resources/data/loans.txt";
	
	public BorrowBook(int patronID, int bookID){
		this.patronID = patronID;
		this.bookID = bookID;
	}
	
	
	@Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
		
		// Create Localdate & DueDate Vars
		LocalDate lcDate = currentDate; // Current Date
		LocalDate dueDate = lcDate.plusDays(library.getLoanPeriod()); // Due Date (Patron can borrow upto a 7 day period)
		
		// Create Patron Object (i.e get using ID)
		Patron pt = library.getPatronByID(patronID);
		
		// Check if patron is hidden 
        if(pt.getVis() == false) {
        	throw new LibraryException("Patron Doesn't Exist...");
        }
		
		// Create Book Object (i.e get using ID)
		Book bk = library.getBookByID(bookID);
				
		// if book is hidden
		if(bk.getVis() == false) {
			throw new LibraryException("Book Doesn't Exist.");
		}		
		
		// Check if Maximum amount (3) of borrowed books is met by patron
		if(pt.getBooks().size() >= 3) {
			throw new LibraryException("Maximum Book Amount (3) is met by Patron");
		}
		
		
		if(bk.isOnLoan()) {
			throw new LibraryException("Book is already on loan.");
		}

		
		// Creating a loan object
		Loan loan = new Loan(pt, bk, lcDate, dueDate);
		
		// Add Loan object to book object
		bk.setLoan(loan);
		
		// Adding book to Patrons list of borrowed books
		pt.borrowBook(bk, dueDate);
		
		// Update Loan Library Storage
		try {
			storeData(library);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Book is Borrowed Successfully...");
    }


	@Override
	public void loadData(Library library) throws IOException, LibraryException {
		// TODO Auto-generated method stub
		
	}


	@Override
    public void storeData(Library library) throws IOException {
        // TODO: implementation here
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Patron patron : library.getPatrons()) {
            	if(patron.getBooks().size() > 0) {
            		for(Book book : patron.getBooks()) {
            			Loan bkln = book.getLoan();
            			out.print(bkln.getPatron().getId() + SEPARATOR);
            			out.print(bkln.getBook().getId() + SEPARATOR);
            			out.print(bkln.getstartDate() + SEPARATOR);
            			out.print(bkln.getdueDate() + SEPARATOR);
            			out.println();
            		}
            	}
            }
        }
    }
}
