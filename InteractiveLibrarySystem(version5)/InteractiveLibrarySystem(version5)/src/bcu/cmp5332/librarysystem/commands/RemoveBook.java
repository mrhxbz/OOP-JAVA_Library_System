package bcu.cmp5332.librarysystem.commands;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import bcu.cmp5332.librarysystem.data.DataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;

public class RemoveBook implements Command, DataManager {
	private int id;
	private final String RESOURCE = "./resources/data/books.txt";
	
	public RemoveBook(int id) {
		this.id = id;
	}
	
	@Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
		Book bk = library.getBookByID(id);
		
		
		// Books that are currently on loan can't be removed 
		if(bk.isOnLoan() == true) {
			throw new LibraryException("Unable to Remove Book as it's currently on loan.");
		}
		
		// Book is hidden from book viewing (listbooks) and can't be accessed by other functions using boolean variable
		bk.setVis(false);
		
		try {
			storeData(library);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Indicate to user book has been removed
		System.out.println("Book has been removed...");
        
    }

	@Override
	public void loadData(Library library) throws IOException, LibraryException {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void storeData(Library library) throws IOException {
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Book book : library.getBooks()) {
                out.print(book.getId() + SEPARATOR);
                out.print(book.getTitle() + SEPARATOR);
                out.print(book.getAuthor() + SEPARATOR);
                out.print(book.getPublicationYear() + SEPARATOR);
                out.print(book.getPublisher() + SEPARATOR);
                out.print(book.getVis() + SEPARATOR);
                out.println();
            }
        }
    }
}
