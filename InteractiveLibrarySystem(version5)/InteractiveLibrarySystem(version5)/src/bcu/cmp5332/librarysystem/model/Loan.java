package bcu.cmp5332.librarysystem.model;

import java.time.LocalDate;

public class Loan {
    
    private Patron patron;
    private Book book;
    private LocalDate startDate;
    private LocalDate dueDate;

    public Loan(Patron patron, Book book, LocalDate startDate, LocalDate dueDate) {
        // TODO: implementation here
    	this.patron = patron;
    	this.book = book;
    	this.startDate = startDate;
    	this.dueDate = dueDate;
    }
    
    // TODO: implementation of Getter and Setter methods
    
    // Getter Methods
    
    public Patron getPatron() {
    	return patron;
    }
    
    public Book getBook() {
    	return book;
    }
    
    public LocalDate getstartDate() {
    	return startDate;
    }
    
    public LocalDate getdueDate() {
    	return dueDate;
    }
    
    // Setter Methods
    
    public void setstartDate(LocalDate crtDate) {
    	startDate = crtDate;
    }
    
    public void setdueDate(LocalDate newDate) {
    	this.dueDate = newDate;
    }
    
}
 