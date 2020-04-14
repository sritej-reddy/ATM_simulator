import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.text.ParseException;

public class Signup extends Frame implements ActionListener {
	private Button createaccount, login, show, hide;
	private TextField name_tf, password_tf;
	private Label name, password, old, l1;
	private Connection con;
	private Statement sta;
	private ResultSet rs;

	public Signup() {
		l1 = new Label();
		name = new Label("user name:");
		password = new Label("enter pin:");
		old = new Label("if you are old then ");
		name_tf = new TextField(20);
		password_tf = new TextField(4);
		password_tf.setEchoChar('*');
		createaccount = new Button("signup");
		login = new Button("login");
		show = new Button("show");
		hide = new Button("hide");
		addComponents();
	}

	public void addComponents() {
		setResizable(false);
		setLayout(null);
		setSize(600, 700);
		add(l1);
		add(name);
		add(password);
		add(name_tf);
		add(password_tf);
		add(login);
		add(old);
		add(createaccount);
		add(show);
		add(hide);
		l1.setBounds(200, 100, 300, 50);
		name.setBounds(100, 200, 100, 50);
		password.setBounds(130, 300, 70, 50);
		name_tf.setBounds(250, 200, 100, 50);
		password_tf.setBounds(250, 300, 100, 50);
		createaccount.setBounds(200, 400, 100, 50);
		old.setBounds(100, 500, 120, 50);
		login.setBounds(230, 500, 100, 50);
		show.setBounds(380, 300, 80, 30);
		hide.setBounds(490, 300, 80, 30);
		setVisible(true);
		login.addActionListener(this);
		createaccount.addActionListener(this);
		show.addActionListener(this);
		hide.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = null;
		if (e.getSource() == login) {
			this.dispose();
			new Login();
			// TODO Auto-generated method stub
		} else if (e.getSource() == show) {
			password_tf.setEchoChar((char) 0);
		} else if (e.getSource() == hide) {
			password_tf.setEchoChar('*');
		} else {

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sritej", "sritej");
				sta = con.createStatement();
				s = name_tf.getText();
				int i = checkIfUsernameExists_PinCondition(s);
				if (checkIfPinIsInteger(password_tf.getText()) == false) {

					l1.setText("pin should only have numbers");
				} else if (i == 1) {
					l1.setText("User Name Exists try other user name");
					name_tf.setText("");
					password_tf.setText("");

				} else if (i == 2) {
					l1.setText("pin error enter 4 digit pin");
					name_tf.setText("");
					password_tf.setText("");
				} else {
					sta.execute("insert into details values('" + name_tf.getText() + "','" + password_tf.getText()
							+ "','" + 0 + "')");
					sta.execute("create table " + name_tf.getText() + "(txn number,txn_date date)");
					this.dispose();
					new EnterScreen1(s);
				}
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		}

	}

	private boolean checkIfPinIsInteger(String text) {
		try {
			Integer.parseInt(text);
			
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private int checkIfUsernameExists_PinCondition(String s) throws ParseException, SQLException {
		rs = sta.executeQuery("select name from details");

		int i = 0;

		while (rs.next()) {
			if (s.equals(rs.getString(1))) {
				i = 1;
			}
		}
		if (i == 1) {
			return 1;
		}
		int j;
		j = Integer.parseInt(password_tf.getText());
		String gString = password_tf.getText();
		if (gString.length() == 4) {
			return 0;
		}
		return 2;

	}

	public static void main(String[] args) {
		new Signup();
	}
}
