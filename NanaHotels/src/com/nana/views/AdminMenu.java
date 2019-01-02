package com.nana.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class AdminMenu {

	Display display = Display.getDefault();
	Shell shell = new Shell(display, SWT.NONE);
	

	public AdminMenu() {
		shell.setText("Hotel Menu");
		shell.setBackgroundImage(SWTResourceManager.getImage(
				"C:\\Users\\NIUElechi\\eclipse-workspace\\jee_innovatives\\NanaHotels\\resources\\img\\menu.JPG"));
		shell.setBackground(SWTResourceManager.getColor(153, 255, 0));
		shell.setSize(1010, 619);
		shell.setText("Nana International Hotel");
		shell.setLayout(new FormLayout());

		createBarMenu(shell);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}

	private void createBarMenu(Shell shell) {
		// Create the bar menu
		Menu menu = new Menu(shell, SWT.BAR);

		// Create all the items in the bar menu
		MenuItem fileItem = new MenuItem(menu, SWT.CASCADE);
		fileItem.setText("Reservation");
		MenuItem editItem = new MenuItem(menu, SWT.CASCADE);
		editItem.setText("Admin Panel");
		MenuItem helpItem = new MenuItem(menu, SWT.CASCADE);
		helpItem.setText("Help");

		// Create the Reservation item's dropdown menu
		Menu userMenu = new Menu(menu);
		fileItem.setMenu(userMenu);

		// Create the Admin Panel item's dropdown menu
		Menu adminMenu = new Menu(menu);
		editItem.setMenu(adminMenu);

		// Create all the items in the File dropdown menu
		MenuItem reservItem = new MenuItem(userMenu, SWT.NONE);
		reservItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new HotelReservation();
			}
		});
		reservItem.setText("Reservation Details");

		MenuItem availItem = new MenuItem(userMenu, SWT.NONE);
		availItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new FreeRooms("Available");
			}
		});
		availItem.setText("Available Rooms");

		MenuItem booksItem = new MenuItem(userMenu, SWT.NONE);
		booksItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new FreeRooms("Occupied");
			}
		});
		booksItem.setText("Booked Rooms");

		MenuItem outItem = new MenuItem(userMenu, SWT.NONE);
		outItem.setText("CheckOut");
		new MenuItem(userMenu, SWT.SEPARATOR);
		outItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new CheckoutForm();
			}
		});

		MenuItem printItem = new MenuItem(userMenu, SWT.NONE);
		printItem.setText("Print Bill");
		new MenuItem(userMenu, SWT.SEPARATOR);

		MenuItem exitItem = new MenuItem(userMenu, SWT.NONE);
		exitItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		exitItem.setText("Exit");

		// Create all the items in the File dropdown menu
		MenuItem usersItem = new MenuItem(adminMenu, SWT.NONE);
		usersItem.setText("View Staff List");
		usersItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new StaffTable();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
				MenuItem empItem = new MenuItem(adminMenu, SWT.NONE);
				empItem.setText("Edit Staff info");
				empItem.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						new HotelStaff();
					}
				});

		MenuItem clientItem = new MenuItem(adminMenu, SWT.NONE);
		clientItem.setText("View Clients List");
		clientItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new ClientTable();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		MenuItem mntmEditClientsInfo = new MenuItem(adminMenu, SWT.NONE);
		mntmEditClientsInfo.setText("Edit Clients Info");
		mntmEditClientsInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new CustomerDetails();
			}
		});
		

		MenuItem roomsItem = new MenuItem(adminMenu, SWT.NONE);
		roomsItem.setText("Rooms");
		roomsItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new EditRooms();
			}
		});

		// Set the bar menu as the menu in the shell
		shell.setMenuBar(menu);
	}
	
	public static void main(String[] args) {

		new AdminMenu();

	}
}
