package kz.el.check;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import kz.el.Main;
import kz.el.readwrite.WriteFile;

public class CheckModel implements TableModel {
	private ArrayList<TableModelListener> listener;

	public CheckModel() {
		listener = new ArrayList<>();
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		listener.add(l);

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		String return_string = "";
		switch (columnIndex) {
		case 0:
			return_string = "Date";
			break;
		case 1:
			return_string = "Description";
			break;
		case 2:
			return_string = "Amount";
			break;
		case 3:
			return_string = "Content";
			break;
		}
		return return_string;
	}

	@Override
	public int getRowCount() {
		return Main.check.size();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object return_object = null;
		switch (columnIndex) {
		case 0:
			return_object = Main.check.get(rowIndex).getDate();
			break;
		case 1:
			return_object = Main.check.get(rowIndex).getDesc();
			break;
		case 2:
			return_object = Main.check.get(rowIndex).getAmount();
			break;
		case 3:
			return_object = Main.check.get(rowIndex).getContent();
			break;
		}
		return return_object;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		WriteFile write = new WriteFile();
		switch (columnIndex) {
		case 0:
			Main.check.get(rowIndex).setDate((String) aValue);
			break;
		case 1:
			Main.check.get(rowIndex).setDesc((String) aValue);
			break;
		case 2:
			Main.check.get(rowIndex).setAmount((String) aValue);
			break;
		case 3:
			Main.check.get(rowIndex).setContent((String) aValue);
			break;
		}
		try {
			write.writeToFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listener.remove(l);

	}

}
