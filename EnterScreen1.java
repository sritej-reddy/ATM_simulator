import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class EnterScreen1 extends Frame implements ActionListener {
	private Button withdraw, deposite, ministatement, balance, pinchange, deactivate;
	String user;

	public EnterScreen1(String s) {
		user = s;
		setSize(600, 600);
		setResizable(false);
		setLayout(null);
		withdraw = new Button("withdraw");
		deposite = new Button("deposite");
		ministatement = new Button("ministatement");
		balance = new Button("balance");
		pinchange = new Button("pinchange");
		deactivate = new Button("deativate");
		add(withdraw);
		add(deposite);
		add(pinchange);
		add(ministatement);
		add(balance);
		add(deactivate);
		withdraw.setBounds(100, 100, 100, 50);
		deposite.setBounds(100, 170, 100, 50);
		ministatement.setBounds(100, 240, 100, 50);
		balance.setBounds(300, 100, 100, 50);
		pinchange.setBounds(300, 170, 100, 50);
		deactivate.setBounds(300, 240, 100, 50);
		withdraw.addActionListener(this);
		deposite.addActionListener(this);
		ministatement.addActionListener(this);
		balance.addActionListener(this);
		pinchange.addActionListener(this);
		deactivate.addActionListener(this);
		setVisible(true);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		this.dispose();
		if (e.getSource() == withdraw) {
			new Withdraw(user);
		} else if (e.getSource() == deposite) {
			new Deposite(user);
		} else if (e.getSource() == ministatement) {
			new Ministatement(user);
		} else if (e.getSource() == balance) {
			new Balance(user);
		} else if (e.getSource() == deactivate) {
			new Deactivate(user);
		} else
			new Pinchange(user);
	}

}
