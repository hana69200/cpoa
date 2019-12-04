/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistantplanning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MarionM
 */
public class AssistantPlanning
{

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		try
		 
		{
		 
		Class.forName("com.mysql.jdbc.Driver");
		 
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:81/phone" ,"root",""); //phone et le nom de BD 
		 
		Statement statement = connection.createStatement();
		 
		System.out.println("Connection Established");
		 
		 
		}catch(SQLException | ClassNotFoundException e){
		    System.err.println(e);
		}
	}

}
