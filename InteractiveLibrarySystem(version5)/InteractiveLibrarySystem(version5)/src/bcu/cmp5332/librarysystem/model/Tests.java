package bcu.cmp5332.librarysystem.model;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import bcu.cmp5332.librarysystem.main.*;
import bcu.cmp5332.librarysystem.data.*;
import bcu.cmp5332.librarysystem.commands.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Tests {
	@Test
    //Email: load and read from txt file
    public void readEmailTest() throws IOException, LibraryException {
        Library library = LibraryData.load();
        List<Patron>patrons = library.getPatrons();
        Patron patron = patrons.get(1);
        assertEquals("vlac@gmail.com", patron.getEmail());
    }
    @Test 
    //Email: create patron with email
    public void makePatronTest() throws IOException, LibraryException {
        Library library = LibraryData.load();
        AddPatron newPatron = new AddPatron("Sam", "0107", "sam@gmail.com");
        newPatron.execute(library, LocalDate.now());
        List<Patron>patrons = library.getPatrons();
        Patron patron = patrons.get(3);
        assertEquals("sam@gmail.com", patron.getEmail());
    }
    @Test
    //Email: save patron with email
    public void savePatronTest() throws IOException, LibraryException {
        Library library = LibraryData.load();
        AddPatron newPatron = new AddPatron("Sam", "0107", "Sam@gmail.com");
        newPatron.execute(library, LocalDate.now());
        LibraryData.store(library);
        library = LibraryData.load();
        List<Patron>patrons = library.getPatrons();
        Patron patron = patrons.get(3);
        assertEquals("Sam@gmail.com", patron.getEmail());
    }
    private void assertEquals(String string, String email) {
		// TODO Auto-generated method stub
		
	}
	@Test
    //checking publisher: able to load and read the data from txt file
    public void readPublisherfromFileTest() throws IOException, LibraryException {
        Library library = LibraryData.load();
        List<Book>books = library.getBooks();
        Book book = books.get(2);
        assertEquals("Pan Books", book.getPublisher());
    }
    @Test
    //publisher: creating book
    public void makeBookTest() throws IOException, LibraryException {
        Library library = LibraryData.load();
        
        String line = "addBook";
        AddBook newBook = new AddBook("vv: Unknown", "Calvin Sam", "2018", "All Star Ltd");
        newBook.execute(library, LocalDate.now());
        List<Book>books = library.getBooks();
        Book book = books.get(5);
        assertEquals("All Star Ltd", book.getPublisher());
    }
    @Test
    //publisher: saving book
    public void saveBookTest() throws IOException, LibraryException {
        Library library = LibraryData.load();
        AddBook newBook = new AddBook("vv: Unknown", "Calvin Sam", "2018", "All Star Ltd");
        newBook.execute(library, LocalDate.now());
        LibraryData.store(library);
        library = LibraryData.load();
        List<Book>books = library.getBooks();
        Book book = books.get(5);
        assertEquals("All Star Ltd", book.getPublisher());
    }
        

}