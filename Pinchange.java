import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.text.ParseException;

public class Pinchange extends Frame implements ActionListener {
	String user;
	Button change, goback, exit, show1, hide1, show2, hide2;
	Label l1, l2, l3, l4;
	TextField tf1, tf2, tf3;

	public Pinchange(String s) {
		user = s;
		l1 = new Label("enter current pin");
		l2 = new Label("enter new pin");
		l3 = new Label("re-enter new pin");
		l4 = new Label();
		tf1 = new TextField(20);
		tf2 = new TextField(20);
		tf3 = new TextField(20);
		tf1.setEchoChar('*');
		tf2.setEchoChar('*');
		tf3.setEchoChar('*');
		change = new Button("change pin");
		goback = new Button("goback");
		exit = new Button("exit");
		show1 = new Button("show");
		hide1 = new Button("hide");
		show2 = new Button("show");
		hide2 = new Button("hide");
		addComponents();

	}

	public void addComponents() {

		add(l1);
		add(tf1);
		add(l2);
		add(tf2);
		add(l3);
		add(tf3);
		add(l4);
		add(new Label(""));
		add(change);
		add(goback);
		add(exit);
		add(show1);
		add(hide1);
		add(show2);
		add(hide2);
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setSize(600, 600);
		change.addActionListener(this);
		goback.addActionListener(this);
		exit.addActionListener(this);
		l1.setBounds(100, 100, 100, 50);
		l2.setBounds(100, 170, 100, 50);
		l3.setBounds(100, 240, 100, 50);
		l4.setBounds(200, 50, 300, 50);
		goback.setBounds(100, 400, 100, 50);
		tf1.setBounds(300, 100, 100, 50);
		tf2.setBounds(300, 170, 100, 50);
		tf3.setBounds(300, 240, 100, 50);
		change.setBounds(250, 330, 100, 50);
		exit.setBounds(300, 400, 100, 50);
		show1.setBounds(420, 170, 70, 30);
		hide1.setBounds(520, 170, 70, 30);
		show2.setBounds(420, 240, 70, 30);
		hide2.setBounds(520, 240, 70, 30);
		show1.addActionListener(this);
		hide1.addActionListener(this);
		show2.addActionListener(this);
		hide2.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == goback) {
			this.dispose();
			new EnterScreen1(user);
		} else if (e.getSource() == show1) {
			tf2.setEchoChar((char) 0);
		} else if (e.getSource() == hide1) {
			tf2.setEchoChar('*');
		} else if (e.getSource() == show2) {
			tf3.setEchoChar((char) 0);
		} else if (e.getSource() == hide2) {
			tf3.setEchoChar('*');
		} else if (e.getSource() == change) {
			try {

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sritej", "sritej");
				Statement sta = con.createStatement();
				ResultSet rs = sta.executeQuery("select pin from details where name='" + user + "'");
				rs.next();
				int tpin = rs.getInt(1);
				if (checkIfPinIsInteger(tf1.getText(), tf2.getText(), tf3.getText())==false) {
					
					l4.setText("pin should only have numbers");
				} else if (tpin == Integer.parseInt(tf1.getText())) {

					if (checkPinCondition(user) == 2) {
						l4.setText("pin error enter 4 digit pin");
					} else if (Integer.parseInt(tf2.getText()) == Integer.parseInt(tf1.getText())) {
						l4.setText("old and new pin are matching");
					}

					else if (Integer.parseInt(tf2.getText()) == Integer.parseInt(tf3.getText())) {
						sta.execute("update details set pin=" + Integer.parseInt(tf2.getText()) + "where name='" + user
								+ "'");
						this.dispose();
						new EnterScreen1(user);
					} else {
						l4.setText("new pin and re-entered pin should match");
					}
				} else {
					l4.setText("pin incorrect");
				}
			} catch (Exception arg) {
				System.out.println("error");
			}
		} else {
			System.exit(0);
		}
	}

	private boolean checkIfPinIsInteger(String text, String text2, String text3) {
		try {
			Integer.parseInt(text);
			Integer.parseInt(text2);
			Integer.parseInt(text3);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private int checkPinCondition(String s) {

		String gString = tf2.getText();
		if (gString.length() == 4) {
			return 0;
		}

		return 2;

	}

}
