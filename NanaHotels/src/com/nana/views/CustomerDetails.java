package com.nana.views;

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

import com.nana.model.Customer;

public class CustomerDetails {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	protected Object result;
	protected Shell shlUserManagement;
	private Text idText;
	private Text firstText;
	private Text lastText;
	private Text addressText;
	private DateTime chkInDate;
	private DateTime chkOutDate;
	private Label lblFirstname;
	private Label lblLastname;
	private Label lblGender;
	private Label lblDepartment;
	private Label lblPosition;
	private Label lblSalary;
	private Label lblBirthDate;
	private Label lblchkOutDate;
	private Label lblUsername;
	private Label lblPassword;
	private Text phoneText;
	private Button btnViewAllUsers;

	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	String dd = "com.mysql.jdbc.Driver";
	String DB_CONNECTION = "jdbc:mysql://localhost:3306/Nanashotel?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	String DB_USER = "root";
	String DB_PASSWORD = "root";

	PreparedStatement ps = null;

	List<Customer> list;

	JPanel p1, p2, p3;
	private Combo countryDD;
	private Combo stateDD;
	private Combo genderDD;
	private Label lblTitle;
	private Combo roomNoText;
	private Combo titleText;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @wbp.parser.entryPoint
	 */
	public CustomerDetails() {
		super();
		Display display = Display.getDefault();
		shlUserManagement = new Shell();
		shlUserManagement.setText("Nana's Hotel Customer Details");
		shlUserManagement.setBackgroundImage(SWTResourceManager.getImage(
				"C:\\Users\\NIUElechi\\eclipse-workspace\\jee_innovatives\\NanaHotels\\resources\\img\\menu.JPG"));
		shlUserManagement.setSize(1010, 619);
//		Composite parent = new Composite(shlUserManagement, SWT.NONE);

		Label custId = new Label(shlUserManagement, SWT.NONE);
		custId.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		custId.setBounds(10, 10, 109, 25);
		custId.setText(" Customer ID");

		idText = new Text(shlUserManagement, SWT.BORDER);
		idText.setBounds(125, 13, 121, 21);

		firstText = new Text(shlUserManagement, SWT.BORDER);
		firstText.setBounds(125, 73, 121, 21);

		lastText = new Text(shlUserManagement, SWT.BORDER);
		lastText.setBounds(125, 105, 121, 21);

		addressText = new Text(shlUserManagement, SWT.BORDER);
		addressText.setBounds(125, 169, 121, 21);

		chkInDate = new DateTime(shlUserManagement, SWT.DATE | SWT.DROP_DOWN);
		chkInDate.setBounds(147, 260, 121, 21);

		chkOutDate = new DateTime(shlUserManagement, SWT.DATE | SWT.DROP_DOWN);
		chkOutDate.setBounds(147, 302, 121, 21);

		lblFirstname = new Label(shlUserManagement, SWT.NONE);
		lblFirstname.setText("*Firstname");
		lblFirstname.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblFirstname.setBounds(10, 69, 109, 25);

		lblLastname = new Label(shlUserManagement, SWT.NONE);
		lblLastname.setText("*Lastname");
		lblLastname.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblLastname.setBounds(10, 101, 109, 25);

		lblGender = new Label(shlUserManagement, SWT.NONE);
		lblGender.setText("*Gender");
		lblGender.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblGender.setBounds(10, 135, 109, 25);

		lblDepartment = new Label(shlUserManagement, SWT.NONE);
		lblDepartment.setText("*Address");
		lblDepartment.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblDepartment.setBounds(10, 167, 109, 25);

		lblPosition = new Label(shlUserManagement, SWT.NONE);
		lblPosition.setText("*State");
		lblPosition.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblPosition.setBounds(10, 198, 109, 25);

		lblSalary = new Label(shlUserManagement, SWT.NONE);
		lblSalary.setText("Country");
		lblSalary.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblSalary.setBounds(10, 231, 109, 25);

		lblBirthDate = new Label(shlUserManagement, SWT.NONE);
		lblBirthDate.setText("*Checkin Date");
		lblBirthDate.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblBirthDate.setBounds(10, 259, 131, 25);

		lblchkOutDate = new Label(shlUserManagement, SWT.NONE);
		lblchkOutDate.setText("*Checkout Date");
		lblchkOutDate.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblchkOutDate.setBounds(10, 295, 131, 25);

		lblUsername = new Label(shlUserManagement, SWT.NONE);
		lblUsername.setText("Room No.");
		lblUsername.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblUsername.setBounds(10, 323, 109, 25);

		lblPassword = new Label(shlUserManagement, SWT.NONE);
		lblPassword.setText("Phone Number");
		lblPassword.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblPassword.setBounds(10, 354, 131, 25);

		phoneText = new Text(shlUserManagement, SWT.BORDER);
		phoneText.setBounds(157, 356, 121, 21);

		Button btnNewButton = new Button(shlUserManagement, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				idText.setText("");
				firstText.setText("");
				lastText.setText("");
				genderDD.setText("");
				countryDD.setText("");
				stateDD.setText("");
				addressText.setText("");
				chkInDate.setDate(0, 0, 0);
				chkOutDate.setDate(0, 0, 0);
				roomNoText.setText("");
				phoneText.setText("");
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
		btnUpdateUserInfo.setText("View Client by ID");
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
					MessageBox messageBox = new MessageBox(shlUserManagement, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
					messageBox.setMessage("Client no. " + idText.getText()+ " Deleted Successfully");
					messageBox.open();
				}
			}
		});
		btnDeleteUser.setText("Delete Client");
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
			}
		});

		btnViewAllUsers = new Button(shlUserManagement, SWT.NONE);
		btnViewAllUsers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					shlUserManagement.dispose();
					new ClientTable();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnViewAllUsers.setText("View all Clients");
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
		btnAddUser.setText("Add Client");
		btnAddUser.setFont(SWTResourceManager.getFont("Tw Cen MT", 12, SWT.NORMAL));
		btnAddUser.setBounds(313, 102, 121, 25);
		btnAddUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {

				try {
					add();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		countryDD = new Combo(shlUserManagement, SWT.READ_ONLY);
		countryDD.setItems(new String[] { "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola",
				"Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
				"Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize",
				"Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island",
				"Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi",
				"Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad",
				"Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
				"Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
				"Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
				"Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
				"Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France",
				"France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon",
				"Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe",
				"Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands",
				"Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia",
				"Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
				"Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of",
				"Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia",
				"Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau",
				"Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali",
				"Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico",
				"Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat",
				"Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
				"New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island",
				"Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea",
				"Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion",
				"Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia",
				"Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia",
				"Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia",
				"Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain",
				"Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname",
				"Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic",
				"Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo",
				"Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
				"Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom",
				"United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu",
				"Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
				"Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe" });
		countryDD.setBounds(125, 231, 121, 23);

		stateDD = new Combo(shlUserManagement, SWT.READ_ONLY);
		stateDD.setItems(new String[] { "Abia State", "Adamawa State", "Akwa Ibom State", "Anambra State",
				"Bauchi State", "Bayelsa State", "Benue State", "Borno State", "Cross River State", "Delta State",
				"Ebonyi State", "Edo State", "Ekiti State", "Enugu State", "FCT", "Gombe State", "Imo State",
				"Jigawa State", "Kaduna State", "Kano State", "Katsina State", "Kebbi State", "Kogi State",
				"Kwara State", "Lagos State", "Nasarawa State", "Niger State", "Ogun State", "Ondo State", "Osun State",
				"Oyo State", "Plateau State", "Rivers State", "Sokoto State", "Taraba State", "Yobe State",
				"Zamfara State" });
		stateDD.setBounds(125, 198, 121, 23);

		genderDD = new Combo(shlUserManagement, SWT.READ_ONLY);
		genderDD.setItems(new String[] { "Male", "Female" });
		genderDD.setBounds(125, 137, 121, 23);

		lblTitle = new Label(shlUserManagement, SWT.NONE);
		lblTitle.setText("*Title");
		lblTitle.setFont(SWTResourceManager.getFont("Segoe UI Black", 12, SWT.NORMAL));
		lblTitle.setBounds(10, 41, 109, 25);

		roomNoText = new Combo(shlUserManagement, SWT.READ_ONLY);
		try {
			roomNoText.setItems(this.getCustRoom());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		roomNoText.setBounds(147, 326, 121, 23);
		
		titleText = new Combo(shlUserManagement, SWT.READ_ONLY);
		titleText.setItems(new String[] {"Mr", "Mrs", "Alh", "Dr", "Prof."});
		titleText.setBounds(125, 40, 121, 23);

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

	private void add() throws ParseException {
		int i = 0;

		Connection con = null;
		idText.setVisible(false);

		// Checking for empty fields
		if ((titleText.getText()) == "" || (firstText.getText()) == "" || (lastText.getText()) == ""
				|| (genderDD.getText()) == "" || (countryDD.getText()) == "" || (stateDD.getText()) == ""
				|| (addressText.getText()) == "") {

			MessageBox messageBox = new MessageBox(shlUserManagement, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
			messageBox.setMessage("Ensure all required fields are completed");
			messageBox.open();
		} else {
			String titleName = titleText.getText();
			String fname = firstText.getText();
			String lname = lastText.getText();
			String gender = genderDD.getText();
			String country = countryDD.getText();
			String state = stateDD.getText();
			String address = addressText.getText();

			// Formatting the Birthdate
			String chkInDateYear = String.valueOf(chkInDate.getYear());
			String chkInDateMonth = String.valueOf(chkInDate.getMonth());
			String chkInDateDay = String.valueOf(chkInDate.getDay());

			String chkInDateString = chkInDateYear;
			chkInDateString += "/";
			chkInDateString += chkInDateMonth;
			chkInDateString += "/";
			chkInDateString += chkInDateDay;

			SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/DD");
			Date chkInDate = (Date) formatter.parse(chkInDateString);
			System.out.println(chkInDate);
			System.out.println(chkInDate);

			// Formatting the Join Date
			String chkOutDateYear = String.valueOf(chkOutDate.getYear());
			String chkOutDateMonth = String.valueOf(chkOutDate.getMonth());
			String chkOutDateDay = String.valueOf(chkOutDate.getDay());

			String chkOutDateString = chkOutDateYear;
			chkOutDateString += "/";
			chkOutDateString += chkOutDateMonth;
			chkOutDateString += "/";
			chkOutDateString += chkOutDateDay;

//			SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/DD");
			Date chkOutDate = (Date) formatter.parse(chkOutDateString);
			System.out.println("The chkOutDate is: " + chkOutDate);

			String roomNo = roomNoText.getText();
			String phone = phoneText.getText();

			try {
				con = getCon();
				String sql = "INSERT INTO customer(fname, lname, gender, country, state, address, checkin, checkout, roomNo, phone, title) values(?,?,?,?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, fname);
				ps.setString(2, lname);
				ps.setString(3, gender);
				ps.setString(4, country);
				ps.setString(5, state);
				ps.setString(6, address);
				java.sql.Date sqlDate = new java.sql.Date(chkInDate.getTime());
				ps.setDate(7, sqlDate);
				java.sql.Date sqlchkOutDate = new java.sql.Date(chkOutDate.getTime());
				ps.setDate(8, sqlchkOutDate);
				ps.setString(9, roomNo);
				ps.setString(10, phone);
				ps.setString(11, titleName);

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

	private void getEdit(String custID) throws SQLException, ParseException {

		Connection con = null;

		// Checking for empty fields
		if ((idText.getText()) == "") {

			MessageBox messageBox = new MessageBox(shlUserManagement, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
			messageBox.setMessage("Ensure a valid ID is entered");
			messageBox.open();
		} else {

			con = getCon();
			// Selecting ALL records from the table
			String sql = "select custID, title, fname, lname, gender, country, state, address, checkin, checkout, roomNo, phone from customer where custID="
					+ custID;
			ps = con.prepareStatement(sql);

			// get customer data from database
			ResultSet result = ps.executeQuery();

			if (result.next()) {

				titleText.setText(result.getString("title"));
				firstText.setText(result.getString("fname"));
				lastText.setText(result.getString("lname"));
				genderDD.setText(result.getString("gender"));
				countryDD.setText(result.getString("country"));
				stateDD.setText(result.getString("state"));
				addressText.setData(result.getDouble("address"));
				chkInDate.setData(result.getDate("checkin"));
				chkOutDate.setData(result.getDate("checkout"));
				roomNoText.setText(result.getString("roomNo"));
				phoneText.setText(result.getString("phone"));

			} else {
				MessageBox messageBox = new MessageBox(shlUserManagement, SWT.OK | SWT.ICON_WARNING | SWT.CANCEL);
				messageBox.setMessage("Sorry, this customer is not in the db");
				messageBox.open();
			}
		}

	}

	private void delete(String custID) {
		PreparedStatement ps = null;
		Connection con = null;
		if (custID != "") {
			try {
				con = getCon();
				String sql = "DELETE FROM customer WHERE custID=" + custID;
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
		String chkInDateYear = String.valueOf(chkInDate.getYear());
		String chkInDateMonth = String.valueOf(chkInDate.getMonth());
		String chkInDateDay = String.valueOf(chkInDate.getDay());

		String chkInDateString = chkInDateYear;
		chkInDateString += "/";
		chkInDateString += chkInDateMonth;
		chkInDateString += "/";
		chkInDateString += chkInDateDay;

		SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/DD");
		Date chkInDate = (Date) formatter.parse(chkInDateString);
		System.out.println(chkInDate);
		System.out.println(chkInDate);

		// Formatting the Join Date
		String chkOutDateYear = String.valueOf(chkOutDate.getYear());
		String chkOutDateMonth = String.valueOf(chkOutDate.getMonth());
		String chkOutDateDay = String.valueOf(chkOutDate.getDay());

		String chkOutDateString = chkOutDateYear;
		chkOutDateString += "/";
		chkOutDateString += chkOutDateMonth;
		chkOutDateString += "/";
		chkOutDateString += chkOutDateDay;

		Date chkOutDate = (Date) formatter.parse(chkOutDateString);
		System.out.println(chkOutDate);
		System.out.println("The chkOutDate is: " + chkOutDate);

		Customer emp = new Customer();

		emp.setCustID((int) idText.getData());
		emp.setTitle(titleText.getText());
		emp.setFname(firstText.getText());
		emp.setLname(lastText.getText());
		emp.setGender(genderDD.getText());
		emp.setCountry(countryDD.getText());
		emp.setState(stateDD.getText());
		emp.setAddress(addressText.getText());

		java.sql.Date sqlDate = new java.sql.Date(chkInDate.getTime());
		emp.setCheckin(sqlDate);

		java.sql.Date sqlchkOutDate = new java.sql.Date(chkOutDate.getTime());
		emp.setCheckout(sqlchkOutDate);
		emp.setRoomNo((int) roomNoText.getData());
		emp.setPhone(phoneText.getText());

	}

	public void reset() {

		// Cleaning the fields
		idText.setText("");
		titleText.setText("");
		firstText.setText("");
		lastText.setText("");
		genderDD.setText("");
		countryDD.setText("");
		stateDD.setText("");
		addressText.setText("");
		chkInDate.setDate(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
		chkOutDate.setDate(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
		roomNoText.setText("");
		phoneText.setText("");
	}

	public final String[] getCustRoom() throws SQLException, ParseException {
		int i = 0;
		Connection con = null;

		con = getCon();
		// Selecting ALL records from the table
		String sql = "select roomNo from rooms ORDER BY roomNo";
		ps = con.prepareStatement(sql);

		// get customer data from database
		ResultSet result = ps.executeQuery();

		String[] rmList = new String[3];

		while (result.next()) {

			// store all data into a List
			rmList[i] = result.getString("roomNo");
			i++;
		}
		System.out.println(list);
		return rmList;
	}

//	public static void main(String[] args) {
//
//		new CustomerDetails();
//
//	}
}