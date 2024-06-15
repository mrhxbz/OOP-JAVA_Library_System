package bcu.cmp5332.librarysystem.main;

import bcu.cmp5332.librarysystem.commands.LoadGUI;
import bcu.cmp5332.librarysystem.commands.LoanHistory;
import bcu.cmp5332.librarysystem.commands.RemoveBook;
import bcu.cmp5332.librarysystem.commands.RemovePatron;
import bcu.cmp5332.librarysystem.commands.RenewBook;
import bcu.cmp5332.librarysystem.commands.ReturnBook;
import bcu.cmp5332.librarysystem.commands.ShowBook;
import bcu.cmp5332.librarysystem.commands.ShowPatron;
import bcu.cmp5332.librarysystem.commands.ListBooks;
import bcu.cmp5332.librarysystem.commands.ListPatrons;
import bcu.cmp5332.librarysystem.commands.AddBook;
import bcu.cmp5332.librarysystem.commands.AddPatron;
import bcu.cmp5332.librarysystem.commands.BorrowBook;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.Help;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@SuppressWarnings("unused")
public class CommandParser {
    
    public static Command parse(String line) throws IOException, LibraryException {
        try {
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];

            
            if (cmd.equals("addbook")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Title: ");
                String title = br.readLine();
                System.out.print("Author: ");
                String author = br.readLine();
                System.out.print("Publication Year: ");
                String publicationYear = br.readLine();
                System.out.print("Publisher: ");
                String publisher = br.readLine();
                
                return new AddBook(title, author, publicationYear, publisher);
            } else if (cmd.equals("addpatron")) {
                // Add New Patron
            	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Name: ");
                String name = br.readLine();
                System.out.print("Phone Number: ");
                String pnum = br.readLine();
                System.out.print("Email: ");
                String email = br.readLine();
                
                // Create new Patron as Object
                return new AddPatron(name, pnum, email);
            } else if (cmd.equals("loadgui")) {
                return new LoadGUI();
            } else if (parts.length == 1) {
                if (line.equals("listbooks")) {
                    return new ListBooks();
                } else if (line.equals("listpatrons")) {
                	// Listing all patrons
                	return new ListPatrons();
                } else if (line.equals("help")) {
                    return new Help();
                } else if(line.equals("loanhistory")) {
                	return new LoanHistory();
                }
            } else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);

                if (cmd.equals("showbook")) {
                	// Add a function to jump to
                	return new ShowBook(id);
                } else if (cmd.equals("showpatron")) {
                	// Add a function to jump to
                	return new ShowPatron(id);
                } else if (cmd.equals("removebook")) {
                	// func for removebook
                	return new RemoveBook(id);
                } else if (cmd.equals("removepatron")) {
                	// func for removebook
                	return new RemovePatron(id);
                }
            } else if (parts.length == 3) {
                int patronID = Integer.parseInt(parts[1]);
                int bookID = Integer.parseInt(parts[2]);

                if (cmd.equals("borrow")) {
                    // Goto borrow function
                	return new BorrowBook(patronID, bookID);
                } else if (cmd.equals("renew")) {
                	// Goto renew function
                	return new RenewBook(patronID, bookID);
                } else if (cmd.equals("return")) {
                	// Goto return function
                	return new ReturnBook(patronID, bookID);
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new LibraryException("Invalid command.");
    }
}
