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
import bcu.cmp5332.librarysystem.commands.RemoveBook;
import bcu.cmp5332.librarysystem.commands.RemovePatron;//
import bcu.cmp5332.librarysystem.main.LibraryException;

@SuppressWarnings({ "serial", "unused" })
public class DeletePatronWindow extends JFrame implements ActionListener{
	private MainWindow mw;
	private JTextField patronIdText = new JTextField();
	private JButton removeBtn = new JButton("Remove");
    private JButton cancelBtn = new JButton("Cancel");
    
    public DeletePatronWindow(MainWindow mw){
    	this.mw = mw;
    	initialize();
    }

	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        } 
		
		setTitle("Remove Patron");
   	 	setSize(300, 200);
   	 
   	 	JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Patron ID : "));
        topPanel.add(patronIdText);
		
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(removeBtn);
        bottomPanel.add(cancelBtn);
        
        
        removeBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);
        
        setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == removeBtn) {
            removePatron();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }		
	}

	private void removePatron() {
		try {
            String id = patronIdText.getText();
            
            Command removePatron = new RemovePatron(Integer.parseInt(id));
            removePatron.execute(mw.getLibrary(), LocalDate.now());
            mw.displayPatrons();
            JOptionPane.showMessageDialog(this, "Patron " + id + " was removed!");
            this.setVisible(false);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }catch(NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this, "Patron ID should be a number");
        }
	}
   
}
