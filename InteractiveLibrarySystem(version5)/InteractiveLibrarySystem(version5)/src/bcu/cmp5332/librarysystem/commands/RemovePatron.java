package bcu.cmp5332.librarysystem.commands;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import bcu.cmp5332.librarysystem.data.DataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

public class RemovePatron implements Command, DataManager {
	private int id;
	private final String RESOURCE = "./resources/data/patrons.txt";
	
	public RemovePatron(int id){
		this.id = id;
	}
	
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		// TODO Auto-generated method stub
		Patron pt = library.getPatronByID(id);
		
		// Check if patron has any books on loan 
		if(pt.getBooks().size() > 0) {
			throw new LibraryException("Unable to Remove Patron as they have book on loan...");
		}
		
		// patron is hidden 
		pt.setVis(false);
		
		try {
			storeData(library);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Patron has been removed...");
	}

	@Override
	public void loadData(Library library) throws IOException, LibraryException {
		// TODO Auto-generated method stub
		
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
                out.print(patron.getVis() + SEPARATOR);
                out.println();
            }
        }
    }
	
}
