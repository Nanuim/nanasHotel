package com.nana.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class MenuPage {

	Display display = Display.getDefault();
	Shell shell = new Shell(display, SWT.NONE);
	
	public MenuPage(String myUser) {
		shell.setText("Hotel Menu");
		shell.setBackgroundImage(SWTResourceManager.getImage(
				"C:\\Users\\NIUElechi\\eclipse-workspace\\jee_innovatives\\NanaHotels\\resources\\img\\menu.JPG"));
		shell.setBackground(SWTResourceManager.getColor(153, 255, 0));
		shell.setSize(1010, 619);
		shell.setText("Nana International Hotel");
		shell.setLayout(new FormLayout());

		createBarMenu(shell, myUser);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Script MT Bold", 20, SWT.NORMAL));
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.bottom = new FormAttachment(0, 57);
		fd_lblNewLabel.right = new FormAttachment(0, 331);
		fd_lblNewLabel.top = new FormAttachment(0, 24);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Welcome " + myUser);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}

//	public static void main(String []args) {
//
//		new MenuPage("Nan");
//
//	}

	private void createBarMenu(Shell shell, String myUser) {
		// Create the bar menu
		Menu menu = new Menu(shell, SWT.BAR);

		// Create all the items in the bar menu
		MenuItem fileItem = new MenuItem(menu, SWT.CASCADE);
		fileItem.setText("Reservation");
		MenuItem helpItem = new MenuItem(menu, SWT.CASCADE);
		helpItem.setText("Help");

		// Create the Reservation item's dropdown menu
		Menu userMenu = new Menu(menu);
		fileItem.setMenu(userMenu);

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
				new FreeRooms("Available", myUser);
			}
		});
		availItem.setText("Available Rooms");
		
		MenuItem booksItem = new MenuItem(userMenu, SWT.NONE);
		booksItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new FreeRooms("Occupied", myUser);
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
		exitItem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
					shell.dispose();
			}
			
			
		});
		exitItem.setText("Exit");

		// Set the bar menu as the menu in the shell
		shell.setMenuBar(menu);
	}
}