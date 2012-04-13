package icellar;

import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;


public class wineClubMenuGUI extends JFrame {

	private static wineClubMenuGUI frame;
	private JPanel contentPane;
	private JTextField textField;
    String[][] curUser = new String[1][1];
    String[][] wineclubs = new String[1][1];
    String[][] userAccount = new String[1][1];
    String[][] wineAccount = new String[1][1];
    private Cellar myCellar;

	/**
	 * Create the frame.
         * Sets the frame up with the needed labels
	 */
	public wineClubMenuGUI(Cellar c) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame = this;
		myCellar = c;
		setBounds(100, 100, 300, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWineClubMenu = new JLabel("Wine Club Menu");
		lblWineClubMenu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWineClubMenu.setBounds(61, 11, 148, 34);
		contentPane.add(lblWineClubMenu);

		JButton btnJoinNewClub = new JButton("Join Club");//action for a new user to join a wineclub
		btnJoinNewClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

                            String club = textField.getText();

                            InportAccount fileReader = new InportAccount();

                            File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());
                            File curFile2 = new File(new File("wineData/WineClub.txt").getAbsolutePath());
                            boolean found = false;
                            //loads in all the club data and the currently logged in user
                            

                            curUser = fileReader.fileReader(curFile);
                            wineclubs = fileReader.fileReader(curFile2);

                            int i = 0;
                            while(wineclubs[0][i] != null){//pulls all the club names from wineData/Wineclubs.txt

                                if(wineclubs[0][i].equals(club)){//if the club is found the user will be added to the wineclub file, aswell as have the club info added to their profile

                                    File curFile3 = new File(new File("wineData/WineClubs/" + club + ".txt").getAbsolutePath());
                                    wineAccount = fileReader.fileReader(curFile3);

                                    UpdateUserClubInfo update = new UpdateUserClubInfo();
                                    update.update(club, curUser[0][0]);

                                    JOptionPane.showMessageDialog(contentPane, "Club " + club + "  joined!");
                                    iCellarGUI.updateJTable(myCellar.toStringArray());//updates the wine inventory page
                                    found = true;

                                    break;
                                }
                                i++;
                            }

                            if (found == false){//if the club is not found then promp the user thay have not been added to a club
                                JOptionPane.showMessageDialog(contentPane, "Club " + club + " not found");
                            }
			}
		});
		btnJoinNewClub.setBounds(31, 118, 101, 34);
		contentPane.add(btnJoinNewClub);

		JButton btnManageClubs = new JButton("Create Club");//action used to create an new account
		btnManageClubs.addActionListener(new ActionListener() {
                    
			public void actionPerformed(ActionEvent arg0) {
                            
				InportAccount fileReader = new InportAccount();

                            File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());
                            File curFile2 = new File(new File("wineData/WineClub.txt").getAbsolutePath());
                            boolean found = false;
                            String club = textField.getText();
                            //pulls all the info on all clubs and the current logged in user.

                            curUser = fileReader.fileReader(curFile);
                            wineclubs = fileReader.fileReader(curFile2);

                            int i = 0;
                            while(wineclubs[0][i] != null){

                                if(wineclubs[0][i].equals(club)){
                                    
                                    found = true;//if the given inputted name is taken prompts the user that it is
                                    JOptionPane.showMessageDialog(contentPane, "Club " + club + "  already in use.");
                                    i++;

                                }
                                
                                else{
                                    
                                    i++;
                                }
                            }
                            
                            if(found == false){//if the clubname is not in use creates the new club, makes the current user the admin, creates a new club file with the user as the first member

                                MakeClub update = new MakeClub();
                                update.update(club, curUser[0][0]);

                                JOptionPane.showMessageDialog(contentPane, "Club " + club + "  Made.");
                                iCellarGUI.updateJTable(myCellar.toStringArray());
                            }
			}
		});
		btnManageClubs.setBounds(82, 163, 101, 34);
		contentPane.add(btnManageClubs);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setBounds(72, 248, 123, 53);
		contentPane.add(btnBack);

		textField = new JTextField();
		textField.setBounds(136, 87, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblClubName = new JLabel("Club Name: ");
		lblClubName.setBounds(46, 90, 71, 14);
		contentPane.add(lblClubName);
		
		JButton btnLeaveClub = new JButton("Leave Club");//action used for the user to leave a club they are in
		btnLeaveClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String club = textField.getText();

                                InportAccount fileReader = new InportAccount();

                                File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());
                                File curFile2 = new File(new File("wineData/WineClub.txt").getAbsolutePath());
                                boolean found = false;


                                curUser = fileReader.fileReader(curFile);
                                wineclubs = fileReader.fileReader(curFile2);

                                int i = 0;
                                while(wineclubs[0][i] != null){//loads all clubs the user is in and checks it with all the current clubs

                                    if(wineclubs[0][i].equals(club)){//if the user inputs a club they belong to it removes them from the club, removes in info in both the club and user file, and removes any bottles the user has in the club
                                        UpdateRemoveUser update = new UpdateRemoveUser();
                                        update.update(club, curUser[0][0]);

                                        JOptionPane.showMessageDialog(contentPane, "Club " + club + "  removed.");
                                        iCellarGUI.updateJTable(myCellar.toStringArray());
                                        found = true;

                                        break;
                                    }
                                    i++;
                                }

                                if (found == false){//if they input a club they do not belong to, or a club that is not there then prompts the user it has not been removed
                                    JOptionPane.showMessageDialog(contentPane, "Club " + club + " not found");
                                }
			}
		});
		btnLeaveClub.setBounds(136, 118, 101, 34);
		contentPane.add(btnLeaveClub);
	}

}

