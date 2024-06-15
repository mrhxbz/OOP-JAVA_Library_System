package bcu.cmp5332.librarysystem.gui;

//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;

@SuppressWarnings("serial")
public class ViewBookLoanWindow extends JFrame implements ActionListener{
	private MainWindow mw;

	
	 public ViewBookLoanWindow(MainWindow mw) {
	        this.mw = mw;
	        initialize();
	        	        
	 }
	
	 
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        } 
		
		setTitle("View Borrowed Books");
		
		setSize(800, 300);


        Library library = mw.getLibrary();
        List<Book> booksList = library.getBooks();
        // headers for the table
        String[] columns = new String[]{"Title", "Author", "Pub Date", "Loaned By:", "Loan Start", "Loan End"};
        
        Object[][] data = new Object[booksList.size()][6];
        boolean booksOnLoan = false;
        for (int i = 0; i < booksList.size(); i++) {
        	
            Book book = booksList.get(i);
            
            if(book.isOnLoan()) {
            	booksOnLoan = true;
            	Loan l = book.getLoan();
                Patron p = l.getPatron();
            	data[i][0] = book.getTitle();
                data[i][1] = book.getAuthor();
                data[i][2] = book.getPublicationYear();
                data[i][3] = p.getId() + " " + p.getName() + " " + p.getEmail() + " " + p.getPhone();
                data[i][4] = book.getStatus();//
                data[i][5] = book.getDueDate();
                System.out.println(book.getDueDate());
            }
            
            
            
        }
        if(booksOnLoan == false) {
			data[1][0] = "No books on Loan";
		}
        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
 
        
        setVisible(true);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}

