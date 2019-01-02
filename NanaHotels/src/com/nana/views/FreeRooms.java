package com.nana.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.nana.model.Rooms;

public class FreeRooms {

	protected Shell shell;
	String dd = "com.mysql.jdbc.Driver";
	String DB_CONNECTION = "jdbc:mysql://localhost:3306/Nanashotel?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	String DB_Room = "root";
	String DB_PASSWORD = "root";

	PreparedStatement ps = null;

	List<Rooms> list;

	/**
	 * @throws Throwable 
	 * @wbp.parser.entryPoint
	 */
	public FreeRooms(String s) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.NONE);
		
		shell.setMinimumSize(new Point(600, 500));
		try {
			createContents(shell, s);
			shell.pack();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				new EditRooms();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Tw Cen MT", 16, SWT.BOLD));
		final FormData fdbtnNewButton = new FormData();
		fdbtnNewButton.bottom = new FormAttachment(100, -56);
		fdbtnNewButton.left = new FormAttachment(0, 139);
		btnNewButton.setLayoutData(fdbtnNewButton);
		btnNewButton.setText("Back to Edit Rooms");
		
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
		shell.layout();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	
	
	private Connection getConn() {

		Connection con = null;

		try {

			Class.forName(dd);

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(DB_CONNECTION, DB_Room, DB_PASSWORD);
			return con;
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return con;
	}

	
	private void createContents(Composite composite, String s ) throws Throwable {
		composite.setLayout(new FormLayout());
		final Table table = new Table(composite, SWT.NONE);
		table.setLayoutData(new FormData());
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//Creating the table columns
		TableColumn[] column = new TableColumn[6];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Room No");

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("Room Type");

		column[2] = new TableColumn(table, SWT.RIGHT);
		column[2].setText("No of Beds");
		
		column[3] = new TableColumn(table, SWT.RIGHT);
		column[3].setText("Room Rate");
		
		column[4] = new TableColumn(table, SWT.RIGHT);
		column[4].setText("Location");
		
		column[5] = new TableColumn(table, SWT.RIGHT);
		column[5].setText("Status");

		fillTable(table, s);
		for (int i = 0, n = column.length; i < n; i++) {
			column[i].pack();
		}
	}




	private void fillTable(Table table, String s) throws Throwable {
		table.setRedraw(false);
		
		for (Iterator<Rooms> iter = this.getRoomList(s).iterator(); iter.hasNext();) {
			Rooms Room = (Rooms) iter.next();
			TableItem item = new TableItem(table, SWT.NONE);
			int c = 0;
			item.setText(c++, String.valueOf(Room.getRoomNo()));
			item.setText(c++, Room.getRoomType());
			item.setText(c++, String.valueOf(Room.getBedNo()));
			item.setText(c++, String.valueOf(Room.getRoomRate()));
			item.setText(c++, Room.getRoomLocation());
			item.setText(c++, Room.getStatus());
		}
		table.setRedraw(true);
	}

	
	public List<Rooms> getRoomList(String s) throws SQLException {
		
		String sql;
		if (s == "Available") {
			sql = "select * from rooms WHERE status = 'Available' ORDER BY roomNo"; 
		}else if (s == "Occupied"){
			sql = "select * from rooms WHERE status = 'Occupied' ORDER BY roomNo";
		}else {
			sql = "select * from rooms ORDER BY roomNo";
		}
		
		Connection con = null;

		con = getConn();
		// Selecting ALL records from the table
		ps = con.prepareStatement(sql);

		// get customer data from database
		ResultSet result = ps.executeQuery();

		List<Rooms> list = new ArrayList<Rooms>();

		while (result.next()) {
			Rooms emp = new Rooms();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			
			emp.setRoomNo(result.getInt("roomNo"));
			emp.setRoomType(result.getString("roomType"));
			emp.setRoomRate(result.getDouble("roomRate"));
			emp.setRoomLocation(result.getString("roomLocation"));
			emp.setBedNo(result.getInt("bedNo"));
			emp.setStatus(result.getString("status"));
			
			// store all data into a List
			list.add(emp);
		}
		System.out.println(list);
		return list;
	

	}

//	public static void main(String[] args) {
//		new FreeRooms("Available");
//	}
}
