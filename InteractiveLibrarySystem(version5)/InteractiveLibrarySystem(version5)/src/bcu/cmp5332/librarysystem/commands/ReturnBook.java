package bcu.cmp5332.librarysystem.commands;

import java.io.FileWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;

import bcu.cmp5332.librarysystem.data.DataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

public class ReturnBook implements Command, DataManager {
	
	private int patronID;
	private int bookID;
	private LocalDate crt;
	public final String RESOURCE = "./resources/data/loan_history.txt";
	
	public ReturnBook(int patronID, int bookID){
		this.patronID = patronID;
		this.bookID = bookID;
	}
	
	@Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
		// Create Patron Object (i.e get using ID)
		Patron pt = library.getPatronByID(patronID);
		crt = currentDate;
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
		
		// Check if book is on loan
		if(bk.isOnLoan() == false) {
			throw new LibraryException("Book is not on loan.");
		}
		
		// If book isn't borrowed by patron
		else if(bk.getLoan().getPatron().getId() != patronID) {
    		throw new LibraryException("The book is not on loan to this patron");
    	}
		
		LocalDate bkdue = bk.getDueDate();
		
		// Is book overdue?
		if(currentDate.isAfter(bkdue)) {
			// By how many days
			Period p = Period.between(bkdue, currentDate); 
			System.out.println("Book is past due by: ");
			System.out.println("Days: " + p.getDays());
			System.out.println("Months: " + p.getMonths());
			System.out.println("Years: " + p.getYears());
		}
		
		pt.returnBook(bk);
		
		// Remove Loan data from Library Storage
		try {
			storeData(library);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Book was returned successfully...");
    }

	@Override
	public void loadData(Library library) throws IOException, LibraryException {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void storeData(Library library) throws IOException {
        // TODO: implementation here
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
    		out.print(patronID + SEPARATOR);
			out.print(bookID + SEPARATOR);
			out.print(crt + SEPARATOR);
			out.println();
        }
    }
}
