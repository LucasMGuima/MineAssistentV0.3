import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CordsTable extends AbstractTableModel {
	 private String[]  columnName= {"Name","Cord Word","Cord Nether"};
	 private ArrayList<MyCords> cordList;
	 
	 public CordsTable(MyCordsList cList) {
		 cordList = cList.getCords();
	 }
	 
	 public void removeRow(int row) {
		 cordList.remove(row);
	 }
	 
	 public void clear() {
		 cordList.clear();
	 }
	 
	 public int getColumnCount() {
		 return columnName.length;
	 }
	 public int getRowCount() {
		 int size;
		 
		 if(cordList == null) {
			 size = 0;
		 }else {
			 size = cordList.size();
		 }
		 
		 return size;
	 }
	 
	 public Object getValueAt(int row, int col) {
		 Object temp = null;
		 
		 if(col == 0) {
			 temp = cordList.get(row).getName();
		 }else if(col == 1) {
			 temp = cordList.get(row).getCordWord();
		 }else if(col == 2) {
			 temp = cordList.get(row).getCordNether();
		 }
		 
		 return temp;
	 }
	 
	public String getColumnName(int col) {
		return columnName[col];
	}
	 
	public Class getColumnClass(int col) {
		return String.class;
	}
	
	//Unable the user to edit the table
	public boolean isCellEditable(int row, int column) {                
        return false;               
	};
}
