import java.io.*;
import java.util.*;
/*
 * class to storage the functions of MineAssitent
 */

public class toolBox{
	
	//Divide the cords by 8
	public int[] findCordsNether(int x, int z) {
		int[] resp = new int[2];
		
		int netherX, netherZ;
		
		netherX = x/8;
		netherZ = z/8;
		
		resp[0] = netherX;
		resp[1] = netherZ;
		
		return resp;
	}
	
	//add a new cord in the cord file
	public void addNewCord2CSV(File file, String cordX, String cordZ, String name) {
		
		//Creat the file writer
		FileWriter write = null;
		try {
			write = new FileWriter(file, true);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		BufferedWriter infileWriter = new BufferedWriter(write);
		
		//Creat the reader
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		BufferedReader infileReader = new BufferedReader(reader);
		String line = "";
		
		//Find a empty line
		try {
			boolean done = false;
			while(!done) {
				line = infileReader.readLine();
				if(line == null) {
					//write the new cord in the file
					infileWriter.write(name+", "+cordX+", "+cordZ);
					infileWriter.newLine();
					infileWriter.flush();
					infileWriter.close();
					done = true;
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	//remove a especific line from the CSV file
	/*
	public static void removeLine(int row, File file) {		
		
	}
	*/
}