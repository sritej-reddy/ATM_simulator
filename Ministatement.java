import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class Ministatement extends Frame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextArea tf;
	private Button goback,exit;
	String user;
	public Ministatement(String s) {
		tf=new TextArea();
		goback=new Button("goback");
		exit=new Button("exit");
		user=s;
		setResizable(false);
		setLayout(null);
		setSize(600,600);
		add(tf);
		add(goback);
		add(exit);
		tf.setBounds(200, 100, 300, 400);
		goback.setBounds(100, 530, 100, 50);
		exit.setBounds(400, 530, 100, 50);
		tf.setEditable(false);
		setVisible(true);
		goback.addActionListener(this);
		exit.addActionListener(this);
		insertIntoTextField();
	}
	public void insertIntoTextField() {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sritej","sritej");
		Statement sta=con.createStatement();
		ResultSet rs=sta.executeQuery("select * from "+user+"");
		String temp=null;
		while(rs.next()) {
			tf.append(rs.getString(1)+'\t'+rs.getString(2)+'\n');
		}
		}
		catch(Exception e) {
			System.out.println("error");
		}
		
		
		
	}
	@Override
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
