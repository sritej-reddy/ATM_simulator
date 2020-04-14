import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class Deactivate extends Frame implements ActionListener {
	String user;
	Button deactivate, goback, exit,show,hide;
	Label l,l1;
	TextField tf;

	public Deactivate(String s) {
		setVisible(true);
		setResizable(false);
		user = s;
		deactivate = new Button("deactivate");
		goback = new Button("goback");
		exit = new Button("exit");
		l = new Label("enter pin to continue");
		l1=new Label();
		tf = new TextField(10);
		tf.setEchoChar('*');
		show=new Button("show");
		hide=new Button("hide");
		goback.addActionListener(this);
		exit.addActionListener(this);
		deactivate.addActionListener(this);
		add(deactivate);
		add(goback);
		add(exit);
		add(l);
		add(l1);
		add(tf);
		add(show);
		add(hide);setSize(600, 600);
		setLayout(null);
		l.setBounds(100, 200, 150, 50);
		l1.setBounds(200, 100, 200, 50);
		tf.setBounds(270, 200, 100, 50);
		deactivate.setBounds(200, 300, 100, 50);
		goback.setBounds(100, 400, 100, 50);
		exit.setBounds(300, 400, 100, 50);
		show.setBounds(390, 200, 80, 30);
		hide.setBounds(490,200,80,30);
		show.addActionListener(this);
		hide.addActionListener(this);
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
		}
		else if(e.getSource()==show){
			tf.setEchoChar((char) 0);
		}
		else if(e.getSource()==hide) {
			tf.setEchoChar('*');
		}
		else if (e.getSource() == deactivate) {
			try {

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sritej", "sritej");
				Statement sta = con.createStatement();
				ResultSet rs = sta.executeQuery("select pin from details where name='" + user + "'");
				rs.next();
				if (Integer.parseInt(tf.getText()) == Integer.parseInt(rs.getString(1))) {
					sta.execute("delete details where name='" + user + "'");
					sta.executeUpdate("drop table " + user + "");
					System.exit(0);
				} else {
					l1.setText("incorrect pin");
				}

			} catch (Exception ex) {
				System.out.println("error");
			}
		} else {
			System.exit(0);
		} 
	}
}
