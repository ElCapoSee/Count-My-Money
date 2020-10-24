package kz.el.readwrite;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import kz.el.Main;
import kz.el.check.Check;

public class ReadFile {
	public static ArrayList<String> readFiles = new ArrayList<>();
	public static WriteFile fileName = new WriteFile();
	public final static String FOOD = "Food";
	public final static String TRANSPORT = "Transport";
	public final static String CLOTHES = "Clothes";
	public final static String NEEDFUL = "Needful";
	public final static String OTHER = "Other";
	private static Scanner scan;

	public static void readFromFile() throws Exception {
		readFiles.clear();
		FileReader fr = new FileReader(fileName.getFileNameTxt());
		scan = new Scanner(fr);
		String result = null;
		while (scan.hasNextLine()) {
			result = scan.nextLine();
			if (result == null) {
				return;
			}
		}
		for (String cut : result.split("`")) {
			readFiles.add(cut);

		}
		fr.close();
		scan.close();
	}

	public static void readValue(int a) {
		if (readFiles.size() > 0) {
			int k = a;
			Check check = new Check();
			check.setDate(readFiles.get(k));
			k++;
			check.setDesc(readFiles.get(k));
			k++;
			check.setAmount(readFiles.get(k));
			k++;
			check.setContent(readFiles.get(k));
			Main.check.add(check);
		}
	}

	public static void upload() {
		int k = 0;
		for (int i = 0; i < readFiles.size() / 4; i++) {
			ReadFile.readValue(k);
			k += 4;
		}
	}

	public static void uploadFood() {
		int k = 0;
		int j = 0;
		for (int i = 0; i < readFiles.size() / 4; i++) {
			if (!readFiles.get(j + 3).equals(FOOD)) {
				k += 4;
				j += 4;
				continue;
			}
			ReadFile.readValue(k);
			k += 4;
			j += 4;
		}
	}

	public static void uploadTransport() {
		int k = 0;
		int j = 0;
		for (int i = 0; i < readFiles.size() / 4; i++) {
			if (!readFiles.get(j + 3).equals(TRANSPORT)) {
				k += 4;
				j += 4;
				continue;
			}
			ReadFile.readValue(k);
			k += 4;
			j += 4;
		}
	}

	public static void uploadClothes() {
		int k = 0;
		int j = 0;
		for (int i = 0; i < readFiles.size() / 4; i++) {
			if (!readFiles.get(j + 3).equals(CLOTHES)) {
				k += 4;
				j += 4;
				continue;
			}
			ReadFile.readValue(k);
			k += 4;
			j += 4;
		}
	}

	public static void uploadNeedful() {
		int k = 0;
		int j = 0;
		for (int i = 0; i < readFiles.size() / 4; i++) {
			if (!readFiles.get(j + 3).equals(NEEDFUL)) {
				k += 4;
				j += 4;
				continue;
			}
			ReadFile.readValue(k);
			k += 4;
			j += 4;
		}
	}

	public static void uploadOther() {
		int k = 0;
		int j = 0;
		for (int i = 0; i < readFiles.size() / 4; i++) {
			if (!readFiles.get(j + 3).equals(OTHER)) {
				k += 4;
				j += 4;
				continue;
			}
			ReadFile.readValue(k);
			k += 4;
			j += 4;
		}
	}
}
