package kz.el.readwrite;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import kz.el.check.Check;
import kz.el.check.CheckModel;

public class WriteFile {
	public ArrayList<Check> checkWrite = new ArrayList<>();
	static CheckModel model = new CheckModel();
	private static String fileName = "root";
	private static String fileNameTxt = "root.txt";

	
	public void writeToFile() throws IOException {
	FileWriter nFile = new FileWriter(getFileNameTxt());
	for (int i = 0; i < model.getRowCount(); i++) {
		for (int k = 0; k < model.getColumnCount(); k++ ) {
			nFile.write(model.getValueAt(i, k).toString());
			nFile.write("!");
		}
	}
	nFile.close();
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		WriteFile.fileName = fileName;
		setFileNameTxt(fileName);
	}


	public String getFileNameTxt() {
		return fileNameTxt;
	}


	public static void setFileNameTxt(String fileNameTxt) {
		WriteFile.fileNameTxt = fileNameTxt + ".txt";
	}
	
}
