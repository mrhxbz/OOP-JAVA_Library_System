package bcu.cmp5332.librarysystem.commands;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import bcu.cmp5332.librarysystem.data.DataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;

public class LoanHistory  implements DataManager, Command {

	private final String RESOURCE = "./resources/data/loan_history.txt";
	
	@Override
	public void loadData(Library library) throws IOException, LibraryException {
		// TODO Auto-generated method stub
		try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            
            System.out.println("Loan History List:");
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                	// Parsing a Loan Record 
                    int id = Integer.parseInt(properties[0]);
                    String bkid = properties[1];
                    String returnDate = properties[2];
                    
                    // Displaying Loan Record
                    System.out.println("Patron ID: " + id + " | Book ID: " + bkid + " | Return Date: " + returnDate);
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse patron id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
	}

	@Override
	public void storeData(Library library) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		// TODO Auto-generated method stub
		try {
			loadData(library);
		} catch (IOException | LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
