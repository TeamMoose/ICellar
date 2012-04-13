package icellar;

import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class editBottleGUI extends JFrame {

	private editBottleGUI frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	String[][] curUser = new String[1][1];
	private Cellar myCellar;
	private int selectedRow;

	/**
	 * Create the frame.
	 */
	public editBottleGUI(Cellar c, int n) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame = this;
		myCellar = c;
		selectedRow = n;
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddBottle = new JLabel("Edit Bottle");
		lblAddBottle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddBottle.setBounds(91, 11, 97, 32);
		contentPane.add(lblAddBottle);
		
		JLabel lblMaker = new JLabel("Maker: *");
		lblMaker.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaker.setBounds(10, 75, 64, 14);
		contentPane.add(lblMaker);
		
		textField = new JTextField();
		textField.setBounds(91, 72, 183, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblType = new JLabel("Type: *");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblType.setBounds(10, 100, 46, 14);
		contentPane.add(lblType);
		
		textField_1 = new JTextField();
		textField_1.setBounds(91, 97, 183, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblYear = new JLabel("Year: *");
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
		
		JLabel lblComments = new JLabel("Comments:");
		lblComments.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblComments.setBounds(10, 225, 64, 14);
		contentPane.add(lblComments);
		
		textField_2 = new JTextField();
		textField_2.setBounds(91, 122, 46, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(91, 147, 183, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(91, 172, 183, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(91, 197, 46, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(91, 220, 183, 127);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		String[][] strs = myCellar.toStringArray();
		textField.setText(strs[selectedRow][0]);
		textField_1.setText(strs[selectedRow][1]);
		textField_2.setText(strs[selectedRow][2]);
		textField_3.setText(strs[selectedRow][3]);
		textField_4.setText(strs[selectedRow][4]);
		textField_5.setText(strs[selectedRow][5]);
		textField_6.setText(strs[selectedRow][6]);
		
		JLabel lblIndicatesA = new JLabel("* indicates a required field.");
		lblIndicatesA.setBounds(60, 44, 152, 20);
		contentPane.add(lblIndicatesA);
		
		JButton btnSaveAndExit = new JButton("Save and Exit");
		btnSaveAndExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				InportAccount fileReader = new InportAccount();

                File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());

                curUser = fileReader.fileReader(curFile);
                
				String[][] strs = myCellar.toStringArray();
				Bottle btl = new Bottle(strs[selectedRow][0],strs[selectedRow][1], strs[selectedRow][2]);
				double rating;
                Comment cm;
                if ( textField_5.getText().equals("") )
                {
                	rating = -1;
                }
                else
                {
                	rating = Double.parseDouble(textField_5.getText());
                }
                if ( textField_6.getText().equals(""))
                {
                	cm = null;
                }
                else
                {
                	cm = new Comment(textField_6.getText(), curUser[0][0]);
                }
				myCellar.editBottle(btl,textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(), rating, cm);
				
				try {
					File curFile2 = new File(new File("wineData/Users/" + curUser[0][0] + "Bottle.txt").getAbsolutePath());
					
					BufferedWriter bw = null;
					
					curFile2.delete();
					curFile2.createNewFile();
					
					bw = new BufferedWriter( new FileWriter( curFile2, true) );
					bw.write(myCellar.toString());
					bw.newLine();
					bw.flush();
					bw.close();						
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				iCellarGUI.updateJTable(myCellar.toStringArray());
				frame.dispose();
			}
		});
		btnSaveAndExit.setBounds(72, 358, 117, 43);
		contentPane.add(btnSaveAndExit);
	}

}
