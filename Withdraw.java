import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.util.Date;

public class Withdraw extends Frame implements ActionListener {
	Label l,l1;
	TextField tf;
	Button goback,withdraw,exit;
	String user;
	Connection con;
	Statement sta;
	public Withdraw(String s) {
		user=s;
		l=new Label("enter amount to withDraw");
		l1=new Label();
		goback=new Button("go back");
		exit=new Button("exit");
		withdraw=new Button("withdraw");
		tf=new TextField(20);
		add(l);
		add(tf);
		add(goback);
		add(exit);
		add(withdraw);
		add(l1);
		setResizable(false);
		setVisible(true);
		setLayout(null);
		setSize(600,600);
		l1.setBounds(250,150,300,50);
		l.setBounds(100,250,150,50);
		tf.setBounds(270,250,100,50);
		withdraw.setBounds(220, 320, 100, 50);
		goback.setBounds(100, 400, 100, 50);
		exit.setBounds(350, 400, 100, 50);
		goback.addActionListener(this);
		withdraw.addActionListener(this);
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
			long amount=Integer.parseInt(tf.getText());
			try {
				
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sritej","sritej");
				sta=con.createStatement();
				ResultSet rs=sta.executeQuery("select * from details where name='"+user+"'");
				rs.next();
				int a=rs.getInt(3);
				if((a-amount)<0) {
					l1.setText("amount greater than the amount in account ");
					
				}
				
				else {
				sta.execute("update details set amount=amount-'"+amount+"'where name='"+user+"'");
				sta.execute("insert into "+user+" values("+-amount+",sysdate)");
				this.dispose();
				new EnterScreen1(user);}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
		}
		
	}
}
