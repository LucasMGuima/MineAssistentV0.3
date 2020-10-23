/*
 * Class for storage the user´s saved cords
 */

public class MyCords {
	
	private toolBox tool = new toolBox();
	
	private String cordsWord;
	private String cordsNether;
	private String name;
	
	//construct
	MyCords (int cordWX, int cordWZ, String name){
		this.cordsWord = "X: " + String.valueOf(cordWX) + " | Z: " + String.valueOf(cordWZ);
		
		int[] nCords = tool.findCordsNether(cordWX, cordWZ);
		this.cordsNether = "X: " + String.valueOf(nCords[0]) + " | Z: " + String.valueOf(nCords[1]);
		
		this.name = name;
	}
	
	//Seters
	public void setCordWord(int[] cord) {
		this.cordsWord = "X: " + String.valueOf(cord[0]) + " | Z: " + String.valueOf(cord[1]);
	}
	public void setCordNether(int[] cord) {
		this.cordsNether = "X: " + String.valueOf(cord[0]) + " | Z: " + String.valueOf(cord[1]);
	}
	public void setName(String n) {
		this.name = n;
	}

	//Getters
	public String getCordWord() {
		return this.cordsWord;
	}
	public String getCordNether() {
		return this.cordsNether;
	}
	public String getName() {
		return this.name;
	}
}
