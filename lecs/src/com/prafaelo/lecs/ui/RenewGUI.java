package com.prafaelo.lecs.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.prafaelo.lecs.collection.BookLending;
import com.prafaelo.lecs.system.user.DefaultUser;
import javax.swing.JTable;

public class RenewGUI implements Runnable{

	private JFrame frm;
	private JTextField txtEnterLibraryCode;
	private JTable table;
	private JScrollPane sp;
	private DefaultTableModel defaultTableModel;
	private Map<Integer, BookLending> bookLendingsMap = new HashMap<Integer, BookLending>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		launch();
	}

	public static void launch(){
		RenewGUI window = new RenewGUI();
		window.frm.setVisible(true);
	}
	
	
	/**
	 * Create the application.
	 */
	public RenewGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frm = new JFrame();
		frm.setTitle("Renew Book Copy");
		frm.setBounds(100, 100, 450, 300);
		frm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(UIManager.getColor("window"));
		frm.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(6, 6, 438, 266);
		desktopPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		desktopPane.setLayer(panel_2, 0);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);		
		
		txtEnterLibraryCode = new JTextField();
		txtEnterLibraryCode.setText("Enter Library Code");
		GridBagConstraints gbc_txtEnterLibraryCode = new GridBagConstraints();
		gbc_txtEnterLibraryCode.gridwidth = 12;
		gbc_txtEnterLibraryCode.insets = new Insets(0, 0, 5, 5);
		gbc_txtEnterLibraryCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEnterLibraryCode.gridx = 0;
		gbc_txtEnterLibraryCode.gridy = 0;
		panel_2.add(txtEnterLibraryCode, gbc_txtEnterLibraryCode);
		txtEnterLibraryCode.setColumns(10);
		txtEnterLibraryCode.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterLibraryCode.setText("");
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showBookLendings(Integer.parseInt(txtEnterLibraryCode.getText()));
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 12;
		gbc_btnNewButton.gridy = 0;
		panel_2.add(btnNewButton, gbc_btnNewButton);

		defaultTableModel = new DefaultTableModel(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		//defaultTableModel.addColumn("Code");
		defaultTableModel.addColumn("Book Title");
		defaultTableModel.addColumn("Return Date");
		table = new JTable(defaultTableModel);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 4;
		gbc_table.gridwidth = 13;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		
		sp = new JScrollPane(table);	
		panel_2.add(sp, gbc_table);

		Panel panel = new Panel();
		panel_1.add(panel, BorderLayout.SOUTH);
		panel.setForeground(Color.BLUE);
		
		JButton button = new JButton("Renew");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Collection<String> msg = new ArrayList<String>();
				
				for(int i : table.getSelectedRows()){
					BookLending bookLending = bookLendingsMap.get(i);
					msg.add(bookLending.renew(15));
				}
				
				for(String message : msg){
					JOptionPane.showMessageDialog(null, message, "", JOptionPane.PLAIN_MESSAGE);
				}

				showBookLendings(Integer.parseInt(txtEnterLibraryCode.getText()));
			}
		});
		panel.add(button);
	}

	private void showBookLendings(int libraryCode){
		
		defaultTableModel.setNumRows(0);
		
		Collection<BookLending> bookLendings = new ArrayList<BookLending>();
		bookLendings = BookLending.getBookLendingByLibraryCode(libraryCode);
		
		int i=0;
		for (BookLending bookLending : bookLendings){
			
			//int bookCopyCode =  bookLending.getBookCopy().getCode();
			String bookTitle = bookLending.getBookCopy().getBook().getTitle();
			String returnDate =  bookLending.getReturnDate().toString();
			
			//Object[] rowData = new Object[] {bookCopyCode, bookTitle, returnDate};
			Object[] rowData = new Object[] {bookTitle, returnDate};
			
			defaultTableModel.addRow(rowData);
			
			bookLendingsMap.put(i, bookLending);
			i++;
		}
	}
	
	@Override
	public void run() {
		launch();
	}
}
