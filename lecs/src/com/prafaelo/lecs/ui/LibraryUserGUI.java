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

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.prafaelo.lecs.system.user.DefaultUser;

public class LibraryUserGUI implements Runnable{

	private JFrame frm;
	private JTextField address;
	private JTextField name;
	private JTextField phoneNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		launch();
	}

	public static void launch(){
		LibraryUserGUI window = new LibraryUserGUI();
		window.frm.setVisible(true);
	}
	
	
	/**
	 * Create the application.
	 */
	public LibraryUserGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm = new JFrame();
		frm.setTitle("Register new Library User");
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
		gbl_panel_2.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel = new JLabel("Name:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		name = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.gridwidth = 14;
		gbc_textField_2.insets = new Insets(0, 0, 0, 0);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 0;
		panel_2.add(name, gbc_textField_2);
		name.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Address:");
		GridBagConstraints gbc_lblEndereo = new GridBagConstraints();
		gbc_lblEndereo.anchor = GridBagConstraints.EAST;
		gbc_lblEndereo.insets = new Insets(0, 0, 0, 0);
		gbc_lblEndereo.gridx = 0;
		gbc_lblEndereo.gridy = 1;
		panel_2.add(lblEndereo, gbc_lblEndereo);
		
		address = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 13;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 0, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_2.add(address, gbc_textField);
		address.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Phone Number:");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 0, 0);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 2;
		panel_2.add(lblTelefone, gbc_lblTelefone);
		
		phoneNumber = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 5;
		gbc_textField_1.insets = new Insets(0, 0, 0, 0);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel_2.add(phoneNumber, gbc_textField_1);
		phoneNumber.setColumns(10);
		panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{address, phoneNumber, name}));
		

		Panel panel = new Panel();
		panel_1.add(panel, BorderLayout.SOUTH);
		panel.setForeground(Color.BLUE);
		
		JButton button = new JButton("Register");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(validateFormReturnError()){
					JOptionPane.showMessageDialog(null, "Name, Address and PhoneNumber can not be empty!", "Error!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				DefaultUser defaultUser = new DefaultUser(name.getText(), address.getText(), phoneNumber.getText());
				
				//System.out.println(defaultUser);
				
				JOptionPane.showMessageDialog(null, " Library user account sucessfully created!\n "
						                           + "LibraryCode: " + defaultUser.getLibraryCode(), "", JOptionPane.PLAIN_MESSAGE); 
				
				clear();
			}
		});
		panel.add(button);
	}
	
	private boolean validateFormReturnError(){
		
		if(name.getText().equals(""))
			return true;
		if(address.getText().equals(""))
			return true;
		if(phoneNumber.getText().equals(""))
			return true;
		
		return false;
	}
	
	private void clear(){
		name.setText("");
		address.setText("");
		phoneNumber.setText(null);
	}

	@Override
	public void run() {
		launch();
	}
}
