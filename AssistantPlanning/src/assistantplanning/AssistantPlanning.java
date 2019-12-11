/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistantplanning;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import baseDeDonnee.metierDAO.JoueurDAO;

/**
 *
 * @author MarionM
 */
public class AssistantPlanning
{

	public static void main(String[] args)
	{
		try
		{
			Class<?> c = Class.forName("com.mysql.cj.jdbc.Driver");
			Driver pilote = (Driver) c.newInstance();
			DriverManager.registerDriver(pilote);
			String protocole = "jdbc:mysql:";
			String ip = "iutdoua-web.univ-lyon1.fr";
			String port = "3306";
			String nomBase = "p1607863";
			String conString = protocole + "//" + ip + ":" + port + "/" + nomBase;
			String nomConnexion = "p1607863";
			String motDePasse = "270858";

			Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse);
			
			JoueurDAO jdao = new JoueurDAO(con);
			System.out.println(jdao.getPlayerByID(1));

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			// gestion des exceptions
		}
	}
}