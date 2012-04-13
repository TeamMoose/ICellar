package icellar;

import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;

import java.io.*;
import java.net.URL;

//This class contains the frame for the 'hub' of iCellar
public class iCellarGUI extends JFrame {

	private static iCellarGUI frame;
	private static JPanel contentPane;
	private static JTable table;
	private static int selectedRow = -1;
    static String[][] curUser = new String[1][1];
    private static Cellar myCellar;
    private static JTabbedPane tabbedPane;
    private static JButton btnSearch;
    private static JButton btnUndoSearch;


	public iCellarGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame = this;
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Add tabs
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 67, 864, 484);
		contentPane.add(tabbedPane);
		
		//Start with individual inventory, create a tab for it.
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

		//Create a new table for the personal inventory.
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
		File curFile2 = new File(new File("wineData/Users/" + curUser[0][0] + ".txt").getAbsolutePath());
        
        try{
            
            String[] wineclubs = new String[10];
            String[] usersWineclub = new String[10];
            
            FileInputStream fstream = new FileInputStream(curFile2);

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String stringLine;
            
            br.readLine();
            br.readLine();
            br.readLine();
            br.readLine();
            br.readLine();
            
            stringLine = br.readLine();
            
            wineclubs = stringLine.split(",");
            
            int i = 1;
            //For each group, add another tab with a scrollable table.
            while(wineclubs.length > i){
                
            	scrollPane = new JScrollPane();
                tabbedPane.addTab(wineclubs[i], null, scrollPane, null);
                
                File curFile3 = new File(new File("wineData/Wineclubs/" + wineclubs[i] + ".txt").getAbsolutePath());
                
                FileInputStream fstream2 = new FileInputStream(curFile3);

                DataInputStream in2 = new DataInputStream(fstream2);
                BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
                
                br2.readLine();
                br2.readLine();
                br2.readLine();
                br2.readLine();
                
                stringLine = br2.readLine();
                
                usersWineclub = stringLine.split(",");
                
                int j = 1;
                Cellar temp = new Cellar();
                
                //For each wine club grab each users inventory and add to collection table.
                while(usersWineclub.length > j){
                    
                    temp.buildFromFile("wineData/Users/" + usersWineclub[j] + "Bottle.txt");                    
                    j++;                            
                }
                
                bottles = temp.toStringArray();

                table = new JTable(bottles, columnHeaders);
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                lsm = table.getSelectionModel();
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
                
                
                i++;                        
            }
        }
        
        catch (Exception e){//Catch exception if any
            
            System.err.println("Error: " + e.getMessage());
        }

        //Button for adding a new bottle
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

		//Button for managing clubs (join, leave create).
		JButton btnManageClubs = new JButton("Manage Clubs");
		btnManageClubs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					wineClubMenuGUI frame = new wineClubMenuGUI(myCellar);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnManageClubs.setBounds(748, 37, 117, 23);
		contentPane.add(btnManageClubs);

		//Button for editing profile information.
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

		//Button for removing a bottle from collection.
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
						iCellarGUI.updateJTable(myCellar.toStringArray());
					}
				}
			}
		});
		btnRemoveBottle.setBounds(621, 11, 117, 23);
		contentPane.add(btnRemoveBottle);

		//Button for searching collection.
		btnSearch = new JButton("Search");
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

		//Button for editing a bottle.
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
		
		//Logo label.
		JLabel lblIcellar = new JLabel("iCellar");
		lblIcellar.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblIcellar.setBounds(10, 11, 152, 41);
		contentPane.add(lblIcellar);
	}
	
	//This method is called whenever current user information is altered and redisplays the
	//refreshed tables and removed or added tabs. It is basically the same code that runs when the
	//iCellarGUI frame is initially loaded.
	public static void updateJTable(String[][] bottles)
	{
		contentPane.remove(tabbedPane);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 67, 864, 484);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("My Cellar", null, scrollPane, null);

        InportAccount fileReader = new InportAccount();

        File curFile = new File(new File("wineData/Login/curLogin.txt").getAbsolutePath());

        curUser = fileReader.fileReader(curFile);

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
		
        File curFile2 = new File(new File("wineData/Users/" + curUser[0][0] + ".txt").getAbsolutePath());
        
        try{
            
            String[] wineclubs = new String[10];
            String[] usersWineclub = new String[10];
            
            FileInputStream fstream = new FileInputStream(curFile2);

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String stringLine;
            
            br.readLine();
            br.readLine();
            br.readLine();
            br.readLine();
            br.readLine();
            
            stringLine = br.readLine();
            
            wineclubs = stringLine.split(",");
            
            int i = 1;
            while(wineclubs.length > i){
                
            	scrollPane = new JScrollPane();
                tabbedPane.addTab(wineclubs[i], null, scrollPane, null);
                
                File curFile3 = new File(new File("wineData/Wineclubs/" + wineclubs[i] + ".txt").getAbsolutePath());
                
                FileInputStream fstream2 = new FileInputStream(curFile3);

                DataInputStream in2 = new DataInputStream(fstream2);
                BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
                
                br2.readLine();
                br2.readLine();
                br2.readLine();
                br2.readLine();
                
                stringLine = br2.readLine();
                
                usersWineclub = stringLine.split(",");
                
                int j = 1;
                Cellar temp = new Cellar();
                
                while(usersWineclub.length > j){
                    
                    temp.buildFromFile("wineData/Users/" + usersWineclub[j] + "Bottle.txt");                    
                    j++;                            
                }
                
                bottles = temp.toStringArray();

                table = new JTable(bottles, columnHeaders);
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                lsm = table.getSelectionModel();
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
                
                
                i++;                        
            }
        }
        
        catch (Exception e){//Catch exception if any
            
            System.err.println("Error: " + e.getMessage());
        }     
	}
	
	//This method is called after a search. It replaces the search button with an
	//undo search button that returns the tabs and tables to their unsearched state.
	public static void replaceSearchButton() {
		contentPane.remove(btnSearch);
		btnUndoSearch = new JButton("Undo Search");
		btnUndoSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iCellarGUI.updateJTable(myCellar.toStringArray());
				contentPane.remove(btnUndoSearch);
				contentPane.add(btnSearch);
			}
		});
		btnUndoSearch.setBounds(621, 37, 117, 23);
		contentPane.add(btnUndoSearch);
	}
}

