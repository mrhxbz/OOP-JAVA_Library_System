package bcu.cmp5332.librarysystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;

public class ShowBook implements Command {
	
	private int id;
	
	public ShowBook(int id){
		this.id = id;
	}
	
	@Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        
        Book bk = library.getBookByID(id);
        
        // Check if book is removed
        if(bk.getVis() == false) {
        	throw new LibraryException("Book Doesn't Exist...");
        }
        
        System.out.println(bk.getDetailsLong());
    }
}
