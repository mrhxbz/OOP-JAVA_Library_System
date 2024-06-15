package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class ListBooks implements Command {

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        List<Book> books = library.getBooks();
        int adaptiveSize = books.size();
        
        for (Book book : books) {
        	// If book has been removed
        	if(book.getVis() == false) {
        		adaptiveSize--; // Decrease Book List size by 1
        		continue; // Skips current iteration 
        	}
            System.out.println(book.getDetailsShort());
        }
        System.out.println(adaptiveSize + " book(s)");
    }
}
 