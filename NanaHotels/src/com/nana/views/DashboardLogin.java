package com.nana.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.UIManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class DashboardLogin {
	Display display = new Display();
	Shell shell = new Shell(display, SWT.NO_TRIM);
	Label label1, label2;
	Text username;
	Text password;
	Text text;
	private Label lblNanaInternationalHotel;
	private Button btnNewButton_1;
	private Connection con;
	String pass = null;
	PreparedStatement ps = null;

	public DashboardLogin() {
		shell.setLocation(new Point(230, 230));
		shell.setBackgroundImage(SWTResourceManager.getImage(
				"C:\\Users\\NIUElechi\\eclipse-workspace\\jee_innovatives\\NanaHotels\\resources\\img\\wood.JPG"));
		shell.setBackground(SWTResourceManager.getColor(153, 255, 0));
		shell.setMinimumSize(new Point(680, 455));
		shell.setSize(450, 300);
		shell.setText("Nana International Hotel");
		shell.setLayout(new FormLayout());

		// Working on the username label
		label1 = new Label(shell, SWT.NULL);
		label1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_label1 = new FormData();
		label1.setLayoutData(fd_label1);
		label1.setFont(SWTResourceManager.getFont("Tw Cen MT", 16, SWT.NORMAL));
		label1.setText("User Name: ");
		// label1.setBackground(null);

		// Working on the username text field
		username = new Text(shell, SWT.SINGLE | SWT.BORDER);
		fd_label1.right = new FormAttachment(username, -6);
		FormData fd_username = new FormData();
		fd_username.top = new FormAttachment(label1, 0, SWT.TOP);
		fd_username.right = new FormAttachment(100, -31);
		fd_username.left = new FormAttachment(0, 449);
		username.setLayoutData(fd_username);
		username.setText("");
		username.setTextLimit(30);

		// Working on the password label
		label2 = new Label(shell, SWT.NULL);
		label2.setBackground(SWTResourceManager.getColor(0, 0, 0));
		fd_label1.bottom = new FormAttachment(label2, -19);
		fd_username.bottom = new FormAttachment(label2, -19);
		label2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_label2 = new FormData();
		fd_label2.top = new FormAttachment(0, 262);
		label2.setLayoutData(fd_label2);
		label2.setFont(SWTResourceManager.getFont("Tw Cen MT", 16, SWT.NORMAL));
		label2.setText("Password: ");
		label1.setBackground(SWTResourceManager.getColor(0, 0, 0));

		// Working on the password text field
		password = new Text(shell, SWT.SINGLE | SWT.BORDER);
		fd_label2.right = new FormAttachment(password, -6);
		FormData fd_password = new FormData();
		fd_password.top = new FormAttachment(label2, -21);
		fd_password.bottom = new FormAttachment(label2, 0, SWT.BOTTOM);
		fd_password.left = new FormAttachment(0, 449);
		fd_password.right = new FormAttachment(100, -31);
		password.setLayoutData(fd_password);
		System.out.println(password.getEchoChar());
		password.setEchoChar('*');
		password.setTextLimit(30);
		password.pack();

		lblNanaInternationalHotel = new Label(shell, SWT.NONE);
		lblNanaInternationalHotel.setForeground(SWTResourceManager.getColor(255, 215, 0));
		lblNanaInternationalHotel.setFont(SWTResourceManager.getFont("Script MT Bold", 40, SWT.NORMAL));
		FormData fd_lblNanaInternationalHotel = new FormData();
		fd_lblNanaInternationalHotel.left = new FormAttachment(0, 42);
		fd_lblNanaInternationalHotel.right = new FormAttachment(100, -31);
		fd_lblNanaInternationalHotel.top = new FormAttachment(0, 33);
		fd_lblNanaInternationalHotel.bottom = new FormAttachment(0, 96);
		lblNanaInternationalHotel.setLayoutData(fd_lblNanaInternationalHotel);
		lblNanaInternationalHotel.setText("Nana's International Hotel");

		// Exit Button
		Button btnNewButton = new Button(shell, SWT.BORDER | SWT.CENTER);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Tw Cen MT Condensed Extra Bold", 16, SWT.NORMAL));
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.right = new FormAttachment(username, 45);
		fd_btnNewButton.left = new FormAttachment(username, 0, SWT.LEFT);
		fd_btnNewButton.bottom = new FormAttachment(100, -23);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Exit");

		// User Button
		Button btnLoginAsUser = new Button(shell, SWT.PUSH);
		btnLoginAsUser.setFont(SWTResourceManager.getFont("Tw Cen MT Condensed Extra Bold", 16, SWT.NORMAL));
		FormData fd_btnLoginAsUser = new FormData();
		fd_btnLoginAsUser.top = new FormAttachment(label2, 48);
		fd_btnLoginAsUser.left = new FormAttachment(label2, 0, SWT.LEFT);
		btnLoginAsUser.setLayoutData(fd_btnLoginAsUser);
		btnLoginAsUser.setText("Login as User");
		btnLoginAsUser.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				if(username.getText().equals ("") && !(username.getText().equals("admin"))) {
					System.out.println("User is loggin in");
					try {
						handleEvent1(arg0);
					} catch (Exception e) {
						System.out.println("The error is " + e);
					}
				} else {
					MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
					messageBox.setMessage("Sorry, try again");
					messageBox.open();
				}
			}
		});

		// Admin Button
		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("Tw Cen MT Condensed Extra Bold", 16, SWT.NORMAL));
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.top = new FormAttachment(btnLoginAsUser, 0, SWT.TOP);
		fd_btnNewButton_1.left = new FormAttachment(btnLoginAsUser, 21);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("Login As Admin");
		btnNewButton_1.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				if (!(username.getText()).equals("") && username.getText().equals("admin")) {
					System.out.println("Admin is loggin in");
					try {
						handleEvent1(arg0);
					} catch (Exception e) {
						System.out.println("The error is " + e);
					}
				} else {
					MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
					messageBox.setMessage("Sorry, this you must be an admin to logon");
					messageBox.open();
				}
			}
		});

		Label label = new Label(shell, SWT.NONE);
		label.setSize(new Point(200, 100));
		label.setImage(SWTResourceManager.getImage("C:\\Users\\NIUElechi\\Desktop\\nanasfront1.JPG"));
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(lblNanaInternationalHotel, 6);
		fd_label.left = new FormAttachment(label2, 0, SWT.LEFT);
		label.setLayoutData(fd_label);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	// DB Connection Method
	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Nanashotel", "root", "root");
		} catch (Exception e) {
			System.out.println("Error in connection " + e);
		}
		return con;

	}

	public void handleEvent1(Event event) throws SQLException {
		String selectedUsername = username.getText();
		String selectedPass = password.getText();

		Connection con = null;

		con = getCon();
		String qry = "select username, emppass from employees where username = '" + selectedUsername + "'";
		ps = con.prepareStatement(qry);

		// get customer data from database
		ResultSet result = ps.executeQuery();

		System.out.println("In handle Events method..");
		if (result.next()) {
			String dbPass = result.getString("emppass");

			try {
				if (selectedUsername.trim().isEmpty()) {
//				if (selectedUsername == "") {
					MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
					messageBox.setMessage("Enter the User Name");
					messageBox.open();
				}
				if (selectedPass == "") {
					MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
					messageBox.setMessage("Enter the Password");
					messageBox.open();
				} else if (selectedUsername.equals("admin") && selectedPass.equals(dbPass)) {
					System.out.println("Admin is about loggin in");
					MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.CANCEL);
					messageBox.setText("Admin Login Form");
					messageBox.setMessage("Welcome: " + selectedUsername);
					messageBox.open();
					shell.dispose();
					new AdminMenu();
				} else if (!selectedUsername.equals("admin") && !(selectedPass.equals(dbPass))) {
					MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
					messageBox.setMessage("Wrong admin pass... please try again");
					messageBox.open();
				} else if (!selectedUsername.equals("admin") && selectedPass.equals(dbPass)) {
					System.out.println("User is about loggin in");
					System.out.println(dbPass);
					MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.CANCEL);
					messageBox.setText("Login Form");
					messageBox.setMessage("Welcome: " + selectedUsername);
					messageBox.open();
					shell.dispose();
					new MenuPage(selectedUsername);
				} else if (selectedPass.isEmpty()) {
					MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
					messageBox.setMessage("Sorry, this user is not in the db");
					messageBox.open();
				}
			} catch (Exception e) {
				System.out.println("Not sure why this system is throwing " + e);
			}
		} else {
			MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
			messageBox.setMessage("Sorry, this user is not in the db");
			messageBox.open();
		}
	}

	// Main Program
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}
		new DashboardLogin();
	}
}
