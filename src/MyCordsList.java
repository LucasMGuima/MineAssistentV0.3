import java.util.ArrayList;
import java.io.*;

public class MyCordsList {

	private ArrayList<MyCords> cordsList;
	
	public MyCordsList() {
		cordsList = new ArrayList<MyCords>();
	}
	
	public void add(MyCords cord) {
		cordsList.add(cord);
	}
	
	public ArrayList<MyCords> getCords(){
		return cordsList;
	}
	
	//read the info from a csv file
	public void readFromCSV(File filename) {
		File file = filename;
		
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		BufferedReader infile = new BufferedReader(reader);
		String line = "";
		try {
			boolean done = false;
			while(!done) {
				line = infile.readLine();
				if(line == null) {
					done = true;
				}
				else {
					String[] tokens = line.trim().split(",");
					String name = tokens[0].trim();
					int cordWordX = Integer.parseInt(tokens[1].trim());
					int cordWordZ = Integer.parseInt(tokens[2].trim());
					
					MyCords cord = new MyCords(cordWordX, cordWordZ, name);
					cordsList.add(cord);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
