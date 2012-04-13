package icellar;

import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;


public class addBottleGUI extends JFrame {

	private JPanel contentPane;
    private static addBottleGUI frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
    private JTextField textField_6;
    String[][] curUser = new String[1][1];
    private Cellar myCellar;

	public addBottleGUI(Cellar c) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame = this;
        myCellar = c;
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddBottle = new JLabel("Add Bottle");
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

		JButton btnAddBottle = new JButton("Add Bottle");
		btnAddBottle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

                            if(textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals("")){

                                JOptionPane.showMessageDialog(contentPane, "All required fields not entered.");
                            }

                            else{

                                InportAccount fileReader = new InportAccount();

                                File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());

                                curUser = fileReader.fileReader(curFile);
                                Bottle btl = new Bottle(textField.getText(), textField_1.getText(), textField_2.getText());
                                if(!textField_3.getText().equals("")) btl.setVineyard(textField_3.getText());
                                if(!textField_4.getText().equals("")) btl.setRegion(textField_4.getText());
                                if(!textField_5.getText().equals("")) btl.setRating(Double.parseDouble(textField_5.getText()));
                                if(!textField_6.getText().equals("")) btl.addComment(new Comment(textField_6.getText(), curUser[0][0]));
                                else {
                                	btl.addComment(null);
                                }
                                //Bottle btl = new Bottle(textField.getText(), textField_1.getText(), textField_2.getText(),
                                		//textField_3.getText(), textField_4.getText(), Double.parseDouble(textField_5.getText()), new Comment(textField_6.getText(), curUser[0][0]));
                                myCellar.addBottle(btl);
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

			}
		});
		btnAddBottle.setBounds(91, 358, 97, 43);
		contentPane.add(btnAddBottle);

		JLabel lblIndicatesA = new JLabel("* indicates a required field.");
		lblIndicatesA.setBounds(62, 50, 152, 14);
		contentPane.add(lblIndicatesA);
	}
}