package bcu.cmp5332.librarysystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

public class ShowPatron implements  Command {
	
	private int id;
	
	public ShowPatron(int id){
		this.id = id;
	}
	
	@Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        
        Patron pt = library.getPatronByID(id);
        
        // Check if patron is hidden 
        if(pt.getVis() == false) {
        	throw new LibraryException("Patron Doesn't Exist...");
        }
        
        System.out.println("Patron #" + pt.getId());
        System.out.println("Name: " + pt.getName());
        System.out.println("Phone Number: " + pt.getPhone());
        System.out.println("Email: " + pt.getEmail());
        if(pt.getBooks().size() > 0) {
        	System.out.println("Currently Borrowed Books:");
        	for(Book book : pt.getBooks()) {
        		System.out.println(book.getDetailsShort() + "| Due Date: " + book.getDueDate());
        	}
        	
        }
    }
}
