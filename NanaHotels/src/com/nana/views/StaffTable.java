package com.nana.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.nana.model.User;

public class StaffTable {

	protected Shell shell;
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	String dd = "com.mysql.jdbc.Driver";
	String DB_CONNECTION = "jdbc:mysql://localhost:3306/Nanashotel?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	String DB_USER = "root";
	String DB_PASSWORD = "root";

	PreparedStatement ps = null;

	List<User> list;
	
	private Connection getConn() {

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

	/**
	 * @throws Throwable 
	 * @wbp.parser.entryPoint
	 */

	public StaffTable() throws Throwable {
		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.NONE);
		
		shell.setMinimumSize(new Point(600, 500));
		shell.setText("Nana's Hotel Staff");
//		shell.setLayout(new FormLayout());
		createContents(shell);
		shell.pack();
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				new HotelStaff();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Tw Cen MT", 16, SWT.BOLD));
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(100, -56);
		fd_btnNewButton.left = new FormAttachment(0, 139);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Back to Menu");
		
		Button btnExit = new Button(shell, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnExit.setText("Exit");
		btnExit.setFont(SWTResourceManager.getFont("Tw Cen MT", 16, SWT.BOLD));
		FormData fd_btnExit = new FormData();
		fd_btnExit.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnExit.left = new FormAttachment(btnNewButton, 29);
		btnExit.setLayoutData(fd_btnExit);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	
	

	private void createContents(Composite composite) throws Throwable {
		composite.setLayout(new FormLayout());
		final Table table = new Table(composite, SWT.NONE);
		table.setLayoutData(new FormData());
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//Creating the table columns
		TableColumn[] column = new TableColumn[11];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Staff ID");

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("First name");

		column[2] = new TableColumn(table, SWT.RIGHT);
		column[2].setText("Last Name");
		
		column[3] = new TableColumn(table, SWT.RIGHT);
		column[3].setText("Gender");
		
		column[4] = new TableColumn(table, SWT.RIGHT);
		column[4].setText("Department");
		
		column[5] = new TableColumn(table, SWT.RIGHT);
		column[5].setText("Position");
		
		column[6] = new TableColumn(table, SWT.RIGHT);
		column[6].setText("Salary");
		
		column[7] = new TableColumn(table, SWT.RIGHT);
		column[7].setText("Birthday");
		
		column[8] = new TableColumn(table, SWT.RIGHT);
		column[8].setText("Joined Date");
		
		column[9] = new TableColumn(table, SWT.RIGHT);
		column[9].setText("Username");
		
		column[10] = new TableColumn(table, SWT.RIGHT);
		column[10].setText("Password");

		fillTable(table);
		for (int i = 0, n = column.length; i < n; i++) {
			column[i].pack();
		}
	}




	private void fillTable(Table table) throws Throwable {
		table.setRedraw(false);
		
		for (Iterator<User> iter = this.getUserList().iterator(); iter.hasNext();) {
			User User = (User) iter.next();
			TableItem item = new TableItem(table, SWT.NONE);
			int c = 0;
			item.setText(c++, String.valueOf(User.getEmployeeId()));
			item.setText(c++, User.getFname());
			item.setText(c++, User.getLname());
			item.setText(c++, User.getGender());
			item.setText(c++, User.getDepartment());
			item.setText(c++, User.getPosition());
			item.setText(c++, String.valueOf(User.getSalary()));
			item.setText(c++, String.valueOf(User.getBdate()));
			item.setText(c++, String.valueOf(User.getJoinedDate()));
			item.setText(c++, User.getUsername());
			item.setText(c++, User.getEmppass());
		}
		table.setRedraw(true);
	}

	
	public List<User> getUserList() throws SQLException, ParseException {

		Connection con = null;

		con = getConn();
		// Selecting ALL records from the table
		String sql = "select * from employees ORDER BY employee_id";
		ps = con.prepareStatement(sql);

		// get customer data from database
		ResultSet result = ps.executeQuery();

		List<User> list = new ArrayList<User>();

		while (result.next()) {
			User emp = new User();
//			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			
			emp.setEmployeeId(result.getInt("employee_id"));
			emp.setFname(result.getString("fname"));
			emp.setLname(result.getString("lname"));
			emp.setGender(result.getString("gender"));
			emp.setDepartment(result.getString("department"));
			emp.setPosition(result.getString("position"));
			emp.setSalary(result.getDouble("salary"));
			
			Calendar c = Calendar.getInstance();
			c.setTime(result.getDate("bdate"));
			emp.setBdate(c.getTime());
			
			Calendar j = Calendar.getInstance();
			c.setTime(result.getDate("joinedDate"));
			emp.setJoinedDate(j.getTime());
			
			emp.setUsername(result.getString("username"));
			emp.setEmppass(result.getString("emppass"));

			// store all data into a List
			list.add(emp);
		}
		System.out.println(list);
		return list;
	

	}
	}



