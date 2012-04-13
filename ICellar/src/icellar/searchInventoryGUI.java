package icellar;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

//This frame is pulled up when the search button is clicked.
//It allows the user to search personal and group inventory
//based on entered criteria.
public class searchInventoryGUI extends JFrame {

	private searchInventoryGUI frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Cellar myCellar;

	/**
	 * Create the frame.
	 */
	public searchInventoryGUI(Cellar c) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame = this;
		myCellar = c;
		setBounds(100, 100, 300, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddBottle = new JLabel("Search Bottles");
		lblAddBottle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddBottle.setBounds(72, 11, 142, 32);
		contentPane.add(lblAddBottle);
		
		JLabel lblMaker = new JLabel("Maker: ");
		lblMaker.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaker.setBounds(10, 75, 64, 14);
		contentPane.add(lblMaker);
		
		//Enter maker to search for here.
		textField = new JTextField();
		textField.setBounds(91, 72, 183, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblType = new JLabel("Type: ");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblType.setBounds(10, 100, 46, 14);
		contentPane.add(lblType);
		
		//Enter type to search for here.
		textField_1 = new JTextField();
		textField_1.setBounds(91, 97, 183, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblYear = new JLabel("Year: ");
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblYear.setBounds(10, 125, 46, 14);
		contentPane.add(lblYear);
		
		JLabel lblVineyard = new JLabel("Vineyard: ");
		lblVineyard.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVineyard.setBounds(10, 150, 64, 14);
		contentPane.add(lblVineyard);
		
		JLabel lblRegion = new JLabel("Region:");
		lblRegion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRegion.setBounds(10, 175, 46, 14);
		contentPane.add(lblRegion);
		
		JLabel lblRating = new JLabel("Rating:");
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRating.setBounds(10, 200, 46, 14);
		contentPane.add(lblRating);
		
		//Enter year to search for here.
		textField_2 = new JTextField();
		textField_2.setBounds(91, 122, 46, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		//Enter vineyard to search for here.
		textField_3 = new JTextField();
		textField_3.setBounds(91, 147, 183, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		//Enter region to search for here.
		textField_4 = new JTextField();
		textField_4.setBounds(91, 172, 183, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		//Enter rating to search for here.
		textField_5 = new JTextField();
		textField_5.setBounds(91, 197, 46, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		//Click this button returns you to the hub menu and displays the bottles that meet
		//your search criteria.
		JButton btnSaveAndExit = new JButton("Search");
		btnSaveAndExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Rewrite table
				iCellarGUI.updateJTable(myCellar.search(textField.getText(), textField_1.getText(), textField_2.getText(),
						textField_3.getText(), textField_4.getText(), textField_5.getText()));
				iCellarGUI.replaceSearchButton();
				frame.dispose();
			}
		});
		btnSaveAndExit.setBounds(72, 240, 117, 43);
		contentPane.add(btnSaveAndExit);
	}

}
