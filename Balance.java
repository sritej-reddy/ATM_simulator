import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class Balance extends Frame implements ActionListener{
	private Label l;
	private Button goback,exit;
	String user;
	public Balance(String s) {
		user=s;
		setResizable(false);
		goback=new Button("goback");
		exit=new Button("exit");
		goback.addActionListener(this);
		exit.addActionListener(this);
		
		try {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sritej","sritej");
		Statement sta = con.createStatement();
		ResultSet rs = sta.executeQuery("select amount from details where name='"+s+"'");
		rs.next();
		int i=rs.getInt(1);
		l=new Label("AMOUNT IN ACCOUNT is '"+i+"'");
		l.setFont(new Font("AMOUNT IN ACCOUNT is '"+i+"'",Font.BOLD,20));
		add(l);
		add(goback);
		add(exit);
		setVisible(true);
		setLayout(null);
		l.setBounds(100, 200, 400, 200);
		goback.setBounds(100, 450, 100, 50);
		exit.setBounds(300, 450, 100, 50);
		setSize(600,600);
	}
		catch(Exception e) {
			System.out.println("error");
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==goback) {
			this.dispose();
			new EnterScreen1(user);
		}
		else {
			System.exit(0);
		}
		
	}
}
