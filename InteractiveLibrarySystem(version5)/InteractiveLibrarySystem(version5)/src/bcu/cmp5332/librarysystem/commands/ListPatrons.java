package bcu.cmp5332.librarysystem.commands;


import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class ListPatrons  implements Command {
	
	@Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        List<Patron> patrons = library.getPatrons();
        int adtSize = patrons.size();
        
        for (Patron patron : patrons) {
        	// If Patron has been removed
        	if(patron.getVis() == false) {
        		adtSize--;
        		continue;
        	}
        	
            System.out.println(patron.getDetailsShort());
        }
        System.out.println(adtSize + " patron(s)");
	}
}
