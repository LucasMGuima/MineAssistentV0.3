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
	public void removeCordFromCSV(File file, int row){
		//Save the file name for lather
		String fileName = file.getName();

		//Creat the new file
		File newFile = new File("save/new"+fileName);

		//Setup the new file
		try {
			if(newFile.createNewFile()) {
				System.out.println("File created: " + newFile.getName());
			}else {
				System.out.println("File already exists.");
			}
		}catch(IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}

		//Creat the file writer
		FileWriter write = null;
		try {
			write = new FileWriter(newFile, true);
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
		
		//Copy the lines of file to the newFile, exluding the line corresponded to row
		try{
			boolean done = false;
			String line = "";
			int contLine = 0;

			while(!done){
				line = infileReader.readLine();
				if(contLine != row){
					if(line != null){
						infileWriter.write(line);
						infileWriter.newLine();
						infileWriter.flush();
					}
				}
				contLine++;
				if(line == null){
					done = true;
					infileReader.close();
					infileWriter.close();
					write.close();
					reader.close();
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		//clear the old file
		try{
			//creat the writer
			FileWriter fileWriter = new FileWriter(file, false);
			PrintWriter printWriter = new PrintWriter(fileWriter, false);

			printWriter.flush();
			printWriter.close();
			fileWriter.close();
			System.out.println("File: "+ file + " cleared");
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}

		//Copy the content of the newFile to the file
		//Creat the file writer
		write = null;
		try {
			write = new FileWriter(file, true);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		infileWriter = new BufferedWriter(write);
		
		//Creat the reader
		 reader = null;
		try {
			reader = new FileReader(newFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		infileReader = new BufferedReader(reader);

		//Copy the lines of newFile to the file
		try{
			boolean done = false;
			String line = "";

			while(!done){
				line = infileReader.readLine();
				if(line != null){
					infileWriter.write(line);
					infileWriter.newLine();
					infileWriter.flush();
				}else{
					done = true;
					infileReader.close();
					infileWriter.close();
				} 
			}
		}catch (IOException e) {
			System.out.println("Erro em copiar as linhas para o o file velho");
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println(newFile.exists());

		//delet the newFile
		if(newFile.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
	}
}