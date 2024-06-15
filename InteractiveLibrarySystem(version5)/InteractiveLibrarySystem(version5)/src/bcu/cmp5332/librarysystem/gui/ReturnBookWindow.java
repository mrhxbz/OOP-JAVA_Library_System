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

import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.ReturnBook;
import bcu.cmp5332.librarysystem.main.LibraryException;

@SuppressWarnings("serial")
public class ReturnBookWindow extends JFrame implements ActionListener{
	private MainWindow mw;
	private JTextField patronIdText = new JTextField();
	private JTextField bookIdText = new JTextField();
	
	private JButton renewBtn = new JButton("Return");
    private JButton cancelBtn = new JButton("Cancel");
    
    public ReturnBookWindow(MainWindow mw) {
    	this.mw = mw;
    	initialize();
    }

    private void initialize() {
   	 	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        } 
   	 
   	 	setTitle("Return Book");
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
        bottomPanel.add(renewBtn);
        bottomPanel.add(cancelBtn);
        
        renewBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        
        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);
        
   }
    
    
    
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == renewBtn) {
            returnBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }			
	}
	
	private void returnBook() {
		try {
			String patronId = patronIdText.getText();
			String bookId = bookIdText.getText();
			Command returnBook = new ReturnBook(Integer.parseInt(patronId), Integer.parseInt(bookId));
			returnBook.execute(mw.getLibrary(), LocalDate.now());
			mw.displayPatrons();
			this.setVisible(false);
			
			
		} catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }		
	}
    
    
}
