package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class LoanDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/loans.txt";

    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        // TODO: implementation here
    	try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                	int id = Integer.parseInt(properties[0]);
                	int bkid = Integer.parseInt(properties[1]);
                	
                	// Patron Obj
                	Patron pt = library.getPatronByID(id);
                	
                	// Book Obj
                	Book bk = library.getBookByID(bkid);
                	
                	// Start Date
                	LocalDate startDate = LocalDate.parse(properties[2]); 
                	
                	// Due Date
                	LocalDate dueDate = LocalDate.parse(properties[3]);
                	
                	// Loan Obj
                	Loan loan = new Loan(pt, bk, startDate, dueDate);
                	
                	// Loan Set to Corresponding Book
                	bk.setLoan(loan);
                	
                	// Book is added to Corresponding Patron Borrowed List
                	pt.borrowBook(bk, dueDate);
                	
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse loan for patron ID "+ properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
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
 