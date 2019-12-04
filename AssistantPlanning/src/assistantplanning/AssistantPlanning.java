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
			// chargement de la classe par son nom
			Class c = Class.forName("com.mysql.cj.jdbc.Driver");
			Driver pilote = (Driver) c.newInstance();
			// enregistrement du pilote aupr�s du DriverManager
			DriverManager.registerDriver(pilote);
			// Protocole de connexion
			String protocole = "jdbc:mysql:";
			// Adresse IP de l�h�te de la base et port
			String ip = "https://iutdoua-web.univ-lyon1.fr/phpmyadmin"; // d�pend du contexte
			String port = "3306"; // port MySQL par d�faut
			// Nom de la base ;
			String nomBase = "p1607863"; // d�pend du contexte
			// Cha�ne de connexion
			String conString = protocole + "//" + ip + ":" + port + "/" + nomBase;
			// Identifiants de connexion et mot de passe
			String nomConnexion = "p1607863"; // d�pend du contexte
			String motDePasse = "270858"; // d�pend du contexte
			// Connexion
			Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse);

			// Envoi d�un requ�te g�n�rique
			String sql = "select * from Joueur";
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next())
			{
				System.out.println(rs.getString("prenom"));
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			// gestion des exceptions
		}
	}

}
