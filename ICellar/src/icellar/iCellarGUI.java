package icellar;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;


public class iCellarGUI extends JFrame {

	private static iCellarGUI frame;
	private static JPanel contentPane;
	private static JTable table;
	private static int selectedRow = -1;
    static String[][] curUser = new String[1][1];
    private static Cellar myCellar;
    private static JTabbedPane tabbedPane;


	public iCellarGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame = this;
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 48, 864, 503);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("My Cellar", null, scrollPane, null);

                InportAccount fileReader = new InportAccount();

                File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());

                curUser = fileReader.fileReader(curFile);

                myCellar = new Cellar("wineData/Users/" + curUser[0][0] + "Bottle.txt");
                String[][] bottles = myCellar.toStringArray();

		String[] columnHeaders = {"Maker",
                "Type",
                "Year",
                "Vineyard",
                "Region",
                "Rating",
                "Comment"
                };

		table = new JTable(bottles, columnHeaders);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				if (lsm.isSelectionEmpty()) {
					selectedRow = -1;
				}
				else {
					selectedRow = lsm.getMinSelectionIndex();
				}
			}
		});
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		//Add Group tabs

		JButton btnAddBottle = new JButton("Add Bottle");
		btnAddBottle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addBottleGUI frame = new addBottleGUI(myCellar);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAddBottle.setBounds(494, 11, 117, 23);
		contentPane.add(btnAddBottle);

		JButton btnManageClubs = new JButton("Manage Clubs");
		btnManageClubs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					wineClubMenuGUI frame = new wineClubMenuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnManageClubs.setBounds(748, 37, 117, 23);
		contentPane.add(btnManageClubs);

		JButton btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					userProfileGUI frame2 = new userProfileGUI(frame);
					frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnProfile.setBounds(748, 11, 117, 23);
		contentPane.add(btnProfile);

		JButton btnRemoveBottle = new JButton("Remove Bottle");
		btnRemoveBottle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(contentPane, "Select a bottle to be removed first.");
				}
				else {
					int result = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to remove this bottle?", null, JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						//REMOVE BOTTLE FROM INVENTORY
						String[][] strs = myCellar.toStringArray();
						Bottle btl = new Bottle(strs[selectedRow][0],strs[selectedRow][1], strs[selectedRow][2]);
						myCellar.removeBottle(btl);
					
						try {
							File curFile = new File(new File("wineData/Users/" + curUser[0][0] + "Bottle.txt").getAbsolutePath());
							
							BufferedWriter bw = null;
							
							curFile.delete();
							curFile.createNewFile();
							
							bw = new BufferedWriter( new FileWriter( curFile, true) );
							bw.write(myCellar.toString());
							bw.newLine();
							bw.flush();
							bw.close();						
							
						} catch (IOException e) {
							e.printStackTrace();
						}
						iCellarGUI.updateJTable();
					}
				}
			}
		});
		btnRemoveBottle.setBounds(621, 11, 117, 23);
		contentPane.add(btnRemoveBottle);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					searchInventoryGUI frame = new searchInventoryGUI(myCellar);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(621, 37, 117, 23);
		contentPane.add(btnSearch);

		JButton btnEditBottle = new JButton("Edit Bottle");
		btnEditBottle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(contentPane, "Select a bottle to be edited first.");
				}
				else {
					try {
						editBottleGUI frame = new editBottleGUI(myCellar, selectedRow);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnEditBottle.setBounds(494, 37, 117, 23);
		contentPane.add(btnEditBottle);
	}
	
	public static void updateJTable()
	{
		contentPane.remove(tabbedPane);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 48, 864, 503);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("My Cellar", null, scrollPane, null);

        InportAccount fileReader = new InportAccount();

        File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());

        curUser = fileReader.fileReader(curFile);

        myCellar = new Cellar("wineData/Users/" + curUser[0][0] + "Bottle.txt");
        String[][] bottles = myCellar.toStringArray();

		String[] columnHeaders = {"Maker",
                "Type",
                "Year",
                "Vineyard",
                "Region",
                "Rating",
                "Comment"
                };
		table = new JTable(bottles, columnHeaders);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				if (lsm.isSelectionEmpty()) {
					selectedRow = -1;
				}
				else {
					selectedRow = lsm.getMinSelectionIndex();
				}
			}
		});
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		//Add Group tabs
	}
}
