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

public class RenewBook implements Command, DataManager {
	
	private int bookID;
	private int ptID;
	public final String RESOURCE = "./resources/data/loans.txt";
	
	public RenewBook(int ptID, int BookID) {
		this.ptID = ptID;
		this.bookID = BookID;
	}

	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		Book bk = library.getBookByID(bookID);
		Patron pt = library.getPatronByID(ptID);
		
		// Check if patron is hidden 
        if(pt.getVis() == false) {
        	throw new LibraryException("Patron Doesn't Exist...");
        }
		
		// Is book removed
		if(bk.getVis() == false) {
			throw new LibraryException("Book Doesn't Exist...");
		}
		
		// If book isn't currently on loan
		if(bk.getLoan() == null) {
			throw new LibraryException("Book is not on loan");
		}
		
		// If book isn't loaned to current patron
		else if(bk.getLoan().getPatron().getId() != ptID) {
			throw new LibraryException("Book isn't loaned to this patron");
		}
		
		
		// Create New Due Date
		LocalDate dueDate = currentDate.plusDays(library.getLoanPeriod());
		pt.renewBook(bk, dueDate);
		
		// Update Library Loan File Storage
		try {
			storeData(library);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Inform user renew has been performed 
		System.out.println("Book Due Date has been renew and set to: " + dueDate);
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
