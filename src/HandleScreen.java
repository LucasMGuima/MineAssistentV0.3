/*
 * Setup the window for the aplication
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class HandleScreen extends JFrame{
	//File to storage the cords
	File file = new File("save/mycords.csv");
	
	//Frame
	private JFrame menu = new JFrame();
	
	//Panels
	private JPanel bg = new JPanel();
	private JPanel inputCord = new JPanel();
	private JPanel text = new JPanel();
	private JPanel myCords = new JPanel();
	private JPanel table = new JPanel();
	private JPanel addCords = new JPanel();
	
	//toolBox store the maths and operations funcs for the code
	private toolBox func = new toolBox();
	
	//Color
	private Color creme = new Color(242, 239, 230);
	
	//Table
	private MyCordsList cordsList = new MyCordsList();
	private CordsTable tableMode = new CordsTable(cordsList);
	private JTable tableCords = new JTable(tableMode);
	private JScrollPane scrollPanel = new JScrollPane(tableCords);
	
	//Construct
	HandleScreen(String screenName){
		menu.setTitle(screenName);
	}
	
	public void run() {
		//setup the file
		try {
			if(file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			}else {
				System.out.println("File already exists.");
			}
		}catch(IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		
		listCordScreen();
		
		//congi bg
		bg.setBackground(creme);
		bg.setBounds(0, 0, 700, 400);
		
		//Config the frame
		menu.setLocationRelativeTo(null);
		menu.add(bg);
		menu.setResizable(false);
		menu.setSize(700,400);
		menu.setLayout(null);
		menu.setVisible(true);
	}
	
	//Call the screens
	private void listCordScreen() {
		listMyCords();
		editCords();
	}
	
	//Func to call the operation in the toolBox when the convert button is used
	private void calcAction(JTextField cordX, JTextField cordY, JTextField displayX, JTextField displayY) {
		int X = Integer.parseInt(cordX.getText());
		int Z = Integer.parseInt(cordY.getText());
		
		int[] cordNether;
		cordNether = func.findCordsNether(X, Z);
		
		System.out.println(cordNether[0]+", "+cordNether[1]);
		
		String x = String.valueOf(cordNether[0]);
		String y = String.valueOf(cordNether[1]);
		displayX.setText(x);
		displayY.setText(y);
	}

	private void listMyCords() {
		cordsList.readFromCSV(file);
		scrollPanel.setBackground(creme);
		scrollPanel.setPreferredSize(new Dimension(400,300));
		
		myCords.add(scrollPanel);
		myCords.setBackground(creme);
		
		menu.add(myCords);
		menu.pack();	
	}
	
	private void editCords() {
		
		//Config the area for the new cords
		addCords.setBorder((BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "New Cords")));
		addCords.setPreferredSize(new Dimension(140, 200));
		addCords.setBounds(450,40,180,150);
		addCords.setBackground(creme);
		
		//Setup text field for input the new cord
		JLabel lbCordName = new JLabel("Name:");
		JLabel lbCordX = new JLabel("X:");
		JLabel lbCordZ = new JLabel("Z:");
		
		JTextField tfCordName = new JTextField(10);
		JTextField tfCordX = new JTextField(13);
		JTextField tfCordZ = new JTextField(13);
		
		tfCordName.setBackground(creme);
		tfCordX.setBackground(creme);
		tfCordZ.setBackground(creme);
		
		lbCordName.setLabelFor(tfCordName);
		lbCordX.setLabelFor(tfCordX);
		lbCordZ.setLabelFor(tfCordZ);
		
		//Put the fields in the panel
		addCords.add(lbCordName);
		addCords.add(tfCordName);
		addCords.add(lbCordX);
		addCords.add(tfCordX);
		addCords.add(lbCordZ);
		addCords.add(tfCordZ);
		
		//Config the add button
		JButton add = new JButton("ADD");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//check if the input is a number	
				if(isNumber(tfCordX.getText()) && isNumber(tfCordZ.getText())) {
					addNewCord(tfCordX.getText(), tfCordZ.getText(), tfCordName.getText());
				}
				//reset the text field
				tfCordX.setText(" ");
				tfCordZ.setText(" ");
				tfCordName.setText(" ");
			}
		});
		addCords.add(add);
		
		//Config the remove button
		/*
		JButton remove = new JButton("REMOVE");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// check for selected row first
	            if(tableCords.getSelectedRow() != -1) {
	               // remove selected row from the model
	               tableMode.removeRow(tableCords.getSelectedRow());
	            }

			}
		});
		addCords.add(remove);
		*/
		//put the panel in the frame
		menu.add(addCords);
	}
	
	//Function to check if the input text is digit
	private boolean isNumber(String str) {
		if(str == null || str.length() == 0) {
			return false;
		}
		
		for(char c : str.toCharArray()){
			if(Character.isDigit(c)) return true;
		}
		
		return false;
				
	}
	
	public void addNewCord(String cordX, String cordZ, String name) {
		toolBox tool = new toolBox();
		tool.addNewCord2CSV(this.file, cordX, cordZ, name);
		tableMode.clear();
		cordsList.readFromCSV(file);
		tableMode.fireTableDataChanged();
	}
}
