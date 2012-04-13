package icellar;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;


public class userProfileGUI extends JFrame {

	private static userProfileGUI frame;
	private static iCellarGUI prevFrame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
        String[][] accounts = new String[1][1];
        String[][] curUser = new String[1][1];
        String[][] userInfo = new String[1][1];
        JLabel label = new JLabel("---------");
        JLabel label_1 = new JLabel("--------");
        JLabel label_2 = new JLabel("--------");
        JLabel label_3 = new JLabel("--------");
        JLabel label_4 = new JLabel("--------");

	/**
	 * Create the frame.
	 */
	public userProfileGUI(iCellarGUI pf) {


                
                File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());
                ImportAccount fileReader = new ImportAccount();

                curUser = fileReader.fileReader(curFile);

                ImportAccount fileReader2 = new ImportAccount();
                File curFile2 = new File(new File("wineData/Account.txt").getAbsolutePath());

                accounts = fileReader2.fileReader(curFile2);


                File curFile3 = new File(new File("wineData/Users/" + curUser[0][0] + ".txt").getAbsolutePath());
                ImportAccount fileReader3 = new ImportAccount();

                userInfo = fileReader3.fileReader(curFile3);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame = this;		
		prevFrame = pf;
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserName = new JLabel("User Name: ");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserName.setBounds(10, 79, 73, 21);
		contentPane.add(lblUserName);

		label.setBounds(102, 82, 82, 14);
		contentPane.add(label);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(10, 111, 73, 14);
		contentPane.add(lblPassword);

		
		label_1.setBounds(102, 111, 82, 14);
		contentPane.add(label_1);

		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

                            String password = textField.getText();
                            userInfo[0][3] = "password: " + password;

                            UpdateUserInfo update = new UpdateUserInfo();
                            update.update(userInfo, curUser[0][0]);

                            label_1.setText(userInfo[0][3].substring(userInfo[0][3].indexOf(" ")));
                            textField.setText("");
			}
		});
		btnChangePassword.setBounds(93, 136, 143, 23);
		contentPane.add(btnChangePassword);

		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmailAddress.setBounds(10, 170, 82, 14);
		contentPane.add(lblEmailAddress);

		
		label_2.setBounds(102, 170, 82, 14);
		contentPane.add(label_2);

		JButton btnChangeEmail = new JButton("Change Email");
		btnChangeEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

                            String email = textField_1.getText();
                            userInfo[0][0] = "email: " + email;

                            UpdateUserInfo update = new UpdateUserInfo();
                            update.update(userInfo, curUser[0][0]);

                            label_2.setText(userInfo[0][0].substring(userInfo[0][0].indexOf(" ")));
                            textField_1.setText("");
			}
		});
		btnChangeEmail.setBounds(93, 195, 143, 23);
		contentPane.add(btnChangeEmail);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(10, 229, 73, 14);
		contentPane.add(lblName);

		
		label_3.setBounds(102, 229, 82, 14);
		contentPane.add(label_3);

		JButton btnChangeName = new JButton("Change Name");
		btnChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

                            String name = textField_2.getText();
                            userInfo[0][1] = "name: " + name;

                            UpdateUserInfo update = new UpdateUserInfo();
                            update.update(userInfo, curUser[0][0]);

                            label_3.setText(userInfo[0][1].substring(userInfo[0][1].indexOf(" ")));
                            textField_2.setText("");
			}
		});
		btnChangeName.setBounds(93, 254, 143, 23);
		contentPane.add(btnChangeName);

		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDateOfBirth.setBounds(10, 288, 82, 14);
		contentPane.add(lblDateOfBirth);

		
		label_4.setBounds(102, 288, 82, 14);
		contentPane.add(label_4);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frame.dispose();
				prevFrame.dispose();
				

				try {
					mainmenuGUI frame2 = new mainmenuGUI();
					frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLogout.setBounds(85, 334, 108, 36);
		contentPane.add(btnLogout);

		JLabel lblNewLabel = new JLabel("User Profile");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(85, 11, 108, 35);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(188, 108, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(188, 167, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(188, 226, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

                

                label.setText(userInfo[0][2].substring(userInfo[0][2].indexOf(" ")));
                label_1.setText(userInfo[0][3].substring(userInfo[0][3].indexOf(" ")));
                label_2.setText(userInfo[0][0].substring(userInfo[0][0].indexOf(" ")));
                label_3.setText(userInfo[0][1].substring(userInfo[0][1].indexOf(" ")));
                label_4.setText(userInfo[0][4].substring(userInfo[0][4].indexOf(" ")));



	}

}

class ImportAccount{//reads all the current users in wineData/Account.txt

    public String[][] fileReader (File fileName){

        String[][] wordList = new String[6][10];
        int i = 0;

        try{

             FileInputStream fstream = new FileInputStream(fileName);

             DataInputStream in = new DataInputStream(fstream);
             BufferedReader br = new BufferedReader(new InputStreamReader(in));
             String stringLine;

             while ((stringLine = br.readLine()) != null)   {

                 wordList[0][i] = stringLine;
                 i++;

             }
             return wordList;
        }

        catch (Exception e){//Catch exception if any
                System.err.println("Error: " + e.getMessage());
        }

        return wordList;
    }
}

class UpdateUserInfo{

    public void update (String[][] info, String user){

        BufferedWriter bw = null;

         try {

            File curFile = new File(new File("wineData/Users/" + user + ".txt").getAbsolutePath());

            curFile.delete();
            curFile.createNewFile();

            bw = new BufferedWriter(new FileWriter(curFile, true));

            bw.write(info[0][0] + "\n"  + info[0][1]+ "\n" + info[0][2]+ "\n" + info[0][3]+ "\n" + info[0][4]+ "\n" + info[0][5]);

            bw.flush();
            bw.close();




        }
         catch (IOException e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}