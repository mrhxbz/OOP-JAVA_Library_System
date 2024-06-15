package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {
    
    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private String publisher;
    private boolean vis = true;

    private Loan loan;

    public Book(int id, String title, String author, String publicationYear, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
	
    public void setPublisher(String publisher) {
    	this.publisher = publisher;
    }
    
    public String getPublisher() {
    	return publisher;
    }

    // Control if Book is deleted or not (Visibility)
    public boolean getVis() {
    	return vis;
    }
    
    public void setVis(boolean setter) {
    	this.vis = setter;
    }
    
    public String getDetailsShort() {
        return "Book #" + id + " - " + title;
    }

    public String getDetailsLong() {
        // TODO: implementation here
    	if(isOnLoan()) {
    		return "Book #" + id + "\nTitle: " + title + "\nAuthor: " + author + 
            		"\nPublication Year: " + publicationYear + "\nPublisher: " + publisher + "\nStatus: On Loan" + "\nBorrower Name: "
            		+ loan.getPatron().getName() + "\nDue Date: " + loan.getdueDate();
    	} 
    	return "Book #" + id + "\nTitle: " + title + "\nAuthor: " + author + 
        		"\nPublication Year: " + publicationYear + "\nPublisher: " + publisher + "\nStatus: Available";
    }
    
    public boolean isOnLoan() {
        return (loan != null);
    }
    
    public String getStatus() {
        // TODO: implementation here
    	if(isOnLoan()) {
    		return "Available";
    	}
        return "On Loan"; //NO LOAN
    }

    public LocalDate getDueDate() {
        // TODO: implementation here
        return loan.getdueDate();
    }
    
    public void setDueDate(LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
    	this.loan.setdueDate(dueDate);
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void returnToLibrary() {
        loan = null;
    }
//
	public Object getstartDate() {
		// TODO Auto-generated method stub
		return null;
	}
}
