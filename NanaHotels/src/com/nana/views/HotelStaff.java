package com.nana.views;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.nana.model.User;

public class HotelStaff {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	protected Object result;
	protected Shell shlUserManagement;
	private Text idText;
	private Text firstText;
	private Text LastText;
	private Text SalaryText;
	private DateTime bday;
	private DateTime joinDate;
	private Text usernameText;
	private Label lblFirstname;
	private Label lblLastname;
	private Label lblGender;
	private Label lblDepartment;
	private Label lblPosition;
	private Label lblSalary;
	private Label lblBirthDate;
	private Label lblJoinDate;
	private Label lblUsername;
	private Label lblPassword;
	private Text passText;
	private Button btnViewAllUsers;

	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	String dd = "com.mysql.jdbc.Driver";
	String DB_CONNECTION = "jdbc:mysql://localhost:3306/Nanashotel?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	String DB_USER = "root";
	String DB_PASSWORD = "root";

	PreparedStatement ps = null;

	List<User> list;

	JPanel p1, p2, p3;
	private Combo deptDD;
	private Combo positionDD;
	private Combo genderDD;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @wbp.parser.entryPoint
	 */
	public HotelStaff(String userName) {
		super();
		final Display display = Display.getDefault();
		shlUserManagement = new Shell();
		shlUserManagement.setSize(487, 418);
		shlUserManagement.setText("Nana's Hotel Staff");

//		Composite parent = new Composite(shlUserManagement, SWT.NONE);
		
		Label lblUserId = new Label(shlUserManagement, SWT.NONE);
		lblUserId.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblUserId.setBounds(10, 10, 109, 25);
		lblUserId.setText(" User ID");

		idText = new Text(shlUserManagement, SWT.BORDER);
		idText.setBounds(125, 13, 121, 21);

		firstText = new Text(shlUserManagement, SWT.BORDER);
		firstText.setBounds(125, 45, 121, 21);

		LastText = new Text(shlUserManagement, SWT.BORDER);
		LastText.setBounds(125, 77, 121, 21);
		
		
		SalaryText = new Text(shlUserManagement, SWT.BORDER);
		SalaryText.setBounds(125, 205, 121, 21);

		bday = new DateTime(shlUserManagement, SWT.DATE | SWT.DROP_DOWN);
		bday.setBounds(125, 232, 121, 21);

		joinDate = new DateTime(shlUserManagement, SWT.DATE | SWT.DROP_DOWN);
		joinDate.setBounds(125, 271, 121, 21);

		usernameText = new Text(shlUserManagement, SWT.BORDER);
		usernameText.setBounds(125, 301, 121, 21);

		lblFirstname = new Label(shlUserManagement, SWT.NONE);
		lblFirstname.setText("*Firstname");
		lblFirstname.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblFirstname.setBounds(10, 41, 109, 25);

		lblLastname = new Label(shlUserManagement, SWT.NONE);
		lblLastname.setText("*Lastname");
		lblLastname.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblLastname.setBounds(10, 73, 109, 25);

		lblGender = new Label(shlUserManagement, SWT.NONE);
		lblGender.setText("*Gender");
		lblGender.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblGender.setBounds(10, 107, 109, 25);

		lblDepartment = new Label(shlUserManagement, SWT.NONE);
		lblDepartment.setText("*Department");
		lblDepartment.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblDepartment.setBounds(10, 139, 109, 25);

		lblPosition = new Label(shlUserManagement, SWT.NONE);
		lblPosition.setText("*Position");
		lblPosition.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblPosition.setBounds(10, 170, 109, 25);

		lblSalary = new Label(shlUserManagement, SWT.NONE);
		lblSalary.setText("*Salary");
		lblSalary.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblSalary.setBounds(10, 203, 109, 25);

		lblBirthDate = new Label(shlUserManagement, SWT.NONE);
		lblBirthDate.setText("*Birth Date");
		lblBirthDate.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblBirthDate.setBounds(10, 231, 109, 25);

		lblJoinDate = new Label(shlUserManagement, SWT.NONE);
		lblJoinDate.setText("*Join date");
		lblJoinDate.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblJoinDate.setBounds(10, 267, 109, 25);

		lblUsername = new Label(shlUserManagement, SWT.NONE);
		lblUsername.setText("Username");
		lblUsername.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblUsername.setBounds(10, 295, 109, 25);

		lblPassword = new Label(shlUserManagement, SWT.NONE);
		lblPassword.setText("Password");
		lblPassword.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblPassword.setBounds(10, 326, 109, 25);

		passText = new Text(shlUserManagement, SWT.BORDER);
		passText.setBounds(125, 328, 121, 21);

		Button btnNewButton = new Button(shlUserManagement, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				idText.setText("");
				firstText.setText("");
				LastText.setText("");
				genderDD.setText("");
				deptDD.setText("");
				positionDD.setText("");
				SalaryText.setText("");
				bday.setDate(0, 0, 0);
				joinDate.setDate(0, 0, 0);
				usernameText.setText("");
				passText.setText("");
			}
		});

		btnNewButton.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnNewButton.setBounds(313, 71, 121, 25);
		btnNewButton.setText("Reset");

		Button btnUpdateUserInfo = new Button(shlUserManagement, SWT.NONE);
		btnUpdateUserInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ((idText.getText()) == "") {
					MessageBox messageBox = new MessageBox(shlUserManagement, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
					messageBox.setMessage("Ensure the ID field is completed");
					messageBox.open();
				} else {
					try {
						try {
							getEdit(idText.getText());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnUpdateUserInfo.setText("View user by ID");
		btnUpdateUserInfo.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnUpdateUserInfo.setBounds(313, 164, 121, 25);

		Button btnDeleteUser = new Button(shlUserManagement, SWT.NONE);
		btnDeleteUser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (idText.getText() == "") {
					MessageBox messageBox = new MessageBox(shlUserManagement, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
					messageBox.setMessage("Please put in a valid ID");
					messageBox.open();
				} else {
					delete(idText.getText());
				}
			}
		});
		btnDeleteUser.setText("Delete User");
		btnDeleteUser.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnDeleteUser.setBounds(313, 202, 121, 25);

		Button btnExit = new Button(shlUserManagement, SWT.NONE);
		btnExit.setText("Exit");
		btnExit.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnExit.setBounds(345, 246, 75, 25);
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlUserManagement.dispose();
				new AdminMenu(userName);
			}
		});

		
		btnViewAllUsers = new Button(shlUserManagement, SWT.NONE);
		btnViewAllUsers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					shlUserManagement.dispose();
					new StaffTable(userName);
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnViewAllUsers.setText("View all Users");
		btnViewAllUsers.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnViewAllUsers.setBounds(313, 40, 121, 25);
		
		Button btnUpdateUser = new Button(shlUserManagement, SWT.NONE);
		btnUpdateUser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					update();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdateUser.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnUpdateUser.setBounds(313, 133, 121, 25);
		btnUpdateUser.setText("Update");
		
		Button btnAddUser = new Button(shlUserManagement, SWT.NONE);
		btnAddUser.setText("Add User");
		btnAddUser.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnAddUser.setBounds(313, 102, 121, 25);
		btnAddUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {

				try {
					add();
				} catch (ParseException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		deptDD = new Combo(shlUserManagement, SWT.READ_ONLY);
		deptDD.setItems(new String[] {"Security", "Rooms", "Kitchen", "IT"});
		deptDD.setBounds(125, 139, 121, 23);
		
		positionDD = new Combo(shlUserManagement, SWT.READ_ONLY);
		positionDD.setItems(new String[] {"Head", "Team Lead", "Team Member","Attendant",});
		positionDD.setBounds(125, 170, 121, 23);
		
		genderDD = new Combo(shlUserManagement, SWT.READ_ONLY);
		genderDD.setItems(new String[] {"Male", "Female"});
		genderDD.setBounds(125, 109, 121, 23);
		

		shlUserManagement.open();
		shlUserManagement.layout();
		while (!shlUserManagement.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the dialog.
	 */
//	private void createContents() {
//	}

	private Connection getCon()throws IOException {

		Connection con = null;

		try {

			Class.forName(dd);

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return con;
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return con;
	}

	private void add() throws ParseException,IOException {
		int i = 0;

		Connection con = null;
		idText.setVisible(false);

		// Checking for empty fields
		if ((firstText.getText()) == "" || (LastText.getText()) == "" || (genderDD.getText()) == ""
				|| (deptDD.getText()) == "" || (positionDD.getText()) == "" || (SalaryText.getText()) == "") {

			MessageBox messageBox = new MessageBox(shlUserManagement, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
			messageBox.setMessage("Ensure all required fields are completed");
			messageBox.open();
		} else {

			String fname = firstText.getText();
			String lname = LastText.getText();
			String gender = genderDD.getText();
			String department = deptDD.getText();
			String position = positionDD.getText();
			String salary = SalaryText.getText();

			// Formatting the Birthdate
			String bDateYear = String.valueOf(bday.getYear());
			String bDateMonth = String.valueOf(bday.getMonth());
			String bDateDay = String.valueOf(bday.getDay());

			String bDateString = bDateYear;
			bDateString += "/";
			bDateString += bDateMonth;
			bDateString += "/";
			bDateString += bDateDay;

			SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/DD");
			Date bDate = (Date) formatter.parse(bDateString);
			System.out.println(bday);
			System.out.println(bDate);

			// Formatting the Join Date
			String joinedDateYear = String.valueOf(joinDate.getYear());
			String joinedDateMonth = String.valueOf(joinDate.getMonth());
			String joinedDateDay = String.valueOf(joinDate.getDay());

			String joinDateString = joinedDateYear;
			joinDateString += "/";
			joinDateString += joinedDateMonth;
			joinDateString += "/";
			joinDateString += joinedDateDay;

//			SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/DD");
			Date joinedDate = (Date) formatter.parse(joinDateString);
			System.out.println("The joindate is: " + joinedDate);

			String username = usernameText.getText();
			String emppass = passText.getText();

			try {
				con = getCon();
				String sql = "INSERT INTO employees(fname, lname, gender, department, position, salary, bdate, joinedDate, username, emppass) values(?,?,?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, fname);
				ps.setString(2, lname);
				ps.setString(3, gender);
				ps.setString(4, department);
				ps.setString(5, position);
				ps.setString(6, salary);
				java.sql.Date sqlDate = new java.sql.Date(bDate.getTime());
				ps.setDate(7, sqlDate);
				java.sql.Date sqlJoinDate = new java.sql.Date(joinedDate.getTime());
				ps.setDate(8, sqlJoinDate);
				ps.setString(9, username);
				ps.setString(10, emppass);

				i = ps.executeUpdate();
				System.out.println("Data Added Successfully");

				MessageBox messageBox = new MessageBox(shlUserManagement, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
				messageBox.setMessage("Data is successfully inserted into database");
				messageBox.open();

				this.reset();

			} catch (Exception e) {
				System.out.println("The error is: " + e);
				MessageBox messageBox = new MessageBox(shlUserManagement, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
				messageBox.setMessage("Error in submitting data");
				messageBox.open();

			} finally {
				try {
					con.close();
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void getEdit(String userID) throws SQLException, ParseException {

		Connection con = null;

		// Checking for empty fields
		if ((idText.getText()) == "") {

			MessageBox messageBox = new MessageBox(shlUserManagement, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
			messageBox.setMessage("Ensure a valid ID is entered");
			messageBox.open();
		} else {

			try {
				con = getCon();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Selecting ALL records from the table
			String sql = "select employee_Id, fname, lname, gender, department, position, salary, bdate, joinedDate, username, emppass from employees where employee_Id="
					+ userID;
			ps = con.prepareStatement(sql);

			// get customer data from database
			ResultSet result = ps.executeQuery();

			if (result.next()) {
				
				firstText.setText(result.getString("fname"));
				LastText.setText(result.getString("lname"));
				genderDD.setText(result.getString("gender"));
				deptDD.setText(result.getString("department"));
				positionDD.setText(result.getString("position"));
				SalaryText.setData(result.getDouble("salary"));
				bday.setData(result.getDate("bdate"));
				joinDate.setData(result.getDate("joinedDate"));
				usernameText.setText(result.getString("username"));
				passText.setText(result.getString("emppass"));				
				
			} else {
				MessageBox messageBox = new MessageBox(shlUserManagement, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
				messageBox.setMessage("Sorry, this user is not in the db");
				messageBox.open();
			}
		}

	}

	private void delete(String userID) {
		PreparedStatement ps = null;
		Connection con = null;
		if (userID != "") {
			try {
				con = getCon();
				String sql = "DELETE FROM employees WHERE employee_id=" + userID;
				ps = con.prepareStatement(sql);
				int i = ps.executeUpdate();
				if (i > 0) {
					System.out.println("Row deleted successfully");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	private void update() throws Exception {
		// Formatting the Birthdate
		String bDateYear = String.valueOf(bday.getYear());
		String bDateMonth = String.valueOf(bday.getMonth());
		String bDateDay = String.valueOf(bday.getDay());

		String bDateString = bDateYear;
		bDateString += "/";
		bDateString += bDateMonth;
		bDateString += "/";
		bDateString += bDateDay;

		SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/DD");
		Date bDate = (Date) formatter.parse(bDateString);
		System.out.println(bday);
		System.out.println(bDate);

		// Formatting the Join Date
		String joinedDateYear = String.valueOf(joinDate.getYear());
		String joinedDateMonth = String.valueOf(joinDate.getMonth());
		String joinedDateDay = String.valueOf(joinDate.getDay());

		String joinDateString = joinedDateYear;
		joinDateString += "/";
		joinDateString += joinedDateMonth;
		joinDateString += "/";
		joinDateString += joinedDateDay;

		Date joinedDate = (Date) formatter.parse(joinDateString);
		System.out.println(joinDate);
		System.out.println("The joindate is: " + joinedDate);
		
		User emp = new User();
		
		emp.setEmployeeId((int) idText.getData());
		emp.setFname(firstText.getText());
		emp.setLname(LastText.getText());
		emp.setGender(genderDD.getText());
		emp.setDepartment(deptDD.getText());
		emp.setPosition(positionDD.getText());
		emp.setSalary((double) SalaryText.getData());
		
		java.sql.Date sqlDate = new java.sql.Date(bDate.getTime());
		emp.setBdate(sqlDate);
		
		java.sql.Date sqlJoinDate = new java.sql.Date(joinedDate.getTime());
		emp.setJoinedDate(sqlJoinDate);
		emp.setUsername(usernameText.getText());
		emp.setEmppass(passText.getText());
		
	}

	public void reset() {

		// Cleaning the fields
		firstText.setText("");
		LastText.setText("");
		genderDD.setText("");
		deptDD.setText("");
		positionDD.setText("");
		SalaryText.setText("");
		bday.setDate(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
		joinDate.setDate(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
		usernameText.setText("");
		passText.setText("");
	}

//	public static void main(String[] args) {
//
//		new HotelStaff("Nana");
//
//	}
}
