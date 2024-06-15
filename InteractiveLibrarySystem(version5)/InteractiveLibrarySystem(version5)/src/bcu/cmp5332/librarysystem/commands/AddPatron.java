package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.data.DataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class AddPatron implements Command, DataManager {

    private final String name;
    private final String phone;
    private final String email;
    private final String RESOURCE = "./resources/data/patrons.txt";
    
    public AddPatron(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // The execute() function is used to give a patron an the appropriate 'ID'.
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        // TODO: implementation here
    	int maxId = 0;
    	if (library.getPatrons().size() > 0) {
    		int lastIndex = library.getPatrons().size() - 1;
            maxId = library.getPatrons().get(lastIndex).getId();
    	}
        Patron patron = new Patron(++maxId, name, phone, email);
        library.addPatron(patron);
        
        // Execute Update to Library Storage
        try {
			storeData(library);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Patron #" + patron.getId() + " added.");
    }
    
    
    @Override
    public void storeData(Library library) throws IOException {
        // TODO: implementation here
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Patron patron : library.getPatrons()) {
                out.print(patron.getId() + SEPARATOR);
                out.print(patron.getName() + SEPARATOR);
                out.print(patron.getPhone() + SEPARATOR);
                out.print(patron.getEmail() + SEPARATOR);
                out.println();
            }
        }
    }

	@Override
	public void loadData(Library library) throws IOException, LibraryException {
		// TODO Auto-generated method stub
	}
    
    
    
}
 