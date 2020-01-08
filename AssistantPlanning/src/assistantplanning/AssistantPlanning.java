/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistantplanning;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import javax.swing.SwingUtilities;

import baseDeDonnee.metierDAO.JoueurDAO;
import vue.Main;

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
			Driver pilote = (Driver) c.getDeclaredConstructor().newInstance();
			DriverManager.registerDriver(pilote);
			final String protocole = "jdbc:mysql:";
			String ip = "iutdoua-web.univ-lyon1.fr";
			String port = "3306";
			String nomBase = "p1607863";
			String conString = protocole + "//" + ip + ":" + port + "/" + nomBase;
			String nomConnexion = "p1607863";
			String motDePasse = "270858";

			Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse);

			SwingUtilities.invokeLater(new Runnable()
			{

				@Override
				public void run()
				{
					Main m = new Main();
					m.setVisible(true);
				}
			});

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			// gestion des exceptions
		}
	}
}