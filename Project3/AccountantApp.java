package Project3;
/*
Name: Landon Morjal
Course: CNT 4714 Spring 2025
Assignment title: Project 3 – A Specialized Accountant Application
Date: March 14, 2025
Class: AccountantApp
*/

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import javax.swing.*;

import com.mysql.cj.jdbc.MysqlDataSource;

public class AccountantApp extends JPanel
{
	private Connection userConn;
	private Connection logConn;

	private JTextField dbSelect;
	private JLabel dbProp;
	private JLabel jcomp3;
	private JTextField userSelect;
	private JLabel userProp;
	private JLabel username;
	private JLabel pass;
	private JPasswordField passEntry;
	private JTextField userEntry;
	private JLabel comLabel;
	private JTextArea comEntry;
	private JButton connectBut;
	private JButton dcBut;
	private JButton clearComBut;
	private JButton execBut;
	private JTable outTable;
	private JLabel connectionLabel;
	private JLabel resultLabel;
	private JButton clearResBut;
	private JButton closeBut;
	private ResultSetTableModel resTable;

	public AccountantApp() {

		resTable = null;
		dbSelect = new JTextField ("operationslog.properties");
		dbProp = new JLabel ("DB URL Properties");
		jcomp3 = new JLabel ("Connection Details");
		userSelect = new JTextField ("theaccountant.properties");
		userProp = new JLabel ("User Properties");
		username = new JLabel ("Username");
		pass = new JLabel ("Password");
		passEntry = new JPasswordField();
		userEntry = new JTextField ();
		comLabel = new JLabel ("Enter A SQL Command");
		comEntry = new JTextArea (5, 5);
		connectBut = new JButton ("Connct to Database");
		dcBut = new JButton ("Disconnect From Database");
		clearComBut = new JButton ("Clear SQL Command");
		execBut = new JButton ("Execute SQL Command");
		outTable = new JTable ();
		connectionLabel = new JLabel ("NO CONNECTION ESTABLISHED");
		resultLabel = new JLabel ("SQL Execution Result Window");
		clearResBut = new JButton ("Clear Result Window");
		closeBut = new JButton ("Close Application");

		connectionLabel.setForeground(Color.RED);
		connectionLabel.setBackground(Color.GRAY);
        connectionLabel.setFont(new Font("Arial",Font.BOLD,20));
		connectionLabel.setOpaque(true);

		connectBut.setBackground(Color.GREEN);
		dcBut.setBackground(Color.CYAN);

		clearComBut.setBackground(Color.ORANGE);
		execBut.setBackground(Color.YELLOW);

		clearResBut.setBackground(Color.MAGENTA);
		closeBut.setBackground(new Color(0xBF0A30));


		//adjust size and set layout
		setPreferredSize (new Dimension (945, 865));
		setLayout (null);

		comEntry.setWrapStyleWord( true );
		comEntry.setLineWrap( true );

		JScrollPane comScrollPane = new JScrollPane( comEntry,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );

		JScrollPane outPane = new JScrollPane(outTable);

		dbSelect.setEditable(false);
		userSelect.setEditable(false);

		//add components
		add (dbSelect);
		add (dbProp);
		add (jcomp3);
		add (userSelect);
		add (userProp);
		add (username);
		add (pass);
		add (passEntry);
		add (userEntry);
		add (comLabel);
		add (comScrollPane);
		add (connectBut);
		add (dcBut);
		add (clearComBut);
		add (execBut);
		add (connectionLabel);
		add (outPane, BorderLayout.CENTER );
		add (clearResBut);
		add (closeBut);
		add (resultLabel);

		//set component bounds (only needed by Absolute Positioning)
		dbSelect.setBounds (135, 65, 210, 30);
		dbProp.setBounds (15, 65, 110, 30);
		jcomp3.setBounds (0, 25, 135, 30);
		userSelect.setBounds (135, 110, 210, 30);
		userProp.setBounds (15, 110, 120, 30);
		username.setBounds (15, 155, 100, 25);
		pass.setBounds (15, 200, 100, 25);
		passEntry.setBounds (135, 195, 210, 30);
		userEntry.setBounds (135, 155, 210, 30);
 		comLabel.setBounds (450, 25, 185, 30);
		comScrollPane.setBounds (450, 60, 480, 180);
		connectBut.setBounds (10, 250, 190, 45);
		dcBut.setBounds (220, 250, 190, 45);
		clearComBut.setBounds (450, 250, 220, 45);
		execBut.setBounds (710, 250, 220, 45);
		outPane.setBounds (15, 410, 920, 365);
		connectionLabel.setBounds (15, 317, 920, 45);
		resultLabel.setBounds (15, 375, 225, 30);
		clearResBut.setBounds (20, 780, 235, 50);
		closeBut.setBounds (695, 780, 235, 50);

		clearComBut.addActionListener(e -> comEntry.setText(""));
		connectBut.addActionListener(e -> dbConnect());
		execBut.addActionListener(e -> runCommand());
		dcBut.addActionListener(e -> dbDC());
		clearResBut.addActionListener(e -> outTable.setModel(new JTable().getModel()));
		closeBut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dbDC();
				System.exit(0);
			}
		});
	}

	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("SQL Accountant Application - CNT 4714 Spring 2025");
		AccountantApp accountant = new AccountantApp();

		frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosed(WindowEvent e)
			{
			   accountant.dbDC();
			   frame.dispose();
			   System.exit(0);
			}
		});

		frame.getContentPane().add(accountant);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible (true);
	}

	private void dbDC()
	{
		try
		  {
			if (userConn != null && !userConn.isClosed()) //check if a connection is active
			{
				userConn.close(); //close log and user connections
				logConn.close();

				connectionLabel.setText("Disconnected from database");
				userEntry.setText("");
				passEntry.setText("");

				outTable.setModel(new JTable().getModel()); //empty output table
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	private void dbConnect()
	{
		try
		{
			MysqlDataSource userSrc = new MysqlDataSource();
			MysqlDataSource logSrc = new MysqlDataSource();

			Properties logProp = new Properties();
			Properties logLogin = new Properties();
			Properties dbProp = new Properties();
			Properties userProp = new Properties();

			//open properties files
			dbProp.load(new FileInputStream(dbSelect.getText()));
			userProp.load(new FileInputStream(userSelect.getText()));
			logProp.load(new FileInputStream("operationslog.properties"));
			logLogin.load(new FileInputStream("project3app.properties"));

			String username = userEntry.getText();
			String password = new String(passEntry.getPassword());

			//set urls for user selected and log
			logSrc.setURL(logProp.getProperty("MYSQL_DB_URL"));
			userSrc.setURL(dbProp.getProperty("MYSQL_DB_URL"));

			//check if username is correct
			if (username.compareTo(userProp.getProperty("MYSQL_DB_USERNAME")) == 0)
			{
				userSrc.setUser(username);
			}
			else
			{
				connectionLabel.setText("NOT CONNECTED - User Credentials Do Not Match Properties File!");
				return;
			}

			//check is password is correct
			if (password.compareTo(userProp.getProperty("MYSQL_DB_PASSWORD")) == 0)
			{
				userSrc.setPassword(password);
			}
			else
			{
				connectionLabel.setText("NOT CONNECTED - User Credentials Do Not Match Properties File!");
				return;
			}

			//if both if statements succeed set properties
			logSrc.setUser(logLogin.getProperty("MYSQL_DB_USERNAME"));
			logSrc.setPassword(logLogin.getProperty("MYSQL_DB_PASSWORD"));

			//set connections
			logConn = logSrc.getConnection();
			userConn = userSrc.getConnection();
			connectionLabel.setText("CONNECTED TO: " + dbProp.getProperty("MYSQL_DB_URL"));

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	private void runCommand()
	{
		try
		{
			if (userConn != null && !userConn.isClosed()) //ensure a connection is live
			{		
				try
				{
					resTable = new ResultSetTableModel(comEntry.getText(), userConn, logConn); //run query

					if (comEntry.getText().toLowerCase().startsWith("select")) { //check query type
						outTable.setModel(resTable);
						outTable.repaint();
					}
					else
					{
						outTable.setModel(new JTable().getModel());
					}

				} // end try
				catch (SQLException sqlException)
				{
					JOptionPane.showMessageDialog( null,
					sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
				}
				catch (ClassNotFoundException e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog( null, 
			e.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
		}
	}
}
