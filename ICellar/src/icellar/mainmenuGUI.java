package icellar;

import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;


public class mainmenuGUI extends JFrame {

	private static mainmenuGUI frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
    String[][] userAccounts = new String[1][1];
    String[][] accounts = new String[1][1];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new mainmenuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainmenuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame = this;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSignIn = new JLabel("Login:");
		lblSignIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSignIn.setBounds(10, 40, 64, 17);
		contentPane.add(lblSignIn);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 68, 64, 14);
		contentPane.add(lblUsername);

		textField = new JTextField();
		textField.setBounds(84, 65, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(10, 93, 64, 14);
		contentPane.add(lblPassword);

		textField_1 = new JTextField();
		textField_1.setBounds(84, 90, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);


		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

                            InportUserAccount fileReader2 = new InportUserAccount();
                            File curFile2 = new File(new File("wineData/Account.txt").getAbsolutePath());
                            //pulls the list of all users from the server

                            userAccounts = fileReader2.fileReader(curFile2);

                            int i = 0;
                            String userExt = "false";
                            while(userAccounts[0][i] != null){

                                if(userAccounts[0][i].equals(textField.getText()) == true){
                                    //checks to make such the user exists

                                    userExt = "true";
                                    break;
                                }

                                if(userAccounts[0][i+1] == null){
                                    break;
                                }
                                i++;
                            }

                            if(userExt.equals("true") == true){

                                InportAccount2 fileReader = new InportAccount2();
                                File curFile = new File(new File("wineData/Users/" + textField.getText() + ".txt").getAbsolutePath());

                                accounts = fileReader.fileReader(curFile);//sets accounts to all usernames in wineData/Account.txt

                                i = 0;
                                while(accounts[0][i] != null){//checks to make sure the username and password matches to log the user in

                                    if((accounts[i][2].equals("userName: " + textField.getText()))&&(accounts[i][3].equals("password: " + textField_1.getText()))){

                                        
                                        CurSession loginSession = new CurSession();
                                        loginSession.loginSession(textField.getText(), textField_1.getText());

                                        frame.dispose();

                                        try{//sets up the display if a user creates a account for the bottle inventory
                                            iCellarGUI frame2 = new iCellarGUI();
                                            frame2.setVisible(true);
                                        }
                                        catch(Exception e){
                                            e.printStackTrace();
                                        }

                                        break;
                                    }

                                    if(accounts[0][i+1] != null){
                                        //System.out.println("Wrong username/password");
                                    	JOptionPane.showMessageDialog(contentPane, "Wrong username/password.");
                                        break;
                                    }
                                    i++;
                                }
                            }

                            else{
                                //System.out.println("No user: " + textField.getText());
                                JOptionPane.showMessageDialog(contentPane, "No user: " + textField.getText());
                            }

			}
		});
		btnLogin.setBounds(10, 118, 89, 23);
		contentPane.add(btnLogin);

                //start of all the fields on the main menu
		JLabel lblSignUp = new JLabel("Sign Up:");
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSignUp.setBounds(264, 41, 64, 14);
		contentPane.add(lblSignUp);

		JLabel lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setBounds(264, 68, 64, 14);
		contentPane.add(lblUsername_1);

		JLabel lblPassword_1 = new JLabel("Password: ");
		lblPassword_1.setBounds(264, 93, 64, 14);
		contentPane.add(lblPassword_1);

		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(264, 118, 46, 14);
		contentPane.add(lblEmail);

		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(264, 143, 46, 14);
		contentPane.add(lblName);

		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(264, 168, 75, 14);
		contentPane.add(lblDateOfBirth);

		textField_2 = new JTextField();
		textField_2.setBounds(338, 65, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(338, 90, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(338, 115, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(338, 140, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(338, 165, 86, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		JButton btnSignUp = new JButton("Sign Up");//action for setting up a new account
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

                            InportAccount fileReader = new InportAccount();
                            //all current accounts are read in to prevent the same user name being picked
                            File curFile = new File(new File("wineData/Account.txt").getAbsolutePath());
                            accounts = fileReader.fileReader(curFile);

                            if(((textField_4.getText().isEmpty())||(textField_5.getText().isEmpty())||(textField_2.getText().isEmpty())||(textField_3.getText().isEmpty())||(textField_6.getText().isEmpty())) == true){
                                //System.out.println("Fields not entered");//error if nothing is entered in a field
                                JOptionPane.showMessageDialog(contentPane, "Fields not entered");
                            }

                            if(((textField_4.getText().contains(" "))||(textField_5.getText().contains(" "))||(textField_2.getText().contains(" "))||(textField_3.getText().contains(" "))||(textField_6.getText().contains(" "))) == true){
                                //System.out.println("No spaces allowed");//error if a space is used in a field
                                JOptionPane.showMessageDialog(contentPane, "No spaces allowed");
                            }

                            else{

                                int i = 0;
                                while(accounts[0][i].equals(textField_2.getText()) == false){//goes to each account breaks if there is a repeated username

                                    if((accounts[0][i+1] == null)&&(accounts[0][i].equals(textField_2.getText()) == false)){//if so makes the new user account, adds the user name to the list of all users, and creates the users bottle file

                                        MakeAccount accountMaker = new MakeAccount();

                                        accountMaker.accountMaker(textField_4.getText(), textField_5.getText(), textField_2.getText(), textField_3.getText(), textField_6.getText());

                                        frame.dispose();
                                        
                                        try{
                                            iCellarGUI frame2 = new iCellarGUI();
                                            frame2.setVisible(true);
                                        }
                                        catch(Exception e){
                                            e.printStackTrace();
                                        }
                                        
                                        break;
                                    }

                                    i++;
                                }
                            }
			}
		});
		btnSignUp.setBounds(264, 193, 89, 23);
		contentPane.add(btnSignUp);
		
		JLabel lblIcellar = new JLabel("iCellar");
		lblIcellar.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIcellar.setBounds(10, 11, 106, 23);
		contentPane.add(lblIcellar);
	}
}

