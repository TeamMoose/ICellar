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

		JButton btnJoinNewClub = new JButton("Join Club");
		btnJoinNewClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

                            String club = textField.getText();

                            InportAccount fileReader = new InportAccount();

                            File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());
                            File curFile2 = new File(new File("wineData/WineClub.txt").getAbsolutePath());
                            boolean found = false;
                            

                            curUser = fileReader.fileReader(curFile);
                            wineclubs = fileReader.fileReader(curFile2);

                            int i = 0;
                            while(wineclubs[0][i] != null){

                                if(wineclubs[0][i].equals(club)){

                                    File curFile3 = new File(new File("wineData/WineClubs/" + club + ".txt").getAbsolutePath());
                                    wineAccount = fileReader.fileReader(curFile3);

                                    UpdateUserClubInfo update = new UpdateUserClubInfo();
                                    update.update(club, curUser[0][0]);

                                    JOptionPane.showMessageDialog(contentPane, "Club " + club + "  joined!");
                                    iCellarGUI.updateJTable(myCellar.toStringArray());
                                    found = true;

                                    break;
                                }
                                i++;
                            }

                            if (found == false){
                                JOptionPane.showMessageDialog(contentPane, "Club " + club + " not found");
                            }
			}
		});
		btnJoinNewClub.setBounds(31, 118, 101, 34);
		contentPane.add(btnJoinNewClub);

		JButton btnManageClubs = new JButton("Create Club");
		btnManageClubs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InportAccount fileReader = new InportAccount();

                File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());
                File curFile2 = new File(new File("wineData/WineClub.txt").getAbsolutePath());
                boolean found = false;
                String club = textField.getText();
                
                curUser = fileReader.fileReader(curFile);
                wineclubs = fileReader.fileReader(curFile2);

                int i = 0;
                while(wineclubs[0][i] != null){

                    if(wineclubs[0][i].equals(club)){
                        found = true;
                        JOptionPane.showMessageDialog(contentPane, "Club " + club + "  already in use.");
                        i++;
                        
                    }
                    else{
                        i++;
                    }
                }
                if(found == false){
                    
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
		
		JButton btnLeaveClub = new JButton("Leave Club");
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
                while(wineclubs[0][i] != null){

                    if(wineclubs[0][i].equals(club)){
                        UpdateRemoveUser update = new UpdateRemoveUser();
                        update.update(club, curUser[0][0]);

                        JOptionPane.showMessageDialog(contentPane, "Club " + club + "  removed.");
                        iCellarGUI.updateJTable(myCellar.toStringArray());
                        found = true;

                        break;
                    }
                    i++;
                }

                if (found == false){
                    JOptionPane.showMessageDialog(contentPane, "Club " + club + " not found");
                }
			}
		});
		btnLeaveClub.setBounds(136, 118, 101, 34);
		contentPane.add(btnLeaveClub);
	}

}

class UpdateUserClubInfo{

    public void update (String clubName, String user){

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

class UpdateRemoveUser{

    public void update (String clubName, String user){

        BufferedWriter bw = null;
        String[][] userAccount = new String[10][10];
        String[][] wineAccount = new String[10][10];
        String[] wineclubs = new String[100];
        String[] users = new String[100];

         try {

            File curFile = new File(new File("wineData/Users/" + user + ".txt").getAbsolutePath());
            
            File curFile2 = new File(new File("wineData/Wineclubs/" + clubName + ".txt").getAbsolutePath());

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
                }
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
            }
            
            bw.flush();
            bw.close();
            
        }
         catch (IOException e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class MakeClub{

    public void update (String clubName, String user){

        BufferedWriter bw = null;

         try {

            File curFile = new File(new File("wineData/Wineclub.txt").getAbsolutePath());
            File curFile2 = new File(new File("wineData/Users/" + user + ".txt").getAbsolutePath());
            File curFile3 = new File(new File("wineData/Wineclubs/" + clubName + ".txt").getAbsolutePath());

            bw = new BufferedWriter(new FileWriter(curFile, true));

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
