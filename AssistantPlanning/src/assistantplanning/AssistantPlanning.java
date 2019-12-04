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
Class c = Class.forName("com.mysql.jdbc.Driver");
Driver pilote = (Driver) c.newInstance();
// enregistrement du pilote auprès du DriverManager
DriverManager.registerDriver(pilote);
// Protocole de connexion
String protocole = "jdbc:mysql:";
// Adresse IP de l’hôte de la base et port
String ip = "https://iutdoua-web.univ-lyon1.fr/phpmyadmin/"; // dépend du contexte
String port = "3306"; // port MySQL par défaut
// Nom de la base ;
String nomBase = "p1607863"; // dépend du contexte
// Chaîne de connexion
String conString = protocole + "//" + ip + ":" + port + "/" + nomBase;
// Identifiants de connexion et mot de passe
String nomConnexion = "p1607863"; // dépend du contexte
String motDePasse = "270858"; // dépend du contexte
// Connexion
Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse);

// Envoi d’un requête générique
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