class InportAccount{//reads all the current users in wineData/Account.txt and sets each account to a array

    public String[][] fileReader (File fileName){

        String[][] wordList = new String[1][100];
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

class MakeAccount{//creats the new user, adds the username to wineData/Account.txt and creats a user file in wineData/Users/"username".txt

    public void accountMaker (String email, String name, String userName, String password, String bDay){


        File curFile = new File(new File("wineData/Account.txt").getAbsolutePath());
        File curFile2 = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());

        BufferedWriter bw = null;

        try {

            curFile2.delete();
            curFile2.createNewFile();
        	
            bw = new BufferedWriter(new FileWriter(curFile, true));
            bw.write(userName);
            bw.newLine();
            bw.flush();
            bw.close();

            File f = new File(new File("wineData/Users/" + userName + ".txt").getAbsolutePath());

            f.createNewFile();

            bw = new BufferedWriter(new FileWriter(f, true));
            bw.write("email: " + email + "\nname: " + name + "\nuserName: " + userName + "\npassword: " + password + "\nbDay: " + bDay + "\nWineclubs: ");
            bw.flush();
            bw.close();

            bw = new BufferedWriter(new FileWriter(curFile2, true));
            
            bw.write(userName + "\n" + password);
            bw.newLine();
            bw.flush();
            bw.close();

            File f2 = new File(new File("wineData/Users/" + userName + "Bottle.txt").getAbsolutePath());
            f2.createNewFile();

        }

        catch (IOException e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class InportAccount2{//like the ImportAccount however changes the array fields to allow for the bottle array

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

class InportUserAccount{//Reads in the users and creates an array to house all the info linked to the given user

    public String[][] fileReader (File fileName){

        String[][] wordList = new String[6][100];
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

class CurSession{//replaces wineData/Login/curLogin.txt with the current users username and password

    public void loginSession (String username, String password){


        BufferedWriter bw = null;

        try {
            File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());
            curFile.delete();
            curFile.createNewFile();

            bw = new BufferedWriter(new FileWriter(curFile, true));
            bw.write(username + "\n" + password);
            bw.newLine();
            bw.flush();
            bw.close();
        }

        catch (IOException e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}