package baseDeDonnee.metierDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import baseDeDonnee.metier.Joueur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JoueurDAO
{
	private Connection connection;

	public JoueurDAO(Connection connection)
	{
		this.connection = connection;
	}

	public List<Joueur> getAllPlayers() throws SQLException
	{
		List<Joueur> liste = new ArrayList<Joueur>();

		String sql = "select * from Marins";
		Statement smt = connection.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next())
		{
			System.out.println(rs.getString("nom"));
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
