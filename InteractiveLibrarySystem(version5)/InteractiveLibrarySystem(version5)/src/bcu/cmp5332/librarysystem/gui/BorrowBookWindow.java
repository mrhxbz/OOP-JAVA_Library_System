package bcu.cmp5332.librarysystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

//import bcu.cmp5332.librarysystem.commands.AddPatron;
import bcu.cmp5332.librarysystem.commands.BorrowBook;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.main.LibraryException;

@SuppressWarnings("serial")
public class BorrowBookWindow extends JFrame implements ActionListener{

	private MainWindow mw;
	private JTextField patronIdText = new JTextField();
	private JTextField bookIdText = new JTextField();
	
	private JButton borrowBtn = new JButton("Borrow");
    private JButton cancelBtn = new JButton("Cancel");
    
    public BorrowBookWindow(MainWindow mw) {
    	this.mw = mw;
    	initialize();
    }
    
    
    private void initialize() {
   	 	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        } 
   	 
   	 	setTitle("Borrow Book");
   	 	setSize(300, 200);
   	 
   	 	JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Patron ID : "));
        topPanel.add(patronIdText);
        
        topPanel.add(new JLabel("Book ID : "));
        topPanel.add(bookIdText);

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(borrowBtn);
        bottomPanel.add(cancelBtn);
        
        borrowBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        
        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);
        
   }
    
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == borrowBtn) {
            borrowBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }		
	}
	private void borrowBook() {
		try {
			String patronId = patronIdText.getText();
			String bookId = bookIdText.getText();
			Command borrowBook = new BorrowBook(Integer.parseInt(patronId), Integer.parseInt(bookId));
			borrowBook.execute(mw.getLibrary(), LocalDate.now());
			mw.displayPatrons();
			this.setVisible(false);
			
			
		} catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

}

