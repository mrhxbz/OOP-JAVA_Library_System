package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patron {
    
    private int id;
    private String name;
    private String phone;
    private final List<Book> books = new ArrayList<>();
    private String email;
    private boolean vis = true;
    
    // TODO: implement constructor here
    public Patron(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    public int getId() {
        return id;
    } 
    
    public String getName() {
    	return name;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public List<Book> getBooks() {
    	return books;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public boolean getVis() {
    	return vis;
    }
    
    public void setVis(boolean setter) {
    	this.vis = setter;
    }
    
    public String getDetailsShort() {
        return "Patron #" + id + " - " + name;
    }
    
    
    
    public void borrowBook(Book book, LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
    	book.setDueDate(dueDate); // Set due date on book 
    	addBook(book); // Add book to patron borrowed list
    }

    public void renewBook(Book book, LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
    	book.setDueDate(dueDate);
    }

    public void returnBook(Book book) throws LibraryException {
        // TODO: implementation here
    	books.remove(book); // Removes book from patron's borrowed list
    	book.returnToLibrary(); // Sets Book's loan status to null
    }
    
    public void addBook(Book book) {
        // TODO: implementation here
    	this.books.add(book);
    }

	public boolean getIsHidden() {
		// TODO Auto-generated method stub
		return false;
	}

}
 