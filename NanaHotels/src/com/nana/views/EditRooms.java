package com.nana.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class EditRooms {
	private static Text roomNoText;
	private static Text roomRateText;
	private static Combo roomTypeDD;
	private static Combo bedNoDD;
	private static Combo roomLocDD;
	private static Combo availTag;

	String dd = "com.mysql.jdbc.Driver";
	String DB_CONNECTION = "jdbc:mysql://localhost:3306/Nanashotel?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	String DB_USER = "root";
	String DB_PASSWORD = "root";

	PreparedStatement ps = null;

	Display display = Display.getDefault();
	Shell shlUpdateRoomInfo = new Shell();

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public EditRooms() {

		shlUpdateRoomInfo.setSize(450, 300);
		shlUpdateRoomInfo.setText("Update Room Info");

		Label lblRoomId = new Label(shlUpdateRoomInfo, SWT.NONE);
		lblRoomId.setText(" Room Number:");
		lblRoomId.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblRoomId.setBounds(0, 10, 135, 25);

		roomNoText = new Text(shlUpdateRoomInfo, SWT.BORDER);
		roomNoText.setBounds(141, 12, 121, 21);

		roomRateText = new Text(shlUpdateRoomInfo, SWT.BORDER);
		roomRateText.setBounds(141, 104, 121, 21);

		roomTypeDD = new Combo(shlUpdateRoomInfo, SWT.READ_ONLY);
		roomTypeDD.setBounds(141, 43, 121, 23);
		String items[] = { "Standard", "Delux", "Executive" };
		roomTypeDD.setItems(items);

		bedNoDD = new Combo(shlUpdateRoomInfo, SWT.READ_ONLY);
		bedNoDD.setItems(new String[] { "1", "2", "4" });
		bedNoDD.setBounds(141, 73, 121, 23);

		roomLocDD = new Combo(shlUpdateRoomInfo, SWT.READ_ONLY);
		roomLocDD.setItems(new String[] { "Mainland", "Island" });
		roomLocDD.setBounds(141, 135, 121, 23);

		availTag = new Combo(shlUpdateRoomInfo, SWT.READ_ONLY);
		availTag.setItems(new String[] { "Available", "Occupied" });
		availTag.setBounds(141, 166, 121, 23);

		Button btnViewAllRooms = new Button(shlUpdateRoomInfo, SWT.NONE);
		btnViewAllRooms.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlUpdateRoomInfo.dispose();
				new FreeRooms("All");
			}
		});
		btnViewAllRooms.setText("View all Rooms");
		btnViewAllRooms.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnViewAllRooms.setBounds(291, 40, 133, 25);

		Button btnViewFreeRooms = new Button(shlUpdateRoomInfo, SWT.NONE);
		btnViewFreeRooms.setText("View Free Rooms");
		btnViewFreeRooms.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnViewFreeRooms.setBounds(291, 71, 133, 25);
		btnViewFreeRooms.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlUpdateRoomInfo.dispose();
				new FreeRooms("Available");
			}
		});

		Button btnViewBookedRooms = new Button(shlUpdateRoomInfo, SWT.NONE);
		btnViewBookedRooms.setText("View Booked Rooms");
		btnViewBookedRooms.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnViewBookedRooms.setBounds(291, 102, 133, 25);
		btnViewBookedRooms.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlUpdateRoomInfo.dispose();
				new FreeRooms("Occupied");
			}
		});

		Label lblRoomType = new Label(shlUpdateRoomInfo, SWT.NONE);
		lblRoomType.setText(" Room Type");
		lblRoomType.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblRoomType.setBounds(0, 41, 135, 25);

		Label lblNumberOfBeds = new Label(shlUpdateRoomInfo, SWT.NONE);
		lblNumberOfBeds.setText("Number of Beds:");
		lblNumberOfBeds.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblNumberOfBeds.setBounds(0, 72, 145, 25);

		Label lblRoomPrice = new Label(shlUpdateRoomInfo, SWT.NONE);
		lblRoomPrice.setText(" Room Rate:");
		lblRoomPrice.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblRoomPrice.setBounds(0, 102, 135, 25);

		Label lblRoomLocation = new Label(shlUpdateRoomInfo, SWT.NONE);
		lblRoomLocation.setText(" Room Location:");
		lblRoomLocation.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblRoomLocation.setBounds(0, 133, 135, 25);

		Button btnAddRooms = new Button(shlUpdateRoomInfo, SWT.NONE);
		btnAddRooms.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					add();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAddRooms.setText("Add Rooms");
		btnAddRooms.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnAddRooms.setBounds(291, 133, 133, 25);

		Button btnDeleteRooms = new Button(shlUpdateRoomInfo, SWT.NONE);
		btnDeleteRooms.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				delete(roomNoText.getText());
			}
		});
		btnDeleteRooms.setText("Delete Rooms");
		btnDeleteRooms.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnDeleteRooms.setBounds(291, 164, 133, 25);

		Button btnExit = new Button(shlUpdateRoomInfo, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlUpdateRoomInfo.dispose();
			}
		});
		btnExit.setText("Exit");
		btnExit.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnExit.setBounds(360, 226, 64, 25);

		Button btnBackToAdmin = new Button(shlUpdateRoomInfo, SWT.NONE);
		btnBackToAdmin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlUpdateRoomInfo.dispose();
				new AdminMenu();
			}
		});
		btnBackToAdmin.setText("Back to Admin Menu");
		btnBackToAdmin.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnBackToAdmin.setBounds(202, 226, 133, 25);

		Label lblAvailability = new Label(shlUpdateRoomInfo, SWT.NONE);
		lblAvailability.setText("Availability");
		lblAvailability.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblAvailability.setBounds(0, 164, 135, 25);
		
		Button btnReset = new Button(shlUpdateRoomInfo, SWT.NONE);
		btnReset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reset();
			}
		});
		btnReset.setText("Reset");
		btnReset.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnReset.setBounds(291, 10, 133, 25);

		shlUpdateRoomInfo.open();
		shlUpdateRoomInfo.layout();
		while (!shlUpdateRoomInfo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private Connection getCon() {

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

	private void delete(String roomNo) {
		PreparedStatement ps = null;
		Connection con = null;
		if (roomNo != "") {
			try {
				con = getCon();
				String sql = "DELETE FROM rooms WHERE roomNo=" + roomNo;
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

	private void add() throws ParseException {
		int i = 0;

		Connection con = null;
		roomNoText.setVisible(false);

		// Checking for empty fields
		if ((roomTypeDD.getText()).isEmpty() || (bedNoDD.getText()).isEmpty()|| (roomLocDD.getText()).isEmpty()
				|| (roomRateText.getText()).isEmpty()|| (availTag.getText()).isEmpty()) {

			MessageBox messageBox = new MessageBox(shlUpdateRoomInfo, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
			messageBox.setMessage("Ensure all required fields are completed");
			messageBox.open();
		} else {

			String rType = roomTypeDD.getText();
			String rLoc = roomLocDD.getText();
			String bedNo = bedNoDD.getText();
			String rRate = roomRateText.getText();
			String rStatus = availTag.getText();

			try {
				con = getCon();
				String sql = "INSERT INTO rooms(roomType, bedNo, roomRate, roomLocation, status) values(?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, rType);
				ps.setString(2, bedNo);
				ps.setString(3, rRate);
				ps.setString(4, rLoc);
				ps.setString(5, rStatus);

				i = ps.executeUpdate();
				System.out.println("Data Added Successfully");

				MessageBox messageBox = new MessageBox(shlUpdateRoomInfo, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
				messageBox.setMessage("Data is successfully inserted into database");
				messageBox.open();

				this.reset();

			} catch (Exception e) {
				System.out.println("The error is: " + e);
				MessageBox messageBox = new MessageBox(shlUpdateRoomInfo, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
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

	private void reset() {

		roomTypeDD.setText("");
		roomLocDD.setText("");
		bedNoDD.setText("");
		roomRateText.setText("");
		availTag.setText("");
	}

//	public static void main(String[] args) {
//		
//		new EditRooms();
//		
//		
//	}
}
