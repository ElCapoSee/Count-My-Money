package kz.el.window;

import javax.swing.*;

import kz.el.readwrite.ReadFile;

public class HelloUser {
	JFrame f;

	HelloUser() {
		f = new JFrame();
		JOptionPane.showMessageDialog(f, "Welcome, Ly");
		ReadFile.upload();
	}

}
