package baseDeDonnee.metierDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import baseDeDonnee.metier.Joueur;
import java.sql.Driver;
import java.sql.DriverManager;

public class JoueurDAO
{
	private Connection connection;

	public JoueurDAO(Connection connection)
	{
		this.connection = connection;
	}

	public List<Joueur> getAllPlayers()
	{
		List<Joueur> liste = new ArrayList<Joueur>();
		// TODO acces BD
                try {
                    Class c = Class.forName("com.mysql.jdbc.Driver") ;
                    Driver pilote = (Driver)c.newInstance() ;
                    DriverManager.registerDriver(pilote);
                    String protocole =  "jdbc:mysql:" ;
                    String url =  "//iutdoua-web.univ-lyon1.fr/phpmyadmin/" ;
                    String port =  "3306" ;
                    String nomBase =  "p1607863" ;
                    String nomConnexion =  "p1607863" ;
                    String motDePasse =  "270858" ;
                    Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse) ;

                    String sql =  "select * from Marins" ;
                    Statement smt = con.createStatement() ;
                    ResultSet rs = smt.executeQuery(sql) ;
                     while (rs.next()) {
                       System.out.println(rs.getString("nom")) ;
                    }
                 }  catch (Exception e) {
                     // gestion des exceptions
                 }
		return liste;
	}

	public Joueur getPlayerByID(int ID)
	{
		Joueur j = new Joueur();
		// TODO acces BD
		return j;
	}
}