class UpdateUserClubInfo{//a class for updating the users info based on a club

    public void update (String clubName, String user){//adds the users to the given club and adds the user to the club file

        BufferedWriter bw = null;

         try {

            File curFile = new File(new File("wineData/Users/" + user + ".txt").getAbsolutePath());
            
            bw = new BufferedWriter(new FileWriter(curFile, true));

            bw.write("," + clubName);

            bw.flush();
            bw.close();

            File curFile2 = new File(new File("wineData/Wineclubs/" + clubName + ".txt").getAbsolutePath());

            bw = new BufferedWriter(new FileWriter(curFile2, true));

            bw.write("," + user);

            bw.flush();
            bw.close();
        }
         catch (IOException e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class UpdateRemoveUser{//removes the user from a given club, and removes the user info from the club, and removes any bottle the user has in the club

    public void update (String clubName, String user){

        BufferedWriter bw = null;
        String[][] userAccount = new String[10][10];
        String[][] wineAccount = new String[10][10];
        String[] wineclubs = new String[100];
        String[] users = new String[100];

         try {

            File curFile = new File(new File("wineData/Users/" + user + ".txt").getAbsolutePath());
            
            File curFile2 = new File(new File("wineData/Wineclubs/" + clubName + ".txt").getAbsolutePath());
            //pulls all user and club info
            FileInputStream fstream = new FileInputStream(curFile);

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String stringLine;
         
            String s1 = br.readLine();
            String s2 = br.readLine();
            String s3 = br.readLine();
            String s4 = br.readLine();
            String s5 = br.readLine();
            String s6 = br.readLine();
            
            br.close();
            
            DataOutputStream os = new DataOutputStream( new FileOutputStream(curFile,false));
			os.writeChars("");
            
            curFile.delete();
            curFile.createNewFile();
            
            bw = new BufferedWriter(new FileWriter(curFile, true));

            bw.write(s1 + "\n" + s2+ "\n" + s3+ "\n" + s4+ "\n" + s5 + "\n");
            //remakes the user info in the user file removing the club info
            wineclubs = s6.split(",");
            
            int i = 1;
            bw.write("Wineclubs:");
            while(wineclubs.length > i){
                
                if(wineclubs[i].equals(clubName)){
                    i++;
                    
                }
                else{
                    
                    bw.write("," + wineclubs[i]);
                    i++;
                }//rebuilds all the prevous info from the user's profile
            }
            

            bw.flush();
            bw.close();
            
            FileInputStream fstream2 = new FileInputStream(curFile2);

            DataInputStream in2 = new DataInputStream(fstream2);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
            
            s1 = br2.readLine();
            s2 = br2.readLine();
            s3 = br2.readLine();
            s4 = br2.readLine();
            s5 = br2.readLine();
            
            br2.close();
            
            os = new DataOutputStream( new FileOutputStream(curFile2,false));
			os.writeChars("");
            
            curFile2.delete();
            curFile2.createNewFile();
            
            bw = new BufferedWriter(new FileWriter(curFile2, true));
            
            bw.write(s1 + "\n" + s2+ "\n" + s3+ "\n" + s4+ "\n");
            //removes all the user info from the club file
            
            users = s5.split(",");
            
            i = 1;
            bw.write("Users:");
            while(users.length > i){
                
                if(users[i].equals(user)){
                    i++;
                    
                }
                else{
                    
                    bw.write("," + users[i]);
                    i++;
                }
            }//rebuilds the club file with all the prevous info and removes the users info
            
            bw.flush();
            bw.close();
            
        }
         catch (IOException e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class MakeClub{//a action for users to make a club

    public void update (String clubName, String user){

        BufferedWriter bw = null;

         try {

            File curFile = new File(new File("wineData/Wineclub.txt").getAbsolutePath());
            File curFile2 = new File(new File("wineData/Users/" + user + ".txt").getAbsolutePath());
            File curFile3 = new File(new File("wineData/Wineclubs/" + clubName + ".txt").getAbsolutePath());
            //pulls all the info needed to check on what clubs there are and the current user
            bw = new BufferedWriter(new FileWriter(curFile, true));
            //just writes all the new data to the user's file, the list of all club names, and makes the club file.
            bw.write(clubName);
            bw.newLine();
            bw.flush();
            bw.close();
            
            bw = new BufferedWriter(new FileWriter(curFile2, true));
            bw.write("," + clubName);
            bw.newLine();
            bw.flush();
            bw.close();
            
            curFile.createNewFile();
            
            bw = new BufferedWriter(new FileWriter(curFile3, true));
            bw.write("wineName: " + clubName + "\ndescription: soon" + "\npassword: soon" + "\nAdmin: " + user + "\nusers:," + user);
            bw.flush();
            bw.close();

        }
         catch (IOException e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
