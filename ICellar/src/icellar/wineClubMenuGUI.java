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


public class wineClubMenuGUI extends JFrame {

	private static wineClubMenuGUI frame;
	private JPanel contentPane;
	private JTextField textField;
        String[][] curUser = new String[1][1];
        String[][] wineclubs = new String[1][1];
        String[][] userAccount = new String[1][1];
        String[][] wineAccount = new String[1][1];

	/**
	 * Create the frame.
	 */
	public wineClubMenuGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame = this;
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
		btnJoinNewClub.setBounds(187, 83, 87, 28);
		contentPane.add(btnJoinNewClub);

		JButton btnManageClubs = new JButton("Manage Clubs");
		btnManageClubs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnManageClubs.setBounds(72, 150, 123, 53);
		contentPane.add(btnManageClubs);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setBounds(72, 214, 123, 53);
		contentPane.add(btnBack);

		textField = new JTextField();
		textField.setBounds(91, 87, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblClubName = new JLabel("Club Name: ");
		lblClubName.setBounds(10, 90, 71, 14);
		contentPane.add(lblClubName);
	}

}

class UpdateUserClubInfo{

    public void update (String clubName, String user){

        BufferedWriter bw = null;

         try {

            File curFile = new File(new File("wineData/Users/" + user + ".txt").getAbsolutePath());

            bw = new BufferedWriter(new FileWriter(curFile, true));

            bw.write("| " + clubName + " ");

            bw.flush();
            bw.close();

            File curFile2 = new File(new File("wineData/Wineclubs/" + clubName + ".txt").getAbsolutePath());

            bw = new BufferedWriter(new FileWriter(curFile2, true));

            bw.write("| " + user + " ");

            bw.flush();
            bw.close();
        }
         catch (IOException e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
