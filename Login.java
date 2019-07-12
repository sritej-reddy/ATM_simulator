import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class Login extends Frame implements ActionListener {
	private Label l_acntnum, l_password, l_noaccount,l1;
	private TextField l_acntnum_tf, l_password_tf;
	private Button login, signup,show,hide;
	private Connection con;
	private Statement sta;
	private ResultSet res;

	public Login() {
		l1=new Label();
		l_acntnum = new Label("user name:");
		l_password = new Label("enter pin:");
		l_noaccount = new Label("if you are new then ");
		l_acntnum_tf = new TextField(20);
		l_password_tf = new TextField(4);
		l_password_tf.setEchoChar('*');
		login = new Button("LOGIN");
		signup = new Button("SIGNUP");
		show=new Button("show");
		hide=new Button("hide");
		addComponents();
	}

	public void addComponents() {
		setResizable(false);
		setLayout(null);
		setSize(600, 700);
		add(l1);
		add(l_acntnum);
		add(l_acntnum_tf);
		add(l_password);
		add(l_password_tf);
		add(login);
		add(l_noaccount);
		add(signup);
		add(show);
		add(hide);
		l1.setBounds(200,100,200,50);
		l_acntnum.setBounds(100, 200, 100, 50);
		l_password.setBounds(130, 300, 70, 50);
		l_acntnum_tf.setBounds(250, 200, 100, 50);
		l_password_tf.setBounds(250, 300, 100, 50);
		login.setBounds(200, 400, 100, 50);
		l_noaccount.setBounds(100, 500, 120, 50);
		signup.setBounds(230, 500, 100, 50);
		show.setBounds(380, 300, 80, 30);
		hide.setBounds(490,300,80,30);
		setVisible(true);
		login.addActionListener(this);
		signup.addActionListener(this);
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
		String s;
		if (e.getSource() == login) {
			int v = 0;
			v = verify();
			if (v < 1) {
				l1.setText("invalid username or pin");
				l_acntnum_tf.setText("");
				l_password_tf.setText("");
			} else {
				s = l_acntnum_tf.getText();
				this.dispose();
				new EnterScreen1(s);
			}

		}
		else if(e.getSource()==show){
			l_password_tf.setEchoChar((char) 0);
		}
		else if(e.getSource()==hide) {
			l_password_tf.setEchoChar('*');
		}
		else {
			this.dispose();
			new Signup();
		}

	}

	public int verify() {
		int i = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sritej", "sritej");
			sta = con.createStatement();

			i = sta.executeUpdate("select * from details where name='" + l_acntnum_tf.getText() + "'and pin='"
					+ l_password_tf.getText() + "'");

		} catch (Exception e) {
			System.out.println("error");
		}
		return i;
	}
	
	
	public static void main(String[] args) {
		new Login();

	}

}
