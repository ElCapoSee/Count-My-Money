package kz.el.window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import kz.el.Main;
import kz.el.amount.Amount;
import kz.el.check.Check;
import kz.el.check.CheckModel;
import kz.el.readwrite.ReadFile;
import kz.el.readwrite.WriteFile;

import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtUpdate;
	CheckModel model;
	WriteFile write;
	ReadFile read;
	boolean isUpdate = false;

	public MainWindow() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(width / 2, height / 2);

		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\El\\eclipse-workspace\\\u0421ount-My-Money\\wallet.ico"));
		setTitle("Count My Money");
		new HelloUser();
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(575, 475);
		setResizable(false);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(10, 11, 419, 346);
		getContentPane().add(scrollPane);

		model = new CheckModel();
		table = new JTable(model);
		table.setBackground(new Color(250, 128, 114));

		scrollPane.setViewportView(table);

		JButton btnNewAdd = new JButton("add");
		btnNewAdd.setBackground(new Color(152, 251, 152));
		btnNewAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Check check = new Check();
				WriteFile fileName = new WriteFile();
				FileWriter nFile = null;
				try {
					nFile = new FileWriter(fileName.getFileNameTxt(), true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					nFile.write(JOptionPane.showInputDialog("Enter Date: "));
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					nFile.write("|");
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
					nFile.write(JOptionPane.showInputDialog("Enter Description: "));
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					nFile.write("`");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					nFile.write(JOptionPane.showInputDialog("Enter Amount: "));
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					nFile.write("`");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				// check.setDate(JOptionPane.showInputDialog("Enter Date: "));
				// check.setDesc(JOptionPane.showInputDialog("Enter Description: "));
				// check.setAmount(JOptionPane.showInputDialog("Enter Amount: "));
				Component source = (Component) e.getSource();
				Object response = JOptionPane.showInputDialog(source, "Choose One", "JOptionPane Sample",
						JOptionPane.QUESTION_MESSAGE, null,
						new String[] { "Food", "Transport", "Clothes", "Needful", "Other", "All" }, "B");
				System.out.println("Response: " + response);
				try {
					nFile.write((String) response);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					nFile.write("`");
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
					nFile.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// check.setContent((String) response);
				try {
					ReadFile.readFromFile();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				check.setDate(ReadFile.readFiles.get(ReadFile.readFiles.size() - 4));
				check.setDesc(ReadFile.readFiles.get(ReadFile.readFiles.size() - 3));
				check.setAmount(ReadFile.readFiles.get(ReadFile.readFiles.size() - 2));
				check.setContent(ReadFile.readFiles.get(ReadFile.readFiles.size() - 1));
				Main.check.add(check);
				txtUpdate.setText(Amount.getAmount());
				table.updateUI();
			}
		});
		btnNewAdd.setBounds(450, 14, 89, 23);
		getContentPane().add(btnNewAdd);

		JButton btnNewRemove = new JButton("remove");
		btnNewRemove.setBackground(new Color(250, 128, 114));
		btnNewRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WriteFile write = new WriteFile();
				if (table.getSelectedRow() == -1 || Main.check.size() <= 0) {
					return;
				}
				Main.check.remove(table.getSelectedRow());
				try {
					write.writeToFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtUpdate.setText(Amount.getAmount());
				table.updateUI();
			}
		});
		btnNewRemove.setBounds(450, 45, 89, 23);
		getContentPane().add(btnNewRemove);

		JRadioButton radioButton_Food = new JRadioButton("Food");
		radioButton_Food.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.check.clear();
				ReadFile.uploadFood();
				txtUpdate.setText(Amount.getAmount());
				table.updateUI();
			}
		});
		radioButton_Food.setBackground(new Color(148, 0, 211));
		radioButton_Food.setBounds(450, 100, 109, 23);
		getContentPane().add(radioButton_Food);

		JRadioButton radioButton_Transport = new JRadioButton("Transport");
		radioButton_Transport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.check.clear();
				ReadFile.uploadTransport();
				txtUpdate.setText(Amount.getAmount());
				table.updateUI();
			}
		});
		radioButton_Transport.setBackground(new Color(0, 191, 255));
		radioButton_Transport.setBounds(450, 136, 109, 23);
		getContentPane().add(radioButton_Transport);

		JRadioButton radioButton_Clothes = new JRadioButton("Clothes");
		radioButton_Clothes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.check.clear();
				ReadFile.uploadClothes();
				txtUpdate.setText(Amount.getAmount());
				table.updateUI();
			}
		});
		radioButton_Clothes.setBackground(new Color(255, 140, 0));
		radioButton_Clothes.setBounds(450, 171, 109, 23);
		getContentPane().add(radioButton_Clothes);

		JRadioButton radioButton_Needful = new JRadioButton("Needful");
		radioButton_Needful.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.check.clear();
				ReadFile.uploadNeedful();
				txtUpdate.setText(Amount.getAmount());
				table.updateUI();
			}
		});
		radioButton_Needful.setBackground(new Color(173, 255, 47));
		radioButton_Needful.setBounds(450, 206, 109, 23);
		getContentPane().add(radioButton_Needful);

		JRadioButton radioButton_Other = new JRadioButton("Other");
		radioButton_Other.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.check.clear();
				ReadFile.uploadOther();
				txtUpdate.setText(Amount.getAmount());
				table.updateUI();
			}
		});
		radioButton_Other.setBackground(new Color(240, 255, 255));
		radioButton_Other.setBounds(450, 242, 109, 23);
		getContentPane().add(radioButton_Other);

		JRadioButton radioButton_All = new JRadioButton("All");
		radioButton_All.setSelected(true);
		radioButton_All.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.check.clear();
				try {
					ReadFile.readFromFile();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ReadFile.upload();
				txtUpdate.setText(Amount.getAmount());
				table.updateUI();
			}
		});
		radioButton_All.setBounds(450, 276, 109, 23);
		getContentPane().add(radioButton_All);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton_Food);
		group.add(radioButton_Transport);
		group.add(radioButton_Clothes);
		group.add(radioButton_Needful);
		group.add(radioButton_Other);
		group.add(radioButton_All);

		txtUpdate = new JTextField();
		txtUpdate.setBackground(new Color(250, 128, 114));
		txtUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtUpdate.setBounds(450, 318, 89, 39);
		getContentPane().add(txtUpdate);
		txtUpdate.setColumns(10);
		txtUpdate.setEditable(false);

		write = new WriteFile();
		JLabel lblNewLabel = new JLabel("Current file: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(250, 128, 114));
		lblNewLabel.setBounds(10, 368, 100, 20);
		getContentPane().add(lblNewLabel);

		JTextPane textGetFileName = new JTextPane();
		textGetFileName.setBackground(new Color(250, 128, 114));
		textGetFileName.setEditable(false);
		textGetFileName.setBounds(81, 368, 58, 20);
		textGetFileName.setText(write.getFileName());
		getContentPane().add(textGetFileName);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setBackground(new Color(220, 220, 220));
		menuBar.add(mnFile);

		JMenuItem mntmCreateNewFile = new JMenuItem("Create new file");
		mntmCreateNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component f = null;
				WriteFile writeFileName = new WriteFile();
				String TEMP = JOptionPane.showInputDialog("Enter file name:");
				File newFileName;
				File tempFile;
				// if OK
				if (TEMP.equals("")) {
					JOptionPane.showMessageDialog(f, "The input field is empty.", "Error file name",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// if CENCEL
				if (TEMP != null) {
					// if File already exists
					tempFile = new File(TEMP + ".txt");
					if (tempFile.exists()) {
						JOptionPane.showMessageDialog(f, "The file name already exists.", "Error file name",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						newFileName = new File(TEMP + ".txt");
						try {
							// new File
							newFileName.createNewFile();

						} catch (IOException e1) {
							JOptionPane.showMessageDialog(f, "Error creating file.", "Alert",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						try {
							FileWriter writeFile = new FileWriter(TEMP + ".txt");
							System.out.println(TEMP);
							writeFile.write("new file");
							writeFile.write("`");
							writeFile.write("delete this string");
							writeFile.write("`");
							writeFile.write("0");
							writeFile.write("`");
							writeFile.write("delete this string");
							writeFile.write("`");
							writeFile.close();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						Main.check.clear();
						writeFileName.setFileName(TEMP);
						try {
							ReadFile.readFromFile();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						if (table.getRowCount() == ReadFile.readFiles.size() / 4) {
							return;
						} else {
							ReadFile.upload();
						}
						txtUpdate.setText(Amount.getAmount());
						textGetFileName.setText(write.getFileName());
						table.updateUI();
						JOptionPane.showMessageDialog(f, "File created successfully.\nThis file is being viewed: "
								+ writeFileName.getFileName());

					}
				}
			}
		});
		mnFile.add(mntmCreateNewFile);

		JMenuItem mntmRenameFile = new JMenuItem("Rename file");
		mntmRenameFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WriteFile writeFileName = new WriteFile();
				Component f = null;
				String TEMP = JOptionPane.showInputDialog("Enter current file name:");
				File currentFileName = new File(TEMP + ".txt");
				// if OK
				if (TEMP.equals("")) {
					JOptionPane.showMessageDialog(f, "The input field is empty.", "Error file name",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// if CENCEL
				if (TEMP != null) {
					// if File not found
					if (!currentFileName.exists()) {
						JOptionPane.showMessageDialog(f, "File not found.", "Error file name",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						String newTEMP = JOptionPane.showInputDialog(f, "Enter new file name");
						File newFileName = new File(newTEMP + ".txt");
						if (newTEMP.equals("")) {
							JOptionPane.showMessageDialog(f, "The input field is empty.", "Error file name",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (newTEMP != null) {
							currentFileName.renameTo(newFileName);
							System.out.println(writeFileName.getFileName());
							System.out.println(TEMP);
							if (writeFileName.getFileName() != TEMP) {
								System.out.println(TEMP);
								Main.check.clear();
								writeFileName.setFileName(newTEMP);
								try {
									ReadFile.readFromFile();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								if (table.getRowCount() == ReadFile.readFiles.size() / 4) {
									return;
								} else {
									ReadFile.upload();
								}
								txtUpdate.setText(Amount.getAmount());
								textGetFileName.setText(write.getFileName());
								table.updateUI();
								JOptionPane.showMessageDialog(f,
										"File created successfully.\nThis file is being viewed: "
												+ writeFileName.getFileName());
							} else {
								JOptionPane.showMessageDialog(f, "File renamed successfully.");
							}
						}
						return;
					}
				}
			}
		});
		mnFile.add(mntmRenameFile);

		JMenuItem mntmViewFile = new JMenuItem("View File");
		mntmViewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WriteFile writeFile = new WriteFile();
				Component f = null;
				String TEMP = JOptionPane.showInputDialog(f, "Enter new file name");
				// if OK
				if (TEMP.equals("")) {
					JOptionPane.showMessageDialog(f, "The input field is empty.", "Error file name",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// if CENCEL
				if (TEMP != null) {
					writeFile.setFileName(TEMP);
					File checkFile = new File(writeFile.getFileNameTxt());
					if (!checkFile.exists()) {
						JOptionPane.showMessageDialog(f, "File Not Found.", "Alert", JOptionPane.WARNING_MESSAGE);
					} else {
						Main.check.clear();
						try {
							ReadFile.readFromFile();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(f, "File is empty.", "Alert", JOptionPane.WARNING_MESSAGE);
							e1.printStackTrace();
						}
						if (table.getRowCount() == ReadFile.readFiles.size() / 4) {
							return;
						} else {
							ReadFile.upload();
						}
						txtUpdate.setText(Amount.getAmount());
						textGetFileName.setText(write.getFileName());
						table.updateUI();
						JOptionPane.showMessageDialog(f, "This file is being viewed: " + writeFile.getFileName());
					}
				}
			}
		});
		mnFile.add(mntmViewFile);

		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component f = null;
				String TEMP = JOptionPane.showInputDialog(f, "Enter file name");
				// if OK
				if (TEMP.equals("")) {
					JOptionPane.showMessageDialog(f, "The input field is empty.", "Error file name",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// if CENCEL
				if (TEMP != null) {
					File fileDelete = new File(TEMP + ".txt");
					if (!fileDelete.exists()) {
						JOptionPane.showMessageDialog(f, "File Not Found.", "Alert", JOptionPane.WARNING_MESSAGE);
					} else {
						fileDelete.delete();
						JOptionPane.showMessageDialog(f, "File successfully deleted.");
						File checkFile = new File(write.getFileNameTxt());
						if (!checkFile.exists()) {
							write.setFileName("");
							textGetFileName.setText(write.getFileName());
							Main.check.clear();
							table.updateUI();
						}
					}
				}
			}
		});
		mnFile.add(mntmDelete);

		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		JMenuItem mntmUpdate = new JMenuItem("Update");
		mntmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WriteFile writeFileName = new WriteFile();
				File currentFileName = new File(writeFileName.getFileNameTxt());
				if (!currentFileName.exists()) {
					Component f = null;
					String TEMP = JOptionPane.showInputDialog(f, "Enter new file name");
					// if OK
					if (TEMP.equals("")) {
						JOptionPane.showMessageDialog(f, "The input field is empty.", "Error file name",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					// if CENCEL
					if (TEMP != null) {
						writeFileName.setFileName(TEMP);
						File checkFile = new File(writeFileName.getFileNameTxt());
						if (!checkFile.exists()) {
							JOptionPane.showMessageDialog(f, "File Not Found.", "Alert", JOptionPane.WARNING_MESSAGE);
						} else {
							Main.check.clear();
							try {
								ReadFile.readFromFile();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(f, "File is empty.", "Alert",
										JOptionPane.WARNING_MESSAGE);
								e1.printStackTrace();
							}
							if (table.getRowCount() == ReadFile.readFiles.size() / 4) {
								return;
							} else {
								ReadFile.upload();
							}
							txtUpdate.setText(Amount.getAmount());
							textGetFileName.setText(write.getFileName());
							table.updateUI();
							JOptionPane.showMessageDialog(f,
									"This file is being viewed: " + writeFileName.getFileName());
						}
					}

				}
				Main.check.clear();
				try {
					ReadFile.readFromFile();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (ReadFile.readFiles.size() > 0 && table.getRowCount() <= 0) {
					if (table.getRowCount() == ReadFile.readFiles.size() / 4) {
						return;
					} else {
						ReadFile.upload();
					}
				} else {
					return;
				}
				txtUpdate.setText(Amount.getAmount());
				table.updateUI();
			}
		});
		mnOptions.add(mntmUpdate);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(null, "Exit the program?");
				if (res == JOptionPane.YES_OPTION)
					System.exit(0);

			}
		});
		mnOptions.add(mntmExit);

		JMenu mnAbout = new JMenu("About");
		mnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		menuBar.add(mnAbout);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
						"Count My Money.\nThese are desktop applications. This application works as\n a calculator and notebook. You can record data\n about purchases or expenses per day or per month. You can split\n the data on waste by month by simply creating files with\n the name of the months for which the waste came",
						"About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnAbout.add(mntmAbout);

		setVisible(true);
	}
}
