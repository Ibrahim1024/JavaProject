package dbms;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ordercust extends JFrame {

	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/dbms_project";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "password@root";
ResultSet rs,rr;
String ss;
public ordercust(String ss)
{
	setBackground(new Color(  253, 232, 215));

	this.ss=ss;
	show();
}

    public void show() {
    	try {
			count();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			initialiseObject();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(232, 5, 452, 427);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        
        panel.add(scrollPane);
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setSize(900,200);
        frame.setVisible(true);
    }
    private Object[][] data;
    public static int total=0;
    public void count() throws SQLException
    {
    	 try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      
	      Statement st = conn.createStatement(); 
	      rr=st.executeQuery(ss.toString());
	      
	      Statement stmt = conn.createStatement(); 
	      ResultSet rs=stmt.executeQuery(ss.toString());
	     
    	int lines=0;
        while (rs.next())
        	lines++;
        data=new Object[lines][4];
        
    }
    public void initialiseObject() throws SQLException {
        int i=0;
        while(rr.next())
        {
        	int prodid=rr.getInt("OrderID");
        	float prodname=rr.getFloat("Amount");
        	int prodpr=rr.getInt("SupplierID");
        	int prodsize=rr.getInt("ProductID");
        	
        	data[i][0]=prodid;
            data[i][1]=prodname;
            data[i][2]=prodpr;
            data[i][3]=prodsize;
            
            i++;
        }
        
        
    }

    String[] columnNames = {"Order ID", "Amount", "Supplier ID", "Product Name"};
}

