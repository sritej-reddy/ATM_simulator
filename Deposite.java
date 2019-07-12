import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class Deposite extends Frame implements ActionListener {
	Label l;
	TextField tf;
	Button goback,deposite,exit;
	String user;
	Connection con;
	Statement sta;
	public Deposite(String s) {
		user=s;
		l=new Label("enter amount to deposite");
		goback=new Button("go back");
		exit=new Button("exit");
		deposite=new Button("deposite");
		tf=new TextField(20);
		add(l);
		add(tf);
		add(goback);
		add(exit);
		add(deposite);
		setResizable(false);
		setVisible(true);
		setLayout(null);
		setSize(600,600);
		l.setBounds(100,250,150,50);
		tf.setBounds(270,250,100,50);
		deposite.setBounds(220, 320, 100, 50);
		goback.setBounds(100, 400, 100, 50);
		exit.setBounds(220, 400, 100, 50);
		goback.addActionListener(this);
		deposite.addActionListener(this);
		exit.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit) {// TODO Auto-generated method stub
			System.exit(0);
			
		}
		else if(e.getSource()==goback) {
			this.dispose();
			new EnterScreen1(user);
		}
		else {
			int amount=Integer.parseInt(tf.getText());
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sritej","sritej");
				sta=con.createStatement();
				sta.execute("update details set amount=amount+'"+amount+"'where name='"+user+"'");
				sta.execute("insert into "+user+" values("+amount+",sysdate)");
				
				this.dispose();
				new EnterScreen1(user);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
		}
		
	}
}
